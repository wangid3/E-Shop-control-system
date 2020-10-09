package com.wangid3.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.wangid3.dao.GoodsDao;
import com.wangid3.domain.Goods;

public class GoodsDaoTest {
	private  GoodsDao goodsDao = new GoodsDao();
	@Test
	public void testGetAllGoods() throws SQLException {
		
		List<Goods> allGoods = goodsDao.getAllGoods();
		System.out.println(allGoods);
	}
	
	@Test
	public void testAddGoods() throws SQLException {
		Goods good = new Goods();
		good.setName("皇帝的的新装");
		good.setPrice(9999.99);
		good.setImage("not_exist.png");
		goodsDao.addGoods(good);
	}

	@Test
	public void testDelGoods() throws SQLException {

		goodsDao.delGoods(20);
	}

	@Test
	public void testupdateGoods() throws SQLException {
		Goods goods = new Goods();
		goods.setId(21);
		goods.setName("myxq222");
		goods.setPrice(40.0);
		goods.setImage("goods_11.png");
		goodsDao.updateGoods(goods);
	}

}
