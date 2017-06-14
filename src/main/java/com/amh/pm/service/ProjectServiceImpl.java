package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.ProjectDao;
import com.amh.pm.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    @Transactional
    public void save(Project project) {
        projectDao.save(project);

    }

    @Override
    @Transactional
    public void delete(Project project) {

        projectDao.delete(project);

    }

    @Override
    @Transactional
    public void update(Project project) {
        projectDao.update(project);

    }

    @Override
    @Transactional
    public List<Project> findAll() {
        return projectDao.findAll();
    }

    @Override
    @Transactional
    public Project findById(int projectId) {
        return projectDao.findById(projectId);
    }

}
