package com.amh.pm.service;

import java.util.List;

import com.amh.pm.entity.Organization;

public interface OrganizationService {

	public void save(Organization organization);

	public void delete(Organization organization);

	public void update(Organization organization);

	public List<Organization> findAll();

	public Organization findById(int organizationId);

	// public List<Integer> findUserIdByOrgnId(int orgId);
	public Organization findOrganizationByName(String organizationName);

}
