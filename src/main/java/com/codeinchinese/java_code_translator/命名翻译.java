package com.codeinchinese.java_code_translator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.codeinchinese.英汉词典.英汉词典;
import com.codeinchinese.英汉词典.词条;

public class 命名翻译 {

  private static final String 词性_计算机 = "[计]";
  private static final String 词性_名词 = "n.";
  private static final Set<String> 不需翻译的词汇 = new HashSet<>(Arrays.asList("to", "for", "of"));

  private static final Map<String, String> 术语词典 = new HashMap<>();
  static {
    术语词典.put("is", "为");
    术语词典.put("get", "获取");
    术语词典.put("set", "设置");
    术语词典.put("instance", "个例");
  }

  public static String 翻译命名(String 英文命名) {
    System.out.print(英文命名);
    List<String> 命名拆分 = 命名处理.拆分Java命名(英文命名);
    String 中文命名 = "";
    for (String 拆分 : 命名拆分) {
      中文命名 += 拆分.equals("_") ? "_" : 首选释义(拆分);
    }
    
    // 过滤所有特殊字符
    中文命名 = 中文命名.replaceAll("[\\-\\+\\.\\^:,<>]","");
    System.out.println(" -> " + 中文命名);
    return 中文命名;
  }

  /**
   * 如找不到释义, 返回原词
   * @param 英文
   * @return
   */
  public static String 首选释义(String 英文) {

    // 优先根据內建词典查词
    if (术语词典.containsKey(英文)) {
      return 术语词典.get(英文);
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

    // TODO: 预处理, 获取原型, 比如downloads->download, has->have等
    
    List<String> 中文词义 = 详细.中文释义;
    if (中文词义.size() == 0) {
      return 英文;
    }
    
    // TODO: 分段0为词性 n. 等等. 需enum
    String 首选词义 = 英文;
    Map<String, List<String>> 词性到释义 = 分词性(详细);
    
    if (词性到释义.containsKey(词性_计算机)) {
      首选词义 = 词性到释义.get(词性_计算机).get(0);
    } else {
      // 第一批的首个
      String 首批词义 = 中文词义.get(0);
      String[] 分段 = 首批词义.split(" ");
      if (分段.length == 1) {
        首选词义 = 分段[0];
      }
      else {
        首选词义 = 分段[1];
      }
    }

    // TODO: 确保它是中文词语(不包含任何特殊字符, 语义正确等等?)
    if (首选词义.indexOf(",") > -1) {
      首选词义 = 首选词义.substring(0, 首选词义.indexOf(","));
    } else if (首选词义.indexOf(";") > -1) {
      首选词义 = 首选词义.substring(0, 首选词义.indexOf(";"));
    }

    首选词义 = 消除括号内容(首选词义);
    return 首选词义;
  }

  public static Map<String, List<String>> 分词性(词条 某词条) {
    Map<String, List<String>> 词性到释义 = new HashMap<>();
    for (String 释义 : 某词条.中文释义) {
      String[] 分段 = 释义.split(" ");
      if (分段.length > 0 && 分段[0].length() >= 2) {
        String 词性 = 分段[0];
        if (分段[0].matches("[a-z]+\\.") || 分段[0].matches("\\[.*\\]")) {
          List<String> 此词性的释义 = new ArrayList<>();
          for (int i = 1; i < 分段.length; i++) {
            此词性的释义.add(分段[1]);
          }
          词性到释义.put(词性, 此词性的释义);
        }
      }
    }
    return 词性到释义;
  }

  static String 消除括号内容(String 中文释义) {
    int 开括号位置 = 中文释义.indexOf("（");
    int 闭括号位置 = 中文释义.indexOf("）");
    if (开括号位置 == -1 || 闭括号位置 == -1) {
      return 中文释义;
    }
    String 括号内容 = 中文释义.substring(开括号位置, 闭括号位置 + 1);
    return 中文释义.replace(括号内容, "");
  }

  private static boolean 不需翻译(String 英文) {
    return 不需翻译的词汇.contains(英文);
  }
}
