package com.ietunnel.riskservice.projectbase.utils;



import cn.afterturn.easypoi.word.WordExportUtil;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockProportionVo;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToWord1 {

    public StringBuffer toWord(List<TunnelRockProportionVo> list) throws IOException {
        //获取模板文档
        File templateFile = new File("\\exportFiles\\隧道围岩各级别所占比例-模板.docx");

        Map<String, Object> params = new HashMap<>();
        params.put("resultList", list);

        try {
            XWPFDocument word = WordExportUtil.exportWord07(templateFile.getPath(), params);

            // 文件输出位置
            FileOutputStream fos = new FileOutputStream("\\exportFiles\\隧道围岩各级别所占比例.docx");
            word.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // word转html
        Document doc = new Document();
        doc.loadFromFile("\\exportFiles\\隧道围岩各级别所占比例.docx", FileFormat.Doc);
        doc.saveToFile("\\exportFiles\\隧道围岩各级别所占比例.html", FileFormat.Html);

        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader("\\exportFiles\\隧道围岩各级别所占比例.html"));
        String content = null;

        while ((content = br.readLine()) != null) {
            sb.append(content);
        }


        return sb;
    }
}