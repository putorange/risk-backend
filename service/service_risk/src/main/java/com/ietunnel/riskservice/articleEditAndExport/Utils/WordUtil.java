package com.ietunnel.riskservice.articleEditAndExport.Utils;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

public class WordUtil {

        /**
         * 导出富文本内容到word
         * @param request
         * @param response
         * @param content 输出内容
         * @param fileName 导出文件名称
         * @throws Exception
         */


        public static void exportHtmlToWord(HttpServletRequest request, HttpServletResponse response, String content, String fileName) throws Exception {

            // 拼接html格式内容
            StringBuffer sbf = new StringBuffer();
            String wordHtmlHead = "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                    "xmlns:w=\"urn:schemas-microsoft-com:office:word\" xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"\n" +
                    "xmlns=\"http://www.w3.org/TR/REC-html40\"><head>\n" +
                    "    <!--[if gte mso 9]><xml><w:WordDocument><w:View>Print</w:View><w:TrackMoves>false</w:TrackMoves><w:TrackFormatting/><w:ValidateAgainstSchemas/><w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid><w:IgnoreMixedContent>false</w:IgnoreMixedContent><w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText><w:DoNotPromoteQF/><w:LidThemeOther>EN-US</w:LidThemeOther><w:LidThemeAsian>ZH-CN</w:LidThemeAsian><w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript><w:Compatibility><w:BreakWrappedTables/><w:SnapToGridInCell/><w:WrapTextWithPunct/><w:UseAsianBreakRules/><w:DontGrowAutofit/><w:SplitPgBreakAndParaMark/><w:DontVertAlignCellWithSp/><w:DontBreakConstrainedForcedTables/><w:DontVertAlignInTxbx/><w:Word11KerningPairs/><w:CachedColBalance/><w:UseFELayout/></w:Compatibility><w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel><m:mathPr><m:mathFont m:val=\"Cambria Math\"/><m:brkBin m:val=\"before\"/><m:brkBinSub m:val=\"--\"/><m:smallFrac m:val=\"off\"/><m:dispDef/><m:lMargin m:val=\"0\"/> <m:rMargin m:val=\"0\"/><m:defJc m:val=\"centerGroup\"/><m:wrapIndent m:val=\"1440\"/><m:intLim m:val=\"subSup\"/><m:naryLim m:val=\"undOvr\"/></m:mathPr></w:WordDocument></xml><![endif]-->\n" +
                    "</head>";

            sbf.append(wordHtmlHead);
            sbf.append("<body>");
            // 富文本内容
            sbf.append(content);
            sbf.append("</body></html>");

            // 必须要设置编码,避免中文就会乱码
            byte[] b = sbf.toString().getBytes("UTF-8");
            // 将字节数组包装到流中
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            // 这代码不能省略,否则导出乱码。
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            //输出文件
            request.setCharacterEncoding("utf-8");
            // 导出word格式
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String(fileName.getBytes("ISO8859-1"), "GBK") + ".doc");
            ServletOutputStream ostream = response.getOutputStream();
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        }

    }
