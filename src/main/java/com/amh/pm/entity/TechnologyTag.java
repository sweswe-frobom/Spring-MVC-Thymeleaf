package com.amh.pm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class TechnologyTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    @NotEmpty(message = "Please fill technology tag name!")
    private String name;

    @ManyToMany
    @JoinTable(name = "TaskTechnologyTag", joinColumns = @JoinColumn(name = "technologyTag ", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "taskId ", referencedColumnName = "id"))
    private List<Task> tasks;

    public TechnologyTag() {
        super();
    }

    public TechnologyTag(String name) {
        super();
        this.name = name;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof TaskNote))
            return false;
        TechnologyTag techTag = (TechnologyTag) obj;
        return techTag.getName() == this.getName();

    }
}
