package com.bamboo.test;

import java.lang.ref.WeakReference;

/**
 * @url
 * @desc
 * @example
 */
public class WeakReferenceTest {

  static class Weak1 extends WeakReference<Object> {
    Object value;
    Weak1(Object key, Object value) {
      super(key);
      this.value = value;
    }
  }

  public static void main(String[] args) {
    Weak1 w = new Weak1(new Object(), new Object());
    System.out.println(w.get() + " ---- " + w.value);
    System.gc();
    System.out.println(w.get() + " ---- " + w.value);

    WeakReference<Object> r0 = new WeakReference<>(new Object());
    System.gc();
    System.out.println(r0.get());

    WeakReference<Weak1> r = new WeakReference<>(w);
    w = null;
    System.gc();
    System.out.println(r.get());

    // output
//    java.lang.Object@330bedb4 ---- java.lang.Object@2503dbd3
//    null ---- java.lang.Object@2503dbd3
//    null
//    null
  }

}
