package com.bamboo.cache;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import org.apache.commons.lang3.concurrent.Computable;

public class Memorizer<A, V> implements Computable<A, V> {

  private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();

  private final Computable<A, V> c;

  public Memorizer(Computable<A, V> c) {
    this.c = c;
  }

  public V compute(final A arg) throws InterruptedException {
    while (true) {
      Future<V> f = cache.get(arg);
      if (f == null) {
        FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
        f = cache.putIfAbsent(arg, ft);
        if (f == null) {
          f = ft;
          ft.run();
        }
      }

      try {
        return f.get();
      } catch (CancellationException e) {
        cache.remove(arg, f);
      } catch (ExecutionException e) {
        //
        throw new RuntimeException(e);
      }
    }
  }
}
