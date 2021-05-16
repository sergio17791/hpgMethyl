package es.hpgMethyl.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class HPGMethylFile extends BaseEntity {

	@ManyToOne
	@JoinColumn(name="\"user\"", nullable=false)
	private User user;
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "path", nullable = false)
	private String path;
	
	@Column(name = "size", nullable = false)
	private Long size;
	
	@Column(name = "content_type", nullable = false)
	private String contentType;
	
	@Column(name = "stored", nullable = false)
	private Boolean stored;
	
	public HPGMethylFile() {
		super();
		this.stored = true;
	}

	public HPGMethylFile(
			UUID id, 
			Date createdAt, 
			Date updatedAt,
			User user, 
			String fileName, 
			String path, 
			Long size, 
			String contentType, 
			Boolean stored
	) {
		super(id, createdAt, updatedAt);
		this.user = user;
		this.fileName = fileName;
		this.path = path;
		this.size = size;
		this.contentType = contentType;
		this.stored = stored;
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
	 * @return the stored
	 */
	public Boolean getStored() {
		return stored;
	}

	/**
	 * @param stored the stored to set
	 */
	public void setStored(Boolean stored) {
		this.stored = stored;
	}
	
	@Override
	public String toString() {
		return fileName;
	}	
}
