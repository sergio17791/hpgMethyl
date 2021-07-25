package es.hpgMethyl.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import es.hpgMethyl.dao.hibernate.HPGMethylFileDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.HPGMethylFile;
import es.hpgMethyl.exceptions.FileNotFound;
import es.hpgMethyl.exceptions.GetObjectException;
import es.hpgMethyl.exceptions.UpdateFileException;
import es.hpgMethyl.usecases.file.CheckFileIsPending.CheckFileIsPending;
import es.hpgMethyl.usecases.file.CheckFileIsPending.CheckFileIsPendingRequest;
import es.hpgMethyl.usecases.file.GetFile.GetFile;
import es.hpgMethyl.usecases.file.GetFile.GetFileRequest;
import es.hpgMethyl.usecases.file.GetFile.GetFileResponse;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFile;
import es.hpgMethyl.usecases.file.UnstoreFile.UnstoreFileRequest;
import es.hpgMethyl.utils.FacesContextUtils;
import es.hpgMethyl.utils.FileUtils;

@ManagedBean(name="fileDetail")
@ViewScoped
public class FileDetail implements Serializable {

	private static final long serialVersionUID = 1873757598295332492L;

	private String id;
	private HPGMethylFile file;
	private List<AnalysisRequest> analysisRequestWithFile;
	private UIComponent deleteComponent;
	
	public FileDetail() {
		this.id = null;
		this.file = null;
		this.analysisRequestWithFile = new ArrayList<AnalysisRequest>();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the file
	 */
	public HPGMethylFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(HPGMethylFile file) {
		this.file = file;
	}

	/**
	 * @return the analysisRequestWithFile
	 */
	public List<AnalysisRequest> getAnalysisRequestWithFile() {
		return analysisRequestWithFile;
	}

	/**
	 * @param analysisRequestWithFile the analysisRequestWithFile to set
	 */
	public void setAnalysisRequestWithFile(List<AnalysisRequest> analysisRequestWithFile) {
		this.analysisRequestWithFile = analysisRequestWithFile;
	}

	/**
	 * @return the deleteComponent
	 */
	public UIComponent getDeleteComponent() {
		return deleteComponent;
	}

	/**
	 * @param deleteComponent the deleteComponent to set
	 */
	public void setDeleteComponent(UIComponent deleteComponent) {
		this.deleteComponent = deleteComponent;
	}

	public String loadFile() {
		
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			
			if(id == null) {
				return "pretty:home";
			}
			
			try {
				GetFileResponse response = new GetFile(new HPGMethylFileDAOHibernate()).execute(
						new GetFileRequest(UUID.fromString(id))
				);
					
				this.file = response.getFile();
					
			} catch (GetObjectException | FileNotFound e) {
				return "pretty:home";
			} 			
		}
		
		return null;
	}
	
	public String removeFile() {
		
		Boolean pending = new CheckFileIsPending(new HPGMethylFileDAOHibernate()).execute(
				new CheckFileIsPendingRequest(file.getUser(), file.getFileName())
		).getPending();
		
		if(pending) {
			String pendingFileError = FacesContextUtils.geti18nMessage("file.detail.remove.pending");
			FacesContextUtils.setMessageInComponent(this.getDeleteComponent(), FacesMessage.SEVERITY_ERROR, pendingFileError, pendingFileError);
			return null;
		}
		
		try {
			new UnstoreFile(new HPGMethylFileDAOHibernate()).execute(
					new UnstoreFileRequest(file.getId())
			);
				
			FileUtils.deleteFile(file.getPath());
				
		} catch (FileNotFound | UpdateFileException | IOException e) {
			String defaultError = FacesContextUtils.geti18nMessage("error.default");
			FacesContextUtils.setMessageInComponent(this.getDeleteComponent(), FacesMessage.SEVERITY_ERROR, defaultError, defaultError);
			return null;
		}
		
		return "pretty:userFiles";
	}
}