package com.demo.bean;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable{

	private static final long serialVersionUID = -7097973777690213033L;

	private Long id;
	
	private String roleName;
	
	private Integer enabled;
	
	private Long createBy;
	
	private Date createTime;
	
	private SysUser user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
