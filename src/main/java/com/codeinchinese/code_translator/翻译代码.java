package com.codeinchinese.code_translator;

import java.util.Arrays;
import java.util.List;

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
  private static final String 例程1 = "class Person:\n" +
      "    pass  # An empty block\n" +
      "\n" +
      "p = Person()\n" +
      "print(p)";
  
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
      String 首批词义 = 中文词义.get(0);
      String[] 分段 = 首批词义.split(" ");
      // TODO: 分段0为词性 n. 等等. 需enum
      String 首选词义 = null;
      if (分段.length == 1) {
        首选词义 = 分段[0];
      }
      else {
        首选词义 = 分段[1];
      }
      
      // TODO: 确保它是中文词语(不包含任何特殊字符, 语义正确等等?)
      if (首选词义.indexOf(",") > -1) {
        return 首选词义.substring(0, 首选词义.indexOf(","));
      }
      return 首选词义;
    }
}
