package com.ietunnel.riskservice.evaluationManagement.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationManagement.entity.EvaluationTunnelSafeManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.evaluationManagement.entity.vo.EvaluationTunnelSafeManagementVo;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道安全管理评估指标打分表 Mapper 接口
 * </p>
 *
 * @author cmy
 * @since 2022-05-22
 */
public interface EvaluationTunnelSafeManagementMapper extends BaseMapper<EvaluationTunnelSafeManagement> {
//    @Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, s.indicator_id, s.description subindicatordescription FROM safe_manage_indicator i,safe_manage_subindicator s  WHERE i.id=s.indicator_id and s.id=c.subindicator_id and i.is_deleted=0")
//
//
//
//    @Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, s.indicator_id, s.description subindicatordescription FROM overall_risk_indicator i,overall_risk_subindicator s, overall_risk_classification c  WHERE i.id=s.indicator_id and s.id=c.subindicator_id and i.is_deleted=0 and s.is_deleted=0")
////    @Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, \n" +
////            "\t\t\ts.indicator_id, s.description subindicatordescription \n" +
////            "FROM safe_manage_indicator i,safe_manage_subindicator s \n" +
////            " \n" +

    ////            "WHERE i.id=s.indicator_id and s.id=c.subindicator_id and i.is_deleted=0 and s.is_deleted=0\n")
//
    //@Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,c.id SafeManagementClassificationid, c.name SafeManagementClassificationname, c.score SafeManagementClassificationscore FROM safe_management_indicator i, safe_management_classification c  WHERE i.id=c.indicator_id and i.is_deleted=0")
    @Select("SELECT i.id safeManagementIndicatorId,i.name indicatorname, i.is_deleted isDeleted ,i.segment_Id segmentId,c.id SafeManagementClassificationid, c.name SafeManagementClassificationname, c.score SafeManagementClassificationscore,s.name Name\n" +
            "FROM safe_management_indicator i, safe_management_classification c,segment s  \n" +
            "WHERE i.id=c.indicator_id and i.is_deleted=0 and s.id=i.segment_id")

    List<EvaluationTunnelSafeManagementVo> selectMajorList(Page page);

    //@Select("SELECT i.id safeManagementIndicatorId, i.name indicatorname, i.is_deleted isDeleted , c.id SafeManagementClassificationId, c.name SafeManagementClassificationname, c.score SafeManagementClassificationscore FROM safe_management_indicator i, safe_management_classification c  WHERE i.id=c.indicator_id and i.is_deleted=0 and c.is_deleted=0")
    @Select("SELECT i.id safeManagementIndicatorId,i.name indicatorname, i.is_deleted isDeleted ,i.segment_Id segmentId,c.id SafeManagementClassificationid, c.name SafeManagementClassificationname, c.score SafeManagementClassificationscore,s.name Name\n" +
            "FROM safe_management_indicator i, safe_management_classification c,segment s  \n" +
            "WHERE i.id=c.indicator_id and i.is_deleted=0 and s.id=i.segment_id and c.is_deleted=0")

    List<EvaluationTunnelSafeManagementVo> selectALLList();

}