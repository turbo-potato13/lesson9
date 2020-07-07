package com.vtb.kortunov.lesson9;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[100000000];
        long time = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100000);
        }
        System.out.println("Array Created Time: " + (System.currentTimeMillis() - time));
        //Task 1
        DemoRecursiveTask demoRecursiveTask = new DemoRecursiveTask(array);

        long timeFork = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(demoRecursiveTask);
        System.out.println("Lead Time RecursiveTask : " + (System.currentTimeMillis() - timeFork));

        //Lead Time RecursiveTask = 4051

        int max = 0;
        long timeSingle = System.currentTimeMillis();
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println("Lead Time Single : " + (System.currentTimeMillis() - timeSingle));
        //Lead Time Single Thread = 33

        //Task 2
        long timeStream = System.currentTimeMillis();
        Arrays.stream(array)
                .max();
        System.out.println("Lead Time Stream : " + (System.currentTimeMillis() - timeStream));
        //Lead Time Stream = 57

        long timeParallelStream = System.currentTimeMillis();
        Arrays.stream(array)
                .parallel()
                .max();
        System.out.println("Lead Time ParallelStream : " + (System.currentTimeMillis() - timeParallelStream));
        //Lead Time ParallelStream = 26

        //Task 3
        //The fastest is a parallel stream. Lead time 26
    }


}
