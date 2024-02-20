package com.bamboo.backtrack;

import java.util.List;

// 定义抽象类， 也可以使用接口+default方法
public abstract class BackTrack<S> {

  abstract boolean verifyState(S s);

  abstract void applyState(S s);

  abstract void rollbackState(S s);

  abstract List<S> getStates();

  abstract boolean isFinish();

  abstract void saveResult();

  // 回溯算法模板
  void backtrack() {
    // 判断是否满足终止条件
    if (isFinish()) {
      // 保存结果并返回
      saveResult();
      return;
    }

    // 遍历所有可行的选择
    getStates().forEach(s -> {
      // 验证选择 - 剪枝
      if (verifyState(s)) {
        // 做出选择 - 更新辅助数据结构
        applyState(s);
        // 递归调用回溯函数
        backtrack();
        // 撤销选择 - 还原辅助数据结构
        rollbackState(s);
      }
    });
  }

}
