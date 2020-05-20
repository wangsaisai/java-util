package com.bamboo.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @url
 * @desc
 * @example
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

  int start;
  int end;
  int[] data;

  int limit = 3;

  public MyRecursiveTask(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  @Override
  protected Integer compute() {
    if (end - start <= limit) {
      System.out.println("split..." + Thread.currentThread().getName());
      int sum = 0;
      for (int i=start; i<end; i++)
        sum += data[i];

      return sum;
    }

    int mid = (start + end)/2;
    MyRecursiveTask left = new MyRecursiveTask(start, mid, data);
    MyRecursiveTask right = new MyRecursiveTask(mid, end, data);

    left.fork();
    right.fork();

    return left.join() + right.join();
  }

  public static void main(String[] args) throws Exception {
    ForkJoinPool pool = new ForkJoinPool(5);

    int[] data = new int[100];
    for (int i = 0; i < data.length; i++) {
      data[i] = i;
    }

    Integer result = pool.invoke(new MyRecursiveTask(0, data.length, data));
    System.out.println(result);


//    pool.awaitTermination(10, TimeUnit.SECONDS);
    pool.shutdown();

  }
}
