package Servlets;

import DataBase.DBFactory;
import Managers.ManagerFactory;
import Objects.Item;
import Objects.ObjectFactory;
import Objects.Transaction;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInput;

/**
 * Created by Boris on 29.06.2016.
 */
@WebServlet("/ItemBuy")
public class ItemBuy extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("status")==null){
        String itemID = request.getParameter("item");
        String buyerID = request.getParameter("buyer");
        String amount = request.getParameter("amount");
        Item it = DBFactory.getDBConnection().getItemById(Integer.parseInt(itemID));
        Transaction tr = ObjectFactory.getTransaction(it.getOwnerID(),Integer.parseInt(buyerID),it.getID(),Integer.parseInt(amount));
        DBFactory.getDBConnection().addTransaction(tr);
        }else if(request.getParameter("status").equals("Accepted")) {
            String trID = request.getParameter("transactionID");
            Transaction tr = DBFactory.getDBConnection().getTransaction(trID);
            try {
                ManagerFactory.getSendMail().sendEmail(DBFactory.getDBConnection().getBuyerByID(tr.getBuyerID()).getEmail(), "Your offer "+trID+" has been accepted");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            DBFactory.getDBConnection().resolveTransaction(trID);
        }else{
            String trID = request.getParameter("transactionID");
            Transaction tr = DBFactory.getDBConnection().getTransaction(trID);
            try {
                ManagerFactory.getSendMail().sendEmail(DBFactory.getDBConnection().getBuyerByID(tr.getBuyerID()).getEmail(), "Your offer "+trID+" has been rejected");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            DBFactory.getDBConnection().deleteTransactionByID(trID);}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
