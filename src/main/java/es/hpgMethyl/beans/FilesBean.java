package es.hpgMethyl.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.CreateFileException;
import es.hpgMethyl.exceptions.DuplicatedFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFile;
import es.hpgMethyl.usecases.file.CreateFile.CreateFileRequest;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFile;
import es.hpgMethyl.usecases.file.ExistsFile.ExistsFileRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFiles;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesRequest;
import es.hpgMethyl.usecases.file.ListUserFiles.ListUserFilesResponse;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name = "filesBean")
@ViewScoped
public class FilesBean {
	
	private List<HPGMethylFile> userFiles;
	private Integer fileLimit;
	private Long fileBytesLimit;
	private String fileBytesLimitStr;
	private User user;
	private List<String> successUploadedFilesNames;
	private List<String> errorUploadedFilesNames;
	private UIComponent uploadFilesComponent;
	
	private Integer BYTES_IN_GIGABYTES = 1073741824;
	
	public FilesBean() {
		this.successUploadedFilesNames = new ArrayList<String>();
		this.errorUploadedFilesNames = new ArrayList<String>();
		this.userFiles = new ArrayList<HPGMethylFile>();
	}
	
	@PostConstruct
	public void init() {						
						
		this.user = (User) FacesContextUtils.getParameterFacesContextSession(FacesContextUtils.SESSION_USER);
		
		if(user != null) {
			ListUserFilesResponse response = new ListUserFiles(new HPGMethylFileDAOHibernate()).execute(
					new ListUserFilesRequest(user, true)
			);
			
			this.userFiles = response.getFiles();
		} 
		
		this.fileLimit = 3 - userFiles.size();		
		this.fileBytesLimit = Long.valueOf("10737418240");
		this.fileBytesLimitStr = String.valueOf(fileBytesLimit/BYTES_IN_GIGABYTES) + " GB";
	}

	/**
	 * @return the userFiles
	 */
	public List<HPGMethylFile> getUserFiles() {
		return userFiles;
	}

	/**
	 * @param userFiles the userFiles to set
	 */
	public void setUserFiles(List<HPGMethylFile> userFiles) {
		this.userFiles = userFiles;
	}

	/**
	 * @return the fileLimit
	 */
	public Integer getFileLimit() {
		return fileLimit;
	}

	/**
	 * @param fileLimit the fileLimit to set
	 */
	public void setFileLimit(Integer fileLimit) {
		this.fileLimit = fileLimit;
	}

	/**
	 * @return the fileBytesLimit
	 */
	public Long getFileBytesLimit() {
		return fileBytesLimit;
	}

	/**
	 * @param fileBytesLimit the fileBytesLimit to set
	 */
	public void setFileBytesLimit(Long fileBytesLimit) {
		this.fileBytesLimit = fileBytesLimit;
	}

	/**
	 * @return the fileBytesLimitStr
	 */
	public String getFileBytesLimitStr() {
		return fileBytesLimitStr;
	}

	/**
	 * @param fileBytesLimitStr the fileBytesLimitStr to set
	 */
	public void setFileBytesLimitStr(String fileBytesLimitStr) {
		this.fileBytesLimitStr = fileBytesLimitStr;
	}

	/**
	 * @return the uploadFilesComponent
	 */
	public UIComponent getUploadFilesComponent() {
		return uploadFilesComponent;
	}

	/**
	 * @param uploadFilesComponent the uploadFilesComponent to set
	 */
	public void setUploadFilesComponent(UIComponent uploadFilesComponent) {
		this.uploadFilesComponent = uploadFilesComponent;
	}

	public void upload(FileUploadEvent event) {
       				
		if(user != null) {
			UploadedFile file = event.getFile();
			String fileName = file.getFileName();
			
			Boolean validated = validateUploadedFile(fileName);
			
			if(validated) {
				try {
					saveFile(user, file, fileName);
			    	this.successUploadedFilesNames.add(fileName);
			    } catch (CreateFileException | DuplicatedFile | IOException e) {
			    	this.errorUploadedFilesNames.add(fileName);
			    }
			} else {
				this.errorUploadedFilesNames.add(fileName);
			}	

		    setUploadSuccessMessage();	
		}						
    }
	
	private void saveFile(User user, UploadedFile file, String inputFileName) throws CreateFileException, DuplicatedFile, IOException {
		
		Boolean duplicatedFile = new ExistsFile(new HPGMethylFileDAOHibernate()).execute(
				new ExistsFileRequest(user, inputFileName)
		).getExists();
		
		if(duplicatedFile) {
			throw new DuplicatedFile();
		}
		
		File uploadedFile = FileUtils.saveFileUploadedByUser(user, file, inputFileName);

		HPGMethylFile hpgMethylFile = new CreateFile(new HPGMethylFileDAOHibernate()).execute(
			new CreateFileRequest(
				user,
				uploadedFile.getName(),
				uploadedFile.getAbsolutePath(),
				file.getSize(),
				file.getContentType()
			)	
		).getFile();	
		
		this.userFiles.add(hpgMethylFile);
	}
	
	private Boolean validateUploadedFile(String fileName) {
		
		Boolean validated = true;
		
		Boolean duplicatedFile = new ExistsFile(new HPGMethylFileDAOHibernate()).execute(
				new ExistsFileRequest(user, fileName)
		).getExists();
		
		validated = !duplicatedFile;
		
		if(3 <= userFiles.size()) {
			validated = false;
		}
		
		return validated;
	}
	
	private void setUploadSuccessMessage() {
		
		String message = null;
		
		if(successUploadedFilesNames.size() > 0) {
			message = FacesContextUtils.geti18nMessage("files.upload.success");
			
			for(String fileName : successUploadedFilesNames) {
				message =  message + "<br/> · " + fileName;
			}
		} 
		
		if(errorUploadedFilesNames.size() > 0) {
			
			if(message != null) {
				message = message + "<br/>" + FacesContextUtils.geti18nMessage("files.upload.error");
			} else {
				message = FacesContextUtils.geti18nMessage("files.upload.error");
			}
						
			for(String fileName : errorUploadedFilesNames) {
				message =  message + "<br/> · " + fileName;
			}
		} 	
		
		Severity severity = FacesMessage.SEVERITY_INFO;
		if(successUploadedFilesNames.size() > 0 && errorUploadedFilesNames.size() > 0) {
			severity = FacesMessage.SEVERITY_WARN;
		} else if(successUploadedFilesNames.size() == 0 && errorUploadedFilesNames.size() > 0) {
			severity = FacesMessage.SEVERITY_ERROR;
		}

		if(message != null) {
			FacesContextUtils.setMessageInComponent(this.getUploadFilesComponent(), severity, message, message);
		}		
	}
	
	public String customLanguageTranslation() {
		
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		
		if ("ca".equals(locale.getLanguage())) {
			return "//cdn.datatables.net/plug-ins/1.10.12/i18n/Catalan.json";
		}
			
	    return null;
	 }
}
