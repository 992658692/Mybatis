package com.demo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 当设置的二级缓存为可读写时，Mybatis使用的是SerializableCache序列化缓存来实现可读写缓存类
 * 并通过序列化和反序列化来保证通过缓存获取数据时，得到的是一个新实例。而这个类要求所有被序列化的对象
 * 实现Serializable接口，如果只是单纯的只读缓存 Mybatis是通过Map来存储的所以不用实现序列化
 * 也没关系。
 *
 */
public class SysUser implements Serializable{

	private static final long serialVersionUID = 4742056142991750197L;

	private Long id;
	
	private String userName;
	
	private String userPassword;
	
	private String userEmail;
	
	private String userInfo;
	
	private byte[] headImg;
	
	private Date createTime;
	
	private SysRole role;
	
	private List<SysRole> roleList;
	
	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public byte[] getHeadImg() {
		return headImg;
	}

	public void setHeadImg(byte[] headImg) {
		this.headImg = headImg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
