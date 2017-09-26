package com.fno.fnoapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fno.fnoapp.fileservice.FileService;
import com.fno.fnoapp.model.UploadFileModel;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity <?> uploadFiles(@ModelAttribute UploadFileModel fileModel){
		
		try {
			

            fileService.saveFiles(Arrays.asList(fileModel.getFiles()));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully uploaded!", HttpStatus.OK);
}
	
	@GetMapping("/download")
	public void downloadFiles(@Context HttpServletResponse resp){
		
		//ResponseBuilder response = Response.ok(resp);
		try{
			fileService.getFiles(resp);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
		
	
	}
