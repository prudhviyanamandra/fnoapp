package com.fno.fnoapp.fileservice;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public void saveFiles(List<MultipartFile> uploadedFiles);
	
	public void getFiles(HttpServletResponse response);
}
