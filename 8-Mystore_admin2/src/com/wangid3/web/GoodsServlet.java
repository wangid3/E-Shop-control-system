package com.wangid3.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangid3.domain.PageBean;
import org.apache.commons.beanutils.BeanUtils;

import com.wangid3.domain.Category;
import com.wangid3.domain.Goods;
import com.wangid3.service.CategoryService;
import com.wangid3.service.GoodsService;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
	//�����Ʒ
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ���в���
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(parameterMap);
		//�Ѳ�����װ����
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_11.png");
			System.out.println(goods);
			//���÷����
			GoodsService goodsService = new  GoodsService();
			if(goods.getPrice()<0) return "/GoodsServlet?action=getPageData&currentPage=1";
			goodsService.addGoods(goods);
			//��ת�б�
			return "/GoodsServlet?action=getPageData&currentPage=1";
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// �����ƷUI
	public String addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.ȡ�����еķ���  
		 CategoryService categoryService = new CategoryService();
		 try {
			List<Category> allCategory = categoryService.findCategory();
			//2.�ѷ������ ����
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			//3.ת��add.jsp
			return  "admin/add.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//ɸѡ��Ʒҳ��
	public String selectUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.ȡ�����еķ���
		CategoryService categoryService = new CategoryService();
		try {
			List<Category> allCategory = categoryService.findCategory();
			//2.�ѷ������ ����
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			//3.ת��select.jsp
			return  "admin/select.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//ɸѡ��Ʒ
	public String select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ���в���
		String currentPage= request.getParameter("currentPage");
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(parameterMap);
		//�Ѳ�����װ����
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			System.out.println(goods);
			//���÷����
			GoodsService goodsService = new  GoodsService();
			PageBean pageBean= goodsService.selectGoods(goods,Integer.parseInt(currentPage));
			request.setAttribute("pageBean",pageBean);
			// ת��
			return "admin/main.jsp";

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// �༭��Ʒ
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.setCharacterEncoding("UTF-8");
		// 1.��ȡ���еĲ���
		Map<String, String[]> parameterMap = request.getParameterMap();
		Goods goods = new Goods();
		// 2.��װ��goods����
		try {
			BeanUtils.populate(goods, parameterMap);
			// 3.����id��������
			System.out.println(goods);
			// 4.���÷���㣬��������
			  if(goods.getPrice()<0) return "/GoodsServlet?action=getPageData&currentPage=1";
			    GoodsService goodsService = new GoodsService();
			Goods temp=goodsService.getGoodsWithId(goods.getId().toString());
			goods.setImage(temp.getImage());
			goodsService.updateGoods(goods);

			// 5.��ת��main.jsp �б�
			return "/GoodsServlet?action=getPageData&currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// �༭��ƷUI
	public String editUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��ǰ��Ʒ��id
		String id = request.getParameter("id");
		try {
			// 1.��ȡ��ǰ��Ʒ
			GoodsService goodsService = new GoodsService();
			Goods goods = goodsService.getGoodsWithId(id);
			// ����Ʒд����
			request.setAttribute("goods", goods);
			// 2.��ȡ���еķ���
			CategoryService categoryService = new CategoryService();
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			// ת����edit.jsp
			return "/admin/edit.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ɾ����Ʒ
	public String delGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.���ղ��� id
		String id = request.getParameter("id");
		// 2.���÷���㣬����ɾ����Ʒ
		GoodsService goodsService = new GoodsService();
		try {
			goodsService.deleteGoods(id);
			return "/GoodsServlet?action=getPageData&currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ��ȡ���е���Ʒ
	public String getListGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.���÷����
		GoodsService goodsService = new GoodsService();
		try {
			List<Goods> allGoods = goodsService.getAllGoods();
			// �Լ��Ͻ��з�ת
			Collections.reverse(allGoods);
			// ������д��request��
			request.setAttribute("allGoods", allGoods);
			// ת��
			return "admin/main.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPageData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1.��ȡ��ǰҳ��
			String currentPage= request.getParameter("currentPage");
			//
			GoodsService goodsService =new GoodsService();
			PageBean pageBean=goodsService.getPageBean(Integer.parseInt(currentPage) );
			//�浽����
			request.setAttribute("pageBean",pageBean);
			//ת��
			return "admin/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
