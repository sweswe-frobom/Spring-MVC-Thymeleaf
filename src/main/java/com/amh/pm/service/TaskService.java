package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amh.pm.entity.Task;

public interface TaskService {

    public void save(Task task);

    public void delete(Task task);

    public void update(Task task);

    public List<Task> findAll();

    public Task findById(int taskId);

}
