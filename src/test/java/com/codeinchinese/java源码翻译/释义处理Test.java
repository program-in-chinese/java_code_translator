package com.codeinchinese.java源码翻译;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.codeinchinese.java源码翻译.释义处理;

public class 释义处理Test {

  @Test
  public void 分词性() {
    
  }

  @Test
  public void 消除括号内容() {
    assertEquals("M类别", 释义处理.消除括号内容("M类别"));
    assertEquals("下载", 释义处理.消除括号内容("下载（download的复数）"));
    assertEquals("一", 释义处理.消除括号内容("一(个)"));
  }

}
