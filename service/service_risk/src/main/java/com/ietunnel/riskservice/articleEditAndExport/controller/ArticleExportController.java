package com.ietunnel.riskservice.articleEditAndExport.controller;

import com.ietunnel.riskservice.articleEditAndExport.service.ArticleEditedService;
import com.ietunnel.riskservice.articleEditAndExport.service.ArticleExportService;
import com.ietunnel.riskservice.articleEditAndExport.service.impl.ArticleEditedServiceImpl;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.pdf.exporting.xps.schema.FragmentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@CrossOrigin
@Api(description = "文章导出操作")
@RestController
@RequestMapping("/fileUpdate")
public class ArticleExportController {

    @Autowired
    private ArticleExportService articleExportService;

    @ResponseBody
    @RequestMapping(value = "/download")
    @ApiOperation("word导出")
    public int download(HttpServletResponse response, HttpServletRequest request)throws Exception {
        String content = articleExportService.getAllContent();
        StringBuffer sbf = new StringBuffer();
        sbf.append("<html><body>");
        sbf.append(content);
        sbf.append("</body></html");
        //新建Document对象
        Document document = new Document();
        //添加section
        Section sec = document.addSection();


        // 保存的就能显示行距
        //添加段落并写入HTML文本
        sec.addParagraph().appendHTML(sbf.toString());

        String filename = "/Users/apple/Documents/项目报告.docx";
        //文档另存为word
        document.saveToFile(filename, FileFormat.Doc);

        File file = new File(filename);
        try(InputStream inputStream =  new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();){
            response.setContentType("application/msword");//导出word格式
            response.addHeader("Content-Disposition", "attachment;filename=" + new String("项目报告".getBytes("GB2312"),"iso8859-1") + ".doc");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // exportWord(request,response,sbf.toString(),"项目报告");

        return 1;
    }


    /**
     *
     * @param request
     * @param response
     * @param content  富文本内容
     * @param fileName 生成word名字
     * @throws Exception
     */
//    public static void exportWord(HttpServletRequest request, HttpServletResponse response, String content, String fileName) throws Exception {
//        byte b[] = content.getBytes("GBK"); //这里是必须要设置编码的，不然导出中文就会乱码。
//        ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
//        POIFSFileSystem poifs = new POIFSFileSystem();
//        DirectoryEntry directory = poifs.getRoot();
//        DocumentEntry documentEntry = directory.createDocument("WordDocument", bais); //该步骤不可省略，否则会出现乱码。
//        //输出文件
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("application/msword");//导出word格式
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"),"iso8859-1") + ".doc");
//        ServletOutputStream ostream = response.getOutputStream();
//        poifs.writeFilesystem(ostream);
//        bais.close();
//        ostream.close();
//        poifs.close();
//    }


}