package com.infoshareacademy;

import com.infoshareacademy.menu.SingleBookViewMenu;
import java.util.List;
import java.util.Scanner;

public class BooksPrinter {

  public static void printListOfBooks(List<Book> listOfBooks) {
    Scanner scanner = new Scanner(System.in);
    BookSorter bookSorter = new BookSorter();
    bookSorter.sortByTitle(listOfBooks);
    int lines = 20;
    int bookCounter = 0;
    int currentLine;
    int currentPage = 0;
    String nextPageCheck;
    do {
      if (currentPage > 0) {
        System.out
            .println(
                " \nEnter -> Kontynuuj wyświetlanie || Z -> Zakończ || W -> Wyświetl szczegóły książki ");
        nextPageCheck = scanner.nextLine();
        if (nextPageCheck.equalsIgnoreCase("Z")) {
          break;
        }
        if (nextPageCheck.equalsIgnoreCase("W")) {
          SingleBookViewMenu singleBookViewMenu = new SingleBookViewMenu();
          singleBookViewMenu.selectBook();
          break;
        }
      }
      currentLine = 0;
      do {
        currentPage++;
        currentLine++;
        System.out.println(
            bookCounter + 1 + ". \"" + listOfBooks.get(bookCounter).getTitle() + "\" - "
                + listOfBooks
                .get(bookCounter).getAuthor());
        bookCounter++;
      } while (currentLine < lines && currentPage < listOfBooks.size());
    } while (currentPage < listOfBooks.size());
  }
}

