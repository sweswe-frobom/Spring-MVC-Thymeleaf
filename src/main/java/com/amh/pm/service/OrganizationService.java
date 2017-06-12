package com.amh.pm.service;

import java.util.List;

import com.amh.pm.entity.Organization;

public interface OrganizationService {

	public void save(Organization organ);

	public void delete(Organization organ);

	public void update(Organization organ);

	public List<Organization> findAll();

	public Organization findById(int organId);

	// public List<Integer> findUserIdByOrgnId(int orgId);
	public Organization findOrganizationByName(String orgName);

}
