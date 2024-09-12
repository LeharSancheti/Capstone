package com.example.capstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.capstone.entity.Incident;
import com.example.capstone.entity.Query1DTO;

import jakarta.persistence.Tuple;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IncidentRepo extends CrudRepository<Incident, String> {
Optional<Incident> findByIncid(String incid);
@Query("SELECT t.priority AS priority, COUNT(t.incid) AS totalticket " +
        "FROM (SELECT i.priority AS priority, i.incid AS incid " +
              "FROM Incident i " +
              "WHERE i.appid = :appid AND i.date BETWEEN :startdate AND :enddate) t " +
        "GROUP BY t.priority")
List<Object[]> findTotalNumberOfPriority(
     @Param("appid") String appid,
     @Param("startdate") LocalDateTime startdate,
     @Param("enddate") LocalDateTime enddate
 );
}
