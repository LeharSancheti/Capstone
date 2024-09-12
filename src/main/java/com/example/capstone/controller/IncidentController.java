package com.example.capstone.controller;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.hibernate.persister.entity.mutation.DeleteCoordinatorStandard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.entity.DateUtils;
import com.example.capstone.entity.Incident;

import com.example.capstone.entity.Query1DTO;
import com.example.capstone.repository.IncidentRepo;
import com.example.capstone.service.IncidentService;

import jakarta.persistence.Tuple;

@RestController
@RequestMapping("/capstone")
public class IncidentController {
	
	
	@Autowired
private IncidentService incidentService;
	@Autowired
private IncidentRepo incidentRepo;
	public IncidentController() {
		
	}
	
	@GetMapping("/IncidentInfo")
	String checkapi()
	{
		return "Lhear";
	}
	
	@PostMapping("/InsertMultipleIncident")
	@ResponseBody
	String AddMultipleIncident(@RequestBody ArrayList<Incident> incidents)
	{
		incidentService.saveAll(incidents);
		return "Saved All incident";
	}
	
	@PostMapping("/InsertIncident")
	@ResponseBody
	String AddIncident(@RequestBody Incident incident)
	{   System.out.println(incident.getDate());
		
		incidentService.save(incident);
		return "Incident Added";
	}
	
	@GetMapping("/GetAllIncidents")
	Iterable<Incident> ListofAllIncident()
	{  
		return  incidentRepo.findAll();
	}
	
	@GetMapping("/{incid}")
	Optional<Incident> IncidentDetails(@PathVariable String incid)
	{  
		return incidentService.findByIncId(incid);
	}
		
	@GetMapping("/Query1/{appid}/{startd}/{endd}/{status}")
	HashMap<String, String> TotalNumberofPriority(@PathVariable String appid,@PathVariable String startd,@PathVariable String endd,@PathVariable String status)
	{   System.out.println("Controller working");
		LocalDateTime startdate=DateUtils.parseDate(startd);
	    LocalDateTime enddate=DateUtils.parseDate(endd);
		return incidentService.TotalNumberofPriority(appid,startdate,enddate,status);
	}
	
	

}
