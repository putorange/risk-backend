package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.Utils.toWord;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.EvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.ExportEvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.EvaluationTunnelWsbzPossibility;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.service.EvaluationTunnelWsbzPossibilityService;
import com.ietunnel.riskservice.significantRisk.entity.WsbzPossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.WsbzPossibilityRankCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸可能性评估打分表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
@RestController
@RequestMapping("/riskservice.evaluationTunnelWsbzPossibility/evaluation-tunnel-wsbz-possibility")
@CrossOrigin
public class EvaluationTunnelWsbzPossibilityController {

	@Autowired
	EvaluationTunnelWsbzPossibilityService evaluationTunnelWsbzPossibilityService;

	@Autowired
	WsbzPossibilityRankCriteriaService wsbzPossibilityRankCriteriaService;

	@PostMapping("/getAllListBytunnelId/{tunnelId}/{expertId}")
	public R getAllListBytunnelId(@PathVariable String tunnelId, @PathVariable String expertId){
		List<EvaluationTunnelWsbzPossibilityVo> list = evaluationTunnelWsbzPossibilityService.getList(tunnelId,expertId);
		return R.ok().data("list",list);
	}

	@GetMapping("/getwsbzPossibilityRankCriteria")
	public R getwsbzPossibilityRankCriteria(){
		List<WsbzPossibilityRankCriteria> list = wsbzPossibilityRankCriteriaService.list(null);
		return R.ok().data("list",list);
	}

	@PostMapping("/saveOrUpadateEvaluationTunnelWsbzPossibility")
	public R saveOrUpadateEvaluationTunnelWsbzPossibility(@RequestBody EvaluationTunnelWsbzPossibility evaluationTunnelWsbzPossibility) {
		String id = evaluationTunnelWsbzPossibility.getId();
		if (id !=null && id.length()>0){
			boolean update = evaluationTunnelWsbzPossibilityService.updateById(evaluationTunnelWsbzPossibility);
			if (update){
				return R.ok();
			}else {
				return R.error();
			}
		}else {
			boolean save = evaluationTunnelWsbzPossibilityService.save(evaluationTunnelWsbzPossibility);
			if (save){
				return R.ok();
			}else {
				return R.error();
			}
		}
	}

	@PostMapping("/removeId/{evaluationTunnelWsbzPossibilityId}")
	public R removeId(@PathVariable String evaluationTunnelWsbzPossibilityId)  {
		boolean b = evaluationTunnelWsbzPossibilityService.removeById(evaluationTunnelWsbzPossibilityId);
		if (b){
			return R.ok();
		}else {
			return R.error();
		}
	}


	@PostMapping("/expotHTML/{tunnelId}/{expertId}")
	public R expotHTML(@PathVariable String tunnelId,@PathVariable String expertId) throws IOException {

		List<ExportEvaluationTunnelWsbzPossibilityVo> list = evaluationTunnelWsbzPossibilityService.exportList(tunnelId,expertId);
		toWord toWord = new toWord();
		CharSequence stringBuffer = toWord.toWord(list);
		if (stringBuffer != null){
			return R.ok().data("info",stringBuffer);
		}else {
			return R.error();
		}
	}

}

