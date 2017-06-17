package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Organization organization) throws RollbackException {
        entityManager.merge(organization);
    }

    @Override
    public void delete(Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Organization organization) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Organization> findAll() {
        return entityManager.createQuery("SELECT o FROM Organization o", Organization.class).getResultList();
    }

    @Override
    public Organization findById(int organizationId) {

        return entityManager.find(Organization.class, organizationId);
    }

    @Override
    public Organization findOrganizationByName(String organizationName) {
        // TODO Auto-generated method stub
        Organization organization = null;
        try {
            Query q = entityManager.createQuery("select o from Organization o WHERE o.name=?");
            q.setParameter(1, organizationName);
            organization = (Organization) q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Error is :" + e);
        }
        return organization;
    }

}
