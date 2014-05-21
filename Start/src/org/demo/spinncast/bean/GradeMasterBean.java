package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.CustomerHBC;
import org.demo.spinncast.hibernate.CustomerPersonnelHBC;
import org.demo.spinncast.hibernate.GradeCompositionHBC;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.InvoiceHeaderHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.CustomerVO;
import org.demo.spinncast.vo.GradeCompositionVO;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.InvoiceHeaderVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "GradeMasterBean")
@SessionScoped
public class GradeMasterBean {

	private GradeMasterVO selectedGradeMasterVO = new GradeMasterVO();
	private GradeCompositionVO selectedGradeCompositionVO = new GradeCompositionVO();
	private String gradeName;
	private List<GradeMasterVO> searchList = new ArrayList<GradeMasterVO>();
	private Boolean editFlag = false;
	
	public GradeMasterVO getSelectedGradeMasterVO() {
		return selectedGradeMasterVO;
	}

	public void setSelectedGradeMasterVO(GradeMasterVO selectedGradeMasterVO) {
		this.selectedGradeMasterVO = selectedGradeMasterVO;
	}

	public GradeCompositionVO getSelectedGradeCompositionVO() {
		return selectedGradeCompositionVO;
	}

	public void setSelectedGradeCompositionVO(
			GradeCompositionVO selectedGradeCompositionVO) {
		this.selectedGradeCompositionVO = selectedGradeCompositionVO;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<GradeMasterVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<GradeMasterVO> searchList) {
		this.searchList = searchList;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public GradeMasterBean() {
		searchList = new ArrayList<GradeMasterVO>();
		selectedGradeCompositionVO = new GradeCompositionVO();
		selectedGradeMasterVO = new GradeMasterVO();
	}

	public String reset() {
		selectedGradeMasterVO = new GradeMasterVO();
		selectedGradeCompositionVO = new GradeCompositionVO();
		gradeName = "";
		searchList = new ArrayList<GradeMasterVO>();
		editFlag = false;
		return "GradeMasterSearch";
	}
	
	@SuppressWarnings("unchecked")
	public String search() {
		searchList = new ArrayList<GradeMasterVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeName like :gradeName order by g.gradeName");
		hibernateQuery.setString("gradeName", "%"+gradeName+"%");
		
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			GradeMasterVO tempObj = new GradeMasterVO();
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setGradeName(results.get(i).getGradeName());
			getGradeComposition(tempObj);
			searchList.add(tempObj);
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return "GradeMasterSearch";
	}
	
	public void getGradeComposition(GradeMasterVO gradeObj){
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeCompositionHBC as g where g.gradeId = :gradeId order by g.gradeCompositionId");
		hibernateQuery.setInteger("gradeId", gradeObj.getGradeId());
		
		java.util.List<GradeCompositionHBC> results = hibernateQuery.list();
		gradeObj.setGradeCompVOList(new ArrayList<GradeCompositionVO>());
		
		for (int i = 0; i < results.size(); i++) {
			GradeCompositionVO tempObj = new GradeCompositionVO();
			tempObj.setGradeCompositionId(results.get(i).getGradeCompositionId());
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setIngrediantName(results.get(i).getIngrediantName());
			tempObj.setIngrediantType(results.get(i).getIngrediantType());
			tempObj.setMaxValue(results.get(i).getMaxValue());
			tempObj.setMinValue(results.get(i).getMinValue());
			gradeObj.getGradeCompVOList().add(tempObj);
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
	}
	
	public String addGradePage(){
		if(editFlag==false){
			selectedGradeMasterVO.setGradeCompVOList(new ArrayList<GradeCompositionVO>());
			gradeName = "";
			selectedGradeCompositionVO = new GradeCompositionVO();
			selectedGradeMasterVO = new GradeMasterVO();
		}
		
		selectedGradeMasterVO.getGradeCompVOList().add(new GradeCompositionVO());
		
		
		return "GradeMasterAdd.jsf";
	}
	
	public String addGrade(){
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		GradeMasterHBC gradeMasterHbc = new GradeMasterHBC(
				selectedGradeMasterVO);
		session.saveOrUpdate(gradeMasterHbc);
		trans.commit();
		session.close();
		
		//selectedGradeMasterVO = new GradeMasterVO();
		selectedGradeMasterVO.getGradeCompVOList().add(new GradeCompositionVO());
		return search();
	}
	
	public void saveGradeComposition() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		selectedGradeCompositionVO.setGradeId(selectedGradeMasterVO.getGradeId());
		GradeCompositionHBC gradeCompositionHbc = new GradeCompositionHBC(
				selectedGradeCompositionVO);
		session.saveOrUpdate(gradeCompositionHbc);
		trans.commit();
		session.close();
		selectedGradeMasterVO.getGradeCompVOList().add(new GradeCompositionVO());
		//edit();
		return;
	}

	public String edit() {

		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		GradeMasterHBC gradeMasterHBC = new GradeMasterHBC(selectedGradeMasterVO);
		session.saveOrUpdate(gradeMasterHBC);
		trans.commit();
		session.close();

		selectedGradeMasterVO = new GradeMasterVO();
		return search();
	}

	/*public String edit() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Transaction trans = session.beginTransaction();
		PartMasterHBC partMasterHbc = new PartMasterHBC(selectedPartMasterVO);
		session.update(partMasterHbc);
		trans.commit();
		session.close();
		selectedPartMasterVO = new PartMasterVO();
		return search();
	}*/

	public String deleteGrade() {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		deleteGradeComposition(selectedGradeMasterVO);
		GradeMasterHBC gradeMasterHbc = new GradeMasterHBC(selectedGradeMasterVO);
		session.delete(gradeMasterHbc);
		session.flush();
		session.close();
		selectedGradeMasterVO = new GradeMasterVO();
		return search();
	}
	
	public void deleteGradeComposition(GradeMasterVO selectedGrade){
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		getGradeComposition(selectedGrade);
		for(GradeCompositionVO gc : selectedGrade.getGradeCompVOList()){
			GradeCompositionHBC gHbc = new GradeCompositionHBC(gc);
			session.delete(gHbc);
		}
		session.flush();
		session.close();
	}

	/*public String addNewPart() {
		selectedPartMasterVO = new PartMasterVO();
		editFlag = false;
		return "PartMasterAdd.xhtml";
	}*/

}