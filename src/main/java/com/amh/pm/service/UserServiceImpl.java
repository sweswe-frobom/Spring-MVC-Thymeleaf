package com.amh.pm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.UserDao;
import com.amh.pm.entity.User;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public List<User> findUserNameByOrgnizationId(int orgId) {
        return userDao.findUserNameByOrgnizationId(orgId);
    }

    @Override
    @Transactional
    public User findUserIdByName(String userName) {
        return userDao.findUserIdByName(userName);
    }

    @Override
    @Transactional
    public void delete(User user) {

    }

    @Override
    @Transactional
    public void update(User user) {

    }

    @Override
    @Transactional
    public User userByName(String name, String password) {
        return userDao.userByName(name, password);
    }

    @Override
    @Transactional
    public User findUserByEmail(String userEmail) {
        return userDao.findUserByEmail(userEmail);
    }

}
