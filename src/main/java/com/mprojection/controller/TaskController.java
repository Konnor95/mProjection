package com.mprojection.controller;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.task.Task;
import com.mprojection.entity.task.Tasks;
import com.mprojection.entity.task.UserTask;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.ObjectSerializer;
import com.mprojection.service.UserService;
import com.mprojection.util.PushManagerUtil;
import com.mprojection.util.Result;
import com.mprojection.util.TaskManager;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/")
public class TaskController {

    @Autowired
    private UserService userService;

    @Autowired
    private Translator translator;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private PushManagerUtil pushManagerUtil;

    private ObjectSerializer serializer = new JSONSerializer();

    @Autowired
    private Tasks tasks;

    @RequestMapping(value = "{userId}/", method = RequestMethod.GET)
    public List<UserTask> getUserTasks(@PathVariable long userId, Integer measureUnit) {
        FullUserInfo fullUserInfo = userService.get(userId, MeasureUnit.define(measureUnit));
        return userService.getActiveTasks(fullUserInfo);
    }

    @RequestMapping(value = "{userId}/{taskId}/", method = RequestMethod.GET)
    public UserTask addUserTask(@PathVariable long userId, @PathVariable String taskId, Integer measureUnit) {
        FullUserInfo executor = userService.get(userId, MeasureUnit.define(measureUnit));
        Task task = tasks.define(taskId);
        task.setExecutor(userId);
        task.setTarget(taskManager);
        task.setHash(getHash(task.getExecutor(), task.getTarget()));
        userService.addTask(task);
        UserTask userTask = tasks.define(taskId, executor.getLang());
        UserTask userTask2 = new UserTask(userTask);
        userTask2.setExecutor(userTask.getTarget());
        userTask2.setTarget(userTask.getExecutor());
        FullUserInfo target = userService.get(userId, MeasureUnit.define(measureUnit));
        sendTask(executor, userTask);
        sendTask(target, userTask2);
        return userTask;
    }

    @RequestMapping(value = "{userId}/{taskId}/{hash}", method = RequestMethod.PUT)
    public List<UserTask> completeTask(@PathVariable long userId, @PathVariable String taskId, @PathVariable String hash, Integer measureUnit) {
        FullUserInfo executor = userService.get(userId, MeasureUnit.define(measureUnit));
        Task task = userService.getActiveTask(executor, taskId);
        if (!checkHash(getHash(task.getExecutor(), task.getTarget()), hash)) {
            throw new IllegalArgumentException("Hash not match");
        }

        userService.completeTask(task.getId());
        sendTaskCompleted(executor);
        sendOperationDone(userService.getPublicInfo(task.getTarget()));
        return userService.getActiveTasks(executor);
    }

    private boolean checkHash(String expectedHash, String hashToCheck) {
        return expectedHash.equals(hashToCheck);
    }

    private String getHash(Long executor, Long target) {
        String s = DigestUtils.sha1Hex(executor + target + "azaza");
        System.out.println(s);
        return s;
    }


    private void sendTask(PublicUserInfo executor, UserTask userTask) {
        String messageBody = serializer.serialize(userTask);
        String messageTitle = userTask.getName();
        pushManagerUtil.send(executor.getAppleToken(), messageTitle, messageBody);
    }

    private void sendTaskCompleted(PublicUserInfo executor) {
        String message = translator.translate("task.done", executor.getLang());
        String messageBody = serializer.serialize(new Result(message));
        pushManagerUtil.send(executor.getAppleToken(), message, messageBody);
    }

    // TODO lang
    private void sendOperationDone(PublicUserInfo target) {
        String message = translator.translate("task.confirmed", "en");
        String messageBody = serializer.serialize(new Result(message));
        pushManagerUtil.send(target.getAppleToken(), message, messageBody);
    }
}
