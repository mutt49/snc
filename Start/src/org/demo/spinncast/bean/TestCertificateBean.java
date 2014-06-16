package org.demo.spinncast.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.vo.TestCertificateVO;
import org.hibernate.Query;
import org.hibernate.Session;


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

	public void setSearchTestCertificateVO(TestCertificateVO searchTestCertificateVO) {
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

	public String search(){
		ConnectionPool cpool = ConnectionPool.getInstance();
		try {
			selectedTestCaseNo = searchTestCertificateVO.getTcNo();
		} catch (NumberFormatException e) {
			selectedTestCaseNo = 0;
		}
		int customer_id = 0;

		Session session = cpool.getSession();
		StringBuilder query = new StringBuilder(
				"from TestCertificateHBC as m where (m.tcNo= :tcNo and :tcNo !=0 ) or (:tcNo = 0) ");

		query.append(" order by m.tcId");

		Query hibernateQuery = session.createQuery(query.toString());
		hibernateQuery.setInteger("tcNo", selectedTestCertificateVO.getTcNo());

		java.util.List<TestCertificateHBC> results = hibernateQuery.list();
		searchTCVOList = new ArrayList<TestCertificateVO>();
		for (int i = 0; i < results.size(); i++) {
			TestCertificateVO tempTc = new TestCertificateVO(results.get(i));
			searchTCVOList.add(tempTc);
		}
		session.close();
		return "TestCertificateSearch";
	}
}