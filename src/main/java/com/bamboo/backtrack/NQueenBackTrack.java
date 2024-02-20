package com.bamboo.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueenBackTrack extends BackTrack<Integer> {
  int n;

  boolean[][] data;
  boolean[] col;
  boolean[] diag1;
  boolean[] diag2;

  List<boolean[][]> result;

  int ri;

  public NQueenBackTrack(int n) {
    this.n = n;
    data = new boolean[n][n];
    col = new boolean[n];
    diag1 = new boolean[2*n-1];
    diag2 = new boolean[2*n-1];
    result = new ArrayList<>();
  }

  @Override
  boolean isFinish() {
    return ri == n;
  }

  @Override
  void saveResult() {
    boolean[][] tmp = new boolean[n][n];
    for (int i=0; i<n; i++) {
      System.arraycopy(data[i], 0, tmp[i], 0, n);
    }
    result.add(tmp);
  }


  @Override
  boolean verifyState(Integer s) {
    return !col[s] && !diag1[ri + s] && !diag2[s - ri + n -1];
  }

  @Override
  void applyState(Integer s) {
    data[ri][s] = true;
    col[s] = true;
    diag1[ri + s] = true;
    diag2[s - ri + n -1] = true;

    ri++;
  }

  @Override
  void rollbackState(Integer s) {
    ri--;

    data[ri][s] = false;
    col[s] = false;
    diag1[ri + s] = false;
    diag2[s - ri + n -1] = false;
  }

  @Override
  List<Integer> getStates() {
    return IntStream.range(0, n).boxed().collect(Collectors.toList());
  }

  public static void main(String[] args) {
    NQueenBackTrack q = new NQueenBackTrack(4);
    q.backtrack();

    // 打印的结果
    String print = """
      [
        [
          [ false true false false ]
          [ false false false true ]
          [ true false false false ]
          [ false false true false ]
        ]
        [
          [ false false true false ]
          [ true false false false ]
          [ false false false true ]
          [ false true false false ]
        ]
      ]
      """;
    print(q.result);
  }

  public static void print(List<boolean[][]> result) {
    System.out.println("[");
    for (boolean[][] e : result) {
      System.out.println("  [");
      for (boolean[] row : e) {
        System.out.print("    [ ");
        for (boolean col : row) {
          System.out.print(col + " ");
        }
        System.out.println("]");
      }
      System.out.println("  ]");
    }
    System.out.println("]");
  }
}
