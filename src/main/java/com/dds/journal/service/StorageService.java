package com.dds.journal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();
	
	void store(Object ojb, String storeLocation, List<MultipartFile> files);	
	
	void deleteAll();

}
