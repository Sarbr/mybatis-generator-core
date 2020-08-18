# mybatis-generator-core
针对generatorConfig文件中table标签添加tableComment的
```
<table tableName="table_test" domainObjectName="Test">
    <property name="tableComment" value="测试"/>
</table>
```
添加描述表信息的注解
```
/**
 * @date 2020-07-20 10:34:24
 * @author sarbr
 */
@Getter
@Setter
@EntityInfo(value = "测试", tableName = "table_test")
public class Test {
    @Meaning("测试字段")
    private String test;
}
```
## 使用方法 ##
pom文件中修改
```
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.3.7</version>
    <configuration>
        <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
        <verbose>true</verbose>
        <overwrite>true</overwrite>
    </configuration>
    <!-- 修改后的逆向工程 -->
    <dependencies>
        <dependency>
            <groupId>generator</groupId>
            <artifactId>generator</artifactId>
            <version>1.1</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/mybatis-generator-core-1.1.jar</systemPath>
        </dependency>
     </dependencies>
</plugin>
```       
