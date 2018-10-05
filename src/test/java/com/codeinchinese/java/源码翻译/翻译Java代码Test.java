package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;
import org.junit.BeforeClass;
import org.junit.Test;

public class 翻译Java代码Test {

  @BeforeClass
  public static void setupClass() {
    翻译Java代码.内置字典.put("Class1", "类1");
    翻译Java代码.内置字典.put("PropertyType1", "属性类1");
    翻译Java代码.内置字典.put("MethodType1", "方法返回类型1");
    翻译Java代码.内置字典.put("property1", "属性1");
    翻译Java代码.内置字典.put("PROPERTY1", "全大写属性1");
    翻译Java代码.内置字典.put("method1", "方法1");
    翻译Java代码.内置字典.put("long", "长1");
    翻译Java代码.内置字典.put("serialVersionUID", "串行版本UID");
  }

  @Test
  public void 翻译类() {
    String 源码 = "public class Class1 {}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译类(类结构);
    assertEquals("类1", 类结构.getName());
  }

  @Test
  public void 翻译数组类型属性() {
    String 源码 = "public class Class1 {\n" +
        "    private final PropertyType1[] property1;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);

    assertEquals("属性1", 属性.getName());
    assertEquals("属性类1[]", 属性.getType().getName());
  }

  @Test
  public void 翻译属性() {
    String 源码 = "public class Class1 {\n" +
        "    private final PropertyType1 property1;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);

    assertEquals("属性1", 属性.getName());
    assertEquals("属性类1", 属性.getType().getName());
  }

  @Test
  public void 翻译静态字段() {
    String 源码 = "public class Class1 {\n" +
        "    private static final PropertyType1 property1;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译字段(类结构);
    List<FieldSource<JavaClassSource>> 属性列表 = 类结构.getFields();
    assertEquals(1, 属性列表.size());
    FieldSource<JavaClassSource> 属性 = 属性列表.get(0);

    assertEquals("属性1", 属性.getName());
    assertEquals("属性类1", 属性.getType().getName());
  }

  @Test
  public void 翻译内置静态字段() {
    String 源码 = "public class Class1 {\n" +
        "    private static final long serialVersionUID = 1L;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译字段(类结构);
    List<FieldSource<JavaClassSource>> 属性列表 = 类结构.getFields();
    assertEquals(1, 属性列表.size());
    FieldSource<JavaClassSource> 属性 = 属性列表.get(0);

    assertEquals("串行版本UID", 属性.getName());
    assertEquals("长1", 属性.getType().getName());
  }

  @Test
  public void 翻译属性方法() {
    String 源码 = "public class Class1 {\n"
        + "    public MethodType1 getProperty1() {\n"
        + "        return null;"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);
    assertEquals("属性1", 属性.getName());
    
    assertEquals("public class Class1 {\n"
        + "    public 方法返回类型1 get属性1() {\n"
        + "        return null;"
        + "    }\n"
        + "}", 类结构.toUnformattedString());
  }

  @Test
  public void 翻译大写属性() {
    String 源码 = "public class Class1 {\n"
        + "    private final PropertyType1 PROPERTY1;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);
    assertEquals("全大写属性1", 属性.getName());

    assertEquals("public class Class1 {\n"
        + "    private final 属性类1 全大写属性1;}",
        类结构.toUnformattedString());
  }

  @Test
  public void 翻译大写属性方法() {
    String 源码 = "public class Class1 {\n"
        + "    public MethodType1 getPROPERTY1() {\n"
        + "        return null;"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);
    assertEquals("全大写属性1", 属性.getName());
    
    assertEquals("public class Class1 {\n"
        + "    public 方法返回类型1 get全大写属性1() {\n"
        + "        return null;"
        + "    }\n"
        + "}", 类结构.toUnformattedString());
  }

  @Test
  public void 翻译方法() {
    String 源码 = "public class Class1 {\n"
        + "    public MethodType1 method1() {\n"
        + "        return 0;\n"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译方法(类结构);
    List<MethodSource<JavaClassSource>> 方法列表 = 类结构.getMethods();
    assertEquals(1, 方法列表.size());
    MethodSource<JavaClassSource> 方法 = 方法列表.get(0);
    assertEquals("方法1", 方法.getName());

    assertEquals("方法返回类型1", 方法.getReturnType().getName());
  }

  @Test
  public void 翻译数组返回类型方法() {
    String 源码 = "public class Class1 {\n"
        + "    public MethodType1[] method1() {\n"
        + "        return 0;\n"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译方法(类结构);
    List<MethodSource<JavaClassSource>> 方法列表 = 类结构.getMethods();
    assertEquals(1, 方法列表.size());
    MethodSource<JavaClassSource> 方法 = 方法列表.get(0);

    assertEquals("方法返回类型1[]", 方法.getReturnType().getName());
  }

  @Test
  public void 不翻译void() {
    String 源码 = "public class Class1 {\n"
        + "    public void method1() {\n"
        + "        return;\n"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.翻译方法(类结构);
    List<MethodSource<JavaClassSource>> 方法列表 = 类结构.getMethods();
    assertEquals(1, 方法列表.size());
    MethodSource<JavaClassSource> 方法 = 方法列表.get(0);
    assertEquals("方法1", 方法.getName());

    assertEquals("void", 方法.getReturnType().getName());
  }

  @Test
  public void 语法错误代码() throws Exception {
    String 源码 = "{}";
    String 错误信息 = "Could not find type declaration in Java source - is this actually code?";
    assertEquals(错误信息, 翻译Java代码.翻译源码结构(源码));
  }
}
