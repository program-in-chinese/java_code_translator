package com.codeinchinese.java_code_translator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// 耗时长 (需载入词典)
public class 命名翻译Test {

  @Test
  public void 首选释义() {
    assertEquals("类别", 命名翻译.首选释义("class"));
    assertEquals("类别", 命名翻译.首选释义("Class"));
    assertEquals("我的", 命名翻译.首选释义("my"));
    assertEquals("我的", 命名翻译.首选释义("My"));
    assertEquals("nosuchword", 命名翻译.首选释义("nosuchword"));
    
    // 术语词典
    assertEquals("个例", 命名翻译.首选释义("instance"));
    assertEquals("个例", 命名翻译.首选释义("Instance"));
  }

  @Test
  public void 翻译命名() {
    assertEquals("我的类别", 命名翻译.翻译命名("MyClass"));

    // 不需翻译字段
    assertEquals("for类别", 命名翻译.翻译命名("forClass"));

    // 单字符分段
    assertEquals("M类别", 命名翻译.翻译命名("MClass"));
  }

}
