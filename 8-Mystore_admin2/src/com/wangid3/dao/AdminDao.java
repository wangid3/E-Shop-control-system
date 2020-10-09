package com.wangid3.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wangid3.domain.Admin;
import com.wangid3.util.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class AdminDao {

	public static List<Admin> getAllAdmins() throws Exception{
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from admin";
		return qr.query(sql, new BeanListHandler<Admin>(Admin.class));
	}

	public Admin checkAdmin(String name, String pwd) throws SQLException {

		//到数据库当中查询
		//1.连接
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		//QueryRunner qr=new QueryRunner(JDBCUtil.getDataSource());
		//2.查询
		String sql ="select * from admin where username=? and password=?";
		//3.执行查询
		Admin admin = null;
		admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),name,pwd);
		//返回查询结果
		return admin;
	}

	public void insertAdmin(Admin admin)throws Exception {
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "insert into admin(username,password) values (?,?)";
		qr.update(sql,admin.getUsername(),admin.getPassword());
	}


}
