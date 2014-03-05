package org.demo.spinncast.misc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Size")
@SessionScoped
public class Size {
	public Size (int width, int breadth, int height) {
		w = width; b = breadth; h = height;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	int w;
	int h;
	int b;
}
