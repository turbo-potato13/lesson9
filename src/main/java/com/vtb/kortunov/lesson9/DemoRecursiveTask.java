package com.vtb.kortunov.lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DemoRecursiveTask extends RecursiveTask<Integer> {

    private final int[] array;

    public DemoRecursiveTask(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        int max = 0;
        if (array.length > 2) {
            List<DemoRecursiveTask> subtasks = createSubtasks();
            for (DemoRecursiveTask subtask : subtasks) {
                subtask.fork();
            }
            for (DemoRecursiveTask subtask : subtasks) {
                if (subtask.join() > max) {
                    max = subtask.join();
                }
            }
        } else {
            for (int value : array) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    List<DemoRecursiveTask> createSubtasks() {
        return new ArrayList<>(Arrays.asList(
                new DemoRecursiveTask(Arrays.copyOfRange(array, 0, array.length / 2)),
                new DemoRecursiveTask(Arrays.copyOfRange(array, array.length / 2, array.length))
        ));

    }
}





