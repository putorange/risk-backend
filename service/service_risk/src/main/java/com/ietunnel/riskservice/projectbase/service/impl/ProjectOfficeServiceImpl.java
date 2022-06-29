package com.ietunnel.riskservice.projectbase.service.impl;

import com.ietunnel.riskservice.projectbase.entity.ProjectManagement;
import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.OneProject;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.TwoProject;
import com.ietunnel.riskservice.projectbase.mapper.ProjectOfficeMapper;
import com.ietunnel.riskservice.projectbase.service.ProjectManagementService;
import com.ietunnel.riskservice.projectbase.service.ProjectOfficeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目办 服务实现类
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
@Service
public class ProjectOfficeServiceImpl extends ServiceImpl<ProjectOfficeMapper, ProjectOffice> implements ProjectOfficeService {

    @Autowired
    private ProjectManagementService projectManagementService;
    @Autowired
    private ProjectOfficeService projectOfficeService;

    @Override
    public List<OneProject> getAllOneTowSubject() {
        // 1 查询所有的一级分类 去project_management表中查询
        List<ProjectManagement> oneProjectList = projectManagementService.list(null);

        // 2 查询所有的二级分类 去project_office表中查询
        List<ProjectOffice> towProjectList = projectOfficeService.list(null);

        // 创建List集合用于存储最终封装的数组
        List<OneProject> finalProjectList = new ArrayList<>();

        // 3 封装一级分类
        // 查询出来所有的一级分类list集合遍历 得到每个一级分类对象 获取每个一级分类对象值
        // 封装到要求的list集合里面 List<OneProject> finalProjectList
        for (int i = 0 ; i < oneProjectList.size(); i++) { // 遍历oneProjectList集合
            // 得到oneProjectList每个projectManagement对象
            ProjectManagement projectManagement = oneProjectList.get(i);
            // 把projectManagement值获取出来放入oneProjectList里面
            OneProject oneProject = new OneProject();
            // 把projectManagement中的值复制到oneProject中，条件是双方的属性名称一致
            BeanUtils.copyProperties(projectManagement,oneProject);
            // 多个oneProject放到finalProjectList里
            finalProjectList.add(oneProject);

            // 4 封装二级分类
            // 在一级分类的循环遍历查询所有的二级分类
            // 创建list集合封装每一个一级分类的二级分类
            List<TwoProject> towFianlProjectList = new ArrayList<>();
            // 遍历二级分类list集合
            for (int j = 0; j < towProjectList.size(); j++){
                // 获取每个二级分类
                ProjectOffice tProject = towProjectList.get(j);
                if (tProject.getManagementId().equals(projectManagement.getId())){
                    // 把towProject值复制到towFianlProjectList里面
                    TwoProject twoProject = new TwoProject();
                    BeanUtils.copyProperties(tProject, twoProject);
                    towFianlProjectList.add(twoProject);
                }
            }
            // 把一级下面的所有二级分类，放回一级中
            oneProject.setChildren(towFianlProjectList);

        }
        return finalProjectList;
    }
}
