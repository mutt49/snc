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
	private String minValue;
	private String maxValue;
	
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
	public String getMinValue() {
		return minValue;
	}
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
		
}
