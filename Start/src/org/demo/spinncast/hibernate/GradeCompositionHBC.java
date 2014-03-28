package org.demo.spinncast.hibernate;

import org.demo.spinncast.vo.GradeCompositionVO;

public class GradeCompositionHBC {
	private int gradeCompositionId;
	private int gradeId;
	private String ingrediantType;
	private String ingrediantName;
	private float minValue;
	private float maxValue;

	public int getGradeCompositionId() {
		return gradeCompositionId;
	}

	public void setGradeCompositionId(int gradeCompositionId) {
		this.gradeCompositionId = gradeCompositionId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getIngrediantType() {
		return ingrediantType;
	}

	public void setIngrediantType(String ingrediantType) {
		this.ingrediantType = ingrediantType;
	}

	public String getIngrediantName() {
		return ingrediantName;
	}

	public void setIngrediantName(String ingrediantName) {
		this.ingrediantName = ingrediantName;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public GradeCompositionHBC() {
	
	}

	public GradeCompositionHBC(GradeCompositionVO gradeCompositionVo) {
		this.gradeCompositionId = gradeCompositionVo.getGradeCompositionId();
		this.gradeId = gradeCompositionVo.getGradeId();
		this.ingrediantName = gradeCompositionVo.getIngrediantName();
		this.ingrediantType = gradeCompositionVo.getIngrediantType();
		this.maxValue = gradeCompositionVo.getMaxValue();
		this.minValue = gradeCompositionVo.getMinValue();
	}

	public void update(GradeCompositionVO gradeCompositionVo) {
		this.gradeCompositionId = gradeCompositionVo.getGradeCompositionId();
		this.gradeId = gradeCompositionVo.getGradeId();
		this.ingrediantName = gradeCompositionVo.getIngrediantName();
		this.ingrediantType = gradeCompositionVo.getIngrediantType();
		this.maxValue = gradeCompositionVo.getMaxValue();
		this.minValue = gradeCompositionVo.getMinValue();
	}
}
