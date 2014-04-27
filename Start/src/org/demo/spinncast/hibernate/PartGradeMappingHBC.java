package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.PartGradeMappingVO;

public class PartGradeMappingHBC {
	private int partId;
	private int gradeId;
	private int partGradeId;

	public int getPartGradeId() {
		return partGradeId;
	}

	public void setPartGradeId(int partGradeId) {
		this.partGradeId = partGradeId;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public PartGradeMappingHBC() {

	}

	public PartGradeMappingHBC(PartGradeMappingVO partGradeVo) {
		partId = partGradeVo.getPartId();
		gradeId = partGradeVo.getGradeId();
	}

	public void update(PartGradeMappingVO partGradeVo) {
		partId = partGradeVo.getPartId();
		gradeId = partGradeVo.getGradeId();
	}

}
