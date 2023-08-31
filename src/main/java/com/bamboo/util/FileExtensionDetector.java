package com.bamboo.util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileExtensionDetector {

  public static final byte[] MAGIC_ZIP = {0x50, 0x4B};
  public static final byte[] MAGIC_TAR = {0x75, 0x73, 0x74, 0x61, 0x72, 0x00};
  public static final byte[] MAGIC_TAR_GZ = {0x1F, (byte) 0x8B, 0x08, 0x00};

  public static final int MAGIC_TAR_INDEX = 257;

  public static String getFileExtension(byte[] data) {
    // ZIP 文件
    if (matchMagicNumber(0, data, MAGIC_ZIP)) {
      return ".zip";
    }

    if (matchMagicNumber(MAGIC_TAR_INDEX, data, MAGIC_TAR)) {
      return ".tar";
    }

    // TAR.GZ 文件
    if (matchMagicNumber(0, data, MAGIC_TAR_GZ)) {
      return ".tar.gz";
    }

    // 未知文件类型
    return null;
  }

  private static boolean matchMagicNumber(int startIndex, byte[] data, byte[] magic) {
    if (data.length < startIndex + magic.length) {
      return false;
    }

    for (int i = 0; i < magic.length; i++) {
      if (data[startIndex + i] != magic[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) throws Exception {
    System.out.println(getFileExtension(Files.readAllBytes(Paths.get("/tmp/x1.tar.gz"))));
    System.out.println(getFileExtension(Files.readAllBytes(Paths.get("/tmp/x2.tar"))));
    System.out.println(getFileExtension(Files.readAllBytes(Paths.get("/tmp/x3.zip"))));
  }
}
