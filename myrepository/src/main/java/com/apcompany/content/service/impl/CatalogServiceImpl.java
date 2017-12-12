package com.apcompany.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.content.dao.CatalogDao;
import com.apcompany.content.pojo.Catalog;
import com.apcompany.content.service.CatalogService;
import com.apcompany.user.utils.TreeUtil;
@Service
public class CatalogServiceImpl implements CatalogService{
	
	@Autowired
	private CatalogDao catalogDao;

	@Override
	public int insert(Catalog catalog) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Map<String,Object>> selectALl(){
		List<Catalog> list=catalogDao.getAll();
		TreeUtil<Catalog> cataTree = new TreeUtil<Catalog>();
		for(Catalog catalog:list){
			cataTree.setNode(catalog.getId(), catalog.getParentId(), catalog);
		}
		//生成所需的组织架构树
		List<Map<String,Object>> destList = new ArrayList<Map<String,Object>>();
		Map<String,Object> rootMap = null;
		Catalog org = null;
		ArrayList<Integer> rootList = cataTree.getRootList();
		for (int i = 0; i < rootList.size(); i++) {
			org = cataTree.getData(rootList.get(i));
			rootMap = new HashMap<String,Object>();
			rootMap.put("id", org.getId());
			rootMap.put("name", org.getName());
			rootMap.put("parentId", org.getParentId());
			rootMap.put("picture", org.getPicture());
			rootMap.put("children", menuChildList(cataTree,rootList.get(i)));
			destList.add(rootMap);
		}
		return destList;
		
	}
	
	// 获取对应节点的子节点列表
		private List<Map<String, Object>> menuChildList(TreeUtil<Catalog> menuTree, Integer rootId) {
			// 节点孩子节点列表
			List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
			// 获取当前节点的子节点
			ArrayList<Integer> children = menuTree.getChildren(rootId);
			if (children.size() == 0) {
				// 没有子节点直接返回
				return null;
			} else {
				Map<String, Object> childMap = null;
				for (Integer childId : children) {
					// 封装子节点信息
					Catalog cata = menuTree.getData(childId);
					childMap = new HashMap<String, Object>();
					childMap.put("id", cata.getId());
					childMap.put("name", cata.getName());
					childMap.put("parentId", cata.getParentId());
					childMap.put("picture", cata.getPicture());
					childMap.put("children", menuChildList(menuTree, childId));
					childList.add(childMap);
				}
				return childList;
			}
		}

}
