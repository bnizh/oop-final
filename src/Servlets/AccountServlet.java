package Servlets;

import DataBase.DBFactory;
import Objects.Buyer;
import Objects.HashCreator;
import Objects.ObjectFactory;
import Objects.Seller;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;

@MultipartConfig
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            createNewAccount(request,response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void createNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
        String imgURL = saveFile(request, response);
        System.out.println("image url-"+imgURL);
      String password=  HashCreator.getHash(request.getParameter("password"));
        if (request.getParameter("type").equals("buyer")) {
           Buyer buyer= ObjectFactory.getNewBuyer(request.getParameter("username"), password,
                    request.getParameter("email"), request.getParameter("name")+" "+request.getParameter("surname"), 0, request.getParameter("mobile"),0,  imgURL
            );
            DBFactory.getDBConnection().addNewBuyer(buyer);
        }
        else{
            Seller seller= ObjectFactory.getNewSeller(request.getParameter("username"), password,
                    request.getParameter("email"), request.getParameter("company"), 0, request.getParameter("mobile"), 0,  imgURL
            );
            DBFactory.getDBConnection().addNewSeller(seller);
        }
    }

    /*
    * this method gets http request and respons as parameters
    * gets sent file from browser, creates directory for new user
    * and puts that file into users folder and returns path of that file
    * */
    private String saveFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uploadDirectory = "C:\\\\Users\\\\Boris\\\\Desktop\\\\final project\\\\oop-final\\\\" + request.getParameter("username");
        Path folder = Paths.get(uploadDirectory);
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
        }
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory+"\\\\"+"profile."+extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory+"\\\\"+"profile."+extension;
    }

    /*
    * this is method for getting name of sent file
    * */
    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}
