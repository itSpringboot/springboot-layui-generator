package com.jcy.factory.custom;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;
import com.jcy.factory.autoconfigation.GeneratorConfigation;
import com.jcy.factory.generator.Generator;
import com.jcy.factory.model.CrudBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 自定义WebConfig配置
 *
 * 可以动态的设置controller和view映射
 * 方法addViewControllers
 *
 *  crud-tempalte WebConfig.java
 *  /**
 * {@inheritDoc}
 * </br>注册controller映射
 * 如果有相同注解controller 以注解优先
 *
 *public void addViewControllers(ViewControllerRegistry registry) {
 *       registry.setOrder(0);
 *       registry.addViewController("/userview").setViewName("example/user");
 *}
 *
 */
//@Component
@Slf4j
public class CustomWebConfigGenerator implements Generator {

    private static final String WEB_CONFIG_JAVA = "WebConfig.java";
    private static final String SRC_MAIN_RESOURCES = "src/main/resources";
    private static final String TEMP = "temp";

    @SneakyThrows
    @Override
    public File genCode(CrudBean crudBean, GeneratorConfigation generatorConfigation) {
        String javaProjectPath = generatorConfigation.getProject().getJavaProjectPath();
        File file = preResource(javaProjectPath);
        String content = addViewController(crudBean);
        saveFile(file, content);
        clearTemp();
        return file;
    }

    private void clearTemp() {
        getWebConfigTempFile().delete();
    }

    private void saveFile(File file, String cu) throws IOException {
        if (cu != null) {
            FileUtils.writeStringToFile(file, cu.toString(), Charsets.UTF_8.name());
        }
    }

    private String addViewController(CrudBean crudBean) {
        SourceRoot sourceRoot = new SourceRoot(CodeGenerationUtils.mavenModuleRoot(CustomWebConfigGenerator.class).resolve(SRC_MAIN_RESOURCES));
        CompilationUnit cu = sourceRoot.parse(TEMP, WEB_CONFIG_JAVA);
        BlockStmt addViewControllers = cu.findAll(MethodDeclaration.class).stream()
                .filter(methodDeclaration -> methodDeclaration.getName().toString().equals("addViewControllers"))
                .findFirst().get().getBody().get();
        String controllerRequestMapping = crudBean.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
        String viewPath = crudBean.getModelAttributes().getViewAttributes().getViewPath();
        String format = String.format("registry.addViewController(\"/%sview\").setViewName(\"%s\");", controllerRequestMapping, viewPath);
        NodeList<Statement> tempList = new NodeList<>(addViewControllers.getStatements());
        boolean hasSave = tempList.stream().anyMatch(statement -> format.equals(statement.toString()));
        if (!hasSave) {
            addViewControllers.addStatement(format);
            return cu.toString();
        }
        return null;
    }

    private File preResource(String javaProjectPath) throws IOException {
        String oraginPath = javaProjectPath+"\\src\\main\\java\\com\\lhy\\config\\" + WEB_CONFIG_JAVA;
        File file = new File(oraginPath);
        File tempFile = getWebConfigTempFile();
        FileUtils.copyFile(file, tempFile);
        return file;
    }

    private File getWebConfigTempFile() {
        String dir = System.getProperty("user.dir");
        if (dir.contains("crud-gen")){
            return new File(dir + "/" + SRC_MAIN_RESOURCES + "/" + TEMP, WEB_CONFIG_JAVA);
        }
        return new File(dir + "/crud-gen/" + SRC_MAIN_RESOURCES + "/" + TEMP, WEB_CONFIG_JAVA);
    }
}
