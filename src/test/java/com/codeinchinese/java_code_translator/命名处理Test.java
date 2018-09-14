package com.codeinchinese.java_code_translator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class 命名处理Test {

  @Test
  public void 拆分Java命名() {
    assertEquals(命名处理.拆分Java命名("class"), Arrays.asList("class"));
    assertEquals(命名处理.拆分Java命名("myClass"), Arrays.asList("my", "Class"));
    assertEquals(命名处理.拆分Java命名("MyClass"), Arrays.asList("My", "Class"));
    assertEquals(命名处理.拆分Java命名("ABCName"), Arrays.asList("ABC", "Name"));
    assertEquals(命名处理.拆分Java命名("getABCName"), Arrays.asList("get", "ABC", "Name"));
    assertEquals(命名处理.拆分Java命名("getUOMPrecision"), Arrays.asList("get", "UOM", "Precision"));
    assertEquals(命名处理.拆分Java命名("MyProduct_bought"), Arrays.asList("My", "Product", "_", "bought"));
    assertEquals(命名处理.拆分Java命名("Product_"), Arrays.asList("Product", "_"));
    assertEquals(命名处理.拆分Java命名("_Product"), Arrays.asList("_", "Product"));
  }

}
