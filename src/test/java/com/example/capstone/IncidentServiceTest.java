package com.example.capstone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.capstone.entity.Incident;
import com.example.capstone.repository.IncidentRepo;
import com.example.capstone.service.IncidentService;



class IncidentServiceTest {
	
	@InjectMocks
	IncidentService incidentService;
	
	@Mock
	IncidentRepo incidentRepo;
	
	@BeforeEach
	void setup()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
    void testFindByAppId_ReturnsTrue_WhenListIsEmpty() {
        String appid = "APP1234";
        when(incidentRepo.findDistinctByAppid(appid)).thenReturn(Collections.emptyList());

        Boolean result = incidentService.findByAppId(appid);

        assertTrue(result);
        verify(incidentRepo, times(1)).findDistinctByAppid(appid);
    }
	
	 @Test
	    void testFindByAppId_ReturnsFalse_WhenListIsNotEmpty() {
	        String appid = "APP1122";
	        List<String> nonEmptyList = List.of("incident1", "incident2");
	        when(incidentRepo.findDistinctByAppid(appid)).thenReturn(nonEmptyList);

	        Boolean result = incidentService.findByAppId(appid);

	        assertFalse(result);
	        verify(incidentRepo, times(1)).findDistinctByAppid(appid);
	    }
	 
	 
	 
	 @Test
	     void testTotalNumberofIncident() {
	        Integer priority = 1;
	        LocalDateTime startd = LocalDateTime.of(2023, 1, 1, 0, 0);
	        LocalDateTime endd = LocalDateTime.of(2023, 12, 31, 23, 59);

	        List<Object[]> mockResult = Arrays.asList(
	            new Object[]{"APP1234", 2L},
	            new Object[]{"APP5678", 0L}
	        );

	        when(incidentRepo.findTotalNumberIncident(priority, startd, endd)).thenReturn(mockResult);

	        Map<String, Long> expectedMap = new HashMap<>();
	        expectedMap.put("APP1234", 2L);
	        expectedMap.put("APP5678", 0L);

	        Map<String, Long> result = incidentService.totalNumberofIncident(priority, startd, endd);

	        assertEquals(expectedMap, result);
	        verify(incidentRepo, times(1)).findTotalNumberIncident(priority, startd, endd);
	    }
	 
	 
	   @Test
	   void testTotalNumberofPriority() {
	        String appid = "APP1234";
	        LocalDateTime startdate = LocalDateTime.of(2023, 1, 1, 0, 0);
	        LocalDateTime enddate = LocalDateTime.of(2023, 12, 31, 23, 59);
	        String date = "2023-09-18";

	        List<Object[]> mockResult = Arrays.asList(
	            new Object[]{1, 10L},
	            new Object[]{2, 5L}
	        );

	        when(incidentRepo.findTotalNumberOfPriority(appid, startdate, enddate, date)).thenReturn(mockResult);

	        Map<String, String> expectedMap = new HashMap<>();
	        expectedMap.put("APPID", appid);
	        expectedMap.put("1", "10");
	        expectedMap.put("2", "5");

	        Map<String, String> result = incidentService.totalNumberofPriority(appid, startdate, enddate, date);

	        assertEquals(expectedMap, result);
	        verify(incidentRepo, times(1)).findTotalNumberOfPriority(appid, startdate, enddate, date);
	    }
	   
	   @Test
	    void testFindByIncId_Found() {
	        String incid = "INC46729";
	        Incident incident = new Incident();
	        when(incidentRepo.findByIncid(incid)).thenReturn(Optional.of(incident));

	        Optional<Incident> result = incidentService.findByIncId(incid);

	        assertTrue(result.isPresent());
	        assertEquals(incident, result.get());
	        verify(incidentRepo, times(1)).findByIncid(incid);
	    }
	   
	   @Test
	    void testFindByIncId_NotFound() {
	        String incid = "INC1234";
	        when(incidentRepo.findByIncid(incid)).thenReturn(Optional.empty());

	        Optional<Incident> result = incidentService.findByIncId(incid);

	        assertFalse(result.isPresent());
	        verify(incidentRepo, times(1)).findByIncid(incid);
	    }
	
	
	 
	
	}
	


