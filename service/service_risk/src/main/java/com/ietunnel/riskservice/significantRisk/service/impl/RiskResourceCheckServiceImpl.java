package com.ietunnel.riskservice.significantRisk.service.impl;

import com.ietunnel.riskservice.significantRisk.entity.RiskResourceCheck;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskSource;
import com.ietunnel.riskservice.significantRisk.entity.vo.RiskResourceCheckTable;
import com.ietunnel.riskservice.significantRisk.mapper.RiskResourceCheckMapper;
import com.ietunnel.riskservice.significantRisk.mapper.SignificantRiskContentMapper;
import com.ietunnel.riskservice.significantRisk.mapper.SignificantRiskMapper;
import com.ietunnel.riskservice.significantRisk.mapper.SignificantRiskSourceMapper;
import com.ietunnel.riskservice.significantRisk.service.RiskResourceCheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 隧道风险源检查表 服务实现类
 * </p>
 *
 * @author zxg
 * @since 2022-06-07
 */
@Service
public class RiskResourceCheckServiceImpl extends ServiceImpl<RiskResourceCheckMapper, RiskResourceCheck> implements RiskResourceCheckService {

    @Autowired
    private SignificantRiskContentMapper significantRiskContentMapper;
    @Autowired
    private SignificantRiskSourceMapper significantRiskSourceMapper;

    @Override
    public RiskResourceCheck getByRiskContentId(SignificantRiskContent significantRiskContent) {
        for(RiskResourceCheck riskResourceCheck : baseMapper.selectAllRiskResourceCheck()){
            if(riskResourceCheck.getSignificantRiskContentId().equals(significantRiskContent.getId())){
                return riskResourceCheck;
            }
        }
        return null;
    }


    public List<RiskResourceCheckTable> getAllRiskResourceCheck() {
        List<SignificantRiskSource> significantRiskSources = significantRiskSourceMapper.selectAllSignificantRiskSource();
        List<SignificantRiskContent> significantRiskContents = significantRiskContentMapper.selectAllSignificantRiskContent();
        List<RiskResourceCheckTable> list = new ArrayList<>();
        for(SignificantRiskContent significantRiskContent : significantRiskContents){
            RiskResourceCheckTable riskResourceCheckTable = new RiskResourceCheckTable();
            riskResourceCheckTable.setContentid(significantRiskContent.getId());
            riskResourceCheckTable.setContentname(significantRiskContent.getName());
            for(SignificantRiskSource significantRiskSource : significantRiskSources){
                if(significantRiskContent.getSignificantRiskSourceId().equals(significantRiskSource.getId())){
                    riskResourceCheckTable.setSourceid(significantRiskSource.getId());
                    riskResourceCheckTable.setSourcename(significantRiskSource.getName());
                }
            }
            list.add(riskResourceCheckTable);
        }

        return list;
    }

    @Override
    public List<RiskResourceCheck> getAllRiskResourceCheckByResourceId() {
        List<RiskResourceCheck> list = baseMapper.selectAllRiskResourceCheckByResourceId();
        return list;
    }

    @Override
    public void removeByRiskContentId(String id) {
        baseMapper.removeByRiskContentId(id);

    }


}


