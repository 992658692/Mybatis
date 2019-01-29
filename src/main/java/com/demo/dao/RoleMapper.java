package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.demo.bean.SysRole;

@Repository("roleMapper")
public interface RoleMapper {

	@Select("select id, role_name , enabled, create_by , "
			+ "create_time  from sys_role where id = #{id}")
	SysRole selectById(Long id);
	
	
	//id=true 与xml 文件中的id标签一样 主键标识
	//针对Mybatis3.3.1版本以上才可以定义id 否则 只能在每个方法上都定义一次results(有点蠢。。。)
	@Results(id="roleResultMap", value= {
		@Result(property = "id", column = "id", id = true),
		@Result(property = "roleName", column = "role_name"),
		@Result(property = "enabled", column = "enabled"),
		@Result(property = "createBy", column = "create_by"),
		@Result(property = "createTime", column = "create_time")
	})
//	@ResultMap("roleResultMap")
	@Select("select id, role_name, enabled, create_by, create_time from sys_role")
	List<SysRole> selectAll();
	
	
	//注解的形式中新增的记录的主键查询方式是用@options注解 注解内的参数都跟xml中一样
	@Insert({"insert into sys_role (role_name, enabled, create_by, create_time)",
			"values (#{roleName}, #{enabled}, #{createBy}, #{createTime})"})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SysRole role);
	
	int updateRoleById(SysRole role);
}
