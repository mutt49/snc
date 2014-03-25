package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.PartMasterVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "PartMasterBean")
@SessionScoped
public class PartMasterBean {

	private PartMasterVO selectedPartMasterVO;
	private String partName;
	private String grade;
	private List<PartMasterVO> searchList = new ArrayList<PartMasterVO>();
	private Boolean editFlag = false;

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public PartMasterVO getSelectedPartMasterVO() {
		return selectedPartMasterVO;
	}

	public void setSelectedPartMasterVO(PartMasterVO selectedPartMasterVO) {
		this.selectedPartMasterVO = selectedPartMasterVO;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<PartMasterVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<PartMasterVO> searchList) {
		this.searchList = searchList;
	}

	public PartMasterBean() {
		selectedPartMasterVO = new PartMasterVO();
		partName = "";
		grade = "";
		searchList = new ArrayList<PartMasterVO>();
	}

	public String reset() {
		selectedPartMasterVO = new PartMasterVO();
		partName = "";
		grade = "";
		searchList = new ArrayList<PartMasterVO>();
		return "";
	}

	@SuppressWarnings("unchecked")
	public String search() {
		searchList = new ArrayList<PartMasterVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where m.partName like :part_name order by m.partId");
		hibernateQuery.setString("part_name", "%"+partName+"%");
		java.util.List<PartMasterHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			PartMasterVO tempObj = new PartMasterVO();
			tempObj.setPartId(results.get(i).getPartId());
			tempObj.setPartName(results.get(i).getPartName());
			tempObj.setDrgNo(results.get(i).getDrgNo());
			tempObj.setPartRate(results.get(i).getPartRate());
			tempObj.setPartUom(results.get(i).getPartUom());
			tempObj.setPmSize(results.get(i).getPmSize());
			tempObj.setGrade(results.get(i).getGrade());
			tempObj.setCastWeight(results.get(i).getCastWeight());
			tempObj.setProofMachineWeight(results.get(i)
					.getProofMachineWeight());
			tempObj.setQuantity(results.get(i).getQuantity());
			searchList.add(tempObj);
		}

		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return "PartMasterSearch";
	}

	public String add() {

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PartMasterHBC partMasterHBC = new PartMasterHBC(selectedPartMasterVO);
		session.saveOrUpdate(partMasterHBC);
		trans.commit();
		session.close();

		selectedPartMasterVO = new PartMasterVO();
		return search();
	}

	public String edit() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PartMasterHBC partMasterHbc = new PartMasterHBC(selectedPartMasterVO);
		session.update(partMasterHbc);
		trans.commit();
		session.close();
		selectedPartMasterVO = new PartMasterVO();
		return search();
	}

	public String deletePart() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		PartMasterHBC partMasterHbc = new PartMasterHBC(selectedPartMasterVO);
		session.delete(partMasterHbc);
		session.flush();
		session.close();
		selectedPartMasterVO = new PartMasterVO();
		return search();
	}

	public String addNewPart() {
		selectedPartMasterVO = new PartMasterVO();
		editFlag = false;
		return "PartMasterAdd.xhtml";
	}

}