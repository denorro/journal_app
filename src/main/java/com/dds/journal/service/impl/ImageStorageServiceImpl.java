package com.dds.journal.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dds.journal.configuration.StorageProperties;
import com.dds.journal.domain.User;
import com.dds.journal.exceptions.StorageException;
import com.dds.journal.service.StorageService;

@Service("imageStorageService")
public class ImageStorageServiceImpl implements StorageService{
	
	private final Path userLocation;
	private final Path journalLocation;
	private Path rootLocation;

    @Autowired
    public ImageStorageServiceImpl(StorageProperties properties) {
        this.userLocation = Paths.get(properties.getUserlocation());
        this.journalLocation = Paths.get(properties.getJournallocation());
        this.rootLocation = null;
    }
    
    @Override
	public void store(Object obj, String fileDirectory, List<MultipartFile> files) {
    	this.rootLocation = (obj.getClass() == User.class) ? this.userLocation : this.journalLocation;
		String filename = null;
		for (MultipartFile file : files) {
			try {
				filename = file.getOriginalFilename();
				if (file.isEmpty()) {
					throw new StorageException("Failed to store empty file " + filename);
				}
				if (filename.contains("..")) {
					throw new StorageException(
							"Cannot store file with relative path outside current directory " + filename);
				}
				Path p = Paths.get(this.rootLocation.resolve(fileDirectory).toString());
				if(!Files.exists(p)) {
					Files.createDirectory(p);
				}				
				Files.copy(file.getInputStream(), p.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new StorageException("Failed to store file " + filename, e);
			}
		}
	}

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(userLocation.toFile());
        FileSystemUtils.deleteRecursively(journalLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(userLocation);
            Files.createDirectories(journalLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
