package com.bts.entities;

import java.util.List;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Employee extends BaseEntity {
	public abstract void setFullName(String fullName);

	public abstract String getFullName();

	public abstract void setEmail(String email);

	public abstract String getEmail();

	public abstract void setPhoneNo(long phoneNo);

	public abstract long getPhoneNo();

	public abstract void setApproved(boolean isApproved);

	public abstract boolean isApproved();

	public abstract void setPassword(String password);

	public abstract String getPassword();
	
	public abstract void setDepartment(String type);
	
	public abstract String getDepartment();
	
	public abstract void setProjectList(List<ProjectEntity> projectList);
	
	public abstract List<ProjectEntity> getProjectList();
	
}
