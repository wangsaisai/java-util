package com.bamboo.test;

public class TryCatchFinallyTest {

  // return 1
  public static int test1() {
    int i = 0;
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      i = 1;
      return i;
    } finally {
      i = 2;
    }
  }

  // return 2
  public static int test1_1() {
    int i = 0;
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      i = 1;
      return i;
    } finally {
      i = 2;
      return i;
    }
  }

  // return 1
  public static Integer test2() {
    Integer i = 0;
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      i = 1;
      return i;
    } finally {
      i = 2;
    }
  }

  // return 2
  public static IntValue test3() {
    IntValue iv = new IntValue();
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      iv.i = 1;
      return iv;
    } finally {
      iv.i = 2;
    }
  }

  // return 1
  public static IntValue test4() {
    IntValue iv = new IntValue();
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      iv.i = 1;
      return iv;
    } finally {
      iv = new IntValue();
      iv.i = 2;
    }
  }

  public static void main(String[] args) {
//    System.out.println(System.currentTimeMillis());
//    System.out.println("sss \n xx");
//    System.out.println(
//        new MessageFormat("metasource service client error : \n{0}\n").format(new Object[]{"xxx"}));

    System.out.println(test1());  // 1
    System.out.println(test1_1());  // 2
    System.out.println(test2());  // 1
    System.out.println(test3().i);  // 2
    System.out.println(test4().i);  // 1


  }

  static class IntValue {
    int i;
  }

}
