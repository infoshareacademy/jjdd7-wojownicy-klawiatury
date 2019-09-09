package com.infoshareacademy.wojownicy.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "book_title")
  private String title;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "author_id")
  Author author;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
  List<Genre> genres = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "favourite_book_to_user",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  List<User> usersFavourites = new ArrayList<>();

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "kind_id")
  Kind kind;

  @NotNull
  @Column(name = "cover_url")
  private String coverURL;

  @NotNull
  @Column(name = "is_reserved")
  private boolean isReserved = false;

  @NotNull
  @Column(name = "has_audio")
  private boolean hasAudio = false;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> bookReservation = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public List<User> getUsersFavourites() {
    return usersFavourites;
  }

  public void setUsersFavourites(
      List<User> usersFavourites) {
    this.usersFavourites = usersFavourites;
  }

  public Kind getKind() {
    return kind;
  }

  public void setKind(Kind kind) {
    this.kind = kind;
  }

  public String getCoverURL() {
    return coverURL;
  }

  public void setCoverURL(String coverURL) {
    this.coverURL = coverURL;
  }

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  public boolean isHasAudio() {
    return hasAudio;
  }

  public void setHasAudio(boolean hasAudio) {
    this.hasAudio = hasAudio;
  }

  public List<Reservation> getBookReservation() {
    return bookReservation;
  }

  public void setBookReservation(
      List<Reservation> bookReservation) {
    this.bookReservation = bookReservation;
  }
}