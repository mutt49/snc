package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "GradeCompositionVO")
@SessionScoped
public class GradeCompositionVO {
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
		
}
