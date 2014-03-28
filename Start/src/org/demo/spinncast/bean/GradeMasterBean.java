package org.demo.spinncast.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.GradeCompositionHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.hibernate.PurchaseOrderHBC;
import org.demo.spinncast.vo.GradeCompositionVO;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.demo.spinncast.vo.PurchaseOrderVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean(name = "GradeMasterBean")
@SessionScoped
public class GradeMasterBean {

	private GradeMasterVO selectedGradeMasterVO;
	private GradeCompositionVO selectedGradeCompositionVO;
	private String gradeName;
	private List<GradeCompositionVO> searchList = new ArrayList<GradeCompositionVO>();
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

	public List<GradeCompositionVO> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<GradeCompositionVO> searchList) {
		this.searchList = searchList;
	}

	public Boolean getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(Boolean editFlag) {
		this.editFlag = editFlag;
	}

	public GradeMasterBean() {
	}

	public String reset() {
		
		return "";
	}

	@SuppressWarnings("unchecked")
	public String search() {
		searchList = new ArrayList<GradeCompositionVO>();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("select gc.gradeCompId from GradeMasterHBC as g, GradeCompositionHBC as gc where g.gradeId = gc.gradeId order by g.gradeName");
		hibernateQuery.setString("grade_name", "%"+gradeName+"%");
		java.util.List<?> results = hibernateQuery.list();
		
		 for (Iterator it = results.iterator(); it.hasNext(); ) {
			 GradeCompositionVO tempObj = new GradeCompositionVO();
             Object[] myResult = (Object[]) it.next();
             tempObj.setGradeCompositionId(Integer.parseInt(myResult[0].toString()));
             searchList.add(tempObj);
          }

		/*for (int i = 0; i < results.size(); i++) {
			GradeCompositionVO tempObj = new GradeCompositionVO();
			tempObj = (GradeCompositionVO) results.get(i);
			tempObj.setGradeCompositionId(results.get(0).getGradeCompId());
			tempObj.setGradeId(results.get(0).getGradeId());
			tempObj.setIngrediantName(results.get(0).getIngrediantName());
			tempObj.setIngrediantType(results.get(0).getIngrediantType());
			tempObj.setMaxValue(results.get(0).getMaxValue());
			tempObj.setMinValue(results.get(0).getMinValue());
			searchList.add(tempObj);
		}*/

		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return "GradeMasterSearch";
	}

	/*public String add() {

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
	}*/

}