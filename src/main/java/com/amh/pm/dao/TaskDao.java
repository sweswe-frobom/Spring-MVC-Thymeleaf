package com.amh.pm.dao;

import java.util.List;

import com.amh.pm.entity.Task;

public interface TaskDao {

    public void save(Task task);

    public void delete(Task task);

    public void update(Task task);

    public List<Task> findAll();

    public Task findById(int taskId);
}
