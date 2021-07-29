package es.hpgMethyl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.primefaces.model.file.UploadedFile;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;

public class FileUtils {

	public static String USERS_FILES_BASE_PATH = "/data/tomcat/files/users/";

	private FileUtils() {};

	public static File saveFileUploadedByUser(User user, UploadedFile file, String usersFilesBasePath, String fileName) throws IOException {

		String directoryPath = concatenatePath(usersFilesBasePath, user.getId().toString());

		return saveFile(file, directoryPath, fileName);
	}

	public static File saveFile(UploadedFile file, String directoryPath, String fileName) throws IOException {

		OutputStream out = null;
		InputStream filecontent = null;

		try {
			File directory = new File(directoryPath);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			File newFile = new File(directory + "/" + fileName);

			out = new FileOutputStream(newFile);

			filecontent = file.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			return newFile;

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
	
	public static void delete(String path) {
        
        File toBeDeleted = new File(path);
        
        if(toBeDeleted.exists()) {
            if(toBeDeleted.isDirectory()) {
                File[] files = toBeDeleted.listFiles();             
                if (files != null) {
                    for (File file : files) {
                        delete(file.getAbsolutePath());
                    }
                }
            }
                        
            toBeDeleted.delete();
        }       
    }
	
	public static String concatenatePath(String path, String directory) {
        
        String completePath = path;
        
        if(!completePath.endsWith("/")) {
            completePath = completePath.concat("/");
        }
        
        completePath = completePath.concat(directory);
        
        return completePath;
    }
    
    public static void compressDirectoryInZip(String directoryPath, String zipFilePath, String zipFileName) throws IOException {
    	
    	String zipFile = concatenatePath(zipFilePath, zipFileName);
    	
    	File file = new File(zipFile + ".zip");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        
        File directory = new File(directoryPath);

        compressToZip(zipOutputStream, directory.listFiles(), zipFileName);
        zipOutputStream.close();
        fileOutputStream.close();
    }
    
    private static void compressToZip(ZipOutputStream zipOutputStream, File[] files, String parentDirectory) throws IOException {
        
    	for (File file : files) {
    		
    		if (file.isHidden()) {
                return;
            }
    		
    		String fileName;
    		if(!parentDirectory.endsWith("/")) {
    			fileName = parentDirectory + "/" + file.getName();
            } else {
            	fileName = parentDirectory + file.getName();
            }
    		    		
    		if (file.isDirectory()) {
    			if(!fileName.endsWith("/")) {
    				fileName = fileName + "/";
    	        }
    	                	                        
    	        ZipEntry entry = new ZipEntry(fileName);
    	        zipOutputStream.putNextEntry(entry);
    	        zipOutputStream.closeEntry();
    	            
    	        File[] childrenFiles = file.listFiles();
    	        compressToZip(zipOutputStream, childrenFiles, fileName);    	           
    	            
    	    } else {
    	    	FileInputStream fileInputStream = new FileInputStream(file);
    	        ZipEntry entry = new ZipEntry(fileName);
    	        zipOutputStream.putNextEntry(entry);
    	            
    	        byte[] bytes = new byte[1024];
    	        int length;
    	            
    	        while ((length = fileInputStream.read(bytes)) >= 0) {
    	        	zipOutputStream.write(bytes, 0, length);
    	        }
    	            
    	        fileInputStream.close();
    	    }            		
    	}                                       
    }        
}
