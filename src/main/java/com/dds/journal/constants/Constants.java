package com.dds.journal.constants;

import java.util.Arrays;

public class Constants {
	
	public static String[] US_STATES = {
			"California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", 
			"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", 
			"Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", 
			"Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", 
			"Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", 
			"New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", 
			"Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", 
			"Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };
	
	public static String[] US_STATES() {
		String[] states = US_STATES;
		Arrays.sort(states);
		return states;
	}

	public static final String USER_IMAGES_DIRECTORY = "/images/user/";
	public static final String JOURNAL_IMAGES_DIRECTORY = "/images/journal/";
	public static final String DEFAULT_PROFILE_IMAGE = "/images/profile_img.png";
	public static final String APP_EMAIL = "denorrostallworth@gmail.com";
}
