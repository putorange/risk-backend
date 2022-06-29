package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.ExportEvaluationTunnelCollapseLevelVo;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class toWord {

    public StringBuffer toWord(List<ExportEvaluationTunnelCollapseLevelVo> list) throws IOException {
        //获取模板文档
        File templateFile= new File("\\exportFiles\\隧道施工区段坍塌风险等级表-模板.docx");

        // 数据处理
        for (ExportEvaluationTunnelCollapseLevelVo exportEvaluationTunnelCollapseLevelVo : list) {
            String riskLevel = exportEvaluationTunnelCollapseLevelVo.getRiskLevel();
            switch (riskLevel)
            {
                case "极高Ⅳ": exportEvaluationTunnelCollapseLevelVo.setRiskLevel("Ⅳ"); break;
                case "高度Ⅲ": exportEvaluationTunnelCollapseLevelVo.setRiskLevel("Ⅲ"); break;
                case "中度Ⅱ": exportEvaluationTunnelCollapseLevelVo.setRiskLevel("Ⅱ"); break;
                case "低度Ⅰ": exportEvaluationTunnelCollapseLevelVo.setRiskLevel("Ⅰ"); break;
            }
        }

        ArrayList<String> column = new ArrayList<>();
        for (ExportEvaluationTunnelCollapseLevelVo exportEvaluationTunnelCollapseLevelVo : list) {
            column.add(exportEvaluationTunnelCollapseLevelVo.getTunnelLineName());
        }

        Map<String,Integer> map1 = new HashMap<>();
        for (String s : column) {
            map1.put(s, map1.get(s) == null? 1:map1.get(s)+1);
        }

        List<String> quchong1 = column.stream().distinct().collect(Collectors.toList());

        ArrayList<Integer> list1 = new ArrayList<>();
        int sum1 = 0;
        for (int i = 0; i < quchong1.size(); i++) {
            sum1 += map1.get(quchong1.get(i));
            list1.add(sum1);
        }


        Map<String,Object> params =new HashMap<>();
        params.put("resultList",list);

        try {
            XWPFDocument word = WordExportUtil.exportWord07(templateFile.getPath(),params);

            //合并单元格
            List<XWPFTable> xwpfTables = word.getTables();
            for (XWPFTable table : xwpfTables) {
                mergeUtil mergeUtil = new mergeUtil();
                mergeUtil.mergeColumn(table, 1, 1, list1.get(0));

                for (int i = 0; i < list1.size(); i++) {
                    for (int j = i + 1; j < list1.size(); j++) {
                        mergeUtil.mergeColumn(table, 1, list1.get(i) + 1, list1.get(j));
                    }
                }
            }

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("\\exportFiles\\隧道施工区段坍塌风险等级表.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("\\exportFiles\\隧道施工区段坍塌风险等级表.docx", FileFormat.Doc);
        doc.saveToFile("\\exportFiles\\隧道施工区段坍塌风险等级表.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("\\exportFiles\\隧道施工区段坍塌风险等级表.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }


        return sb;
    }
}