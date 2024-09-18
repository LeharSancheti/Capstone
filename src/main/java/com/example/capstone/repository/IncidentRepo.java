package com.example.capstone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.capstone.entity.Incident;




import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface IncidentRepo extends CrudRepository<Incident, String> {


	
Optional<Incident> findByIncid(String incid);

@Query("Select distinct i.appid from Incident i where appid=:appid")
List<String> findDistinctByAppid(String appid);

@Query("SELECT t.priority AS priority, COUNT(t.incid) AS totalticket " +
        "FROM (SELECT i.priority AS priority, i.incid AS incid " +
              "FROM Incident i " +
              "WHERE i.appid = :appid AND i.date BETWEEN :startdate AND :enddate AND i.status=:status) t " +
        "GROUP BY t.priority")
List<Object[]> findTotalNumberOfPriority(
     @Param("appid") String appid,
     @Param("startdate") LocalDateTime startdate,
     @Param("enddate") LocalDateTime enddate,
     @Param("status") String status
 );

@Query("Select i.appid,COUNT(CASE WHEN i.priority=:priority THEN 1 END) AS NumberofIncident from Incident i WHERE  i.date BETWEEN :startdate AND :enddate group by i.appid")
List<Object[]> findTotalNumberIncident(@Param("priority") Integer priority,
@Param("startdate") LocalDateTime startdate,
@Param("enddate") LocalDateTime enddate		
);
}
