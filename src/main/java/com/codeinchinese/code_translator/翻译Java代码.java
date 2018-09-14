package com.codeinchinese.code_translator;

import java.util.HashMap;
import java.util.Map;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.PropertySource;

import com.codeinchinese.功用.文件功用;

public class 翻译Java代码 {

  static Map<String, String> 字典 = new HashMap<>();
  static {
    字典.put("id", "号");
    字典.put("fullName", "全名");
    字典.put("getId", "取号");

    字典.put("Integer", "整型");
    字典.put("String", "字符串");

    字典.put("Person", "人");
  }

  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("测试.java");
    JavaClassSource 分析结果 = Roaster.parse(JavaClassSource.class, 源码);

    // 汉化类名
    分析结果.setName(查词(分析结果.getName()));

    // 汉化Bean属性名, 以及属性的类型名
    for (PropertySource<JavaClassSource> 某属性 : 分析结果.getProperties()) {
      String 属性名 = 某属性.getName();
      某属性.setName(查词(属性名));

      String 属性类型名 = 某属性.getType().getName();
      某属性.setType(查词(属性类型名));
    }

    System.out.println(分析结果);
  }

  private static String 查词(String 英文) {
    return 字典.containsKey(英文) ? 字典.get(英文) : 英文;
  }
}
