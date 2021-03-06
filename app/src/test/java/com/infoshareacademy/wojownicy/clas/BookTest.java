package com.infoshareacademy.wojownicy.clas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BookTest {

  @Test
  void getFavourite() {
    Book book = new Book();
    book.setFavourite(true);

    assertThat(book.getFavourite()).isTrue()
        .isNotNull();
  }

  @Test
  void setFavourite() {
    Book book = new Book();
    book.setFavourite(false);

    assertThat(book.getFavourite()).isFalse()
        .isNotNull();
  }

  @Test
  void getId() {
    Book book = new Book();
    book.setId((long) 1);

    assertThat(book.getId()).isEqualTo(1)
        .isNotNull();
  }

  @Test
  void setId() {
    Book book = new Book();
    book.setId((long) 1);

    assertThat(book.getId()).isEqualTo(1)
        .isNotNull();
  }

  @Test
  void getKind() {
    Book book = new Book("drama", "Romeo and Juliet", "William Shakespeare", true, "tragedy");

    String output = book.getKind();

    assertThat(output).isEqualToIgnoringCase("Drama")
        .containsIgnoringCase("RaM")
        .isNotEmpty();
  }

  @Test
  void setKind() {
    Book book = new Book();

    book.setKind("epic");

    assertThat(book.getKind()).isEqualToIgnoringCase("EpIc")
        .containsIgnoringCase("PI")
        .isNotEmpty();
  }

  @Test
  void getTitle() {
    Book book = new Book("drama", "Romeo and Juliet", "William Shakespeare", true, "tragedy");

    String output = book.getTitle();

    assertThat(output).isEqualToIgnoringCase("romeo AND juliet")
        .containsIgnoringCase("LiEt")
        .isNotEmpty();
  }

  @Test
  void setTitle() {
    Book book = new Book();

    book.setTitle("Harry Potter");

    assertThat(book.getTitle()).isEqualToIgnoringCase("haRRy pOtter")
        .containsIgnoringCase("Otter")
        .isNotEmpty();
  }

  @Test
  void getAuthor() {
    Book book = new Book("drama", "Romeo and Juliet", "William Shakespeare", true, "tragedy");

    String output = book.getAuthor();

    assertThat(output).isEqualToIgnoringCase("wiLLiam SHakeSpeare")
        .containsIgnoringCase("shakes")
        .isNotEmpty();
  }

  @Test
  void setAuthor() {
    Book book = new Book();

    book.setAuthor("Charles Bukowski");

    assertThat(book.getAuthor()).isEqualToIgnoringCase("chaRleS bUkOwSki")
        .containsIgnoringCase("Les Buk")
        .isNotEmpty();
  }

  @Test
  void getHasAudio() {
    Book book = new Book("drama", "Romeo and Juliet", "William Shakespeare", true, "tragedy");

    Boolean output = book.getHasAudio();

    assertThat(output).isTrue()
        .isNotNull();
  }

  @Test
  void setHasAudio() {
    Book book = new Book();

    book.setHasAudio(false);

    assertThat(book.getHasAudio()).isFalse()
        .isNotNull();
  }

  @Test
  void getGenre() {
    Book book = new Book("drama", "Romeo and Juliet", "William Shakespeare", true, "tragedy");

    String output = book.getGenre();

    assertThat(output).isEqualToIgnoringCase("TrAgEdY")
        .containsIgnoringCase("aGE")
        .isNotEmpty();
  }

  @Test
  void setGenre() {
    Book book = new Book();

    book.setGenre("comedy");

    assertThat(book.getGenre()).isEqualToIgnoringCase("CoMEDy")
        .containsIgnoringCase("mEd")
        .isNotEmpty();
  }
}