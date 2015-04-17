package com.mprojection.entity.task;


import com.mprojection.entity.FullUserInfo;
import com.mprojection.util.TaskManager;

public class FindAlly extends Task {

    public FindAlly() {
        super("1", "task.findAlly.name", "task.findAlly.description");
    }

    @Override
    public void complete(FullUserInfo executor, FullUserInfo target) {

    }

    @Override
    public void setTarget(TaskManager taskManager) {
        setTarget(taskManager.findNearestUser(getExecutor()));
    }
}
