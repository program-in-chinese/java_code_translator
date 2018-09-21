package com.codeinchinese.java.源码翻译;

import java.util.ArrayList;
import java.util.List;

public class 命名处理 {

  /**
   * 按驼峰命名拆分, 如有下划线也拆分
   * 
   * 如遇连续大写, 如: getUPCCode, 拆为['get', 'UPC', 'Code']
   * 
   * @param 英文命名
   * @return 返回所有字段, 包括下划线. 如 MyProduct_bought: ['My', 'Product', '_', 'bought']
   */
  public static List<String> 拆分Java命名(String 英文命名) {
    String 下划线后 = 英文命名;
    List<String> 下划线分隔字段 = new ArrayList<>();
    String 下划线前 = "";
    while (下划线后.indexOf("_") > -1) {
      下划线前 = 下划线后.substring(0, 下划线后.indexOf("_"));
      下划线后 = 下划线后.substring(下划线后.indexOf("_") + 1);
      if (!下划线前.isEmpty()) {
        下划线分隔字段.add(下划线前);
      }
      下划线分隔字段.add("_");
    }
    if (!下划线后.isEmpty()) {
      下划线分隔字段.add(下划线后);
    }

    List<String> 单词列表 = new ArrayList<>();
    for (String 字段 : 下划线分隔字段) {
      String[] 按驼峰命名拆分 = 拆分骆驼命名(字段);
      for (String 驼峰分段 : 按驼峰命名拆分) {
        单词列表.add(驼峰分段);
      }
    }
    return 单词列表;
  }

  /**
   * @param s 输入一个骆驼法的命名, 如 aCamelString
   * @return 拆分开的单词, 即[a, Camel, String]
   */
  public static String[] 拆分骆驼命名(String s) {
    return s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
  }
}
