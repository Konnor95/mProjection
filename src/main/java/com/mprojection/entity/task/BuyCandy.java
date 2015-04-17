package com.mprojection.entity.task;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.util.TaskManager;

public class BuyCandy extends Task {

    public BuyCandy() {
        super("2", "task.buyCandy.name", "task.buyCandy.description");
    }

    @Override
    public void complete(FullUserInfo executor, FullUserInfo target) {
        executor.setHp((int) (executor.getHp() * 1.1));
    }

    @Override
    public void setTarget(TaskManager taskManager) {
        setTarget(taskManager.findNearestUser(getExecutor()));
    }

}
