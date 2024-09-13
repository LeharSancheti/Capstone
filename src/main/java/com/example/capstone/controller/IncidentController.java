package com.example.capstone.controller;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Not;
import org.hibernate.persister.entity.mutation.DeleteCoordinatorStandard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.entity.DateUtils;
import com.example.capstone.entity.Incident;


import com.example.capstone.repository.IncidentRepo;
import com.example.capstone.service.IncidentService;

import jakarta.persistence.Tuple;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/capstone")
public class IncidentController {
	private Logger logger = LoggerFactory.getLogger(IncidentController.class);
	
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
	public ResponseEntity<String> AddMultipleIncident(  @RequestBody ArrayList<Incident> incidents)
	{
              try {
            	  incidentService.saveAll(incidents);
            	  logger.info("Incident is saved successfully");
            	  return new ResponseEntity<>("All Incident are saved successfully",HttpStatus.OK);
              }catch(Exception e){
            	  logger.error("Error while saving incident",e);
            	  return new ResponseEntity<String>("Error while saving Incident",HttpStatus.INTERNAL_SERVER_ERROR);
              }
	}
	
	@PostMapping("/InsertIncident")
	@ResponseBody
	ResponseEntity<String> AddIncident(@Valid @RequestBody Incident incident)
	{   
		try {
			incidentService.save(incident);
			logger.info("Incidents are saved successfully");
			return new ResponseEntity<>("Incident Save Successfully",HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error while saving incident",e);
			return new ResponseEntity<>("Error while Saving Incident",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
	
	@GetMapping("/GetAllIncidents")
	Iterable<Incident> ListofAllIncident()
	{  
		return  incidentRepo.findAll();
	}
	
	@GetMapping("/{incid}")
	ResponseEntity<Incident> IncidentDetails(@PathVariable String incid)
	{   logger.info("API to fetch Incident via incid called");
		Optional<Incident> optional= incidentService.findByIncId(incid);
		if(optional.isPresent())
		{    logger.info("API Returned Results");
			return ResponseEntity.ok(optional.get());
		}
		else {
			logger.info("No Incindent was Found with following IncidentID "+incid);
			return ResponseEntity.status(404).body(null);
		}
	}
		
	@GetMapping("/Query1/{appid}/{startd}/{endd}/{status}")
	ResponseEntity<HashMap<String, String>> TotalNumberofPriority(@PathVariable String appid,@PathVariable String startd,@PathVariable String endd,@PathVariable String status)
	{   
		try {
			logger.info(" Query1 API called");
			LocalDateTime startdate=DateUtils.parseDate(startd);
		    LocalDateTime enddate=DateUtils.parseDate(endd);
			HashMap<String,String> sHashMap=incidentService.TotalNumberofPriority(appid,startdate,enddate,status);
			return ResponseEntity.ok(sHashMap);
			}catch (Exception e) {
				logger.error("Error in Query1: ", e);
	            return ResponseEntity.status(400).body(null);
			}
		
	}
	
	@GetMapping("/NumberofIncident")
	ResponseEntity<HashMap<String, Long>> TotalNumberofPriorityforAppid(@RequestParam("priority")Integer priority, @RequestParam("startd") String startd, @RequestParam("endd") String endd)
	{   
		try {
			
		LocalDateTime startdate=DateUtils.parseDate(startd);
        LocalDateTime enddate=DateUtils.parseDate(endd);
        HashMap<String, Long> mHashMap=incidentService.TotalNumberofIncident(priority,startdate,enddate);
        return ResponseEntity.ok(mHashMap);
	     }catch (Exception e) {
			logger.error("Error found"+e);
			return ResponseEntity.status(400).body(null);
		}
		
	}
	
	

}
