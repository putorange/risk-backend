package com.ietunnel.riskservice.articleEditAndExport.controller;


import com.ietunnel.riskservice.articleEditAndExport.Utils.WordUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0.0
 * @Description: #类描述
 * @Date: 2022/5/16 13:57
 * @Copyright (C) zxg
 */
@RestController
@RequestMapping("/riskservice/exportReport")
public class Demo {

    @ApiOperation("word导出")
    @RequestMapping(value = "/exportWord1")
    public void exprotWord(@RequestParam String id, HttpServletRequest request,
                           HttpServletResponse response) {
        // 数据库查富文本数据
        String content = "通风竖井结构设计测试通风竖井测试通风测试通风竖井结构设计测试通风竖井结构设计测试通风竖井结构设计测试通风竖井结构设计测试";
        String richText = "<html><body>" + content + "</body></html>";
        try {
            //设置编码
            byte b[] = richText.getBytes("utf-8");
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            POIFSFileSystem poifs = new POIFSFileSystem();
            // ##############下面这两个不能删掉
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            //################这两个不能删掉
            //输出文件
            String name = "测试";
            response.reset();
            response.setHeader("Content-Disposition",
                    "attachment;filename=" +
                            new String(name.getBytes("utf-8"), "iso-8859-1") + ".doc");
            response.setContentType("application/msword");
            OutputStream ostream = response.getOutputStream();
            //输出到本地文件的话，new一个文件流
            // FileOutputStream ostream = new FileOutputStream("E:\\桌面\\测试.doc");
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/exportWord")
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String tmpContent = "<h1 style=\"text-align: center;\"><strong>第一章 </strong><strong>&nbsp;&nbsp;</strong><strong>总 论</strong></h1>\n" +
                    "<h2><strong>1.1</strong><strong>&nbsp;</strong><strong>隧道概况</strong></h2>\n" +
                    "<p>坪盐通道工程位于深圳市东部地区，工程范围跨越坪山、盐田两区，道路北起坪山新区现状锦龙大道&mdash;&mdash;中山大道交叉口，南至盐田区盐坝高速，路线全长约11.25km。本项目采用城市快速路标准，设计速度80km/h，双向六车道。全线共设大型互通立交2座，桥梁15座（总面积约9.5万m<sup>2</sup>）。特长隧道1座。</p>\n" +
                    "<p>坪山新区南向对外交通是主要对外方向，而现有路网中南向对外通道明显不足，坪盐通道的建成可加强坪山新区南向对外的交通联系，对于进一步完善深圳市整体快速路网体系，加强坪山新区与深圳中心区、盐田区的交通联系，丰富坪山新区群众至深圳中心区的出行线路，节约出行时间和出行成本，改善坪山新区的投资环境，提升土地利用价值等有重要的意义。</p>\n" +
                    "<p>汕头至湛江高速公路惠州至清远段项目共分2个标段（分别为3标和4标），路线全长约11.25km。马峦山隧道全线位置图如图1.1-1所示，隧道情况如表1.1-1所示。</p>\n" +
                    "<table>\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td rowspan=\"2\" width=\"53\">\n" +
                    "<p>隧道名称</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"35\">\n" +
                    "<p>布置</p>\n" +
                    "<p>形式</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"127\">\n" +
                    "<p>起讫桩号</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"46\">\n" +
                    "<p>长度</p>\n" +
                    "<p>(m)</p>\n" +
                    "</td>\n" +
                    "<td colspan=\"2\" width=\"81\">\n" +
                    "<p>洞门型式</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"38\">\n" +
                    "<p>照明</p>\n" +
                    "<p>方式</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"35\">\n" +
                    "<p>通风</p>\n" +
                    "<p>方式</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td width=\"39\">\n" +
                    "<p>进口</p>\n" +
                    "</td>\n" +
                    "<td width=\"42\">\n" +
                    "<p>出口</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td rowspan=\"2\" width=\"53\">\n" +
                    "<p>马峦山</p>\n" +
                    "<p>隧道</p>\n" +
                    "</td>\n" +
                    "<td rowspan=\"2\" width=\"35\">\n" +
                    "<p>分离式</p>\n" +
                    "</td>\n" +
                    "<td width=\"127\">\n" +
                    "<p>YK2+585 ~ YK10+484.91</p>\n" +
                    "</td>\n" +
                    "<td width=\"46\">\n" +
                    "<p>7899.91</p>\n" +
                    "</td>\n" +
                    "<td width=\"39\">\n" +
                    "<p>削竹式</p>\n" +
                    "</td>\n" +
                    "<td width=\"42\">\n" +
                    "<p>削竹式</p>\n" +
                    "</td>\n" +
                    "<td width=\"38\">\n" +
                    "<p>电光</p>\n" +
                    "</td>\n" +
                    "<td width=\"35\">\n" +
                    "<p>分段纵向</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td width=\"127\">\n" +
                    "<p>ZK2+590 ~ ZK10+489.205</p>\n" +
                    "</td>\n" +
                    "<td width=\"46\">\n" +
                    "<p>7899.20</p>\n" +
                    "</td>\n" +
                    "<td width=\"39\">\n" +
                    "<p>削竹式</p>\n" +
                    "</td>\n" +
                    "<td width=\"42\">\n" +
                    "<p>削竹式</p>\n" +
                    "</td>\n" +
                    "<td width=\"38\">\n" +
                    "<p>电光</p>\n" +
                    "</td>\n" +
                    "<td width=\"35\">\n" +
                    "<p>分段纵向</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p><strong>主要技术标准：</strong></p>\n" +
                    "<p>（1）道路等级：城市快速路；</p>\n" +
                    "<p>（2）设计速度：80km/h；</p>\n" +
                    "<p>（3）隧道建筑限界：</p>\n" +
                    "<p>①行车道宽：3.5+2&times;3.75m，路缘带宽：左侧0.5m，右侧0.5m；</p>\n" +
                    "<p>②净高：5m（考虑大型双层公交车通行及高速衔接）；</p>\n" +
                    "<p>③检修道宽：双侧各0.75m，净高2.5m；</p>\n" +
                    "<p>（4）人行横通道建筑限界：净宽2.0m，净高2.5m。</p>\n" +
                    "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人车共用横通道建筑限界：净宽5.5m，净高5.0m。</p>\n" +
                    "<p>（5）路面荷载等级：城－A级；</p>\n" +
                    "<p>（6）隧道结构安全等级：一级；</p>\n" +
                    "<p>（7）隧道主体结构防水等级：二级，重要机电设备集中区域：一级；</p>\n" +
                    "<p>（8）隧道主体结构设计应满足使用年限100年的要求；</p>\n" +
                    "<p>（9）结构设计按七度地震烈度进行抗震验算，按八度采取相应抗震构造措施。</p>\n" +
                    "<h2><strong>1.2</strong><strong>&nbsp;</strong><strong>工程地质条件</strong></h2>\n" +
                    "<h3><strong>1.2.1 </strong><strong>地形地貌</strong></h3>\n" +
                    "<p>深圳市全境地势东南高，西北低，大部分为低山丘陵区，间以平缓的台地；西部为滨海平原。境内最高山峰为梧桐山，海拔943.7米。坪盐通道工程位于深圳市东部地区，线路大致呈北东－南西走向连接坪山新区与盐田区，项目工程范围跨越坪山、盐田两区，北起坪山新区现状锦龙大道－－中山大道交叉路口北侧、坪山河南岸，南至盐田区盐坝高速、规划盐港东立交。</p>\n" +
                    "<p>马峦山隧道工程进洞口位于黄竹坑采石场东北约500m处马峦山山体，在盐田端（南端）大水坑盐坝高速以北出洞，线址原始地貌为丘陵，局部人工改造成居住区、景区（东部华侨城景区和马峦山公园）以及采石场，山体植被发育、树木茂盛。沿线地形起伏较大，标高在60m-412m间变化。</p>";

//            // 获取img图片标签
//            // 1.Jsoup解析html
//            Document document = Jsoup.parse(tmpContent);
//            // 获取所有img图片标签
//            Elements img = document.getElementsByTag("img");
//            int index = 0;
//            List<String> imgBase64List = new ArrayList<>();
//            for (Element element : img) {
//                imgBase64List.add(element.attr("src"));
//                // 处理特殊符号
//                String attrData = element.attr("src");
//                // base64编码后可能包含 + 特殊字符,所以需要转义
//                attrData = attrData.replaceAll("\\+", "\\\\+");
//                tmpContent = tmpContent.replaceAll(attrData, "{{image_src" + index + "}}");
//                index++;
//            }
//            // 缩放图片大小,然后重新base64编码后替换到富文本内容里面导出word
//            index = 0;
//            String prefix = "data:image/png;base64,"; // base64编码前缀
//            for (String base64 : imgBase64List) {
//                if (StringUtils.isNotBlank(base64)) {
//                    // 缩小图片
//                    base64 = base64.replaceAll(prefix, "");
//                    BufferedImage bufferedImage = ImageUtils.bytesToBufferedImage(ImageUtils.base64ToByte(base64));
//                    if (bufferedImage == null) {
//                        tmpContent = tmpContent.replaceAll("\\{\\{image_src" + index + "}}", "");
//                    } else {
//                        int height = bufferedImage.getHeight();
//                        int width = bufferedImage.getWidth();
//                        // 如果图片宽度小于700,则图片不缩放,还是使用原图片
//                        if (width > 700) {
//                            BufferedImage imgZoom = ImageUtils.resizeImage(bufferedImage, 700, height);
//                            String imageToBase64 = ImageUtils.imageToBase64(ImageUtils.imageToBytes(imgZoom));
//                            tmpContent = tmpContent.replaceAll("\\{\\{image_src" + index + "}}", prefix + imageToBase64);
//                        } else {
//                            tmpContent = tmpContent.replaceAll("\\{\\{image_src" + index + "}}", prefix + base64);
//                        }
//                    }
//                } else {
//                    tmpContent = tmpContent.replaceAll("\\{\\{image_src" + index + "}}", "");
//                }
//                index++;
//            }
            // 执行导出操作
            WordUtil.exportHtmlToWord(request, response, tmpContent, "富文本内容导出word");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}