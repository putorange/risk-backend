package com.ietunnel.riskservice.specialRisk.service.impl;

import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.ietunnel.riskservice.specialRisk.entity.TunnelDivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.TunnelSubdivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWork;
import com.ietunnel.riskservice.specialRisk.entity.vo.DivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.vo.SubDivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.vo.WorkEventContent;
import com.ietunnel.riskservice.specialRisk.mapper.EvaluationWorkEventMapper;
import com.ietunnel.riskservice.specialRisk.mapper.TunnelDivisionWorkMapper;
import com.ietunnel.riskservice.specialRisk.mapper.TunnelSubdivisionWorkMapper;
import com.ietunnel.riskservice.specialRisk.mapper.TunnelUnitWorkMapper;
import com.ietunnel.riskservice.specialRisk.service.EvaluationWorkEventService;
import com.ietunnel.riskservice.specialRisk.service.TunnelDivisionWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 隧道分部工程 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@Service
public class TunnelDivisionWorkServiceImpl extends ServiceImpl<TunnelDivisionWorkMapper, TunnelDivisionWork> implements TunnelDivisionWorkService {

    @Autowired
    private TunnelDivisionWorkMapper tunnelDivisionWorkMapper;
    @Autowired
    private TunnelSubdivisionWorkMapper tunnelSubdivisionWorkMapper;
    @Autowired
    private TunnelUnitWorkMapper tunnelUnitWorkMapper;

    @Autowired
    private EvaluationWorkEventMapper evaluationWorkEventMapper;
    @Override
    public List<DivisionWork> getAllDivisionWork() {
        //创建List集合，封装查出的分部工程
        List<TunnelDivisionWork> divisionWorksList = tunnelDivisionWorkMapper.selectAllDivisionWork();

        //创建list集合，封装查出的分项工程
        List<TunnelSubdivisionWork> subDivisionWorksList = tunnelSubdivisionWorkMapper.selectAllSubDivisionWork();

        //创建list集合，封装查出的单位作业
        List<TunnelUnitWork> tunnelUnitWorkList = tunnelUnitWorkMapper.selectAllTunnelUnitWork();

        //for(TunnelUnitWork unitWork:tunnelUnitWorkList){
        //    EvaluationWorkEvent evaluationWorkEvent = new EvaluationWorkEvent();
        //    copyUnitWorkPropertiesToEvaluaWork(unitWork,evaluationWorkEvent);
        //    evaluationWorkEventService.save(evaluationWorkEvent);
        //}


        List<EvaluationWorkEvent> evaluationWorkEventList = evaluationWorkEventMapper.selectAllEvaluationWorkEvent();


        //创建list集合，封装api所需的数据类型
        List<DivisionWork> finalWorkList = new ArrayList<>();
        //封装一级（分部工程）
        for(TunnelDivisionWork divisionWork: divisionWorksList){
            // divisionWork 为查询出来的 TunnelDivisionWork 对象，divisionWork1 为自定义的 DivisionWork 对象
            DivisionWork divisionWork1 =new DivisionWork();
            // 封装自定义类DivisionWork
            divisionWork1.setId(divisionWork.getId());
            divisionWork1.setName(divisionWork.getName());


            //封装二级（分项工程）
            List<SubDivisionWork> twoFinalWorKList = new ArrayList<>();
            int x = 0;
            for(TunnelSubdivisionWork subDivisionWork: subDivisionWorksList){

                // subDivisionWork为查询出来的TunnelSubDivisionWork对象，subDivisionWork1为自定义的SubDivisionWork对象

                if(subDivisionWork.getDivisionId().equals(divisionWork.getId())){
                    x++;
                    SubDivisionWork subDivisionWork1 = new SubDivisionWork();
                    subDivisionWork1.setId(subDivisionWork.getId());
                    subDivisionWork1.setName(x+"、"+subDivisionWork.getName());
                    //封装三级（单位作业）
                    List<WorkEventContent> threeFinalWorKList = new ArrayList<>();
                    int y=0;
                    for(EvaluationWorkEvent evaluationWorkEvent: evaluationWorkEventList){
                        if(evaluationWorkEvent.getSubdivisionId().equals(subDivisionWork.getId())){
                            WorkEventContent workEventContent = new WorkEventContent();
                            // workEvent 从数据表evaluationworkevent中取值
                            workEventContent.setName((char)('a'+y)+"、"+evaluationWorkEvent.getName());
                            copyProperties(evaluationWorkEvent,workEventContent);
                            threeFinalWorKList.add(workEventContent);
                            y++;
                        }

                    }
                    subDivisionWork1.setChildren(threeFinalWorKList);
                    twoFinalWorKList.add(subDivisionWork1);
                }
            }
            divisionWork1.setChildren(twoFinalWorKList);
            finalWorkList.add(divisionWork1);
        }
        return finalWorkList;
    }

    private void copyProperties(EvaluationWorkEvent  o1, WorkEventContent o2){
        o2.setId(o1.getUnitId());
        o2.setHit(o1.getHit());
        o2.setFall(o1.getFall());
        o2.setElectricity(o1.getElectricity());
        o2.setLift(o1.getLift());
        o2.setExplore(o1.getExplore());
        o2.setRoof(o1.getRoof());
        o2.setWater(o1.getWater());
        o2.setBlast(o1.getBlast());
        o2.setFire(o1.getFire());
        o2.setMechanical(o1.getMechanical());
        o2.setVehicle(o1.getVehicle());
        o2.setCollapse(o1.getCollapse());
        o2.setOthers(o1.getOthers());
    }

    @Override
    public boolean updateByList(DivisionWork divisionWork) {
        return false;
    }
}
