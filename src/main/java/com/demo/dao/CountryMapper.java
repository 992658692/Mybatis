package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.demo.bean.Country;

@Repository("countryMapper")
public interface CountryMapper {

	public List<Country> selectAll();
}
