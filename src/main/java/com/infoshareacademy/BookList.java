package com.infoshareacademy;

import static com.infoshareacademy.Parser.getBooks;

import java.util.List;
import java.util.Scanner;

class BookList {

  void listBooks() {
    Scanner scanner = new Scanner(System.in);
    List<Book> books = getBooks();
    int lines = 20;
    int bookCounter = 0;
    int currentLine;
    int currentPage = 0;
    String nextPageCheck;
    do {
      if (currentPage > 0) {
        System.out
            .println("Enter- Kontynuuj wyświetlanie Z- Zakończ W- wyświetl szczegóły książki ");
        nextPageCheck = scanner.nextLine();
        if (nextPageCheck.equals("Z") || nextPageCheck.equals("z")) {
          Menu menu = new Menu();
          menu.menu();
          break;
        }
        if ((nextPageCheck.equals("W") || nextPageCheck.equals("w"))) {
          SingleBookView singleBookView = new SingleBookView();
          singleBookView.selectBook();
          break;
        }
      }
      currentLine = 0;
      do {
        currentPage++;
        currentLine++;
        System.out.println(
            bookCounter + ". \"" + books.get(bookCounter).getTitle() + "\" - " + books
                .get(bookCounter).getAuthor());
        bookCounter++;
      } while (currentLine < lines && currentPage < books.size());
    } while (currentPage < books.size());
  }
}
