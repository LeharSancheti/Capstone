package com.example.capstone.entity;



import java.time.LocalDateTime;






import jakarta.persistence.Entity;


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
    
    @jakarta.validation.constraints.NotNull
	private String  description;
	
	@jakarta.validation.constraints.NotNull
	private String status;
	
	public Incident()
	{
		
	}
	
	
	public Incident(String incid, @jakarta.validation.constraints.NotNull LocalDateTime date,
			@jakarta.validation.constraints.NotNull String appid,
			@jakarta.validation.constraints.NotNull String appname,
			@jakarta.validation.constraints.NotNull Integer priority,
			@jakarta.validation.constraints.NotNull String incidentname,
			@jakarta.validation.constraints.NotNull String description,
			@jakarta.validation.constraints.NotNull String status) {
		super();
		this.incid = incid;
		this.date = date;
		this.appid = appid;
		this.appname = appname;
		this.priority = priority;
		this.incidentname = incidentname;
		this.description = description;
		this.status = status;
	}
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

