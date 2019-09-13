package com.infoshareacademy.wojownicy.servlet;

import com.infoshareacademy.wojownicy.domain.entity.Book;
import com.infoshareacademy.wojownicy.freemarker.TemplateProvider;
import com.infoshareacademy.wojownicy.service.BookListService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book-list")
public class BooksListServlet extends HttpServlet {

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private BookListService bookListService;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html; charset=UTF-8");
    Template template = templateProvider.getTemplate(getServletContext(), "book-list.ftlh");
    long part = Integer.parseInt(req.getParameter("part"));
    long from = (part - 1) * 10;
    long to = (from - 1) + 10;

    List<Book> partOfBooks = bookListService.partOfBooks(from, to);
    Map<String, Object> pagesMap = bookListService.pages(part);

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("books", partOfBooks);
    dataModel.put("page", pagesMap);

    PrintWriter printWriter = resp.getWriter();
    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      e.printStackTrace();
    }
  }
}
