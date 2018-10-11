package com.codeinchinese.java.源码翻译;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.codeinchinese.功用.文件功用;

public class 翻译示例代码Test {

  @Test
  public void 翻译测试代码() throws Exception {
    String 目标代码 = "package com.company.example;\n" +
        "\n" +
        "import java.io.Serializable;\n" +
        "\n" +
        "public class 人 implements Serializable {\n" +
        "\n" +
        "    private static final 长1 串行版本UID = 1L;\n" +
        "    private final 整数 识别;\n" +
        "    private 字符串 完整名称;\n" +
        "    private 字符串[] 拜访国家;\n" +
        "\n" +
        "    public 整数 get识别() {\n" +
        "        return 识别;\n" +
        "    }\n" +
        "\n" +
        "    public 字符串 get完整名称() {\n" +
        "        return 完整名称;\n" +
        "    }\n" +
        "\n" +
        "    public void set完整名称(字符串 完整名称) {\n" +
        "        this.完整名称 = 完整名称;\n" +
        "    }\n" +
        "\n" +
        "    public 人(java.lang.Integer id) {\n" +
        "        this.id = id;\n" +
        "    }\n" +
        "    \n" +
        "    private 人[] get所有的孩子() {\n" +
        "      return null;\n" +
        "    }\n" +
        "    \n" +
        "    private 列表 get一些孩子() {\n" +
        "      return null;\n" +
        "    }\n" +
        "}\n";
    String 源码 = 文件功用.取源文件文本("测试.java");
    String 翻译代码 = 翻译Java代码.翻译源码结构(源码);
    // System.out.println(翻译代码);
    assertEquals(目标代码, 翻译代码);
  }

}
