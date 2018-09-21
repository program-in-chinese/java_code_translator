package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.codeinchinese.java.源码翻译.命名处理;

public class 命名处理Test {

  @Test
  public void 拆分Java命名() {
    assertEquals(Arrays.asList("class"), 命名处理.拆分Java命名("class"));
    assertEquals(Arrays.asList("my", "Class"), 命名处理.拆分Java命名("myClass"));
    assertEquals(Arrays.asList("My", "Class"), 命名处理.拆分Java命名("MyClass"));
    assertEquals(Arrays.asList("ABC", "Name"), 命名处理.拆分Java命名("ABCName"));
    assertEquals(Arrays.asList("get", "ABC", "Name"), 命名处理.拆分Java命名("getABCName"));
    assertEquals(Arrays.asList("get", "UOM", "Precision"), 命名处理.拆分Java命名("getUOMPrecision"));
    assertEquals(Arrays.asList("My", "Product", "_", "bought"), 命名处理.拆分Java命名("MyProduct_bought"));
    assertEquals(Arrays.asList("Product", "_"), 命名处理.拆分Java命名("Product_"));
    assertEquals(Arrays.asList("_", "Product"), 命名处理.拆分Java命名("_Product"));
  }

}
