package org.demo.spinncast.vo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "TestCertificateActualValuesVO")
@SessionScoped
public class TestCertificateActualValuesVO {
	private int tableId;
	private int tcId;
	private String propName = "";
	private String propType = "";
	private String minValue;
	private String maxValue;
	private String actual1;
	private String actual2;
	private String actual3;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getPropType() {
		return propType;
	}
	public void setPropType(String propType) {
		this.propType = propType;
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
	public String getActual1() {
		return actual1;
	}
	public void setActual1(String actual1) {
		this.actual1 = actual1;
	}
	public String getActual2() {
		return actual2;
	}
	public void setActual2(String actual2) {
		this.actual2 = actual2;
	}
	public String getActual3() {
		return actual3;
	}
	public void setActual3(String actual3) {
		this.actual3 = actual3;
	}
	public TestCertificateActualValuesVO() {
	}
	
	public TestCertificateActualValuesVO(GradeCompositionVO gradeComp) {
		propName = gradeComp.getIngrediantName();
		propType = gradeComp.getIngrediantType();
		minValue = gradeComp.getMinValue();
		maxValue = gradeComp.getMaxValue();
	}

}
