package com.amh.pm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid Email Format!")
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false, unique = false)
    @NotEmpty
    @Size(min = 3, max = 80, message = "Password should be length between 3 and 80!")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OrganizationMember", joinColumns = @JoinColumn(name = "userId ", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "organizationId ", referencedColumnName = "id"))
    private List<Organization> organizations;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ProjectMember", joinColumns = @JoinColumn(name = "userId ", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "projectId ", referencedColumnName = "id"))
    private List<Project> projects;

    public User() {
        super();
    }

    public User(String name, String email, String password) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /*
     * @Override public int hashCode() { int result = 17; result = 31 * result + name.hashCode(); result = 31 * result +
     * email.hashCode(); result = 31 * result + password.hashCode(); return result; }
     * @Override public boolean equals(Object obj) { if (obj == this) return true; if (!(obj instanceof User)) return false;
     * User user = (User) obj; return user.getName() == this.getName() && user.getEmail() == this.getEmail() &&
     * user.getPassword() == this.getPassword(); }
     */

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object object) {

        User user = (User) object;

        if (user.id == this.id) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
