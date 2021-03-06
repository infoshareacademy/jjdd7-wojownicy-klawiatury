package com.infoshareacademy.wojownicy.service;

import com.infoshareacademy.wojownicy.Config;
import com.infoshareacademy.wojownicy.clas.Book;
import java.util.Comparator;
import java.util.List;

public class BookSorter {

  private Config config = new Config();

  public void sortByAuthor(List<Book> listOfBooks) {
    config.loadConfig("sortByAuthor");
    String property = config.getProperty();
    if (property.equals("ASC")) {
      listOfBooks.sort(Comparator.comparing(Book::getAuthor));
    } else if (property.equals("DESC")) {
      listOfBooks.sort(Comparator.comparing(Book::getAuthor).reversed());
    }
  }

  public void sortByTitle(List<Book> listOfBooks) {
    config.loadConfig("sortByTitle");
    String property = config.getProperty();
    if (property.equals("ASC")) {
      listOfBooks.sort(Comparator.comparing(Book::getTitle));
    } else if (property.equals("DESC")) {
      listOfBooks.sort(Comparator.comparing(Book::getTitle).reversed());
    }
  }

  public void sortByGenre(List<Book> listOfBooks) {
    config.loadConfig("sortByGenre");
    String property = config.getProperty();
    if (property.equals("ASC")) {
      listOfBooks.sort(Comparator.comparing(Book::getGenre));
    } else if (property.equals("DESC")) {
      listOfBooks.sort(Comparator.comparing(Book::getGenre).reversed());
    }
  }

  public void sortByKind(List<Book> listOfBooks) {
    config.loadConfig("sortByKind");
    String property = config.getProperty();
    if (property.equals("ASC")) {
      listOfBooks.sort(Comparator.comparing(Book::getKind));
    } else if (property.equals("DESC")) {
      listOfBooks.sort(Comparator.comparing(Book::getKind).reversed());
    }
  }
}
