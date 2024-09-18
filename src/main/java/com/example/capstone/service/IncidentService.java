package com.example.capstone.service;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;




import org.springframework.stereotype.Service;

import com.example.capstone.entity.Incident;


import com.example.capstone.repository.IncidentRepo;


@Service
public class IncidentService {
	
	private final IncidentRepo incidentRepo;
	
	 
	    public IncidentService(IncidentRepo incidentRepository) {
	        this.incidentRepo = incidentRepository;
	    }

	public void save(Incident incident) {
		incidentRepo.save(incident);
	}

	public void saveAll(List<Incident> incidents) {
		for (Incident incident : incidents) {
			incidentRepo.save(incident);
		}
		
	}

	public Optional<Incident> findByIncId(String incid) {
	  
		return incidentRepo.findByIncid(incid);
	}

	public Map<String, String> totalNumberofPriority(String appid, LocalDateTime startdate,LocalDateTime enddate,String date) {
		
		List<Object[]> obj=incidentRepo.findTotalNumberOfPriority(appid,startdate,enddate,date);
		Map<String, String> mpHashMap=new HashMap<>();
		mpHashMap.put("APPID",appid);
		for (Object[] tuple : obj) {
			int priority = (int) tuple[0];
			long totalTicket= (long) tuple[1];
			mpHashMap.put(String.valueOf(priority) , String.valueOf(totalTicket) );
            
		}
		
		return mpHashMap;
		
	}

	public Map<String, Long> totalNumberofIncident(Integer priority, LocalDateTime startd, LocalDateTime endd) {
		List<Object[]> objects= incidentRepo.findTotalNumberIncident(priority,startd,endd);
		Map<String, Long> mpHashMap=new HashMap<>();
		for(Object[] obj:objects)
		{  String appidString=(String) obj[0];
	       Long numberofIncidetInteger=(Long) obj[1];
	       mpHashMap.put(appidString, numberofIncidetInteger);
		}
		 return mpHashMap;	
	}

	public Boolean findByAppId(String appid) {
	    List<String> optional=incidentRepo.findDistinctByAppid(appid);
		return optional.isEmpty();
		 
		
	}
	
	}
	

