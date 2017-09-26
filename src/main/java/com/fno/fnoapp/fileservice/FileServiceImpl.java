package com.fno.fnoapp.fileservice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.web.multipart.MultipartFile;

public class FileServiceImpl implements FileService {

	private final File uploadFolder = "";

	private final File downloadFolder = "";
	@Override
	public void saveFiles(List<MultipartFile> uploadedFiles) {
		for (MultipartFile file : uploadedFiles) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            
            try{
            	
            	byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadFolder + file.getOriginalFilename());
                Files.write(path, bytes);
            
            }catch(IOException ie){
            	ie.printStackTrace();
            }

            
		}
	}

	@Override
	public void getFiles(HttpServletResponse response) {
		
		//ResponseBuilder response = Response.ok();
		
		
		// Set the content type based to zip
				response.setContentType("Content-type: text/zip");
				response.setHeader("Content-Disposition",
						"attachment; filename=mytest.zip");

				// List of files to be downloaded
				
					File[] filesToDownload = downloadFolder.listFiles();
				

				//ServletOutputStream out = response.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));

				for (File file : filesToDownload) {

					//System.out.println("Adding " + file.getName());
					zos.putNextEntry(new ZipEntry(file.getName()));

					// Get the file
					FileInputStream fis = null;
					try {
						fis = new FileInputStream(file);

					} catch (FileNotFoundException fnfe) {
						// If the file does not exists, write an error entry instead of
						// file
						// contents
						//zos.write(("ERRORld not find file " + file.getName())
								//.getBytes());
						zos.closeEntry();
						System.out.println("Couldfind file "
								+ file.getAbsolutePath());
						continue;
					}

					BufferedInputStream buf = new BufferedInputStream(fis);

					// Write the contents of the file
					int data = 0;
					while ((data = buf.read()) != -1) {
						zos.write(data);
					}
					buf.close();

					zos.closeEntry();
					System.out.println("Finishedng file " + file.getName());
				}

				zos.close();
			}
		
		
	}

