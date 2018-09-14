package com.codeinchinese.java_code_translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// 耗时长 (需载入词典)
public class 命名翻译Test {

  @Test
  public void 首选释义() {
    assertEquals(命名翻译.首选释义("class"), "类别");
    assertEquals(命名翻译.首选释义("my"), "我的");
    assertEquals(命名翻译.首选释义("nosuchword"), "nosuchword");
  }

  @Test
  public void 翻译命名() {
    assertEquals(命名翻译.翻译命名("MyClass"), "我的类别");
    assertEquals(命名翻译.翻译命名("forClass"), "for类别");
    assertEquals(命名翻译.翻译命名("MClass"), "M类别");
  }
}
