package com.ietunnel.riskservice.evaluationTunnelWsbzLevel.Utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.ExportEvaluationTunnelWsbzLevelVo;
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

    public StringBuffer toWord(List<ExportEvaluationTunnelWsbzLevelVo> list) throws IOException {
        //获取模板文档
        File templateFile= new File("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表-模板.docx");


        // 数据处理
        for (ExportEvaluationTunnelWsbzLevelVo exportEvaluationTunnelWsbzLevelVo : list) {
            String riskLevel = exportEvaluationTunnelWsbzLevelVo.getRiskLevel();
            switch (riskLevel)
            {
                case "极高Ⅳ": exportEvaluationTunnelWsbzLevelVo.setRiskLevel("Ⅳ"); break;
                case "高度Ⅲ": exportEvaluationTunnelWsbzLevelVo.setRiskLevel("Ⅲ"); break;
                case "中度Ⅱ": exportEvaluationTunnelWsbzLevelVo.setRiskLevel("Ⅱ"); break;
                case "低度Ⅰ": exportEvaluationTunnelWsbzLevelVo.setRiskLevel("Ⅰ"); break;
            }
        }

        ArrayList<String> column = new ArrayList<>();      //第一列数据
        for (ExportEvaluationTunnelWsbzLevelVo exportEvaluationTunnelWsbzLevelVo : list) {
            column.add(exportEvaluationTunnelWsbzLevelVo.getTunnelLineName());
        }

        // 数据统计（相同的数据有多少个）
        Map<String,Integer> map1 = new HashMap<>();
        for (String s : column) {
            // System.out.println(s);
            map1.put(s, map1.get(s) == null? 1:map1.get(s)+1);
        }

       // map1.forEach((key, value) -> {
       //     System.out.println(key + "    " + value);
       // });

        // 去重
        List<String> quchong1 = column.stream().distinct().collect(Collectors.toList());

        // 累加
        ArrayList<Integer> list1 = new ArrayList<>();
        int sum1 = 0;
        for (int i = 0; i < quchong1.size(); i++) {
            sum1 += map1.get(quchong1.get(i));
            list1.add(sum1);
        }

       // for (Integer integer : list1) {
       //     System.out.println(integer);
       // }

        Map<String,Object> params =new HashMap<>();
        params.put("resultList",list);

        try {
            // System.out.println(templateFile.getPath());
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
            FileOutputStream fos = new FileOutputStream("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.docx", FileFormat.Doc);
        doc.saveToFile("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

       // // 删除最后文件
       // if (sb != null){
       //     File file1 = new File("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.docx");
       //     File file2 = new File("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表.html");
       //     File file3 = new File("D:\\Desktop\\exportFiles\\隧道瓦斯爆炸风险等级表_styles.css");
       //     // 路径为文件且不为空则进行删除
       //     if (file1.isFile() && file1.exists()) {
       //         file1.delete();
       //     }
       //     // 路径为文件且不为空则进行删除
       //     if (file2.isFile() && file2.exists()) {
       //         file2.deleteOnExit();
       //     }
       //     // 路径为文件且不为空则进行删除
       //     if (file3.isFile() && file3.exists()) {
       //         file3.delete();
       //     }
       // }

        return sb;
    }

}
