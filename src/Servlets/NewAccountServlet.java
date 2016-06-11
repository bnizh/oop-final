package Servlets;

import Managers.ErrorStatus;
import Managers.FileManager;
import Managers.ManagerFactory;
import Managers.UserManager;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@MultipartConfig
@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserManager um = ManagerFactory.getUserManager();
        Part filePart = request.getPart("file");
        ErrorStatus es=ErrorStatus.CORRECT;
        PrintWriter out = response.getWriter();


        if (request.getParameter("type").equals("seller"))
            try {
               es=   um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("company"), request.getParameter("mobile"), filePart, request.getParameter("type"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        else {
            try {
                 es= um.createNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"),
                        request.getParameter("name") + " " + request.getParameter("surname"), request.getParameter("mobile"),
                          filePart, request.getParameter("type"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        if(es==ErrorStatus.USED_USERNAME){
            out.write("usedusername");
            out.close();
        }
        else if(es==ErrorStatus.INCORRECT_EMAIL_STRUCTURE){
            out.write("emailstructure");
            out.close();
        }
        else if(es==ErrorStatus.WEAK_PASSWORD){
            out.write("weakpassword");
            out.close();
        }
        else if(es==ErrorStatus.USED_EMAIL){
            out.write("usedemail");
            out.close();
        }
        HttpSession session = request.getSession();
        session.setAttribute("UserName", request.getParameter("username"));
        RequestDispatcher dispatch = request.getRequestDispatcher("user-panel.jsp");
        dispatch.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
