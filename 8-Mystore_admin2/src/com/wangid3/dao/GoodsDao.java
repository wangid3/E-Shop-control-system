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
	//1.�����ݿ⵱�в�ѯ������Ʒ�б�
	public List<Goods> getAllGoods() throws SQLException{
		
		//1.��ѯ����
		String sql = "select * from goods";
		//2ִ��sql
		return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
	}
	//2.�����Ʒ�����ݿ⵱��
	public void addGoods(Goods goods) throws SQLException {
		//�������9
		if(goods.getPrice()<0) return;
		String sql = "insert into goods(name,price,image,gdesc,is_hot,cid) values (?,?,?,?,?,?)";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	//3.����id�����ݿ⵱��ɾ��һ����Ʒ
	public void delGoods(int id) throws SQLException {
		//ɾ������
		String sql = "delete from goods where id=?";
		qr.update(sql,id);
	}
	
	//4.����һ����Ʒ�������ݿ��и���id���¸���Ʒ
	public void updateGoods(Goods goods) throws SQLException {
		//���²���
		if(goods.getPrice()<0) return;
		String sql = "update goods set name=?,price=?,image=?,gdesc=?,is_hot=?,cid=? where id=?";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}
	//����id��ѯһ����Ʒ
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
