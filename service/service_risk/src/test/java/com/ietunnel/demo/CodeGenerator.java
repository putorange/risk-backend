package com.ietunnel.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author
 * @since 2021/12/13
 */
public class CodeGenerator {

    @Test
    public void run() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 路径一定要对，尽量写绝对路径
        gc.setOutputDir("E:\\risk_project\\riskAssessment_0620\\risk_springboot\\service\\service_risk" + "/src/main/java");
        //gc.setOutputDir("/Users/zouxuegang/Documents/project/risk_assessment/riskAssessment_0606/risk_springboot/service/service_risk" + "/src/main/java");
        gc.setAuthor("cmy");
        gc.setOpen(false);           //生成后是否打开资源管理器
        gc.setFileOverride(false);  //重新生成时文件是否覆盖


        gc.setServiceName("%sService");	//去掉Service接口的首字母I

        gc.setIdType(IdType.ID_WORKER_STR);     //主键策略
        gc.setDateType(DateType.ONLY_DATE);     //定义生成的实体类中日期类型
        gc.setSwagger2(true);   //开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/risk_assessment?serverTimezone=GMT%2B8");
        dsc.setUrl("jdbc:mysql://localhost:3306/risk_assessment?serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("3998");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("riskservice.tunnelRockConstructionmethod"); //模块名
        pc.setParent("com.ietunnel");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 数据表
        //strategy.setInclude("tunnel");

        strategy.setInclude("tunnel_rock_constructionmethod");

        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作

        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
