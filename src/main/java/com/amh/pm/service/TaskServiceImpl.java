package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.TaskDao;
import com.amh.pm.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    @Transactional
    public void save(Task task) {
        taskDao.save(task);
    }

    @Override
    @Transactional
    public void delete(Task task) {
        taskDao.delete(task);

    }

    @Override
    @Transactional
    public void update(Task task) {
        taskDao.update(task);
    }

    @Override
    @Transactional
    public List<Task> findAll() {

        return taskDao.findAll();
    }

    @Override
    @Transactional
    public Task findById(int taskId) {
        return taskDao.findById(taskId);
    }

}
