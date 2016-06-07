package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type.equals("buyer")) {
            System.out.println(request.getParameter("busername"));
            System.out.println(request.getParameter("bmobile"));
            System.out.println(request.getParameter("bemail"));
            System.out.println(request.getParameter("bsurname"));
            System.out.println(request.getParameter("bname"));
            System.out.println(request.getParameter("bpassword"));
        }
        else {
            System.out.println(request.getParameter("company"));
            System.out.println(request.getParameter("susername"));
            System.out.println(request.getParameter("semail"));
            System.out.println(request.getParameter("smobile"));
            System.out.println(request.getParameter("semail"));

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
