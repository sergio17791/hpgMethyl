package es.hpgMethyl.usecases.file.CreateFile;

import es.hpgMethyl.entities.User;

public class CreateFileRequest {

	private User user;
	
	private String fileName;
	
	private String path;
	
	private Long size;
	
	private String contentType;
	
	private Boolean uploaded;

	public CreateFileRequest(User user, String fileName, String path, Long size, String contentType, Boolean uploaded) {
		this.user = user;
		this.fileName = fileName;
		this.path = path;
		this.size = size;
		this.contentType = contentType;	
		this.uploaded = uploaded;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the uploaded
	 */
	public Boolean getUploaded() {
		return uploaded;
	}

	/**
	 * @param uploaded the uploaded to set
	 */
	public void setUploaded(Boolean uploaded) {
		this.uploaded = uploaded;
	}		
}
