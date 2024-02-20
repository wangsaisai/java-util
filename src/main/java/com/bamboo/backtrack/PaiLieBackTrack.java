package com.bamboo.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaiLieBackTrack extends BackTrack<Integer> {

  int[] nums;
  boolean[] selected;
  List<Integer> state;
  List<List<Integer>> result;

  public PaiLieBackTrack(int[] nums) {
    this.nums = nums;
    selected = new boolean[nums.length];
    result = new ArrayList<>();
    state = new ArrayList<>();
  }

  @Override
  public boolean verifyState(Integer s) {
    return !selected[s];
  }

  @Override
  void applyState(Integer s) {
    selected[s] = true;
    state.add(nums[s]);
  }

  @Override
  void rollbackState(Integer s) {
    selected[s] = false;
    state.remove(state.size() - 1);
  }

  @Override
  List<Integer> getStates() {
    return IntStream.range(0, nums.length).boxed().collect(Collectors.toList());
  }

  @Override
  boolean isFinish() {
    return state.size() == nums.length;
  }

  @Override
  void saveResult() {
    result.add(new ArrayList<>(state));
  }

  public static void main(String[] args) {
    PaiLieBackTrack track = new PaiLieBackTrack(new int[]{1,2,3});
    track.backtrack();

    // 打印的结果
    String print = """
        [
          [ 1 2 3 ]
          [ 1 3 2 ]
          [ 2 1 3 ]
          [ 2 3 1 ]
          [ 3 1 2 ]
          [ 3 2 1 ]
        ]
      """;
    print(track.result);
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
