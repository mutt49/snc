package org.demo.spinncast.handler;

import java.util.ArrayList;
import java.util.List;

import org.demo.spinncast.connections.ConnectionPool;
import org.demo.spinncast.hibernate.GradeMasterHBC;
import org.demo.spinncast.hibernate.PartGradeMappingHBC;
import org.demo.spinncast.hibernate.PartMasterHBC;
import org.demo.spinncast.vo.GradeMasterVO;
import org.demo.spinncast.vo.PartGradeMappingVO;
import org.demo.spinncast.vo.PartMasterVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PartMasterHandler {
	
	public PartMasterVO populatePartMaster(String partName) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where part_name =:part_name");
		hibernateQuery.setString("part_name", partName);
		java.util.List<PartMasterHBC> results = hibernateQuery.list();

		PartMasterVO tempPartVo = null;
		if (results.size() > 0 && results.size() < 2) {
			tempPartVo = new PartMasterVO(results.get(0));
		}
		session.close();
		return tempPartVo;
	}
	
	public PartMasterVO populatePartMaster(int partId) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where part_id =:part_id");
		hibernateQuery.setInteger("part_id", partId);
		java.util.List<PartMasterHBC> results = hibernateQuery.list();

		PartMasterVO tempPartVo = null;
		if (results.size() > 0 && results.size() < 2) {
			tempPartVo = new PartMasterVO(results.get(0));
		}
		session.close();
		return tempPartVo;
	}
	
	public GradeMasterVO prepareGradeList(int gradeId) {
		//List<GradeMasterVO> gradeList = new ArrayList<GradeMasterVO>();
		GradeMasterVO tempObj = new GradeMasterVO();
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeId = :gradeName order by g.gradeName");
		hibernateQuery.setInteger("gradeId", gradeId);
		
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			
			tempObj.setGradeId(results.get(i).getGradeId());
			tempObj.setGradeName(results.get(i).getGradeName());
			//getGradeComposition(tempObj);
			//gradeList.add(tempObj);
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
		return tempObj;
	}
	
	public void prepareGradesList(List<PartGradeMappingVO> partGradeMapping){
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeName like :gradeName order by g.gradeName");
		hibernateQuery.setString("gradeName", "%");
		
		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			PartGradeMappingVO partGrade = new PartGradeMappingVO();
			partGrade.setGradeId(results.get(i).getGradeId());
			partGrade.setGradeName(results.get(i).getGradeName());
			partGradeMapping.add(partGrade);
			
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.close();
	}
	
	public void getGradesForPart(PartMasterVO partMasterVo) {
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		
		Query hibernateQuery = session
				.createQuery("from PartGradeMappingHBC as m where m.partId =:partId order by m.gradeId");
		hibernateQuery.setInteger("partId", partMasterVo.getPartId());
		List<PartGradeMappingHBC> results = hibernateQuery.list();
		List<GradeMasterVO> gradeList = new ArrayList<GradeMasterVO>();
		for (int i = 0; i < results.size(); i++) {
			PartGradeMappingVO temp = new PartGradeMappingVO();
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
	}
	
	public String getGradeNameForId(int gradeId) {
		String gradeName = "";
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from GradeMasterHBC as g where g.gradeId = :gradeId order by g.gradeName");
		hibernateQuery.setInteger("gradeId", gradeId);

		java.util.List<GradeMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			gradeName = results.get(i).getGradeName();
		}
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.flush();
		session.close();
		return gradeName;
	}
	
	public int getPartIdByName(String partName) {
		int partId = 0;
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where m.partName like :part_name order by m.partId");
		hibernateQuery.setString("part_name", "%" + partName + "%");
		java.util.List<PartMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			partId = results.get(i).getPartId();
		}
		return partId;
	}
	
	public int getPartIdByExactName(String partName) {
		int partId = 0;
		ConnectionPool cpool = ConnectionPool.getInstance();
		Session session = cpool.getSession();
		Query hibernateQuery = session
				.createQuery("from PartMasterHBC as m where m.partName like :part_name order by m.partId");
		hibernateQuery.setString("part_name", partName);
		java.util.List<PartMasterHBC> results = hibernateQuery.list();
		for (int i = 0; i < results.size(); i++) {
			partId = results.get(i).getPartId();
		}
		return partId;
	}
}
