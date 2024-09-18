package com.example.capstone.controller;


import java.time.LocalDateTime;


import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.example.capstone.entity.DateUtils;
import com.example.capstone.entity.Incident;


import com.example.capstone.repository.IncidentRepo;
import com.example.capstone.service.IncidentService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/capstone")
public class IncidentController {
	private Logger logger = LoggerFactory.getLogger(IncidentController.class);

private final IncidentService incidentService;
private  final IncidentRepo incidentRepo;


	
	
	@Autowired
	private IncidentController(IncidentService incidentService, IncidentRepo incidentRepo) {
		
		this.incidentService = incidentService;
		this.incidentRepo = incidentRepo;
	}

	@PostMapping("/InsertMultipleIncident")
	public ResponseEntity<String> addmultipleincident(  @RequestBody List<Incident> incidents)
	{
              try {
            	  incidentService.saveAll(incidents);
            	  logger.info("Incident is saved successfully");
            	  return new ResponseEntity<>("All Incident are saved successfully",HttpStatus.OK);
              }catch(Exception e){
            	  logger.error("Error while saving incident",e);
            	  return new ResponseEntity<>("Error while saving Incident", HttpStatus.INTERNAL_SERVER_ERROR);
            	 
              }
	}
	
	@PostMapping("/InsertIncident")
	ResponseEntity<String> addIncident(@Valid @RequestBody Incident incident)
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
	Iterable<Incident> listofAllIncident()
	{  
		return  incidentRepo.findAll();
	}
	
	@GetMapping("/{incid}")
	ResponseEntity<Incident> incidentDetails(@PathVariable String incid)
	{   logger.info("API to fetch Incident via incid called");
		Optional<Incident> optional= incidentService.findByIncId(incid);
		if(optional.isPresent())
		{    logger.info("API Returned Results");
			return ResponseEntity.ok(optional.get());
		}
		else {
			logger.error(String.format("No Incident was found with the following IncidentID: %s", incid));
			return ResponseEntity.status(404).body(null);
		}
	}
		
	@GetMapping("/Query1/{appid}/{startd}/{endd}/{status}")
	ResponseEntity<Map<String, String>> totalNumberofPriority(@PathVariable String appid,@PathVariable String startd ,@PathVariable String endd,@PathVariable String status)
	{   
		try {
			
			logger.info(" Query1 API called");
			LocalDateTime startdate=DateUtils.parseDate(startd);
		    LocalDateTime enddate=DateUtils.parseDate(endd);
		    
		    if(Boolean.TRUE.equals(incidentService.findByAppId(appid)))
		    		{logger.error("Appid does not exisit");
		    	return ResponseEntity.status(404).body(null);
}
			Map<String,String> sHashMap=incidentService.totalNumberofPriority(appid,startdate,enddate,status);
			return ResponseEntity.ok(sHashMap);
			}catch (Exception e) {
				logger.error("Error in Query1: ", e);
	            return ResponseEntity.status(400).body(null);
			}
		
	}
	
	@GetMapping("/NumberofIncident")
	ResponseEntity<Map<String, Long>> totalNumberofPriorityforAppid(@RequestParam("priority")Integer priority, @RequestParam("startd") String startd, @RequestParam("endd") String endd)
	{   
		try {
			
		LocalDateTime startdate=DateUtils.parseDate(startd);
        LocalDateTime enddate=DateUtils.parseDate(endd);
        Map<String, Long> mHashMap=incidentService.totalNumberofIncident(priority,startdate,enddate);
        return ResponseEntity.ok(mHashMap);
	     }catch (Exception e) {
	    	 logger.error(String.format("Error found: %s", e));

			return ResponseEntity.status(400).body(null);
		}
		
	}
	
	

}
