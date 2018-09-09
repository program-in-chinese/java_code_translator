package com.codeinchinese.code_translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class 查词Test {

  @Test
  public void test() {
    assertEquals(翻译代码.首选释义("class"), "类别");
  }

}
