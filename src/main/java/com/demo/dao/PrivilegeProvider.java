package com.demo.dao;

public class PrivilegeProvider {

	public String selectById (final Long id) {
//		return new SQL() {
//			{
//				SELECT("id, privilege_name, privilege_url");
//				FROM("sys_privilege");
//				WHERE("id = #{id}");
//			}
//		}.toString();
		return "select id, privilege name, privilege url from sys_privilege where id = #{id }";
	}
}
