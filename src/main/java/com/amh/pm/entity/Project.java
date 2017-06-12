package com.amh.pm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", nullable = false, unique = false)
	@NotEmpty(message = "Please fill project name!")
	private String name;

	@Column(name = "description", nullable = false, unique = false)
	@NotEmpty(message = "Please fill description!")
	private String description;

	@Column(name = "scheduleStartDate", nullable = false, unique = false)
	private Date scheduleStartDate;

	@Column(name = "scheduleEndDate", nullable = false, unique = false)
	private Date scheduleEndDate;

	@Column(name = "actualStartDate", nullable = false, unique = false)
	private Date actualStartDate;

	@Column(name = "actualEndDate", nullable = false, unique = false)
	private Date actualEndDate;

	@OneToOne
	private User manager;

	@OneToMany(mappedBy = "project")
	private List<ProjectMember> user;

	public Project() {
		super();
	}

	public Project(String name, String description, Date scheduleStartDate, Date scheduleEndDate, Date acturalStartDate,
			Date actualEndDate) {
		super();
		this.name = name;
		this.description = description;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.actualStartDate = acturalStartDate;
		this.actualEndDate = actualEndDate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(Date scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public Date getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(Date scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<ProjectMember> getUser() {
		return user;
	}

	public void setUser(List<ProjectMember> user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + scheduleStartDate.hashCode();
		result = 31 * result + scheduleEndDate.hashCode();
		result = 31 * result + actualStartDate.hashCode();
		result = 31 * result + actualEndDate.hashCode();
		return result;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Organization))
			return false;
		Project prj = (Project) obj;
		return prj.getName() == this.getName() && prj.getDescription() == this.getDescription()
				&& prj.getScheduleStartDate() == this.getScheduleEndDate()
				&& prj.getScheduleEndDate() == this.getScheduleEndDate()
				&& prj.getActualStartDate() == this.getActualStartDate()
				&& prj.getActualEndDate() == this.getActualEndDate();
	}

}