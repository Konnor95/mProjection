package com.mprojection.entity.task;

import com.mprojection.util.Translator;

public class UserTask {

    private String id;
    private String name;
    private String description;
    private Long executor;
    private Long target;
    private String hash;

    public UserTask(Task task, Translator translator, String locale) {
        id = task.getId();
        name = task.getName(translator, locale);
        description = task.getDescription(translator, locale);
        executor = task.getExecutor();
        target = task.getTarget();
        hash = task.getHash();
    }

    public UserTask(UserTask userTask) {
        id = userTask.getId();
        name = userTask.getName();
        description = userTask.getDescription();
        executor = userTask.getExecutor();
        target = userTask.getTarget();
        hash = userTask.getHash();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getExecutor() {
        return executor;
    }

    public void setExecutor(Long executor) {
        this.executor = executor;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
