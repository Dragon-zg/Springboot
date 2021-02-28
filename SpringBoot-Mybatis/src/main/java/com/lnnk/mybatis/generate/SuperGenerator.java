package com.lnnk.mybatis.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.*;

/**
 * <p>
 * 代码生成器父类
 * </p>
 *
 * @author Caratacus
 */
public class SuperGenerator {

    private static final String TEMPLATE_PATH = "/templates/mapper.xml.vm";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bbc?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
//    private static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";


    /**
     * 获取TemplateConfig
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig().setXml(null);
    }

    /**
     * 获取InjectionConfig
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig(String rootPath) {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(TEMPLATE_PATH) {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getResourcePath(rootPath) + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }

    /**
     * 获取PackageConfig
     *
     * @return
     */
    protected PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent("com.seedeer.project")
                .setController("controller")
                .setEntity("domain.entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
    }

    /**
     * 获取StrategyConfig
     *
     * @param tableName
     * @return
     */
    protected StrategyConfig getStrategyConfig(String[] tableNames) {
        List<TableFill> tableFillList = getTableFills();
        StrategyConfig strategyConfig = new StrategyConfig()
                // 全局大写命名
                .setCapitalMode(false)
                // 去除前缀
//                .setTablePrefix("bbc_")
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
//                .setExclude()
                //自定义实体父类
                .setSuperEntityClass("com.seedeer.project.domain.base.BaseEntity")
                //定义基础的Entity类，公共字段
                .setSuperEntityColumns("create_user", "create_time", "update_user", "update_time", "delete_flag")
                // 自定义实体，公共字段
//                .setSuperEntityColumns("id")
                .setTableFillList(tableFillList)
                // 自定义 mapper 父类
//                .setSuperMapperClass("org.crown.framework.mapper.BaseMapper")
                // 自定义 controller 父类
//                .setSuperControllerClass("org.crown.framework.controller.SuperController")
                // 自定义 service 实现类父类
//                .setSuperServiceImplClass("org.crown.framework.service.impl.BaseServiceImpl")
                // 自定义 service 接口父类
//                .setSuperServiceClass("org.crown.framework.service.BaseService")
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(false)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false)
                // 逻辑删除属性名称
//                .setLogicDeleteFieldName("delete_flag")
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(false)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 是否生成实体时，生成字段注解
                .setEntityTableFieldAnnotationEnable(true)
                .setRestControllerStyle(true);
        if (null != tableNames && tableNames.length > 0) {
            // 需要生成的表
            strategyConfig.setInclude(tableNames);
        }
        return strategyConfig;
    }

    /**
     * 获取TableFill策略
     *
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
        tableFillList.add(new TableFill("createUser", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("updateUser", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 获取DataSourceConfig
     *
     * @return
     */
    protected DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().equals("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("tinyint")) {
                            return DbColumnType.INTEGER;
                        }
                        if (fieldType.toLowerCase().equals("date")) {
                            return DbColumnType.DATE_SQL;
                        }
                        if (fieldType.toLowerCase().equals("time")) {
                            return DbColumnType.DATE_SQL;
                        }
                        if (fieldType.toLowerCase().equals("datetime")) {
                            return DbColumnType.DATE_SQL;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDriverName(DRIVERNAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setUrl(URL);
    }

    /**
     * 获取GlobalConfig
     *
     * @return
     */
    protected GlobalConfig getGlobalConfig(String rootPath) {
        return new GlobalConfig()
                //输出目录
                .setOutputDir(getJavaPath(rootPath))
                // 是否覆盖文件
                .setFileOverride(false)
                //swagger
                .setSwagger2(true)
                // 开启 activeRecord 模式
                .setActiveRecord(false)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                //是否生成 kotlin 代码
                .setKotlin(false)
                .setOpen(false)
                //作者
                .setAuthor("wangqiang")
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");
    }


    /**
     * 获取根目录
     *
     * @return
     */
    private String getRootPath() {
        String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
        return new File(file).getParentFile().getParentFile().getParent();
    }

    /**
     * 获取JAVA目录
     *
     * @return
     */
    protected String getJavaPath(String rootPath) {
        return (null != rootPath ? rootPath : getRootPath()) + "/src/main/java";
    }

    /**
     * 获取Resource目录
     *
     * @return
     */
    protected String getResourcePath(String rootPath) {
        return (null != rootPath ? rootPath : getRootPath()) + "/src/main/resources";
    }

    /**
     * 获取AutoGenerator
     *
     * @param tableName
     * @return
     */
    protected AutoGenerator getAutoGenerator(String[] tableNames, String rootPath) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig(rootPath))
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tableNames))
                // 包配置
                .setPackageInfo(getPackageConfig())
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                .setCfg(getInjectionConfig(rootPath))
                .setTemplate(getTemplateConfig());
    }

}
