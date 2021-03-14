package es.hpgMethyl.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

import es.hpgMethyl.entities.User;

public class FileUtils {
	
	public static String USERS_FILES_BASE_PATH = "/data/tomcat/files/users/";

	private FileUtils() {};
	
	public static void saveFileUploadedByUser(User user, Part filePart, String fileName) throws IOException {
		
		String directoryPath = USERS_FILES_BASE_PATH + user.getId();
		
		saveFile(filePart, directoryPath, fileName);
	}
	
	public static void saveFile(Part filePart, String directoryPath, String fileName) throws IOException {
		
		OutputStream out = null;
		InputStream filecontent = null;
		
		try {
			File directory = new File(directoryPath);
		    if (! directory.exists()){
		    	directory.mkdirs();
		    }
			
			File newFile = new File(directory + "/" + fileName);
			
			out = new FileOutputStream(newFile);
			
			filecontent = filePart.getInputStream();
			int read = 0;
		    final byte[] bytes = new byte[1024];
		    
		    while ((read = filecontent.read(bytes)) != -1) {
		    	out.write(bytes, 0, read);
		    }
			
		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
		}
	}
}