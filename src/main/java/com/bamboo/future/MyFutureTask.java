package com.bamboo.future;


import java.util.concurrent.Callable;

// another implementation of java.util.concurrent.FutureTask
public class MyFutureTask<V> implements Runnable {

  private Callable<V> callable;

  private V result;

  private volatile String state; // new, fail, complete

  public MyFutureTask(Callable<V> callable) {
    this.callable = callable;
    this.state = "new";
  }

  public V get() throws InterruptedException {
    while ("new".equals(state)) {
      synchronized (this) {
        this.wait();
      }
    }

    return result;
  }

  public boolean isComplete() {
    return "complete".equals(state);
  }

  @Override
  public void run() {

    try {
      result = callable.call();
      this.state = "complete";
    } catch (Exception e) {
      e.printStackTrace();
      this.state = "fail";
    }

    synchronized (this) {
      this.notifyAll();
    }

  }
}
