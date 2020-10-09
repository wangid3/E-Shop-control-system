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
	//添加商品
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取所有参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(parameterMap);
		//把参数封装对象
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			goods.setImage("goods_11.png");
			System.out.println(goods);
			//调用服务层
			GoodsService goodsService = new  GoodsService();
			if(goods.getPrice()<0) return "/GoodsServlet?action=getPageData&currentPage=1";
			goodsService.addGoods(goods);
			//跳转列表，
			return "/GoodsServlet?action=getPageData&currentPage=1";
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 添加商品UI
	public String addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.取出所有的分类  
		 CategoryService categoryService = new CategoryService();
		 try {
			List<Category> allCategory = categoryService.findCategory();
			//2.把分类存域 当中
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			//3.转发add.jsp
			return  "admin/add.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//筛选商品页面
	public String selectUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.取出所有的分类
		CategoryService categoryService = new CategoryService();
		try {
			List<Category> allCategory = categoryService.findCategory();
			//2.把分类存域 当中
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			//3.转发select.jsp
			return  "admin/select.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//筛选产品
	public String select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取所有参数
		String currentPage= request.getParameter("currentPage");
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(parameterMap);
		//把参数封装对象
		Goods goods = new Goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			System.out.println(goods);
			//调用服务层
			GoodsService goodsService = new  GoodsService();
			PageBean pageBean= goodsService.selectGoods(goods,Integer.parseInt(currentPage));
			request.setAttribute("pageBean",pageBean);
			// 转发
			return "admin/main.jsp";

		}  catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 编辑商品
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request.setCharacterEncoding("UTF-8");
		// 1.获取所有的参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		Goods goods = new Goods();
		// 2.封装成goods对象
		try {
			BeanUtils.populate(goods, parameterMap);
			// 3.根据id更新数据
			System.out.println(goods);
			// 4.调用服务层，更新数据
			  if(goods.getPrice()<0) return "/GoodsServlet?action=getPageData&currentPage=1";
			    GoodsService goodsService = new GoodsService();
			Goods temp=goodsService.getGoodsWithId(goods.getId().toString());
			goods.setImage(temp.getImage());
			goodsService.updateGoods(goods);

			// 5.跳转回main.jsp 列表
			return "/GoodsServlet?action=getPageData&currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 编辑商品UI
	public String editUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前商品的id
		String id = request.getParameter("id");
		try {
			// 1.获取当前商品
			GoodsService goodsService = new GoodsService();
			Goods goods = goodsService.getGoodsWithId(id);
			// 把商品写到域
			request.setAttribute("goods", goods);
			// 2.获取所有的分类
			CategoryService categoryService = new CategoryService();
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);
			// 转发到edit.jsp
			return "/admin/edit.jsp";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 删除商品
	public String delGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.接收参数 id
		String id = request.getParameter("id");
		// 2.调用服务层，让其删除商品
		GoodsService goodsService = new GoodsService();
		try {
			goodsService.deleteGoods(id);
			return "/GoodsServlet?action=getPageData&currentPage=1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 获取所有的商品
	public String getListGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.调用服务层
		GoodsService goodsService = new GoodsService();
		try {
			List<Goods> allGoods = goodsService.getAllGoods();
			// 对集合进行反转
			Collections.reverse(allGoods);
			// 把数据写到request域
			request.setAttribute("allGoods", allGoods);
			// 转发
			return "admin/main.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPageData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 1.获取当前页码
			String currentPage= request.getParameter("currentPage");
			//
			GoodsService goodsService =new GoodsService();
			PageBean pageBean=goodsService.getPageBean(Integer.parseInt(currentPage) );
			//存到域中
			request.setAttribute("pageBean",pageBean);
			//转发
			return "admin/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
