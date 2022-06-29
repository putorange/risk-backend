package com.ietunnel.riskservice.evaluationTunnelWsbzLevel.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.Utils.toWord;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.EvaluationTunnelWsbzLevel;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.EvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.ExportEvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.service.EvaluationTunnelWsbzLevelService;
import com.ietunnel.riskservice.levelGrading.entity.RiskLevelCriteria;
import com.ietunnel.riskservice.levelGrading.service.RiskLevelCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸风险等级打分表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-06-25
 */
@RestController
@RequestMapping("/riskservice.evaluationTunnelWsbzLevel/evaluation-tunnel-wsbz-level")
@CrossOrigin
public class EvaluationTunnelWsbzLevelController {

	@Autowired
	private EvaluationTunnelWsbzLevelService evaluationTunnelWsbzLevelService;

	@Autowired
	private RiskLevelCriteriaService riskLevelCriteriaService;

	@PostMapping("/getAllListByTunnelId/{tunnelId}/{expertId}")
	public R getAllListByTunnelId(@PathVariable String tunnelId, @PathVariable String expertId){
		List<EvaluationTunnelWsbzLevelVo> list = evaluationTunnelWsbzLevelService.getList(tunnelId,expertId);
		return R.ok().data("info",list);
	}

	@GetMapping("/getriskLevelCriteria")
	public R getriskLevelCriteria(){
		List<RiskLevelCriteria> list = riskLevelCriteriaService.list(null);
		return R.ok().data("list",list);
	}

	@PostMapping("/saveOrUpadateEvaluationTunnelWsbzLevel")
	public R saveOrUpadateEvaluationTunnelWsbzLevel(@RequestBody EvaluationTunnelWsbzLevel evaluationTunnelWsbzLevel){
		String id = evaluationTunnelWsbzLevel.getId();
		if(id != null && id.length()>0){
			boolean update = evaluationTunnelWsbzLevelService.updateById(evaluationTunnelWsbzLevel);
			if (update){
				return R.ok();
			}else {
				return R.error();
			}
		}else {
			boolean save = evaluationTunnelWsbzLevelService.save(evaluationTunnelWsbzLevel);
			if (save){
				return R.ok();
			}else {
				return R.error();
			}
		}
	}

	@PostMapping("/removeId/{evaluationTunnelWsbzLevelId}")
	public R removeById(@PathVariable String evaluationTunnelWsbzLevelId){
		boolean b = evaluationTunnelWsbzLevelService.removeById(evaluationTunnelWsbzLevelId);
		if (b){
			return R.ok();
		}else {
			return R.error();
		}
	}


	@PostMapping("/exportHTML/{tunnelId}/{expertId}")
	public R exportHTML(@PathVariable String tunnelId, @PathVariable String expertId) throws IOException {

		List<ExportEvaluationTunnelWsbzLevelVo> list = evaluationTunnelWsbzLevelService.exportList(tunnelId,expertId);

		toWord toWord = new toWord();
		CharSequence stringBuffer = toWord.toWord(list);
		if (stringBuffer != null){
			return R.ok().data("info",stringBuffer);
		}else {
			return R.error();
		}
	}

}

