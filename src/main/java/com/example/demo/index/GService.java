package com.example.demo.index;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;

import static com.example.demo.index.GUtils.*;


/**
 * Created by codefactory on 2018/3/5.
 */
public class GService {

    private static final Logger logger = LoggerFactory.getLogger(GService.class);
    private Configuration configuration = null;
    private String TEMPLATE_FILE;
    private String PROJECT_PATH;
    private String JAVA_PATH = "/src/main/java/";
    private Table table;
    private Map<String, Object> model = new HashMap<String, Object>();

    public static void main(String[] args) {
        GService cgm = new GService();
        cgm.run();
    }

    //1初始化
    public void run() {
        try {
            ClassPathResource resource = new ClassPathResource("generatorConfig.properties");
            Properties properties = new Properties();
            properties.load(resource.getStream());

            String projectName = properties.getProperty("projectName");
            TEMPLATE_FILE = properties.getProperty("template.file");
            PROJECT_PATH = properties.getProperty("project.path") == null ? "" : properties.getProperty("project.path");
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            String configPath = ClassLoader.getSystemResource("").getPath() + TEMPLATE_FILE;
            configuration.setDirectoryForTemplateLoading(new File(configPath));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            model.put("projectName", projectName);
            getFile();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }



    /**
     * 获取所有template下的模板路径,有文件夹带文件夹
     * @param resource
     * @return
     */
    private List<String> returnftlPathList(ClassPathResource resource ){
        File[] ls = FileUtil.ls(resource.getPath());
        List<String> list = new ArrayList<String>();
        for (File l : ls) {
            if(l.isDirectory()){
                File[] ls1 = FileUtil.ls(l.getPath());
                for (File file : ls1) {
                    list.add(l.getPath().substring(l.getPath().lastIndexOf("\\")+1)+"/"+file.getPath().substring(file.getPath().lastIndexOf("\\")+1));
                }
            }else{
                list.add(l.getName());
            }
        }
        return list;
    }
    /*4生成File*/
    private void getFile() throws Exception {
        /*所有模板*/
        ClassPathResource resource = new ClassPathResource(TEMPLATE_FILE);
        List<String> list = returnftlPathList(resource);
        /*循环生成*/
        for (String filename : list) {
            /*目标文件生成*/
            targetFileGenerate(filename);
        }
        /*end*/
        logger.info("生成成功!");

    }

    /*5生成文件生成*/
    private void targetFileGenerate(String template) throws Exception {
        /*生成*/
        configuration.getTemplate(template).getCustomLookupCondition();
        String filepath = System.getProperty("user.dir") + PROJECT_PATH;
        if (template.contains("Mapper")) {
            if(template.contains("/")){
                //文件夹
                String Folder = template.split("/")[0];
                //文件
                String file = template.split("/")[1];
                filepath = filepath  + file.replace(".ftl", "");
            }else{
                filepath = filepath  + template.replace(".ftl", "");
            }
        } else {
            if(template.contains("/")){
                //文件夹
                String Folder = template.split("/")[0];
                //文件
                String file = template.split("/")[1];
                filepath = filepath + JAVA_PATH + "/" + Folder +"/" + file.replace(".ftl", "");
            }else{
                filepath = filepath + JAVA_PATH  + "/"  + template.replace(".ftl", "");
            }

        }
        File newfile = new File(filepath);
        if (!newfile.getParentFile().exists()) {
            newfile.getParentFile().mkdirs();
        }
        if (newfile.exists()) {
            newfile.delete();//删除文件
        }
        /*先生成后转移*/
        configuration.getTemplate(template).process(model, new FileWriter(newfile));
    }



}
