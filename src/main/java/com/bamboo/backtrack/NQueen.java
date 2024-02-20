package com.bamboo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

  static void backtrack(int row, int n, boolean[][] cur, boolean[] col, boolean[] diag1, boolean[] diag2, List<boolean[][]> res) {
    if (row == n) {
      boolean[][] tmp = new boolean[n][n];
      for (int i = 0; i < n; i++) {
        System.arraycopy(cur[i], 0, tmp[i], 0, n);
      }
      res.add(tmp);
      return;
    }

    for (int c = 0; c < n; c++) {
      int i1 = row + c;
      int i2 = c - row + n - 1;
      if (!col[c] && !diag1[i1] && !diag2[i2]) {
        cur[row][c] = true;
        col[c] = true;
        diag1[i1] = true;
        diag2[i2] = true;
        backtrack(row + 1, n, cur, col, diag1, diag2, res);
        cur[row][c] = false;
        col[c] = false;
        diag1[i1] = false;
        diag2[i2] = false;
      }
    }
  }

  static List<boolean[][]> nqueen(int n) {
    boolean[][] cur = new boolean[n][n];
    boolean[] col = new boolean[n];
    boolean[] diag1 = new boolean[2 * n - 1];
    boolean[] diag2 = new boolean[2 * n - 1];

    List<boolean[][]> res = new ArrayList<>();
    backtrack(0, n, cur, col, diag1, diag2, res);
    return res;
  }

  public static void main(String[] args) {
    List<boolean[][]> n1 = nqueen(1);
    List<boolean[][]> n2 = nqueen(2);
    List<boolean[][]> n3 = nqueen(3);
    List<boolean[][]> n4 = nqueen(4);
    print(n1);
    System.out.println();
    print(n2);
    System.out.println();
    print(n3);
    System.out.println();
    print(n4);
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
