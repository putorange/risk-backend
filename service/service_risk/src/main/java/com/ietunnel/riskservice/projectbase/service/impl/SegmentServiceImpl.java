package com.ietunnel.riskservice.projectbase.service.impl;

import com.ietunnel.riskservice.projectbase.entity.OfficeSegmentClass.OneSegment;
import com.ietunnel.riskservice.projectbase.entity.OfficeSegmentClass.TwoSegment;
import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.TwoProject;
import com.ietunnel.riskservice.projectbase.entity.Segment;
import com.ietunnel.riskservice.projectbase.mapper.SegmentMapper;
import com.ietunnel.riskservice.projectbase.service.ProjectOfficeService;
import com.ietunnel.riskservice.projectbase.service.SegmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 标段 服务实现类
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
@Service
public class SegmentServiceImpl extends ServiceImpl<SegmentMapper, Segment> implements SegmentService {

    @Autowired
    private ProjectOfficeService projectOfficeService;
    @Autowired
    private SegmentService segmentService;

    @Override
    public List<OneSegment> getAllOneTowSubject() {
        // 1 查询所有的一级分类 去project_management表中查询
        List<ProjectOffice> oneSegmentList = projectOfficeService.list(null);

        // 2 查询所有的二级分类 去project_office表中查询
        List<Segment> towSegmentList = segmentService.list(null);

        // 创建List集合用于存储最终封装的数组
        List<OneSegment> finalSegmentList = new ArrayList<>();

        // 3 封装一级分类
        // 查询出来所有的一级分类list集合遍历 得到每个一级分类对象 获取每个一级分类对象值
        // 封装到要求的list集合里面 List<OneProject> finalProjectList
        for (int i = 0 ; i < oneSegmentList.size(); i++) { // 遍历oneProjectList集合
            // 得到oneSegmentList每个ProjectOffice对象
            ProjectOffice projectOffice = oneSegmentList.get(i);
            // 把ProjectOffice值获取出来放入oneSegmentList里面
            OneSegment oneSegment = new OneSegment();
            // 把projectManagement中的值复制到oneProject中，条件是双方的属性名称一致
            BeanUtils.copyProperties(projectOffice,oneSegment);
            // 多个oneProject放到finalProjectList里
            finalSegmentList.add(oneSegment);

            // 4 封装二级分类
            // 在一级分类的循环遍历查询所有的二级分类
            // 创建list集合封装每一个一级分类的二级分类
            List<TwoSegment> towFianlSegmentList = new ArrayList<>();
            // 遍历二级分类list集合
            for (int j = 0; j < towSegmentList.size(); j++){
                // 获取每个二级分类
                Segment tSegment = towSegmentList.get(j);
                if (tSegment.getOfficeId().equals(projectOffice.getId())){
                    // 把towSegment值复制到towFianlSegmentList里面
                    TwoSegment twoSegment = new TwoSegment();
                    BeanUtils.copyProperties(tSegment, twoSegment);
                    towFianlSegmentList.add(twoSegment);
                }
            }
            // 把一级下面的所有二级分类，放回一级中
            oneSegment.setChildren(towFianlSegmentList);

        }
        return finalSegmentList;
    }
}
