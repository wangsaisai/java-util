package com.bamboo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @url
 * @desc
 * @example
 */
public class MyRecursiveAction extends RecursiveAction {

  int start;
  int end;
  int[] data;

  int limit = 3;

  public MyRecursiveAction(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  @Override
  protected void compute() {
    if (end - start <= limit) {
      System.out.println("split..." + Thread.currentThread().getName());
      for (int i=start; i<end; i++)
        System.out.println(data[i]);

      return;
    }

    int mid = (start + end)/2;
    MyRecursiveAction left = new MyRecursiveAction(start, mid, data);
    MyRecursiveAction right = new MyRecursiveAction(mid, end, data);

    left.fork();
    right.fork();

  }

  public static void main(String[] args) throws Exception {
    ForkJoinPool pool = new ForkJoinPool(5);

    int[] data = new int[100];
    for (int i = 0; i < data.length; i++) {
      data[i] = i;
    }
    pool.submit(new MyRecursiveAction(0, data.length, data));

    pool.awaitTermination(10, TimeUnit.SECONDS);
    pool.shutdown();

  }
}
