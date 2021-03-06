package com.infoshareacademy.wojownicy.servlet;

import com.infoshareacademy.wojownicy.processor.FileUploadProcessor;
import com.infoshareacademy.wojownicy.exception.UserFileNotFound;
import com.infoshareacademy.wojownicy.freemarker.TemplateProvider;
import com.infoshareacademy.wojownicy.service.ApiDataHandler;
import com.infoshareacademy.wojownicy.service.SaveToDataBase;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MultipartConfig
@WebServlet("/books-upload")
public class FileUploadServlet extends HttpServlet {

  @Inject
  TemplateProvider templateProvider;

  @Inject
  FileUploadProcessor fileUploadProcessor;

  @Inject
  ApiDataHandler apiDataHandler;

  @Inject
  SaveToDataBase saveToDataBase;

  private static final Logger logger = LoggerFactory.getLogger(FileUploadServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Template template = templateProvider.getTemplate(getServletContext(), "file-upload-site.ftlh");

    String upload = req.getParameter("upload");
    String siteType = (String) req.getAttribute("siteType");
    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("siteType", siteType);

    dataModel.put("upload", upload);

    PrintWriter printWriter = resp.getWriter();
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    Part file = req.getPart("file");

    String fileURL = "";

    try {
      fileURL = "/books-upload/" + fileUploadProcessor
          .uploadFile(file).getName();
      resp.sendRedirect("/books-upload?upload=successful");
      logger.info("Json API file has been uploaded");
    } catch (UserFileNotFound userFileNotFound) {
      logger.warn(userFileNotFound.getMessage());
      resp.sendRedirect("/books-upload?upload=failed");
    }
    apiDataHandler.setFileURL(fileURL);
    saveToDataBase.saveBooksFromFile();
  }
}
