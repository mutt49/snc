package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "PartGradeMappingVO")
@SessionScoped
public class PartGradeMappingVO {
	private int partId;
	private int gradeId;
	private int partGradeId;
	private String gradeName;

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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
