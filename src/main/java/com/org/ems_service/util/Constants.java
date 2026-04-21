/**
 * 
 */
package com.org.ems_service.util;

import java.time.format.DateTimeFormatter;

/**
 * 
 */
public class Constants {
	public static final String SAVE = "Saved Successfully";
	public static final String UPDATE = "Updated Successfully";
	public static final String DELETE = "Deleted Successfully";

	public static String getNameExistResponse(String module, String name) {
		return module + " is present with " + name + " name , you must try with another name.";
	}

	// Define your custom format
	public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String DEFAULT_TIME_ZONE = "Asia/Kolkata";

}
