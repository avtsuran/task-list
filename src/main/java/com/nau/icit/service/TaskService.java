package com.nau.icit.service;

import com.nau.icit.model.Task;

import java.util.Comparator;

public class TaskService implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if(t1.getPriority() > t2.getPriority()) {
            return -1;
        } else if(t1.getPriority() < t2.getPriority()){
            return 1;
        } else return 0;
    }
}
