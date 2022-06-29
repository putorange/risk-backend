package com.ietunnel.riskservice.evaluationOverallRisk.Utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.common.utils.mergeUtil;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;
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

    public StringBuffer toWord(List<EportEvalutionOverallRisk> list) throws IOException {
        //获取模板文档
        File templateFile= new File("/Users/apple/Documents/riskAssessment_0612/隧道工程总体风险评估指标体系.docx");


//        主要思路：
//        首先是合并行：
//          1.对第一列的行进行合并：
//                 由于通过数据查到的是一条一条的数组，所以只需要对数组中相同的数据求总数就可以知道要合并几行。
//                 所以拿到第一列数据后求出每个评估指标名称的个数,拿到每个名称的个数之后，需要对其累加即可拿到改数组所占的最后一行的索引，最后调用mergeUtil对其合并。
//          2.对第二列的行进行合并：
//                 和第一列的做法一样。
//           接着是合并列：
//                    由于第一列和第二列的数据有些是一样的，所以首先要对第二列的数据需要做些处理，当第二列数据和第一列数据一样时，将第三列的数据赋值给第二列的数据。这个时候就可以知道对哪些数据进行合并了，
//                    对数组进行循环即可拿到相应的数据索引，将索引位置传给mergeUtil工具进行合并即可。


        // 在合并前，进行数据处理，当第二列数据和第一列数据一样时，将第三列的数据赋值给第二列的数据。可以适当的输出看看。
        for (EportEvalutionOverallRisk eportEvalutionOverallRisk : list) {
            if (eportEvalutionOverallRisk.getIndicatorname().equals(eportEvalutionOverallRisk.getSubindicatorname())){
                eportEvalutionOverallRisk.setSubindicatorname(eportEvalutionOverallRisk.getClassificationname());
            }
        }


        // 第一 二列合并数据预处理
        ArrayList<String> column1 = new ArrayList<>();      //第一列数据
        ArrayList<String> column2 = new ArrayList<>();      //第二列数据
        ArrayList<String> column3 = new ArrayList<>();      //第三列数据
        for (EportEvalutionOverallRisk eportEvalutionOverallRisk : list) {
            column1.add(eportEvalutionOverallRisk.getIndicatorname());
            column2.add(eportEvalutionOverallRisk.getSubindicatorname());
            column3.add(eportEvalutionOverallRisk.getClassificationname());
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


        Map<String,Integer> map2 = new HashMap<>();
        for (String s : column2) {
            map2.put(s, map2.get(s) == null? 1:map2.get(s)+1);
        }

//        map2.forEach((key, value) -> {
//            System.out.println(key + "    " + value);
//        });

        // 去重
        List<String> quchong2 = column2.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> list2 = new ArrayList<>();
        int sum2 = 0;
        for (int i = 0; i < quchong2.size(); i++) {
            // System.out.println(quchong2.get(i));
            sum2 += map2.get(quchong2.get(i));
            list2.add(sum2);
        }


        // 获取第三列数据的索引位置
        ArrayList<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < column3.size(); i++) {
            for (int i1 = 0; i1 < column2.size(); i1++) {
                if (column2.get(i).equals(column3.get(i1))){
//                    System.out.println(i);
                    list3.add(i+1);
                }
            }
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

                for (int i = 0; i < list1.size(); i++) {
                    for (int j = i+1; j < list1.size(); j++) {
                        mergeUtil.mergeColumn(table,0,list1.get(i)+1,list1.get(j));
                    }
                }
                for (int i = 0; i < list2.size(); i++) {
                    for (int j = i+1; j < list2.size(); j++) {
                        mergeUtil.mergeColumn(table,1,list2.get(i)+1,list2.get(j));
                    }
                }

                for (int i = 0; i < list3.size(); i++) {
//                    System.out.println(list4.get(i)+1);
                    mergeUtil.mergeLine(table,list3.get(i),1,2);
                }
            }

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("/Users/apple/Documents/riskAssessment_0612/隧道工程总体风险评估指标体系1.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("/Users/apple/Documents/riskAssessment_0612/隧道工程总体风险评估指标体系1.docx", FileFormat.Doc);
        doc.saveToFile("/Users/apple/Documents/riskAssessment_0612/隧道工程总体风险评估指标体系.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("/Users/apple/Documents/riskAssessment_0612/隧道工程总体风险评估指标体系.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }

        // 删除最后文件
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
