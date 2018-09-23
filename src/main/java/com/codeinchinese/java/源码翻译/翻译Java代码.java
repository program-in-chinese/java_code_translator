package com.codeinchinese.java.源码翻译;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.jboss.forge.roaster.ParserException;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.PropertySource;

import com.codeinchinese.功用.文件功用;

public class 翻译Java代码 {

  private static final HashSet<String> 已翻译属性方法 = new HashSet<>();
  // 仅为测试用, 避免载入全部词汇, 也不需拆分命名
  static final HashMap<String, String> 内置字典 = new HashMap<>();

  static final HashMap<String, String> 关键词字典 = new HashMap<>();

  static {
    关键词字典.put("void", "void");
  }

  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("测试.java");

    System.out.println(翻译源码结构(源码));
    翻译源码结构(文件功用.取源文件文本("MProduct.java"));
  }
  
  public static String 翻译源码结构(String 源码) {
    JavaClassSource 类结构;
    try {
      类结构 = 取类结构(源码);
    } catch (ParserException e) {
      return e.getLocalizedMessage();
    }

    翻译类(类结构);
    翻译属性(类结构);
    翻译方法(类结构);
    
    return 类结构.toUnformattedString();
  }

  static void 翻译类(JavaClassSource 类结构) {
    类结构.setName(查词(类结构.getName()));
  }

  static void 翻译属性(JavaClassSource 类结构) {
    List<PropertySource<JavaClassSource>> 属性 = 类结构.getProperties();
    // 翻译Bean属性名, 以及属性的类型名
    // 所有getXX/setXX方法中的XX也被识别为属性, 无论是否有对应域
    for (PropertySource<JavaClassSource> 某属性 : 属性) {
      String 属性名 = 某属性.getName();
      
      // 如果无相关Field, 假设驼峰命名, 如果第二个字母也是大写, 则大写首字母
      // 否则, getUOMPrecision中, 提取出的属性名为uOMPrecision
      if (!某属性.hasField() && 属性名.length() > 1 && Character.isUpperCase(属性名.charAt(1))) {
        属性名 = 属性名.substring(0, 1).toUpperCase() + 属性名.substring(1);
      }
      try {
        //System.out.println("属性名: " + 属性名);
        String 翻译属性名 = 查词(属性名);
        某属性.setName(翻译属性名);
        已翻译属性方法.add("get" + 翻译属性名);
        已翻译属性方法.add("set" + 翻译属性名);
      } catch (IllegalArgumentException e) {
        System.out.println("不合法属性名: " + 属性名);
      }

      // TODO: 需特别处理[] - ArrayType等, 或者<>
      Type<JavaClassSource> 类型 = 某属性.getType();
      //List<Type<JavaClassSource>> 类型参数 = 类型.getTypeArguments();
      // TODO: 单元测试 -
    //类型.isArray()
      //类型.isParameterized()
      /*for(Type<JavaClassSource> 某参数 : 类型参数) {
        System.out.println(某参数.getName());
        
      }*/
      
      /*
      if (类型 instanceof ParameterizedType) {
        List 类型参数 = ((ParameterizedType)类型).typeArguments();
      }*/
      某属性.setType(翻译类型(类型));
    }
  }

  static void 翻译方法(JavaClassSource 类结构) {
    List<MethodSource<JavaClassSource>> 方法 = 类结构.getMethods();
    for (MethodSource<JavaClassSource> 某方法 : 方法) {
      // 构造方法已随类型名重命名, 且无返回类型
      if (!某方法.isConstructor()) {
        String 方法名 = 某方法.getName();

        if (已翻译属性方法.contains(方法名)) {
          continue;
        }
        try {
          String 翻译方法名 = 查词(方法名);
          System.out.println("方法名: " + 方法名 + " -> " + 翻译方法名);
          某方法.setName(翻译方法名);
        } catch (IllegalArgumentException e) {
          System.out.println("不合法方法名: " + 方法名);
        }
        // TODO: get方法已随属性名改变了返回类型, 如Integer getId()
        if (!某方法.isReturnTypeVoid()) {
          Type<JavaClassSource> 方法类型 = 某方法.getReturnType();
          某方法.setReturnType(翻译类型(方法类型));
        }

        //System.out.println(方法类型.getName() + " 数组维度: " + 方法类型.getArrayDimensions() + " type arg:" + 方法类型.getTypeArguments());
      }

    }
  }

  static String 翻译类型(Type<JavaClassSource> 类型) {
    String 返回类型 = 类型.getName();
    String 提取数组类型 = 返回类型;
    if (!关键词字典.containsKey(返回类型)) {
      int 数组维度 = 类型.getArrayDimensions();
      提取数组类型 = 提取数组类型.substring(0, 提取数组类型.length() - 2 * 数组维度 );
      提取数组类型 = 查词(提取数组类型);
      for (int i = 0; i < 数组维度; i++) {
        提取数组类型 += "[]";
      }
    }
    return 提取数组类型;
  }

  static JavaClassSource 取类结构(String 源码) throws ParserException {
    try {
      return Roaster.parse(JavaClassSource.class, 源码);
    } catch (ParserException e) {
      throw e;
    }
  }

  private static String 查词(String 英文) {
    if (内置字典.containsKey(英文)) {
      return 内置字典.get(英文);
    }
    String 释义 = 命名翻译.翻译命名(英文);
    return 释义;
  }
}
