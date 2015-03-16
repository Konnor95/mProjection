package com.mprojection.entity.task;

import com.mprojection.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Tasks {

    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
            new FindAlly(), new BuyCandy()
    ));

    @Autowired
    private Translator translator;


    public List<UserTask> define(List<Task> tasks, String locale) {
        List<UserTask> userTasks = new ArrayList<>();
        for (Task task : tasks) {
            userTasks.add(new UserTask(task, translator, locale));
        }
        return userTasks;
    }

    public Task define(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public UserTask define(String id, String locale) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return new UserTask(task, translator, locale);
            }
        }
        return null;
    }

}
