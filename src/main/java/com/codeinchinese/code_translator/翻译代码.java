package com.codeinchinese.code_translator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codeinchinese.英汉词典.英汉词典;
import com.codeinchinese.英汉词典.词条;

/**
 * Hello world!
 *
 */
public class 翻译代码
{
  // 测试源码: https://github.com/swaroopch/byte-of-python/blob/master/programs/oop_simplestclass.py
  /*
class Person:
    pass  # An empty block

p = Person()
print(p)
   */
  
  private static final String 词性_计算机 = "[计]";
  private static final String 词性_名词 = "n.";
  
  private static final String 例程1 = "class Person:\n" +
      "    pass  # An empty block\n" +
      "\n" +
      "p = Person()\n" +
      "print(p)";
  
  // TODO: 暂不识别注释, 保留原有所有空格, 如上: 一 空 块
  // p -- 缩写词 暂时忽略, 不翻译?
  // print -- 有[计] DOS外部命令:在打印机上打印文件, 但最好用自定义的
    public static void main( String[] args )
    {
      List<String> 命名 = Arrays.asList("class", "Person", "pass", "An", "empty",
          "block", "print", "p");// 取所有命名(例程1);
      String 结果 = 例程1;
      for (String 某命名 : 命名) {
        结果 = 结果.replaceAll(某命名, 首选释义(某命名));
      }
      System.out.println(结果);
      /*
        System.out.println(首选释义("Person"));
        System.out.println(首选释义("class"));
        System.out.println(首选释义("print"));*/
    }
    
    public static String 首选释义(String 英文) {
      
      // TODO: 大写没有时再查询小写?
      词条 详细 = 英汉词典.查词(英文.toLowerCase());
      System.out.println(详细);
      List<String> 中文词义 = 详细.中文释义;
      if (中文词义.size() == 0) {
        return null;
      }
      // TODO: 分段0为词性 n. 等等. 需enum
      String 首选词义 = null;
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
