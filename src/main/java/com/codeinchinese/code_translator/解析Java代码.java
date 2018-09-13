package com.codeinchinese.code_translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.PropertySource;

public class 解析Java代码 {

  static Map<String, String> 字典 = new HashMap<>();
  static String 源码 = "package com.company.example;\n" +
      "\n" +
      "import java.io.Serializable;\n" +
      "\n" +
      "public class Person implements Serializable {\n" +
      "\n" +
      "    private static final long serialVersionUID = 1L;\n" +
      "    private final Integer id;\n" +
      "    private String firstName;\n" +
      "    private String lastName;\n" +
      "\n" +
      "    public Integer getId() {\n" +
      "        return id;\n" +
      "    }\n" +
      "\n" +
      "    public String getFirstName() {\n" +
      "        return firstName;\n" +
      "    }\n" +
      "\n" +
      "    public void setFirstName(String firstName) {\n" +
      "        this.firstName = firstName;\n" +
      "    }\n" +
      "\n" +
      "    public String getLastName() {\n" +
      "        return lastName;\n" +
      "    }\n" +
      "\n" +
      "    public void setLastName(String lastName) {\n" +
      "        this.lastName = lastName;\n" +
      "    }\n" +
      "\n" +
      "    public Person(java.lang.Integer id) {\n" +
      "        this.id = id;\n" +
      "    }\n" +
      "}\n" +
      "";

  static {
    字典.put("id", "号");
    字典.put("firstName", "名");
    字典.put("lastName", "姓");
    字典.put("getId", "取号");
    字典.put("get名", "取名");
  }
  public static void main(String[] 参数) {
    JavaClassSource 分析结果 = Roaster.parse(JavaClassSource.class, 源码);

    List<PropertySource<JavaClassSource>> 属性 = 分析结果.getProperties();
    for (PropertySource<JavaClassSource> 某属性 : 属性) {
        String 属性名 = 某属性.getName();
        某属性.setName(字典.containsKey(属性名) ? 字典.get(属性名) : 属性名);
    }
    /*
    List<FieldSource<JavaClassSource>> 域 = 分析结果.getFields();
    for (FieldSource<JavaClassSource> 某域 : 域) {
      //if (!某方法.isConstructor()) {
        String 域名 = 某域.getName();
        某域.setName(字典.containsKey(域名) ? 字典.get(域名) : 域名);
      //}
    }
    
    List<MethodSource<JavaClassSource>> 方法 = 分析结果.getMethods();
    for (MethodSource<JavaClassSource> 某方法 : 方法) {
      if (!某方法.isConstructor()) {
        String 方法名 = 某方法.getName();
        某方法.setName(字典.containsKey(方法名) ? 字典.get(方法名) : 方法名);
      }
    }*/
    System.out.println(分析结果);
  }
}
