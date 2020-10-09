package com.wangid3.service;

import java.sql.SQLException;
import java.util.List;

import com.wangid3.dao.GoodsDao;
import com.wangid3.domain.Goods;
import com.wangid3.domain.PageBean;

public class GoodsService {
	private  GoodsDao goodsDao = new GoodsDao();
	public List<Goods> getAllGoods() throws SQLException {
		//�����ݿ⵱�л�ȡ���е�����
		List<Goods> allGoods = goodsDao.getAllGoods();
		return allGoods;
	}

	public void deleteGoods(String id) throws Exception {
		//����dao����ɾ����Ʒ
		goodsDao.delGoods(Integer.parseInt(id)); 
	}

	public void addGoods(Goods goods) throws Exception {
		//����dao �������
		goodsDao.addGoods(goods);
		
	}

	public Goods getGoodsWithId(String id) throws Exception {
		//����dao ��ѯһ����Ʒ����id����
	 	Goods goods = goodsDao.getGoodsWithId(id);
	 	return goods;
	}

	public void updateGoods(Goods goods) throws Exception {
		//����dao ������Ʒ
		goodsDao.updateGoods(goods);
	}


	public PageBean getPageBean(Integer currentPage) throws SQLException {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		//��ȡ�ж�������¼
		//�����ݿ��н��в�ѯ
		int count= goodsDao.getCount();
		pageBean.setTotalCount(count);
		//һҳչʾ��������
		Integer pageCount=5;
		//�ܹ�ҳ��
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);//ת���ɸ�����������������������������
		pageBean.setTotalPage((int)totalPage);
		//��ǰҳ��ѯ�Ǳ�
		Integer index=pageBean.getCurrentPage()*pageCount;
		List<Goods> list= goodsDao.getPageData(index,pageCount);
		pageBean.setGoodsList(list);
		return pageBean;
	}

	public PageBean selectGoods(Goods goods,Integer currentPage) throws SQLException{
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		//��ȡ�ж�������¼
		//�����ݿ��н��в�ѯ
		int count= goodsDao.getCountByCid(goods.getCid());
		pageBean.setTotalCount(count);
		//һҳչʾ��������
		Integer pageCount=5;
		//�ܹ�ҳ��
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);//ת���ɸ�����������������������������
		pageBean.setTotalPage((int)totalPage);
		//��ǰҳ��ѯ�Ǳ�
		Integer index=pageBean.getCurrentPage()*pageCount;
		List<Goods> list= goodsDao.selectGoods(goods,index,pageCount);
		pageBean.setGoodsList(list);
		return pageBean;
	}
}
