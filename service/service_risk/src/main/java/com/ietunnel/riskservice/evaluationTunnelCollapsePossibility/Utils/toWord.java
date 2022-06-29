package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
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

    public StringBuffer toWord(List<ExportEvaluationTunnelCollapsePossibilityVo> list) throws IOException {
        //获取模板文档 /Users/apple/Documents/riskAssessment_0612
        File templateFile= new File("/Users/apple/Documents/riskAssessment_0612/各区段坍塌事故可能性评估指标取值.docx");


//        主要思路：
//        首先是合并行：
//          1.对第一列的行进行合并：
//                 由于通过数据查到的是一条一条的数组，所以只需要对数组中相同的数据求总数就可以知道要合并几行。
//                 所以拿到第一列数据后求出每个评估指标名称的个数,拿到每个名称的个数之后，需要对其累加即可拿到改数组所占的最后一行的索引，最后调用mergeUtil对其合并。

        for (ExportEvaluationTunnelCollapsePossibilityVo exportEvaluationTunnelCollapsePossibilityVo : list) {
            //对阿拉伯数字进行修改，将阿拉伯数字改为数字
            String rockGrade = exportEvaluationTunnelCollapsePossibilityVo.getRockGrade();
            switch (rockGrade)
            {
                case "V": exportEvaluationTunnelCollapsePossibilityVo.setRockGrade("5"); break;
                case "Ⅳ": exportEvaluationTunnelCollapsePossibilityVo.setRockGrade("4"); break;
                case "Ⅲ": exportEvaluationTunnelCollapsePossibilityVo.setRockGrade("3"); break;
                case "Ⅱ": exportEvaluationTunnelCollapsePossibilityVo.setRockGrade("2"); break;
                case "Ⅰ": exportEvaluationTunnelCollapsePossibilityVo.setRockGrade("1"); break;
            }
        }

        // 第一 二列合并数据预处理
        ArrayList<String> column1 = new ArrayList<>();      //第一列数据
        for (ExportEvaluationTunnelCollapsePossibilityVo exportEvaluationTunnelCollapsePossibilityVo : list) {
            column1.add(exportEvaluationTunnelCollapsePossibilityVo.getTunnelLineName());
        }

        // 数据统计（相同的数据有多少个）
        Map<String,Integer> map1 = new HashMap<>();
        for (String s : column1) {
            // System.out.println(s);
            map1.put(s, map1.get(s) == null? 1:map1.get(s)+1);
        }

//        map1.forEach((key, value) -> {
//            System.out.println(key + "    " + value);
//        });

        // 去重
        List<String> quchong1 = column1.stream().distinct().collect(Collectors.toList());

        // 累加
        ArrayList<Integer> list1 = new ArrayList<>();
        int sum1 = 0;
        for (int i = 0; i < quchong1.size(); i++) {
            sum1 += map1.get(quchong1.get(i));
            list1.add(sum1);
        }

//        for (Integer integer : list1) {
//            System.out.println(integer);
//        }

        Map<String,Object> params =new HashMap<>();
        params.put("resultList",list);

        try {
            XWPFDocument word = WordExportUtil.exportWord07(templateFile.getPath(),params);
            //合并单元格
            List<XWPFTable> xwpfTables = word.getTables();
            for (XWPFTable table : xwpfTables) {
                mergeUtil mergeUtil = new mergeUtil();
                mergeUtil.mergeColumn(table,0,2,list1.get(0)+1);

                for (int i = 0; i < list1.size(); i++) {
                    for (int j = i+1; j < list1.size(); j++) {
                        mergeUtil.mergeColumn(table,0,list1.get(i)+2,list1.get(j)+1);
                    }
                }
            }

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("/Users/apple/Documents/riskAssessment_0612/各区段坍塌事故可能性评估指标取值1.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("/Users/apple/Documents/riskAssessment_0612/各区段坍塌事故可能性评估指标取值1.docx", FileFormat.Doc);
        doc.saveToFile("/Users/apple/Documents/riskAssessment_0612/各区段坍塌事故可能性评估指标取值.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("/Users/apple/Documents/riskAssessment_0612/各区段坍塌事故可能性评估指标取值.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

//        // 删除最后文件
//        if (sb != null){
//            File file1 = new File("D://隧道工程总体风险评估指标体系.docx");
//            File file2 = new File("D://隧道工程总体风险评估指标体系.html");
//            File file3 = new File("D://隧道工程总体风险评估指标体系_styles.css");
//            // 路径为文件且不为空则进行删除
//            if (file1.isFile() && file1.exists()) {
//                file1.delete();
//            }
//            // 路径为文件且不为空则进行删除
//            if (file2.isFile() && file2.exists()) {
//                file2.deleteOnExit();
//            }
//            // 路径为文件且不为空则进行删除
//            if (file3.isFile() && file3.exists()) {
//                file3.delete();
//            }
//        }

        return sb;
    }

}
