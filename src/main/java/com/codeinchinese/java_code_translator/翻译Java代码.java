package com.codeinchinese.java_code_translator;

import java.util.List;

import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;

import com.codeinchinese.功用.文件功用;

public class 翻译Java代码 {

  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("MProduct.java");

    long 时间戳 = System.currentTimeMillis();
    System.out.println(汉化源码结构(源码));
    System.out.println(System.currentTimeMillis() - 时间戳);
    时间戳 = System.currentTimeMillis();
    System.out.println(汉化源码结构(文件功用.取源文件文本("测试.java")));
    System.out.println(System.currentTimeMillis() - 时间戳);
  }
  
  public static String 汉化源码结构(String 源码) {
    JavaClassSource 分析结果;
    try {
      分析结果 = Roaster.parse(JavaClassSource.class, 源码);
    } catch (ParserException e) {
      return e.getLocalizedMessage();
    }

    // 汉化类名
    分析结果.setName(查词(分析结果.getName()));
    /*
    List<FieldSource<JavaClassSource>> 域 = 分析结果.getFields();
    for (FieldSource<JavaClassSource> 某域 : 域) {
        String 域名 = 某域.getName();
        某域.setName(查词((域名));
    }*/
    
    // 汉化Bean属性名, 以及属性的类型名
    for (PropertySource<JavaClassSource> 某属性 : 分析结果.getProperties()) {
      String 属性名 = 某属性.getName();
      
      // 需要大写首字母, 因为getUOMPrecision中, 提取出的属性名为uOMPrecision
      属性名 = 属性名.substring(0, 1).toUpperCase() + 属性名.substring(1);
      try {
        System.out.println("属性名: " + 属性名);
        某属性.setName(查词(属性名));
      } catch (IllegalArgumentException e) {
        System.out.println("不合法属性名: " + 属性名);
      }

      // TODO: 需特别处理[]等, 或者<>
      String 属性类型名 = 某属性.getType().getName();
      某属性.setType(查词(属性类型名));
    }
    
    List<MethodSource<JavaClassSource>> 方法 = 分析结果.getMethods();
    for (MethodSource<JavaClassSource> 某方法 : 方法) {
      if (!某方法.isConstructor()) {
        String 方法名 = 某方法.getName();
        try {
          System.out.println("方法名: " + 方法名);
          某方法.setName(查词(方法名));
        } catch (IllegalArgumentException e) {
          System.out.println("不合法方法名: " + 方法名);
        }
      }
    }
    return 分析结果.toString();
  }

  private static String 查词(String 英文) {
    String 释义 = 命名翻译.翻译命名(英文);
    return 释义;
  }
}
