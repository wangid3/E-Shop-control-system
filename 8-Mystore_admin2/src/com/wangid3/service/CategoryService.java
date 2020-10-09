package com.wangid3.service;

import java.util.List;

import com.wangid3.dao.CategoryDao;
import com.wangid3.domain.Category;

public class CategoryService {

	//获取所有的分类信息
	public List<Category> findCategory() throws Exception {
		//从数据库当中查找
		CategoryDao categoryDao = new CategoryDao();
		List<Category> allCategory = categoryDao.getAllCategory();
		return allCategory;
	}

}
