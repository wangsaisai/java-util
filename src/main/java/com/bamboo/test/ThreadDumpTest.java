package com.bamboo.learn;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

/**
 * @url
 * @desc
 * @example
 */
public class ThreadDumpTest {


  public static void main(String[] args) throws Exception {

    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

    for(ThreadInfo threadInfo : threadInfos) {
      System.out.println("[" + threadInfo.getThreadName() + ", " + threadInfo.getThreadId() + "]");
    }

  }

}
