package com.apcompany.user.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 树辅助操作类
 * @author wangpeng31
 * @version 1.0 2017-03-27
 */
public class TreeUtil<T> {
	private	ArrayList<Integer> rootList= new ArrayList<Integer>();
	private Map<Integer,ArrayList<Integer>> children = new HashMap<Integer,ArrayList<Integer>>();
	private Map<Integer,Integer> parent= new HashMap<Integer,Integer>();
	private Map<Integer,Integer> layer = new HashMap<Integer,Integer>();
	private Map<Integer,T> data = new HashMap<Integer,T>();
	
	public TreeUtil(){}
	
	/**
	 * 设置节点
	 * @param id
	 * @param parentId
	 * @param T
	 */
	public void setNode(Integer id,Integer parentId,T data){
		if(parentId==0){
			//设置根节点
			if(!this.rootList.contains(id)){
				this.rootList.add(id);
			}
		}else{
			//设置菜单的父子节点关系
			ArrayList<Integer> child = this.children.get(parentId);
			child = child==null?new ArrayList<Integer>():child;
			if(!child.contains(id)){
				child.add(id);
				this.children.put(parentId, child);
				//设置菜单的子父节点关系
				this.parent.put(id, parentId);
			}
		}
		//保存数据
		this.data.put(id, data);
		//设置节点对应的层级
		Integer plevel = this.layer.get(parentId);
		this.layer.put(id, (plevel==null || plevel==0? 1:plevel+1));
	}
	
	/**
	 * 获取根节点列表
	 * @return
	 */
	public ArrayList<Integer> getRootList() {
		return rootList;
	}
	/**
	 * 获取父节点对应的子节点
	 * @param parentId
	 * @return
	 */
	public ArrayList<Integer> getChildren(Integer parentId){
		ArrayList<Integer> childList = this.children.get(parentId);
		return childList==null?new ArrayList<Integer>():childList;
	}
	/**
	 * 获取子节点对应的父节点
	 * @param id
	 * @return
	 */
	public Integer getParentId(Integer id){
		return this.parent.get(id);
	}
	/**
	 * 获取节点对应的层级
	 * @param id
	 * @return
	 */
	public Integer getLayer(Integer id){
		return this.layer.get(id);
	}
	/**
	 * 获取id对应的数据
	 * @param id
	 * @return
	 */
	public T getData(Integer id){
		return this.data.get(id);
	}
}
