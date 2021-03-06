package com.infoshareacademy.wojownicy.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(
        name = "Book.findBookList",
        query = "SELECT b FROM Book b"
    ),
    @NamedQuery(
        name = "Book.listCount",
        query = "SELECT COUNT (book_title) FROM Book b"
    ),
    @NamedQuery(
        name = "Book.LirykaBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 1"
    ),
    @NamedQuery(
        name = "Book.EpikaBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 2"
    ),
    @NamedQuery(
        name = "Book.DramatBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 3"
    ),
    @NamedQuery(
        name = "Book.AudioBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.hasAudio = 1"
    ),
    @NamedQuery(
        name = "Book.LirykaAudioBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 1 AND b.hasAudio=1"
    ),
    @NamedQuery(
        name = "Book.EpikaAudioBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 2 AND b.hasAudio=1"
    ),
    @NamedQuery(
        name = "Book.DramatAudioBookListCount",
        query = "SELECT COUNT (book_title) FROM Book b WHERE b.kind = 3 AND b.hasAudio=1"
    ),
    @NamedQuery(
        name = "Book.getPartOfBookList",
        query = "SELECT b FROM Book b"
    ),
    @NamedQuery(
        name = "Book.getLirykaPartOfBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 1"
    ),
    @NamedQuery(
        name = "Book.getEpikaPartOfBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 2"
    ),
    @NamedQuery(
        name = "Book.getDramatPartOfBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 3"
    ),
    @NamedQuery(
        name = "Book.LiveSearch",
        query = "SELECT b from Book as b INNER JOIN b.author AS a WHERE b.title LIKE :searchParam OR a.authorName LIKE :searchParam"
    ),
    @NamedQuery(
        name = "Book.getPartOfAudioBooks",
        query = "SELECT b FROM Book b WHERE b.hasAudio = 1"
    ),
    @NamedQuery(
        name = "Book.LirykaAudioBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 1 AND b.hasAudio=1"
    ),
    @NamedQuery(
        name = "Book.EpikaAudioBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 2 AND b.hasAudio=1"
    ),
    @NamedQuery(
        name = "Book.DramatAudioBookList",
        query = "SELECT b FROM Book b WHERE b.kind = 3 AND b.hasAudio=1"
    ),

})
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

  @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  @JoinColumn(name = "author_id")
  private Author author;

  @ManyToMany(cascade ={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  @JoinTable(
      name = "book_genre",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
  private List<Genre> genres = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "favourite_book_to_user",
      joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
  private List<User> usersFavourites = new ArrayList<>();

  @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  @JoinColumn(name = "kind_id")
  private Kind kind;

  @Column(name = "cover_url")
  private String coverURL;

  @Column(name = "thumbnail")
  private String thumbnail;

  @NotNull
  @Column(name = "is_reserved", columnDefinition = "Boolean")
  private boolean isReserved = false;

  @NotNull
  @Column(name = "has_audio", columnDefinition = "Boolean")
  private boolean hasAudio = false;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name ="reservation_counter", unique = true)
  ReservationsBookStatistics reservationsBookStatistics;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Reservation> bookReservation = new ArrayList<>();

  public ReservationsBookStatistics getReservationsBookStatistics() {
    return reservationsBookStatistics;
  }

  public void setReservationsBookStatistics(
      ReservationsBookStatistics reservationsBookStatistics) {
    this.reservationsBookStatistics = reservationsBookStatistics;
  }

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

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public boolean isReserved() {
    return isReserved;
  }

  public void setReserved(boolean reserved) {
    isReserved = reserved;
  }

  public boolean isAudio() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Book book = (Book) o;
    return hasAudio == book.hasAudio &&
        title.equals(book.title) &&
        author.equals(book.author) &&
        genres.equals(book.genres) &&
        kind.equals(book.kind) &&
        Objects.equals(coverURL, book.coverURL) &&
        Objects.equals(thumbnail, book.thumbnail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, genres, kind, coverURL, thumbnail, hasAudio);
  }
}
