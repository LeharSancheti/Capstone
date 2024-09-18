package com.example.capstone.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtils {
	
	
   private DateUtils()
   {
	   
   }
	private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
	            .parseCaseInsensitive()
	            .appendOptional(DateTimeFormatter.ofPattern("d-M-yy h:mm a"))
	            .appendOptional(DateTimeFormatter.ofPattern("dd-M-yy h:mm a"))
	            .appendOptional(DateTimeFormatter.ofPattern("d-MM-yy h:mm a"))
	            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yy h:mm a"))
	            .toFormatter();

	    public static String formatDate(LocalDateTime dateTime) {
	    	return dateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy h:mm a"));
	    }

	    public static LocalDateTime parseDate(String dateString) {
	        return LocalDateTime.parse(dateString, FORMATTER);
	    }
}

