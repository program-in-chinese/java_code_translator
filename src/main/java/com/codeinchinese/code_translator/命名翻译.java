package com.codeinchinese.code_translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codeinchinese.英汉词典.英汉词典;
import com.codeinchinese.英汉词典.词条;

public class 命名翻译 {

  private static final String 词性_计算机 = "[计]";
  private static final String 词性_名词 = "n.";

  /**
   * 如找不到释义, 返回原词
   * @param 英文
   * @return
   */
  public static String 首选释义(String 英文) {

    // TODO: 大写没有时再查询小写?
    词条 详细 = 英汉词典.查词(英文.toLowerCase());
    if (详细 == null) {
      return 英文;
    }
    // System.out.println(详细);
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
      // TODO: 第一批的首个?
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
      return 首选词义.substring(0, 首选词义.indexOf(","));
    } else if (首选词义.indexOf(";") > -1) {
      return 首选词义.substring(0, 首选词义.indexOf(";"));
    }
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
}
