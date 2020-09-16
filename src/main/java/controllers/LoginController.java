package controllers;

import auth.models.User;
import ejb.UserBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LoginController extends HttpServlet {

    @EJB
    private UserBean userBean;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMsg;
        boolean success=true;
        User user=new User();
        try{
            BeanUtils.populate(user,req.getParameterMap());
            user=userBean.authenticate(user);


        }catch (Exception ex){
        ex.printStackTrace();
        errorMsg=ex.getMessage();
        success=false;

        }
        RequestDispatcher dispatcher;
        errorMsg="";
        if(success){
            req.getSession(true).setAttribute("loggedInUser",user);
            dispatcher=req.getRequestDispatcher("index.html");
        }else{
            req.setAttribute("erroMsg",errorMsg);
            req.setAttribute("pageMsg", "Error");
            dispatcher = req.getRequestDispatcher("login.html");

        }

    }
}
