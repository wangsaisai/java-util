package com.bamboo.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : wangsai
 * @date : Created in 2021/6/30
 * @description :
 */
public class SimDateFormatThreadSafeTest {

    private static DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");

    private static Object obj = new Object();



    public static void main(String[] args) throws InterruptedException {
        String str = "20210226";
        String str2 = "2021-02-26";
        CountDownLatch count = new CountDownLatch(1000);
        Runnable r = ()->{
            String format = LocalDate.parse(str, dateTimeFormatter1).format(dateTimeFormatter2);
            String format2 = LocalDate.parse(str2, dateTimeFormatter2).format(dateTimeFormatter1);
            System.out.println("DateTimeFormatter format:"+format+" format2"+format2);
            count.countDown();
        };
        Runnable r2 = ()->{
            try {
//                synchronized (obj) {
                    System.out.println("simpleDateFormat:"+simpleDateFormat2.format(simpleDateFormat.parse(str2)));
                    System.out.println("simpleDateFormat:"+simpleDateFormat.format(simpleDateFormat2.parse(str)));
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                count.countDown();
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,1000L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(1000));
        for(int i = 0; i < 500; i++) {
            threadPoolExecutor.execute(r);
            threadPoolExecutor.execute(r2);
        }
        threadPoolExecutor.shutdown();
        count.await();
    }

}
