package com.mprojection.entity.task;


import com.mprojection.entity.FullUserInfo;
import com.mprojection.util.TaskManager;
import com.mprojection.util.Translator;

public abstract class Task {

    private String id = "";
    private String nameKey;
    private String descriptionKey;
    private Long executor;
    private Long target;
    private String hash;

    protected Task(String id, String nameKey, String descriptionKey) {
        this.id = id;
        this.nameKey = nameKey;
        this.descriptionKey = descriptionKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(Translator translator, String locale) {
        return translator.translate(nameKey, locale);
    }

    public String getDescription(Translator translator, String locale) {
        return formatDescription(translator, translator.translate(descriptionKey, locale), locale);
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

    public abstract void complete(FullUserInfo executor, FullUserInfo target);

    public abstract void setTarget(TaskManager taskManager);

    protected String formatDescription(Translator translator, String description, String locale) {
        return description;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
