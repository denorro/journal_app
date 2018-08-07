package com.dds.journal.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String userlocation = "src\\main\\resources\\static\\images\\user";
    private String journallocation = "src\\main\\resources\\static\\images\\journal";
    
	public String getUserlocation() {
		return userlocation;
	}
	
	public void setUserlocation(String userlocation) {
		this.userlocation = userlocation;
	}
	
	public String getJournallocation() {
		return journallocation;
	}
	
	public void setJournallocation(String journallocation) {
		this.journallocation = journallocation;
	}

    
}
