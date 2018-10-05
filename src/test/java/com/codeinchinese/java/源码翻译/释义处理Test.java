package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.codeinchinese.英汉词典.词条;

public class 释义处理Test {

  @Test
  public void 分词性() {
    词条 词 = new 词条();
    词.中文释义 = Arrays.asList("abbr. 用户名（User Identifier）；用户界面设计（User Interface Design）");
    Map<String, List<String>> 词性到释义 = 释义处理.分词性(词);
    assertEquals(1, 词性到释义.size());
    List<String> 释义 = 词性到释义.get("abbr.");
    assertEquals(2, 释义.size());
  }

  @Test
  public void 消除括号内容() {
    assertEquals("M类别", 释义处理.消除括号内容("M类别"));
    assertEquals("下载", 释义处理.消除括号内容("下载（download的复数）"));
    assertEquals("一", 释义处理.消除括号内容("一(个)"));
  }

  @Test
  public void 首选() {
    词条 词 = new 词条();
    词.中文释义 = Arrays.asList("abbr. 用户名（User Identifier）；用户界面设计（User Interface Design）");
    assertEquals("用户名", 释义处理.首选("UID", 词));
  }
}
