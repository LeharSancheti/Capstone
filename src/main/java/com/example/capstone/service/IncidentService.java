package com.example.capstone.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.capstone.entity.Incident;
import com.example.capstone.entity.Priority;

import com.example.capstone.repository.IncidentRepo;

import ch.qos.logback.classic.Logger;
import jakarta.persistence.Tuple;
@Service
public class IncidentService {
	@Autowired
	private IncidentRepo incidentRepo;

	public void save(Incident incident) {
		incidentRepo.save(incident);
	}

	public void saveAll(ArrayList<Incident> incidents) {
		for (Incident incident : incidents) {
			incidentRepo.save(incident);
		}
		
	}

	public Optional<Incident> findByIncId(String incid) {
	
		return incidentRepo.findByIncid(incid);
	}

	public HashMap<String, String> TotalNumberofPriority(String appid, LocalDateTime startdate,LocalDateTime enddate, String status) {
		
		List<Object[]> obj=incidentRepo.findTotalNumberOfPriority(appid,startdate,enddate);
		System.out.println(obj);
		HashMap<String, String> mpHashMap=new HashMap<String, String>();
		mpHashMap.put("APPID",appid);
		for (Object[] tuple : obj) {
			int priority = (int) tuple[0];
			long totalTicket= (long) tuple[1];
			mpHashMap.put("Priority"+ String.valueOf(priority) , String.valueOf(totalTicket) );
            
		}
		
		return mpHashMap;
		
	}

	public HashMap<String, Long> TotalNumberofIncident(Integer priority, LocalDateTime startd, LocalDateTime endd) {
		List<Object[]> objects= incidentRepo.findTotalNumberIncident(priority,startd,endd);
		HashMap<String, Long> mpHashMap=new HashMap<String, Long>();
		for(Object[] obj:objects)
		{  String appidString=(String) obj[0];
	       Long numberofIncidetInteger=(Long) obj[1];
	       mpHashMap.put(appidString, numberofIncidetInteger);
		}
		 return mpHashMap;	
	}
	
	}
	

