package es.hpgMethyl.junit.usecases.analysis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;

import es.hpgMethyl.dao.AnalysisRequestDAO;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.exceptions.HpgMethylException;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysis;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysisResponse;

public class ListMethylationAnalysisTest {
	
	private AnalysisRequestDAO analysisRequestDAO;
	
	private ListMethylationAnalysis listMethylationAnalysis;
	
	@Before
	public void setUp() {
		this.analysisRequestDAO = Mockito.mock(AnalysisRequestDAO.class);
		this.listMethylationAnalysis = new ListMethylationAnalysis(this.analysisRequestDAO);		
	}
	
	public void test_execute_givenAnErrorWhenListMethylationAnalysisObjects_expectListObjectsException() throws HpgMethylException {
				
		Mockito.doThrow(ListObjectsException.class).when(analysisRequestDAO).listAll();
		
		this.listMethylationAnalysis.execute();
	}
	
	public void test_execute_givenNonExistentMethylationAnalysis_expectEmptyList() throws HpgMethylException {
		
		Mockito.doReturn(new ArrayList<AnalysisRequest>()).when(analysisRequestDAO).listAll();
		
		ListMethylationAnalysisResponse response = this.listMethylationAnalysis.execute();
		
		Assert.assertTrue(response.getAnalysisRequestList().isEmpty());
	}
	
	public void test_execute_givenThereAreMethylationAnalysis_expectList() throws HpgMethylException {
		
		List<AnalysisRequest> analysisRequestList = new ArrayList<AnalysisRequest>();
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		analysisRequestList.add(new AnalysisRequest());
		
		Mockito.doReturn(analysisRequestList).when(analysisRequestDAO).listAll();
		
		ListMethylationAnalysisResponse response = this.listMethylationAnalysis.execute();
		
		Assert.assertFalse(response.getAnalysisRequestList().isEmpty());
	}
}
