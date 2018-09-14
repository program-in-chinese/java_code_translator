package com.codeinchinese.code_translator;

import java.util.HashMap;
import java.util.List;
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
  }
  public static void main(String[] 参数) throws Exception {
    String 源码 = 文件功用.取源文件文本("测试.java");
    JavaClassSource 分析结果 = Roaster.parse(JavaClassSource.class, 源码);

    List<PropertySource<JavaClassSource>> 属性 = 分析结果.getProperties();
    for (PropertySource<JavaClassSource> 某属性 : 属性) {
        String 属性名 = 某属性.getName();
        某属性.setName(字典.containsKey(属性名) ? 字典.get(属性名) : 属性名);
    }
    System.out.println(分析结果);
  }
}
