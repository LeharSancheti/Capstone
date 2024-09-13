package com.example.capstone.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Sample")
public class Incident {
	
	@Id
	private String incid;
	
	
	

    @jakarta.validation.constraints.NotNull
	private LocalDateTime date;
    
    @jakarta.validation.constraints.NotNull
	private String appid;
    
    @jakarta.validation.constraints.NotNull
	private String appname;
    
    @jakarta.validation.constraints.NotNull
	private Integer priority;
    
    @jakarta.validation.constraints.NotNull
	private String incidentname;
    
	private String  description;
	@jakarta.validation.constraints.NotNull
	private String status;
	
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getIncid() {
		return incid;
	}
	public void setIncid(String incid) {
		this.incid = incid;
	}
	public String getDate() {
		return DateUtils.formatDate(date);
	}
	public void setDate(String date) {
		LocalDateTime eventDate = DateUtils.parseDate(date);
		this.date = eventDate;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getIncidentname() {
		return incidentname;
	}
	public void setIncidentname(String incidentname) {
		this.incidentname = incidentname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Incident [incid=" + incid + ", date=" + date + ", appid=" + appid + ", priority=" + priority
				+ ", incidentname=" + incidentname + ", description=" + description + ", status=" + status + "]";
	}
	
	
}

