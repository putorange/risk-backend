package com.ietunnel.riskservice.projectbase.service;

import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.OneProject;

import java.util.List;

/**
 * <p>
 * 项目办 服务类
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
public interface ProjectOfficeService extends IService<ProjectOffice> {

    List<OneProject> getAllOneTowSubject();
}
