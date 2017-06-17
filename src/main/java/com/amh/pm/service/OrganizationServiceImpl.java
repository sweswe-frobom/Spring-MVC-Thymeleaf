package com.amh.pm.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amh.pm.dao.OrganizationDao;
import com.amh.pm.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	private OrganizationDao organizationDao;

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	@Transactional
	public void save(Organization organization) {
		organizationDao.save(organization);

	}

	@Override
	@Transactional
	public void delete(Organization organization) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public void update(Organization organization) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Organization> findAll() {
		// TODO Auto-generated method stub
		return organizationDao.findAll();
	}

	@Override
	@Transactional
	public Organization findById(int organizationId) {
		return organizationDao.findById(organizationId);
	}

	@Override
	@Transactional
	public Organization findOrganizationByName(String organizationName) {
		return organizationDao.findOrganizationByName(organizationName);
	}

}
