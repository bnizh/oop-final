package Servlets;

import DataBase.DBFactory;
import Managers.ManagerFactory;
import Objects.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.Writer;
import java.util.List;

/**
 * Created by Boris on 29.06.2016.
 */
@WebServlet("/ItemBuy")
public class ItemBuy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("status").equals("Accepted")) {
            String trID = request.getParameter("transactionID");
            Transaction tr = DBFactory.getDBConnection().getTransaction(trID);
            try {
                ManagerFactory.getSendMail().sendEmail(DBFactory.getDBConnection().getBuyerByID(tr.getBuyerID()).getEmail(), "Your offer "+trID+" has been accepted");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            DBFactory.getDBConnection().resolveTransaction(trID);
        }else if(request.getParameter("status").equals("Rejected")){
            String trID = request.getParameter("transactionID");
            Transaction tr = DBFactory.getDBConnection().getTransaction(trID);
            try {
                ManagerFactory.getSendMail().sendEmail(DBFactory.getDBConnection().getBuyerByID(tr.getBuyerID()).getEmail(), "Your offer "+trID+" has been rejected");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            DBFactory.getDBConnection().deleteTransactionByID(trID);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        Writer out = response.getWriter();
        if(request.getParameter("userID")!= null){
            int ID = Integer.parseInt(request.getParameter("userID"));
            List<Transaction> list = DBFactory.getDBConnection().getTransactionByBuyer(ID);
            if(list.size()==0)
                list= DBFactory.getDBConnection().getTransactionBySeller(ID);
            String st ="";
            for(int i=0; i< list.size(); i++){
                Item it = DBFactory.getDBConnection().getItemById(list.get(i).getItemID());
                User b = DBFactory.getDBConnection().getSellerByID(list.get(i).getBuyerID());
                if(b==null)
                    b = DBFactory.getDBConnection().getBuyerByID(list.get(i).getBuyerID());
                Seller s =DBFactory.getDBConnection().getSellerByID(list.get(i).getSellerID());
              st+= "<p>";
                st+= "  <span>Item Name : "+it.getName()+"</span>";
                st+= "  <span>Item Price : "+it.getPrice()+"</span>";
                st+= "  <span>Amount : "+list.get(i).getAmount()+"</span>";
                st+= "  <span>Sum : "+it.getPrice()*list.get(i).getAmount()+"</span>";
                st+= "  <span>Buyer : "+b.getName()+"</span>";
                st+= "  <span>Seller : "+s.getName()+"</span>";
                st+= "  <span>Transaction ID : "+list.get(i).getId()+"</span>";
                st+= "  </p>";
        }
            out.write(st);
            out.close();
            return;
    }
        out.write("failed");
        out.close();
}
}
