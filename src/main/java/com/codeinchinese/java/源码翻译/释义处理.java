package com.codeinchinese.java.源码翻译;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.codeinchinese.功用.词典.词典常量;
import com.codeinchinese.英汉词典.词条;

public class 释义处理 {

  static final String 词性_计算机 = "[计]";

  // "uid","abbr. 用户名（User Identifier）；用户界面设计（User Interface Design）"
  public static LinkedHashMap<String, List<String>> 分词性(词条 某词条) {
    LinkedHashMap<String, List<String>> 词性到释义 = new LinkedHashMap<>();
    for (String 释义 : 某词条.中文释义) {
      String 除去词性 = 释义;
      String 当前词性 = "";
      for (String 词性 : 词典常量.词性) {
        if (释义.indexOf(词性) == 0) {
          当前词性 = 词性;
          除去词性 = 释义.substring(词性.length()).trim();
          break;
        }
      }
      // 按分号分隔词义
      String[] 词义 = 除去词性.split("；");
      List<String> 此词性的释义 = new ArrayList<>();
      for (String 某词义 : 词义) {
        此词性的释义.add(某词义);
      }
      词性到释义.put(当前词性, 此词性的释义);
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
      Entry<String, List<String>> 首个词性含义 = 词性到释义.entrySet().iterator().next();
      List<String> 首批词义 = 首个词性含义.getValue();
      首选词义 = 首批词义.get(0);
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
  // TODO: 清除方括号内容. "凭本身的资格[权利]"
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
