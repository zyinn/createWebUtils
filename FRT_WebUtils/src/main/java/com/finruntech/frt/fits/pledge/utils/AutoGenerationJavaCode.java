package com.finruntech.frt.fits.pledge.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Component
public class AutoGenerationJavaCode {
    private String url;
    private String name;
    private String passWord;
    private String driver;
    private String sql;
    private String tableName;
    private String templateDir;
    private String autoGeneratedFile;
    private String basePackage;
    private static String[][] fileNameArray = new String[7][2];

    static {
        fileNameArray[0][0] = "entityTemplate.ftl";
        fileNameArray[0][1] = "Entity.java";

        fileNameArray[1][0] = "serviceTemplate.ftl";
        fileNameArray[1][1] = "Service.java";

        fileNameArray[2][0] = "serviceImplTemplate.ftl";
        fileNameArray[2][1] = "ServiceImpl.java";

        fileNameArray[3][0] = "repositoryTemplate.ftl";
        fileNameArray[3][1] = "Mapper.java";

        fileNameArray[4][0] = "pageBaseTemplate.ftl";
        fileNameArray[4][1] = "PageBaseDto.java";

        fileNameArray[5][0] = "controllerTemplate.ftl";
        fileNameArray[5][1] = "Controller.java";

        fileNameArray[6][0] = "MapperXMLTemplate.ftl";
        fileNameArray[6][1] = "XML.xml";
    }

    public AutoGenerationJavaCode(String url, String name, String passWord, String driver, String tableName,
                                  String autoGeneratedFile, String templateDir) {
        this.url = url;
        this.name = name;
        this.passWord = passWord;
        this.driver = driver;
        this.sql = "select * from " + tableName;
        this.tableName = tableName;
        this.templateDir = templateDir;
        this.autoGeneratedFile = autoGeneratedFile;
    }
    public static final String dateToStringFormat(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setSqlAndTableName(String tableName){
        this.sql="select * from " + tableName;
        this.tableName=tableName;
    }

    private void setBasePackage(){
        if(autoGeneratedFile!=null && autoGeneratedFile.contains("com")){
            String  basePackagePath=autoGeneratedFile.substring(autoGeneratedFile.indexOf("com"),autoGeneratedFile.length());
            basePackage=basePackagePath.replace("\\",".");
        }
    }

    public void autoGenerationJavaCode() throws IOException, TemplateException, ClassNotFoundException,
            SQLException {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");

        String className = dealTableName();

        String fileName = dealClassName(className);
        List<Map<String, Object>> columnMap = getColumn();

        //设置模板文件路径
        cfg.setDirectoryForTemplateLoading(new File(templateDir));
        setBasePackage();//设置代码生成位置
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("basePackage", basePackage);
        rootMap.put("currentDate", dateToStringFormat(new Date()));
        rootMap.put("className", className);
        rootMap.put("columnMap", columnMap.get(0));
        rootMap.put("columnDesc", columnMap.get(1));
        rootMap.put("tableName", tableName);
        File dir = new File(autoGeneratedFile + "\\");
        //检查目录是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdir();
        }
        for (int i = 0; i < fileNameArray.length; i++) {

            Template temp = cfg.getTemplate(fileNameArray[i][0]);
            File docFile;
            if(fileNameArray[i][1].equals("PageBaseDto.java")){
                docFile = new File(autoGeneratedFile + "\\" +  fileNameArray[i][1]);
            }else{
                docFile = new File(autoGeneratedFile + "\\" + fileName + fileNameArray[i][1]);
            }

            Writer docout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            //输出文件
            temp.process(rootMap, docout);
        }
        System.out.println("==============文件生产成功===============");

    }

    //获取数据库表字段名放入map中
    public List<Map<String, Object>> getColumn() throws ClassNotFoundException, SQLException {
        Connection conn;
        PreparedStatement pStemt = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, name, passWord);
        pStemt = conn.prepareStatement(sql);
        ResultSetMetaData rsmd = pStemt.getMetaData();

        Map<String, Object> columnMap = new HashMap<String, Object>();
        Map<String, Object> columnDesc = new HashMap<String, Object>();
        int size = rsmd.getColumnCount();
        for (int i = 0; i < size; i++) {
            String columnName = dealColumnName(rsmd, i);
            columnMap.put(columnName, rsmd.getColumnName(i + 1));
            columnDesc.put(rsmd.getColumnName(i + 1),rsmd.getColumnName(i + 1));
        }
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(columnMap);
        list.add(columnDesc);
        conn.close();
        return list;
    }

    //将表名转换为DMO的字段名，比如 operate_type 转换后为 operateType
    private String dealColumnName(ResultSetMetaData rsmd, int i) throws SQLException {
        String columnName = rsmd.getColumnName(i + 1).toLowerCase();
        String charAfterLine = String.valueOf(columnName.charAt((columnName.indexOf("_") + 1)));
        String convertedChar = charAfterLine.toUpperCase();
        columnName = columnName.replace("_" + charAfterLine, convertedChar);
        return columnName;
    }

     //将表名转换为类型类名 比如 t_operate_log 转换后为 operateLog ,类名首字母应为大写，这里在freemarker的模板里直接转换
    private String dealTableName() {
        String className = tableName.toLowerCase().substring(tableName.indexOf("_") + 1);
        String charAfterLine = String.valueOf(className.charAt((className.indexOf("_") + 1)));
        String convertedChar = charAfterLine.toUpperCase();
        className = className.replace("_" + charAfterLine, convertedChar);
        return className;
    }

    //将类名转换为文件名，java公共类名与其文件名应该相同，这里将首字母转换为大写 如operateLog 转换后为 OperateLog
    private String dealClassName(String className) {
        String first = className.substring(0, 1).toUpperCase();
        String rest = className.substring(1, className.length());
        String fileName = new StringBuffer(first).append(rest).toString();
        return fileName;
    }

}
