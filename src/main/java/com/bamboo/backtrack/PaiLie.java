package com.bamboo.backtrack;

import java.util.ArrayList;
import java.util.List;

public class PaiLie {

  /* 回溯算法：全排列 */
  static void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
    // 当状态长度等于元素数量时，记录解
    if (state.size() == choices.length) {
      res.add(new ArrayList<Integer>(state));
      return;
    }
    // 遍历所有选择
    for (int i = 0; i < choices.length; i++) {
      int choice = choices[i];
      // 剪枝：不允许重复选择元素
      if (!selected[i]) {
        // 尝试：做出选择，更新状态
        selected[i] = true;
        state.add(choice);
        // 进行下一轮选择
        backtrack(state, choices, selected, res);
        // 回退：撤销选择，恢复到之前的状态
        selected[i] = false;
        state.remove(state.size() - 1);
      }
    }
  }

  /* 全排列 */
  static List<List<Integer>> permutationsI(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    backtrack(new ArrayList<Integer>(), nums, new boolean[nums.length], res);
    return res;
  }

  public static void main(String[] args) {
    print(permutationsI(new int[]{1, 2, 3}));
  }

  public static void print(List<List<Integer>> result) {
    System.out.println("[");
    for (List<Integer> e : result) {
      System.out.print("  [ ");
      for (Integer e2 : e) {
        System.out.print(e2 + " ");
      }
      System.out.println("]");
    }
    System.out.println("]");
  }

}
