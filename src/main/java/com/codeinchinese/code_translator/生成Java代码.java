package com.codeinchinese.code_translator;
import java.io.Serializable;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

public class 生成Java代码 {

  public static void main(String[] 参数) {
    final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
    javaClass.setPackage("com.company.example").setName("Person");

    javaClass.addInterface(Serializable.class);
    javaClass.addField()
      .setName("serialVersionUID")
      .setType("long")
      .setLiteralInitializer("1L")
      .setPrivate()
      .setStatic(true)
      .setFinal(true);

    javaClass.addProperty(Integer.class, "id").setMutable(false);
    javaClass.addProperty(String.class, "firstName");
    javaClass.addProperty("String", "lastName");

    javaClass.addMethod()
      .setConstructor(true)
      .setPublic()
      .setBody("this.id = id;")
      .addParameter(Integer.class, "id");
    
    System.out.println(javaClass);
  }
}
