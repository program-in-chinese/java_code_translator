package com.codeinchinese.功用;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class 文件功用 {
  public static String 取源文件文本(String 文件名) throws Exception {
    InputStream 输入流 =
        文件功用.class.getClassLoader().getResourceAsStream(文件名);
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    while ((length = 输入流.read(buffer)) != -1) {
        result.write(buffer, 0, length);
    }
    return result.toString("UTF-8");
  }

}
