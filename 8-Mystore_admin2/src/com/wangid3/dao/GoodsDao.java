package com.wangid3.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wangid3.domain.Goods;
import com.wangid3.util.JdbcUtil;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class GoodsDao {
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	//1.从数据库当中查询所有商品列表
	public List<Goods> getAllGoods() throws SQLException{
		
		//1.查询操作
		String sql = "select * from goods";
		//2执行sql
		return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
	}
	//2.添加商品到数据库当中
	public void addGoods(Goods goods) throws SQLException {
		//插入操作9
		if(goods.getPrice()<0) return;
		String sql = "insert into goods(name,price,image,gdesc,is_hot,cid) values (?,?,?,?,?,?)";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	//3.根据id从数据库当中删除一个商品
	public void delGoods(int id) throws SQLException {
		//删除操作
		String sql = "delete from goods where id=?";
		qr.update(sql,id);
	}
	
	//4.传入一个商品，到数据库中根据id更新该商品
	public void updateGoods(Goods goods) throws SQLException {
		//更新操作
		if(goods.getPrice()<0) return;
		String sql = "update goods set name=?,price=?,image=?,gdesc=?,is_hot=?,cid=? where id=?";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}
	//根据id查询一个商品
	public Goods getGoodsWithId(String id) throws Exception {
		
		String sql = "select * from goods where id=?";
		Goods goods = qr.query(sql, new BeanHandler<Goods>(Goods.class),Integer.parseInt(id));
		return goods;
		
	}

    public int getCount() throws SQLException {
		String sql="select count(*) from goods";
		int count= Integer.parseInt((qr.query(sql,new ScalarHandler())).toString());
		return count;
    }

	public int getCountByCid(Integer cid) throws SQLException {
		String sql="select count(*) from goods where cid = ?";
		int count= Integer.parseInt((qr.query(sql,new ScalarHandler(),cid)).toString());
		return count;
	}

	public List<Goods> getPageData(Integer index, Integer pageCount) throws SQLException {
		String sql="select * from goods where rownum<= ? minus select * from goods where rownum<= ?";
		List<Goods> pagegoods=null;
		pagegoods=qr.query(sql,new BeanListHandler<Goods>(Goods.class),index,(index-pageCount));
		return pagegoods;
	}

	public List<Goods> selectGoods(Goods goods,Integer index, Integer pageCount) throws SQLException{
		String sql="select * from goods where cid = ? and rownum<= ? minus select * from goods where cid = ? and rownum<= ?";
		List<Goods> goodsilst=null;
		goodsilst=qr.query(sql,new BeanListHandler<Goods>(Goods.class),goods.getCid(),index,goods.getCid(),(index-pageCount));
		return goodsilst;
	}

}
