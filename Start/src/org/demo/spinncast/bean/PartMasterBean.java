package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.handler.PartMasterHandler;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.PartGradeMappingHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
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
	private PartGradeMappingVO selectedPartGradeMapping = new PartGradeMappingVO();
	private List searchGradeList = new ArrayList<GradeMasterVO>();
	private List<String> grades = new ArrayList<String>();
	private List<Integer> gradesIdsList = new ArrayList<Integer>();
	private List<PartGradeMappingVO> partGradeMapping = new ArrayList<PartGradeMappingVO>();
	private List<PartGradeMappingVO> partGradeMappingList = new ArrayList<PartGradeMappingVO>();
	private List<Integer> gradeList = new ArrayList<Integer>();
	private Integer[] gradesForPickList;

	public List<Integer> getGradesIdsList() {
		return gradesIdsList;
	}

	public void setGradesIdsList(List<Integer> gradesIdsList) {
		this.gradesIdsList = gradesIdsList;
	}

	public Integer[] getGradesForPickList() {
		return gradesForPickList;
	}

	public void setGradesForPickList(Integer[] gradesForPickList) {
		this.gradesForPickList = gradesForPickList;
	}

	public List<Integer> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Integer> gradeList) {
		this.gradeList = gradeList;
	}

	public PartGradeMappingVO getSelectedPartGradeMapping() {
		return selectedPartGradeMapping;
	}

	public void setSelectedPartGradeMapping(
			PartGradeMappingVO selectedPartGradeMapping) {
		this.selectedPartGradeMapping = selectedPartGradeMapping;
	}

	public List<PartGradeMappingVO> getPartGradeMapping() {
		return partGradeMapping;
	}

	public void setPartGradeMapping(List<PartGradeMappingVO> partGradeMapping) {
		this.partGradeMapping = partGradeMapping;
	}

	public List<String> getGrades() {
		return grades;
	}

	public void setGrades(List<String> grades) {
		this.grades = grades;
	}

	public List getSearchGradeList() {
		return searchGradeList;
	}

	public void setSearchGradeList(List searchGradeList) {
		this.searchGradeList = searchGradeList;
	}

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
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		selectedPartMasterVO = new PartMasterVO();
		partName = "";
		grade = "";
		searchList = new ArrayList<PartMasterVO>();
		partMasterHandler.prepareGradesList(partGradeMapping);
	}

	public String reset() {
		selectedPartMasterVO = new PartMasterVO();
		partName = "";
		grade = "";
		searchList = new ArrayList<PartMasterVO>();
		return "PartMasterSearch.jsf";
	}

	@SuppressWarnings("unchecked")
	public String search() {
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		searchList = new ArrayList<PartMasterVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where m.partName like :part_name order by m.partId");
		hibernateQuery.setString("part_name", "%"+partName+"%");
		java.util.List<PartMasterHBC> results = hibernateQuery.list();

		for (int i = 0; i < results.size(); i++) {
			PartMasterVO tempObj = new PartMasterVO(results.get(i));
			partMasterHandler.getGradesForPart(tempObj);
			for(GradeMasterVO gm : tempObj.getGrades()){
				grades.add(gm.getGradeName());
				gradeList.add(gm.getGradeId());
			}
			searchList.add(tempObj);
		}

		session.close();
		return "PartMasterSearch";
	}

	public String add() {

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		populatePartGradeList();
		selectedPartMasterVO.setPartGradeMapping(partGradeMappingList);
		PartMasterHBC partMasterHBC = new PartMasterHBC(selectedPartMasterVO);
		session.saveOrUpdate(partMasterHBC);
		int partId = partMasterHBC.getPartId();
		trans.commit();
		session.close();
		
		addPartGradeMapping(partId);

		selectedPartMasterVO = new PartMasterVO();
		return search();
	}
	
	public void addPartGradeMapping(int partId){
		ConnectionPool cpool = ConnectionPool.getInstance();
		List<PartGradeMappingVO> tempList = new ArrayList<PartGradeMappingVO>();
		tempList = selectedPartMasterVO.getPartGradeMapping();
		for(int i = 0; i< tempList.size();i++){
			Session session = cpool.getSession();
			session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			trans = session.beginTransaction();
			PartGradeMappingVO pgM = tempList.get(i);
			pgM.setPartId(partId);
			PartGradeMappingHBC partGraderHBC = new PartGradeMappingHBC(pgM);
			session.saveOrUpdate(partGraderHBC);
			trans.commit();
			session.close();
		}
	}

	public String edit() {
		populatePartGradeList();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PartMasterHBC partMasterHbc = new PartMasterHBC(selectedPartMasterVO);
		session.update(partMasterHbc);
		trans.commit();
		session.close();
		editPartGradeMapping();
		selectedPartMasterVO = new PartMasterVO();
		return search();
	}
	
	public void editPartGradeMapping() {
		for(PartGradeMappingVO selectedPartGradeMapping : selectedPartMasterVO.getPartGradeMapping()){
			ConnectionPool cpool = ConnectionPool.getInstance();
			Session session = cpool.getSession();
			Transaction trans = session.beginTransaction();
			PartGradeMappingHBC partGradeMappingHBC = new PartGradeMappingHBC(selectedPartGradeMapping);
			session.saveOrUpdate(partGradeMappingHBC);
			trans.commit();
			session.close();
		}
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
	
	/*public void getGradesForPart(){
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		List<GradeMasterVO> grades = new ArrayList<GradeMasterVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartGradeMappingHBC as m where m.partId = :part_Id order by m.partId");
		hibernateQuery.setInteger("part_Id", selectedPartMasterVO.getPartId());
		java.util.List<PartGradeMappingHBC> results = hibernateQuery.list();
		for(PartGradeMappingHBC partGrade : results){
			grades.add(partMasterHandler.prepareGradeList(partGrade.getGradeId()));
		}
		selectedPartMasterVO.setGrades(grades);
		session.close();
	}*/
	
	public void populatePartGradeList(){
		PartMasterHandler partMasterHandler = new PartMasterHandler();
		for(Integer i : gradeList){
			PartGradeMappingVO tempObj = new PartGradeMappingVO();
			GradeMasterVO tmpGrdMasterVO = new GradeMasterVO();
			PartMasterHBC tempPartMaster = new PartMasterHBC(selectedPartMasterVO);
			tmpGrdMasterVO = partMasterHandler.getGradesById(i);
			tempObj.setGradeId(i);
			tempObj.setPartId(selectedPartMasterVO.getPartId());
			partGradeMappingList.add(tempObj);
			selectedPartMasterVO.setPartGradeMapping(partGradeMappingList);
		}
	}

	public String viewForEdit(){
		gradeList = new ArrayList<Integer>();
		for(int i=0;i<selectedPartMasterVO.getGrades().size();i++){
			gradeList.add(selectedPartMasterVO.getGrades().get(i).getGradeId());
		}
		return "PartMasterAdd.xhtml";
	}
}