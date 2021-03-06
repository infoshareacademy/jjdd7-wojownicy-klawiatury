package com.infoshareacademy.wojownicy.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "Author.findAuthorsList",
        query = "SELECT a FROM Author a"
    ),
    @NamedQuery(
        name = "Author.findAuthorByName",
        query = "SELECT a FROM Author a WHERE a.authorName = :authorName"
    ),
})
@Entity
@Table(name = "author", indexes = {
    @Index(columnList = "author_name", name = "author_name_index")
})
public class Author {

  @Id
  @Column(name = "author_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long authorId;

  @NotNull
  @Column(name = "author_name")
  private String authorName;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  private List<Book> books = new ArrayList<>();

  @OneToOne(cascade =  CascadeType.ALL)
  @JoinColumn(name="reservation_counter")
  ReservationsAuthorStatistics reservationsAuthorStatistics;

  public ReservationsAuthorStatistics getReservationsAuthorStatistics() {
    return reservationsAuthorStatistics;
  }

  public void setReservationsAuthorStatistics(
      ReservationsAuthorStatistics reservationsAuthorStatistics) {
    this.reservationsAuthorStatistics = reservationsAuthorStatistics;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}
