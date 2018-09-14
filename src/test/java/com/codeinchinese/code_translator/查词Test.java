package com.codeinchinese.code_translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class 查词Test {

  @Test
  public void test() {
    assertEquals(命名翻译.首选释义("class"), "类别");
    assertEquals(命名翻译.首选释义("nosuchword"), "nosuchword");
  }

}
