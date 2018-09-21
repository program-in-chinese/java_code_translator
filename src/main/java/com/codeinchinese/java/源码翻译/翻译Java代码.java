package com.codeinchinese.java.源码翻译;

import java.util.HashMap;
import java.util.List;

import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;

import com.codeinchinese.功用.文件功用;

public class 翻译Java代码 {

  static final HashMap<String, String> 内置字典 = new HashMap<>();
  static {
    // TODO: 添加内置字典
  }

  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("测试.java");

    long 时间戳 = System.currentTimeMillis();
    System.out.println(汉化源码结构(源码));
    System.out.println(System.currentTimeMillis() - 时间戳);
    时间戳 = System.currentTimeMillis();
    System.out.println(汉化源码结构(文件功用.取源文件文本("测试.java")));
    System.out.println(System.currentTimeMillis() - 时间戳);
  }
  
  public static String 汉化源码结构(String 源码) {
    JavaClassSource 类结构 = 取类结构(源码);
    try {
      类结构 = Roaster.parse(JavaClassSource.class, 源码);
    } catch (ParserException e) {
      return e.getLocalizedMessage();
    }

    汉化类(类结构);
    汉化属性(类结构);
    汉化方法(类结构);
    
    return 类结构.toString();
  }

  static void 汉化类(JavaClassSource 类结构) {
    类结构.setName(查词(类结构.getName()));
  }

  static void 汉化属性(JavaClassSource 类结构) {
    // 汉化Bean属性名, 以及属性的类型名
    // 所有getXX/setXX方法中的XX也被识别为属性, 无论是否有对应域
    for (PropertySource<JavaClassSource> 某属性 : 类结构.getProperties()) {
      String 属性名 = 某属性.getName();
      
      // 需要大写首字母, 因为getUOMPrecision中, 提取出的属性名为uOMPrecision
      属性名 = 属性名.substring(0, 1).toUpperCase() + 属性名.substring(1);
      try {
        System.out.println("属性名: " + 属性名);
        某属性.setName(查词(属性名));
      } catch (IllegalArgumentException e) {
        System.out.println("不合法属性名: " + 属性名);
      }

      // TODO: 需特别处理[] - ArrayType等, 或者<>
      Type<JavaClassSource> 类型 = 某属性.getType();
      List<Type<JavaClassSource>> 类型参数 = 类型.getTypeArguments();
      // TODO: 单元测试 -
    //类型.isArray()
      //类型.isParameterized()
      for(Type<JavaClassSource> 某参数 : 类型参数) {
        System.out.println(某参数.getName());
        
      }
      
      /*
      if (类型 instanceof ParameterizedType) {
        List 类型参数 = ((ParameterizedType)类型).typeArguments();
      }*/
      String 属性类型名 = 类型.getName();
      某属性.setType(查词(属性类型名));
    }
  }

  static void 汉化方法(JavaClassSource 类结构) {
    List<MethodSource<JavaClassSource>> 方法 = 类结构.getMethods();
    for (MethodSource<JavaClassSource> 某方法 : 方法) {
      // 构造方法已随类型名重命名, 且无返回类型
      if (!某方法.isConstructor()) {
        String 方法名 = 某方法.getName();
        try {
          System.out.println("方法名: " + 方法名);
          某方法.setName(查词(方法名));
        } catch (IllegalArgumentException e) {
          System.out.println("不合法方法名: " + 方法名);
        }
        
        // TODO: get方法已随属性名改变了返回类型, 如Integer getId()
        Type<JavaClassSource> 方法类型 = 某方法.getReturnType();
        System.out.println(方法类型.getName() + " 数组维度: " + 方法类型.getArrayDimensions() + " type arg:" + 方法类型.getTypeArguments());
      }

    }
  }

  static JavaClassSource 取类结构(String 源码) throws ParserException {
    try {
      return Roaster.parse(JavaClassSource.class, 源码);
    } catch (ParserException e) {
      throw e;
    }
  }

  // TODO: 添加字典缓存
  private static String 查词(String 英文) {
    if (内置字典.containsKey(英文)) {
      return 内置字典.get(英文);
    }
    String 释义 = 命名翻译.翻译命名(英文);
    return 释义;
  }
}
