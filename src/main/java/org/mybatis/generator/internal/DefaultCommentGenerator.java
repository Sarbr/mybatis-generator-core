package org.mybatis.generator.internal;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;

/**
 * @author Jeff Butler
 */
public class DefaultCommentGenerator implements CommentGenerator {

    public DefaultCommentGenerator() {
        super();
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    @Override
    public void addComment(XmlElement xmlElement) {
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable) {
    }

    @Override
    public void addClassComment(InnerClass innerClass,
                                IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass,
                                     IntrospectedTable introspectedTable) {
        final TableConfiguration tableConfiguration = introspectedTable.getTableConfiguration();
        final String tableName = tableConfiguration.getTableName();
        final String tableComment = tableConfiguration.getTableComment();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @date " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //topLevelClass.addJavaDocLine(" * 实体名称: " );
        //topLevelClass.addJavaDocLine(" * 表名: " + introspectedTable.getFullyQualifiedTable().toString());
        topLevelClass.addJavaDocLine(" * @author " + System.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * alter table "+tableName+" comment" + " '" + tableComment +"';");
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum,
                               IntrospectedTable introspectedTable) {
    }

    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("@Meaning(\"".concat(introspectedColumn.getRemarks()).concat("\")"));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGeneralMethodComment(Method method,
                                        IntrospectedTable introspectedTable) {
    }

    @Override
    public void addGetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method,
                                 IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
                                           Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        method.addAnnotation(getGeneratedAnnotation(comment));
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
                                           IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source field: "
                + introspectedTable.getFullyQualifiedTable().toString()
                + "."
                + introspectedColumn.getActualColumnName();
        method.addAnnotation(getGeneratedAnnotation(comment));
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        field.addAnnotation(getGeneratedAnnotation(comment));
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source field: "
                + introspectedTable.getFullyQualifiedTable().toString()
                + "."
                + introspectedColumn.getActualColumnName();
        field.addAnnotation(getGeneratedAnnotation(comment));

        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * Database Column Remarks:");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" *   " + remarkLine);
            }
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
                                   Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString();
        innerClass.addAnnotation(getGeneratedAnnotation(comment));
    }

    private String getGeneratedAnnotation(String comment) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("@Generated(");
        buffer.append("value=\"");

        buffer.append(MyBatisGenerator.class.getName());
        buffer.append('\"');

        buffer.append(", date=\"");
        buffer.append(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now()));
        buffer.append('\"');

        buffer.append(", comments=\"");
        buffer.append(comment);
        buffer.append('\"');

        buffer.append(')');
        return buffer.toString();
    }
}