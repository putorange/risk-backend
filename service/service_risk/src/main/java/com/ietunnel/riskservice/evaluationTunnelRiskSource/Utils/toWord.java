package com.ietunnel.riskservice.evaluationTunnelRiskSource.Utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.Vo.ExportEvaluationTunnelRiskSourceVo;
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

    public StringBuffer toWord(List<ExportEvaluationTunnelRiskSourceVo> list) throws IOException {
        //获取模板文档
        File templateFile= new File("\\exportFiles\\隧道风险源风险分析表-模板.docx");

        ArrayList<String> column = new ArrayList<>();
        for (ExportEvaluationTunnelRiskSourceVo exportEvaluationTunnelTsynLevelVo : list) {
            column.add(exportEvaluationTunnelTsynLevelVo.getTunnelDivisionWork());
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

            List<XWPFTable> xwpfTables = word.getTables();
            for (XWPFTable table : xwpfTables) {
                mergeUtil mergeUtil = new mergeUtil();
                mergeUtil.mergeColumn(table, 0, 1, list1.get(0));

                for (int i = 0; i < list1.size(); i++) {
                    for (int j = i + 1; j < list1.size(); j++) {
                        mergeUtil.mergeColumn(table, 0, list1.get(i) + 1, list1.get(j));
                    }
                }
            }

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("\\exportFiles\\隧道风险源风险分析表.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("\\exportFiles\\隧道风险源风险分析表.docx", FileFormat.Doc);
        doc.saveToFile("\\exportFiles\\隧道风险源风险分析表.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("\\exportFiles\\隧道风险源风险分析表.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

        // // 删除最后文件
        // if (sb != null){
        //     File file1 = new File("\\exportFiles\\隧道风险源风险分析表.docx");
        //     File file2 = new File("\\exportFiles\\隧道风险源风险分析表.html");
        //     File file3 = new File("\\exportFiles\\隧道风险源风险分析表.css");
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