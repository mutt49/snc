package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.GradeMasterVO;

public class GradeMasterHBC {
	private int gradeId;
	private String gradeName;
	
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

	public GradeMasterHBC () {
		
	}
	
	public GradeMasterHBC (GradeMasterVO gradeMasterVo) {
		this.gradeId = gradeMasterVo
				.getGradeId();
		this.gradeName = gradeMasterVo.getGradeName();
	}

	public void update (GradeMasterVO gradeMasterVo) {
		this.gradeId = gradeMasterVo
				.getGradeId();
		this.gradeName = gradeMasterVo.getGradeName();
	}
	
}
