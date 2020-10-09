package com.wangid3.web;
import com.wangid3.domain.Admin;
import com.wangid3.service.AdminService;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminServletPro")
public class AdminServletPro extends BaseServlet{
    public String getListAdmins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        AdminService adminService = new AdminService();
        try {
            List<Admin> allAdmins = AdminService.getAllAdmins();
            for(Admin admin:allAdmins){
                System.out.println(admin);
            }
            // 对集合进行反转
            //Collections.reverse(allAdmins);
            // 把数据写到request域
            request.setAttribute("allAdmins", allAdmins);
            // 转发
            return "admin/account.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String addAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        //把参数封装对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, parameterMap);
            System.out.println(admin);
            //调用服务层
            AdminService adminService = new  AdminService();
            adminService.addAdmin(admin);
            //跳转列表，
            return "/AdminServletPro?action=getListAdmins";

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
