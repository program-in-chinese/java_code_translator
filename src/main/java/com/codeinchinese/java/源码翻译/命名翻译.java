package com.codeinchinese.java.源码翻译;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codeinchinese.英汉词典.英汉词典;
import com.codeinchinese.英汉词典.词条;

public class 命名翻译 {

  private static final String 词性_名词 = "n.";
  private static final Set<String> 不需翻译的词汇 = new HashSet<>(Arrays.asList("to", "for", "of"));

  private static final Map<String, String> 术语词典 = new HashMap<>();
  static {
    术语词典.put("is", "为");
    术语词典.put("get", "获取");
    术语词典.put("set", "设置");
    术语词典.put("has", "具有");
    术语词典.put("instance", "个例");
    术语词典.put("env", "环境");
  }

  public static String 翻译命名(String 英文命名) {
    List<String> 命名拆分 = 命名处理.拆分Java命名(英文命名);
    String 中文命名 = "";
    for (String 拆分 : 命名拆分) {
      中文命名 += 拆分.equals("_") ? "_" : 首选释义(拆分);
    }

    // 过滤所有特殊字符
    中文命名 = 中文命名.replaceAll("[\\-\\+\\.\\^:,<>]", "");
    //System.out.println(" -> " + 中文命名);
    return 中文命名;
  }

  /**
   * 如找不到释义, 返回原词
   * 
   * @param 英文
   * @return
   */
  public static String 首选释义(String 英文) {

    // 优先根据內建词典查词
    if (术语词典.containsKey(英文)) {
      return 术语词典.get(英文);
    } else {
      String 小写 = 英文.toLowerCase();
      if (术语词典.containsKey(小写)) {
        return 术语词典.get(小写);
      }
    }
    // 无视所有单字符的字段, 由于歧义太大
    if (英文.length() == 1 || 不需翻译(英文)) {
      return 英文;
    }
    // 大写没有时再查询小写
    词条 详细 = 英汉词典.查词(英文);
    if (详细 == null) {
      详细 = 英汉词典.查词(英文.toLowerCase());
    }
    if (详细 == null) {
      return 英文;
    }

    return 释义处理.首选(英文, 详细);
  }


  private static boolean 不需翻译(String 英文) {
    return 不需翻译的词汇.contains(英文);
  }
}
