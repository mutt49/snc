package org.demo.spinncast.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.InvoiceLineItemVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PartMasterHBC {
	private int partId;
	private String partName;
	private String drgNo;
	private float partRate;
	private float pmSize;
	private String partUom;
	private String grade;
	private float castWeight;
	private float proofMachineWeight;
	private float quantity;

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getDrgNo() {
		return drgNo;
	}

	public void setDrgNo(String drgNo) {
		this.drgNo = drgNo;
	}

	public float getPartRate() {
		return partRate;
	}

	public void setPartRate(float partRate) {
		this.partRate = partRate;
	}

	public float getPmSize() {
		return pmSize;
	}

	public void setPmSize(float pmSize) {
		this.pmSize = pmSize;
	}

	public String getPartUom() {
		return partUom;
	}

	public void setPartUom(String partUom) {
		this.partUom = partUom;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public float getCastWeight() {
		return castWeight;
	}

	public void setCastWeight(float castWeight) {
		this.castWeight = castWeight;
	}

	public float getProofMachineWeight() {
		return proofMachineWeight;
	}

	public void setProofMachineWeight(float proofMachineWeight) {
		this.proofMachineWeight = proofMachineWeight;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public PartMasterHBC() {

	}

	public PartMasterHBC(PartMasterVO partMasterVo) {
		partId = partMasterVo.getPartId();
		partName = partMasterVo.getPartName();
		drgNo = partMasterVo.getDrgNo();
		partRate = partMasterVo.getPartRate();
		pmSize = partMasterVo.getPmSize();
		partUom = partMasterVo.getPartUom();
		// grade = partMasterVo.getGrade();
		castWeight = partMasterVo.getCastWeight();
		proofMachineWeight = partMasterVo.getProofMachineWeight();
		quantity = partMasterVo.getQuantity();
	}

	public void update(PartMasterVO partMasterVo) {
		partId = partMasterVo.getPartId();
		partName = partMasterVo.getPartName();
		drgNo = partMasterVo.getDrgNo();
		partRate = partMasterVo.getPartRate();
		pmSize = partMasterVo.getPmSize();
		partUom = partMasterVo.getPartUom();
		// grade = partMasterVo.getGrade();
		castWeight = partMasterVo.getCastWeight();
		proofMachineWeight = partMasterVo.getProofMachineWeight();
		quantity = partMasterVo.getQuantity();
	}

	/*public void getGradesForPart(PartMasterVO partMasterVo) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		PartGradeMappingVO temp = new PartGradeMappingVO();
		
		Query hibernateQuery = session
				.createQuery("from PartGradeMappingHBC as m where m.partId =:partId order by m.gradeId");
		hibernateQuery.setInteger("partId", partMasterVo.getPartId());
		List<PartGradeMappingHBC> results = hibernateQuery.list();
		List<GradeMasterVO> gradeList = new ArrayList<GradeMasterVO>();
		for (int i = 0; i < results.size(); i++) {
			gradeList.add(getGradesById(results.get(i).getGradeId()));
			temp.setGradeId(results.get(i).getGradeId());
			temp.setPartGradeId(partMasterVo.getPartId());
			temp.setPartGradeId(results.get(i).getPartGradeId());
			temp.setGradeName(getGradesById(results.get(i).getGradeId()).getGradeName());
			partMasterVo.getPartGradeMapping().add(temp);
		}
	
		partMasterVo.setGrades(gradeList);
		session.close();
	}

	public GradeMasterVO getGradesById(int gradeId) {
		GradeMasterVO tempObj = new GradeMasterVO();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeId = :gradeId order by g.gradeName");
		hibernateQuery.setInteger("gradeId", gradeId);

		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setGradeName(results.get(i).getGradeName());
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return tempObj;
	}*/

}
