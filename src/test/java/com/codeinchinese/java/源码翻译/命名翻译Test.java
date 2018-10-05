package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// 耗时长 (需载入词典)
public class 命名翻译Test {

  @Test
  public void 首选释义() {
    assertTrue("尚未查词class", !命名翻译.已查词典.containsKey("class"));
    assertEquals("类别", 命名翻译.首选释义("class"));
    assertTrue("已查词class", 命名翻译.已查词典.containsKey("class"));
    
    assertEquals("类别", 命名翻译.首选释义("Class"));
    assertEquals("我的", 命名翻译.首选释义("my"));
    assertEquals("我的", 命名翻译.首选释义("My"));
    assertEquals("nosuchword", 命名翻译.首选释义("nosuchword"));
  }

  @Test
  public void 首选释义_术语词典() {
    assertEquals("个例", 命名翻译.首选释义("instance"));
    assertEquals("用户名", 命名翻译.首选释义("UID"));

    // 大写
    assertEquals("个例", 命名翻译.首选释义("Instance"));
  }

  @Test
  public void 翻译命名() {
    assertEquals("我的类别", 命名翻译.翻译命名("MyClass"));
    assertEquals("拜访国家", 命名翻译.翻译命名("visitedCountries"));

    // TODO: 应置于术语词典
    assertEquals("连续的版本用户名", 命名翻译.翻译命名("serialVersionUID"));

    // 不需翻译字段
    assertEquals("for类别", 命名翻译.翻译命名("forClass"));

    // 单字符分段
    assertEquals("M类别", 命名翻译.翻译命名("MClass"));
  }

}
