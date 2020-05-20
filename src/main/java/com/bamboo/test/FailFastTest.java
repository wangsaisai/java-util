package com.bamboo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @url
 * @desc
 * @example
 */
public class FailFastTest {

  public static void test1(List<Integer> list) {
    for (Integer i: list) {
      if (i.equals(1)) {
        list.remove(i);
      }
    }
  }

  public static void test2(List<Integer> list) {
    for (Integer i: list) {
      if (i.equals(2)) {
        list.remove(i);
      }
    }
  }

  public static void test3(List<Integer> list) {
    for (Integer i: list) {
      if (i.equals(3)) {
        list.remove(i);
      }
    }
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);

//    test1(list);  //ConcurrentModificationException
//    test2(list);  //ok
//    test3(list);  //ConcurrentModificationException
  }

}
