package com.infoshareacademy.wojownicy.dao;

import com.infoshareacademy.wojownicy.domain.entity.ReservationsAuthorStatistics;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Stateless
public class ReservationsAuthorStatisticsDao {

  @PersistenceContext
  EntityManager entityManager;

  public void addReservationToAuthor(ReservationsAuthorStatistics reservationsAuthorStatistics){
    entityManager.merge(reservationsAuthorStatistics);
  }

  public ReservationsAuthorStatistics getReservationsOfAuthor(long id){
    return entityManager.find(ReservationsAuthorStatistics.class, id);
  }

  public List<ReservationsAuthorStatistics> getReservationsAuthorList(){
    return entityManager.createNamedQuery("StatisticsAuthor.findAuthorStatisticsList").getResultList();

  }


}
