package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.TestCertificateHBC;
import org.demo.spinncast.vo.TestCertificateVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "TestCertificateBean")
@SessionScoped
public class TestCertificateBean {

	TestCertificateVO testCertificateVO;

	private List<TestCertificateVO> searchList;

	int editid;
	Transaction trans;
	private Integer selectedId;

	private TestCertificateVO editedTc;

	public TestCertificateVO getEditedTc() {
		return editedTc;
	}

	public void setEditedTc(TestCertificateVO editedTc) {
		System.out.println("set" + editedTc.getId());
		this.editedTc = editedTc;
	}

	public int getEditid() {
		return editid;
	}

	public void setEditid(int editid) {
		this.editid = editid;
	}

	public TestCertificateBean() {
		testCertificateVO = new TestCertificateVO();
		searchList = new ArrayList<TestCertificateVO>();

		editedTc = new TestCertificateVO();
	}

	public TestCertificateVO getTestCertificateVO() {
		return testCertificateVO;
	}

	public void setTestCertificateVO(TestCertificateVO testCertificateVO) {
		this.testCertificateVO = testCertificateVO;
	}

	public void store() {
		searchList.set(editid, editedTc);
	}

	public String search() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from TestCertificateHBC as m order by m.id");
		@SuppressWarnings("unchecked")
		java.util.List<TestCertificateHBC> results = hibernateQuery.list();
		setSearchList(new ArrayList<TestCertificateVO>());
		for (int i = 0; i < results.size(); i++) {
			TestCertificateVO tcItem = new TestCertificateVO();
			tcItem.setId(results.get(i).getId());
			tcItem.setName(results.get(i).getName());
			tcItem.setDescription(results.get(i).getDescription());
			tcItem.setQuantity (results.get(i).getQuantity());
			tcItem.setRate (results.get (i).getRate());
			getSearchList().add(tcItem);
		}
		System.out.println(getSearchList().size());
		Transaction trans = session.beginTransaction();
		trans.commit();

		session.close();
		return "TestCertificateSearch";
	}

	private boolean editFlag = true;

	public String add() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		TestCertificateHBC testcertificatehbc = new TestCertificateHBC(testCertificateVO);
		session.saveOrUpdate(testcertificatehbc);
		trans.commit();
		session.close ();
		testCertificateVO = new TestCertificateVO ();
		return search ();
	}
	
	
	public String edit() {
		System.out.println(getSelectedId());
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		TestCertificateHBC testcertificatehbc = new TestCertificateHBC(testCertificateVO);
		System.out.println("TCHBC ID: " + testcertificatehbc.getId());
		session.update(testcertificatehbc);
		trans.commit();
		session.close ();
		testCertificateVO = new TestCertificateVO ();
		return search ();
	}

	public String reset() {
		getSearchList().clear();
		return "TestCertificateSearch";
	}

	public String delete() {
		return "";
	}

	public List<TestCertificateVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<TestCertificateVO> searchList) {
		this.searchList = searchList;
	}

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public Integer getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}
}