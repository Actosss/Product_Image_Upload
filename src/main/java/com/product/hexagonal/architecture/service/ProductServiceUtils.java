package com.product.hexagonal.architecture.service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ProductServiceUtils {

	private String fileDownloadUri;
	private String fileName;
	private final String storageDirectoryPath = "C://Users//Kevin//git//Product-Crud-Server_Side//Images";

	public String uploadToLocalFileSystem(MultipartFile imageFile) {
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		this.setFileName(fileName);
		Path storageDirectory = Paths.get(storageDirectoryPath);
		if (!Files.exists(storageDirectory)) {
			try {
				Files.createDirectories(storageDirectory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Path destination = Paths.get(storageDirectory.toString() + "//" + fileName);
		try {
			Files.copy(imageFile.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/getImages/")
				.path(fileName).toUriString();
		this.fileDownloadUri = fileDownloadUri;
		return fileDownloadUri;
	}

	public byte[] getImageWithMediaType(String imageName) {
		Path destination = Paths.get(storageDirectoryPath + "//" + imageName);
		try {
			return IOUtils.toByteArray(destination.toUri());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStorageDirectoryPath() {
		return storageDirectoryPath;
	}
}
