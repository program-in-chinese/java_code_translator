package com.codeinchinese.演示;

import java.util.Arrays;
import java.util.List;

import com.codeinchinese.java源码翻译.命名翻译;

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
  
  // TODO: 暂不识别注释, 保留原有所有空格, 如上: 一 空 块
  // p -- 缩写词 暂时忽略, 不翻译?
  // print -- 有[计] DOS外部命令:在打印机上打印文件, 但最好用自定义的
    public static void main( String[] args )
    {
      List<String> 命名 = Arrays.asList("class", "Person", "pass", "An", "empty",
          "block", "print", "p");// 取所有命名(例程1);
      String 结果 = 例程1;
      for (String 某命名 : 命名) {
        结果 = 结果.replaceAll(某命名, 命名翻译.首选释义(某命名));
      }
      System.out.println(结果);
      /*
        System.out.println(首选释义("Person"));
        System.out.println(首选释义("class"));
        System.out.println(首选释义("print"));*/
    }
    

}
