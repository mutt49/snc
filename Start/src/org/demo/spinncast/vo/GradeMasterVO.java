package org.demo.spinncast.vo;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "GradeMasterVO")
@SessionScoped
public class GradeMasterVO {
	private int gradeId;
	private String gradeName;
	private List<GradeCompositionVO> gradeCompVOList;
	
	public GradeMasterVO() {
		gradeCompVOList = new ArrayList<GradeCompositionVO>();
	}

	public List<GradeCompositionVO> getGradeCompVOList() {
		return gradeCompVOList;
	}

	public void setGradeCompVOList(List<GradeCompositionVO> gradeCompVOList) {
		this.gradeCompVOList = gradeCompVOList;
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
