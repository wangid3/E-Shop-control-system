package com.wangid3.service;

import java.util.List;

import com.wangid3.dao.AdminDao;
import com.wangid3.domain.Admin;

public class AdminService {

	public static List<Admin> getAllAdmins() throws  Exception {
		List<Admin> allAdmins = AdminDao.getAllAdmins();
		for(Admin admin:allAdmins) {
			System.out.println(admin);
		}
		return allAdmins;
	}

	public Admin login(String name, String pwd) throws Exception {
		//����dao�㵽���ݿ⵱�в�ѯ
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.checkAdmin(name,pwd);
		if(admin != null) {
			return admin;
		}else {
			throw new Exception("�û������������");
			//throw new RunTimeException("??");
		}
	}

	public void addAdmin(Admin admin) throws Exception {
		AdminDao adminDao=new AdminDao();
		adminDao.insertAdmin(admin);
	}
}
