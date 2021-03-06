package Managers;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static Managers.SiteConstants.ADMIN_IMAGE_DIRECTORY;
import static Managers.SiteConstants.IMAGE_DIRECTORY;

public class FileManager {
    public String saveProfilePicture(String userName, Part filePart) throws IOException, ServletException {
        String uploadDirectory = IMAGE_DIRECTORY + userName;
        Path folder = Paths.get(uploadDirectory);
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
        }
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory + "\\\\" + "profile." + extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory + "\\\\" + "profile." + extension;
    }

    public String saveAdminPicture(String userName, Part filePart) throws IOException, ServletException {
        String uploadDirectory = ADMIN_IMAGE_DIRECTORY + userName;
        Path folder = Paths.get(uploadDirectory);
        if (Files.notExists(Paths.get(ADMIN_IMAGE_DIRECTORY))) {
            new File(ADMIN_IMAGE_DIRECTORY).mkdir();
        }
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
        }
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory + "\\\\" + "profile." + extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory + "\\\\" + "profile." + extension;
    }

    /*
    * this is method for getting name of sent file
    * */
    public String editProfile(String userName, Part filePart, String currentImg) throws IOException {
        String ext = FilenameUtils.getExtension(currentImg);
        String uploadDirectory = IMAGE_DIRECTORY + userName;
        Path folder = Paths.get(uploadDirectory);
        Path curPath = Paths.get(uploadDirectory + "\\\\profile." + ext);
        System.out.println(curPath);
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
        }
        if (Files.exists(curPath)) {
            Files.delete(curPath);
        }
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory + "\\\\" + "profile." + extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory + "\\\\" + "profile." + extension;
    }

    public String addItemImage(String userName, String itemName, Part filePart) throws IOException {
        String uploadDirectory = IMAGE_DIRECTORY + userName;
        Path folder = Paths.get(uploadDirectory);
        Path folder2 = Paths.get(uploadDirectory + "\\\\products");
        Path folder3 = Paths.get(uploadDirectory + "\\\\products\\\\" + itemName);
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
            new File(uploadDirectory + "\\\\products").mkdir();
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();
        }
        if (Files.notExists(folder2)) {
            new File(uploadDirectory + "\\\\products").mkdir();
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();
        }
        if (Files.notExists(folder3)) {
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();

        }
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory + "\\\\products\\\\" + itemName + "\\\\" + "main." + extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory + "\\\\products\\\\" + itemName + "\\\\" + "main." + extension;
    }

    public String editItemImage(String userName, String itemName, String currentImg, Part filePart) throws IOException {
        String ext = FilenameUtils.getExtension(currentImg);
        String uploadDirectory = IMAGE_DIRECTORY + userName;
        Path folder = Paths.get(uploadDirectory);
        Path curPath = Paths.get(uploadDirectory + "\\\\products\\\\" + itemName + "\\\\main." + ext);
        System.out.println(curPath);
        Path folder2 = Paths.get(uploadDirectory + "\\\\products");
        Path folder3 = Paths.get(uploadDirectory + "\\\\products\\\\" + itemName);
        if (Files.notExists(folder)) {
            new File(uploadDirectory).mkdir();
            new File(uploadDirectory + "\\\\products").mkdir();
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();
        }
        if (Files.notExists(folder2)) {
            new File(uploadDirectory + "\\\\products").mkdir();
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();
        }
        if (Files.notExists(folder3)) {
            new File(uploadDirectory + "\\\\products\\\\" + itemName).mkdir();

        }
        if (Files.exists(curPath)) {
            Files.delete(curPath);
        }
        String filename = getSubmittedFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        String extension = FilenameUtils.getExtension(filename);
        System.out.println(extension);
        Path file = Paths.get(uploadDirectory + "\\\\products\\\\" + itemName + "\\\\" + "main." + extension);
        System.out.println(file);
        try (InputStream input = fileContent) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file);
        return uploadDirectory + "\\\\products\\\\" + itemName + "\\\\" + "main." + extension;
    }

    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
