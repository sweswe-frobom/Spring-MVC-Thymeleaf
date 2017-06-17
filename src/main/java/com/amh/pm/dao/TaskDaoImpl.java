package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.amh.pm.entity.Project;
import com.amh.pm.entity.Task;

public class TaskDaoImpl implements TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Task task) {
        entityManager.persist(task);

    }

    @Override
    public void delete(Task task) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Task task) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    @Override
    public Task findById(int taskId) {
        return entityManager.find(Task.class, taskId);
    }

}
