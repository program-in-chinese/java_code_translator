package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
    翻译Java代码.内置字典.put("method1", "方法1");
  }

  @Test
  public void 汉化类() {
    String 源码 = "public class Class1 {}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.汉化类(类结构);
    assertEquals("类1", 类结构.getName());
  }

  @Test
  public void 汉化属性() {
    String 源码 = "public class Class1 {\n" +
        "    private final PropertyType1 property1;}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.汉化属性(类结构);
    List<PropertySource<JavaClassSource>> 属性列表 = 类结构.getProperties();
    assertEquals(1, 属性列表.size());
    PropertySource<JavaClassSource> 属性 = 属性列表.get(0);

    // TODO: 首字母大写导致此问题
    assertEquals("Property1", 属性.getName());
    assertEquals("属性类1", 属性.getType().getName());
  }

  @Test
  public void 汉化方法() {
    String 源码 = "public class Class1 {\n"
        + "    public MethodType1 method1() {\n"
        + "        return 0;\n"
        + "    }\n"
        + "}";
    JavaClassSource 类结构 = 翻译Java代码.取类结构(源码);
    翻译Java代码.汉化方法(类结构);
    List<MethodSource<JavaClassSource>> 方法列表 = 类结构.getMethods();
    assertEquals(1, 方法列表.size());
    MethodSource<JavaClassSource> 属性 = 方法列表.get(0);
    assertEquals("方法1", 属性.getName());

    // TODO: 翻译返回类型
    assertEquals("MethodType1", 属性.getReturnType().getName());
  }

}
