package com.demo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * �����õĶ�������Ϊ�ɶ�дʱ��Mybatisʹ�õ���SerializableCache���л�������ʵ�ֿɶ�д������
 * ��ͨ�����л��ͷ����л�����֤ͨ�������ȡ����ʱ���õ�����һ����ʵ�����������Ҫ�����б����л��Ķ���
 * ʵ��Serializable�ӿڣ����ֻ�ǵ�����ֻ������ Mybatis��ͨ��Map���洢�����Բ���ʵ�����л�
 * Ҳû��ϵ��
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
