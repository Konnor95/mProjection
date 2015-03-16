package com.mprojection.db.extractor;

import com.mprojection.entity.task.Task;
import com.mprojection.entity.task.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskExtractor implements Extractor<Task> {

    @Autowired
    private Tasks tasks;

    @Override
    public Task extract(ResultSet rs) throws SQLException {
        Task task = tasks.define(rs.getString("taskId"));
        task.setExecutor(rs.getLong("userId"));
        task.setTarget(rs.getLong("target"));
        return task;
    }

}
