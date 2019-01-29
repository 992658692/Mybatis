package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.cache.decorators.FifoCache;
import org.springframework.stereotype.Repository;

import com.demo.bean.SysRole;
import com.demo.bean.SysUser;

/**
 * cacheNameSpace开启缓存的注解
 * 唯一不同的属性就是readWrite:false为只读，true为读写 默认为true
 *
 */
@CacheNamespaceRef(value = UserMapper.class)
/*@CacheNamespace(
		eviction = FifoCache.class,
		flushInterval = 60000,
		size = 512,
		readWrite = true)*/
@Repository("userMapper")
public interface UserMapper {

	public SysUser selectById(Long id);
	
	public List<SysUser> selectAll();
	
	public List<SysUser> selectRolesByUserId(Long id);
	
	int insert(SysUser user);
	
	int update(SysUser user);
	
	int deleteById(SysUser user);
	
	List<SysUser> selectByIdList(Map<String, Object> idList);
}
