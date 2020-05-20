package com.bamboo.test;

/**
 * @url
 * @desc
 * @example
 */
public class Nqueen {

  public static void getQueen(int n) {
    int[] queen = new int[n];
    for (int r=0; r<n; r++) {
      queen[r] = -1;
    }

    boolean[] col = new boolean[n];
    boolean[] left = new boolean[2*n-1];
    boolean[] right = new boolean[2*n-1];

    backTrace(0, n, queen, col, left, right);
  }



//  queen[r] = c
//
//  row:r, col:c Q
//
//  col[c]
//  left[l]  l = j-i+n-1    max:2n-2
//  right[r] r = i+j        max:2n-2
//
//  backtrace(r)

  public static void backTrace(int r, int n, int[] queen, boolean[] col, boolean[] left, boolean[] right) {
    if (r == n) {
      print(queen);
      return;
    }

    for (int c=0; c<n; c++) {
      if (!col[c] && !left[c-r+n-1] && !right[r+c]) {
        queen[r] = c;
        col[c] = left[c-r+n-1] = right[r+c] = true;
        backTrace(r+1, n, queen, col, left, right);
        col[c] = left[c-r+n-1] = right[r+c] = false;
        queen[r] = -1;
      }
    }

  }

  public static void print(int[] queen) {
    char[] str = new char[queen.length];
    for (int i = 0; i < str.length; i++) {
      str[i] = '.';
    }

    for (int r=0; r<queen.length; r++) {
      str[queen[r]] = 'Q';
      System.out.println(str);
      str[queen[r]] = '.';
    }
    System.out.println();
  }

  public static void main(String[] args) {
    getQueen(8);
  }



}
