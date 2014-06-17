package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.GradeCompositionHBC;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.vo.GradeCompositionVO;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.TestCertificateActualValuesVO;
import org.demo.spinncast.vo.TestCertificateVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "TestCertificateBean")
@SessionScoped
public class TestCertificateBean {
	private TestCertificateVO searchTestCertificateVO = new TestCertificateVO();
	private TestCertificateVO selectedTestCertificateVO = new TestCertificateVO();
	private ArrayList<TestCertificateVO> searchTCVOList;
	private Boolean editFlag = false;
	private int selectedTestCaseNo;

	public TestCertificateVO getSearchTestCertificateVO() {
		return searchTestCertificateVO;
	}

	public void setSearchTestCertificateVO(
			TestCertificateVO searchTestCertificateVO) {
		this.searchTestCertificateVO = searchTestCertificateVO;
	}

	public TestCertificateVO getSelectedTestCertificateVO() {
		return selectedTestCertificateVO;
	}

	public void setSelectedTestCertificateVO(
			TestCertificateVO selectedTestCertificateVO) {
		this.selectedTestCertificateVO = selectedTestCertificateVO;
	}

	public ArrayList<TestCertificateVO> getSearchTCVOList() {
		return searchTCVOList;
	}

	public void setSearchTCVOList(ArrayList<TestCertificateVO> searchTCVOList) {
		this.searchTCVOList = searchTCVOList;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public int getSelectedTestCaseNo() {
		return selectedTestCaseNo;
	}

	public void setSelectedTestCaseNo(int selectedTestCaseNo) {
		this.selectedTestCaseNo = selectedTestCaseNo;
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		try {
			selectedTestCaseNo = searchTestCertificateVO.getTcNo();
		} catch (NumberFormatException e) {
			selectedTestCaseNo = 0;
		}

		Session session = cpool.getSession();
		StringBuilder query = new StringBuilder(
				"from TestCertificateHBC as m where ((m.tcNo= :tcNo and :tcNo !=0 ) or (:tcNo = 0) ");

		if (searchTestCertificateVO.getPoNo() != null
				&& !searchTestCertificateVO.getPoNo().equals("")) {
			query.append(" and m.poNo = :poNo ");
		}
		if (searchTestCertificateVO.getHeatBatchNo() != null
				&& !searchTestCertificateVO.getHeatBatchNo().equals("")) {
			query.append(" and m.heatBatchNo = :hcNo ");
		}
		if (searchTestCertificateVO.getChallanNo() != null
				&& !searchTestCertificateVO.getChallanNo().equals("")) {
			query.append(" and m.challanNo = :chNo ");
		}

		query.append(") order by m.tcId");

		Query hibernateQuery = session.createQuery(query.toString());
		hibernateQuery.setInteger("tcNo", searchTestCertificateVO.getTcNo());

		if (searchTestCertificateVO.getPoNo() != null
				&& !searchTestCertificateVO.getPoNo().equals("")) {
			hibernateQuery.setString("poNo", searchTestCertificateVO.getPoNo());
		}
		if (searchTestCertificateVO.getHeatBatchNo() != null
				&& !searchTestCertificateVO.getHeatBatchNo().equals("")) {
			hibernateQuery.setString("hcNo",
					searchTestCertificateVO.getHeatBatchNo());
		}
		if (searchTestCertificateVO.getChallanNo() != null
				&& !searchTestCertificateVO.getChallanNo().equals("")) {
			hibernateQuery.setString("chNo",
					searchTestCertificateVO.getChallanNo());
		}

		java.util.List<TestCertificateHBC> results = hibernateQuery.list();
		searchTCVOList = new ArrayList<TestCertificateVO>();
		for (int i = 0; i < results.size(); i++) {
			TestCertificateVO tempTc = new TestCertificateVO(results.get(i));
			searchTCVOList.add(tempTc);
		}
		session.close();
		return "TestCertificateSearch";
	}
	
	// Add reset method
	
	public String reset(){
		searchTestCertificateVO = new TestCertificateVO();
		selectedTestCertificateVO = new TestCertificateVO();
		searchTCVOList = new ArrayList<TestCertificateVO>();
		editFlag = false;
		selectedTestCaseNo = 0;
		return "TestCertificateAdd";
	}
	
	public void fetchGradeDetails(ValueChangeEvent event){
		String gradeName = (String) event.getNewValue();
		selectedTestCertificateVO.setActualValues(new ArrayList<TestCertificateActualValuesVO>());
		int gradeId = getGrdeIdbyName(gradeName);
		if (gradeName.isEmpty()) {
			return;
		}
		// Move this method to handler
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeCompositionHBC as g where g.gradeId = :gradeId order by g.gradeCompositionId");
		hibernateQuery.setInteger("gradeId", gradeId);
		
		java.util.List<GradeCompositionHBC> results = hibernateQuery.list();
		
		for (int i = 0; i < results.size(); i++) {
			GradeCompositionVO tempObj = new GradeCompositionVO();
			tempObj.setIngrediantName(results.get(i).getIngrediantName());
			tempObj.setIngrediantType(results.get(i).getIngrediantType());
			tempObj.setMaxValue(results.get(i).getMaxValue());
			tempObj.setMinValue(results.get(i).getMinValue());
			//gradeObj.getGradeCompVOList().add(tempObj);
			selectedTestCertificateVO.getActualValues().add(new TestCertificateActualValuesVO(tempObj));
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
	}
	// Move this method to handler
	public int getGrdeIdbyName(String gradeName){
		GradeMasterVO tempObj = new GradeMasterVO();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeName like :gradeName order by g.gradeName");
		hibernateQuery.setString("gradeName", gradeName);
		
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setGradeName(results.get(i).getGradeName());
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return tempObj.getGradeId();
	}

	public List<String> vendorCodeAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from CustomerHBC as m where vendor_code like '%"
						+ prefix + "%'");
		java.util.List<CustomerHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getVendor_code());
		}
		session.close();
		return result;
	}
	
	public List<String> gradeAutoComplete(String prefix) {
		List<String> result = new ArrayList<String>();

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as m where gradeName like '%"
						+ prefix + "%'");
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			result.add(results.get(i).getGradeName());
		}
		session.close();
		return result;
	}
}