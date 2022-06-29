package com.ietunnel.riskservice.projectbase.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.projectbase.entity.vo.ExportTunnelRockCharacteristicsVo;
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

public class ToWord {

    public StringBuffer toWord(List<ExportTunnelRockCharacteristicsVo> list) throws IOException {
        //获取模板文档
        File templateFile = new File("\\exportFiles\\围岩级别与特征表-模板.docx");


        ArrayList<String> column1 = new ArrayList<>();      //第一列数据
        ArrayList<String> column2 = new ArrayList<>();      //第二列数据
        ArrayList<String> column3 = new ArrayList<>();      //第三列数据
        ArrayList<String> column4 = new ArrayList<>();
        for (ExportTunnelRockCharacteristicsVo exportTunnelRockCharacteristicsVo : list) {
            column1.add(exportTunnelRockCharacteristicsVo.getTunnelLineName());
            column2.add(exportTunnelRockCharacteristicsVo.getRockGrade());
            column3.add(exportTunnelRockCharacteristicsVo.getBasicQuality());
            column4.add(exportTunnelRockCharacteristicsVo.getQualitativeCharacteristics());
        }

        // 线路
        Map<String,Integer> map1 = new HashMap<>();
        for (String s : column1) {
            map1.put(s, map1.get(s) == null? 1:map1.get(s)+1);
        }
        List<String> quchong1 = column1.stream().distinct().collect(Collectors.toList());

        ArrayList<Integer> list1 = new ArrayList<>();
        int sum1 = 0;
        for (int i = 0; i < quchong1.size(); i++) {
            sum1 += map1.get(quchong1.get(i));
            list1.add(sum1);
        }

        // 围岩级别
        Map<String,Integer> map2 = new HashMap<>();
        for (String s : column2) {
            map2.put(s, map2.get(s) == null? 1:map2.get(s)+1);
        }
        List<String> quchong2 = column2.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> list2 = new ArrayList<>();
        int sum2 = 0;
        for (int i = 0; i < quchong2.size(); i++) {
            sum2 += map2.get(quchong2.get(i));
            list2.add(sum2);
        }

        // [BQ]
        Map<String,Integer> map3 = new HashMap<>();
        for (String s : column3) {
            map3.put(s, map3.get(s) == null? 1:map3.get(s)+1);
        }

        List<String> quchong3 = column3.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> list3 = new ArrayList<>();
        int sum3 = 0;
        for (int i = 0; i < quchong3.size(); i++) {
            sum3 += map3.get(quchong3.get(i));
            list3.add(sum3);
        }

        // 定性特征
        Map<String,Integer> map4 = new HashMap<>();
        for (String s : column4) {
            map4.put(s, map4.get(s) == null? 1:map4.get(s)+1);
        }
        List<String> quchong4 = column4.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> list4 = new ArrayList<>();
        int sum4 = 0;
        for (int i = 0; i < quchong4.size(); i++) {
            sum4 += map4.get(quchong4.get(i));
            list4.add(sum4);
        }


        Map<String,Object> params =new HashMap<>();
        params.put("resultList",list);

        try {
            XWPFDocument word = WordExportUtil.exportWord07(templateFile.getPath(),params);
            //合并单元格
            List<XWPFTable> xwpfTables = word.getTables();
            for (XWPFTable table : xwpfTables) {
                mergeUtil mergeUtil = new mergeUtil();
                mergeUtil.mergeColumn(table,0,1,list1.get(0));
                mergeUtil.mergeColumn(table,1,1,list2.get(0));
                mergeUtil.mergeColumn(table,2,1,list3.get(0));
                mergeUtil.mergeColumn(table,3,1,list4.get(0));

                for (int i = 0; i < list1.size(); i++) {
                    for (int j = i+1; j < list1.size(); j++) {
                        mergeUtil.mergeColumn(table,0,list1.get(i) + 1,list1.get(j));
                    }
                }
                for (int i = 0; i < list2.size(); i++) {
                    for (int j = i + 1; j < list2.size(); j++) {
                        mergeUtil.mergeColumn(table, 1, list2.get(i) + 1, list2.get(j));
                    }
                }
                for (int i = 0; i < list3.size(); i++) {
                    for (int j = i+1; j < list3.size(); j++) {
                        mergeUtil.mergeColumn(table,2,list3.get(i)+1 ,list3.get(j));
                    }
                }
                for (int i = 0; i < list4.size(); i++) {
                    for (int j = i+1; j < list4.size(); j++) {
                        mergeUtil.mergeColumn(table,3,list4.get(i) + 1,list4.get(j));
                    }
                }
            }

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("\\exportFiles\\围岩级别与特征表.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("\\exportFiles\\围岩级别与特征表.docx", FileFormat.Doc);
        doc.saveToFile("\\exportFiles\\围岩级别与特征表.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("\\exportFiles\\围岩级别与特征表.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

        return sb;
    }
}