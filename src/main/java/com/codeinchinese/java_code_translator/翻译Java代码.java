package com.codeinchinese.java_code_translator;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;

import com.codeinchinese.功用.文件功用;

public class 翻译Java代码 {

  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("MProduct.java");
    JavaClassSource 分析结果 = Roaster.parse(JavaClassSource.class, 源码);

    // 汉化类名
    分析结果.setName(查词(分析结果.getName()));

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
    
    //System.out.println(分析结果);
  }

  private static String 查词(String 英文) {
    String 释义 = 命名翻译.翻译命名(英文);
    return 释义;
  }
}
