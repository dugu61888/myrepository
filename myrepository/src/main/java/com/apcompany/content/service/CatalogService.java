package com.apcompany.content.service;

import java.util.List;
import java.util.Map;

import com.apcompany.content.pojo.Catalog;

public interface CatalogService {
	
	public int insert(Catalog catalog);
	
	public List<Map<String,Object>> selectALl();
	
	

}
