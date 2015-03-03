package org.wx.model;

import java.util.List;

public class Model {
	
	private List<String> record;

	public List<String> getRecord() {
		return record;
	}

	public void setRecord(List<String> record) {
		this.record = record;
	}

	public Model() {
	}

	public Model(List<String> record) {
		this.record = record;
	}
	
	@Override
	public String toString() {
		return record+"\n";
	}
}
