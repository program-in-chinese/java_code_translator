package com.codeinchinese.java源码翻译;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codeinchinese.英汉词典.词条;

public class 释义处理 {

  static final String 词性_计算机 = "[计]";

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

  static String 首选(String 原词, 词条 详细) {
    
    // TODO: 预处理, 获取原型, 比如downloads->download, has->have等
    List<String> 中文词义 = 详细.中文释义;
    if (中文词义.size() == 0) {
      return 原词;
    }

    // TODO: 分段0为词性 n. 等等. 需enum
    String 首选词义 = 原词;
    Map<String, List<String>> 词性到释义 = 分词性(详细);

    if (词性到释义.containsKey(词性_计算机)) {
      首选词义 = 词性到释义.get(词性_计算机).get(0);
    } else {
      // 第一批的首个
      String 首批词义 = 中文词义.get(0);
      String[] 分段 = 首批词义.split(" ");
      if (分段.length == 1) {
        首选词义 = 分段[0];
      } else {
        首选词义 = 分段[1];
      }
    }

    // TODO: 确保它是中文词语(不包含任何特殊字符, 语义正确等等?)
    if (首选词义.indexOf(",") > -1) {
      首选词义 = 首选词义.substring(0, 首选词义.indexOf(","));
    } else if (首选词义.indexOf(";") > -1) {
      首选词义 = 首选词义.substring(0, 首选词义.indexOf(";"));
    }

    首选词义 = 释义处理.消除括号内容(首选词义);
    return 首选词义;
  }

  // TODO: 提取非外部词典部分, 测试太耗时; 或者缩减英汉词典为测试专用
  static String 消除括号内容(String 中文释义) {
    String 清理后 = 消除括号内容(中文释义, "（", "）");
    清理后 = 消除括号内容(清理后, "(", ")");
    return 清理后;
  }

  static String 消除括号内容(String 中文释义, String 开括号, String 闭括号) {
    int 开括号位置 = 中文释义.indexOf(开括号);
    int 闭括号位置 = 中文释义.indexOf(闭括号);
    if (开括号位置 == -1 || 闭括号位置 == -1) {
      return 中文释义;
    }
    String 括号内容 = 中文释义.substring(开括号位置, 闭括号位置 + 1);
    return 中文释义.replace(括号内容, "");
  }

}
