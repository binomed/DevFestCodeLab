package com.binomed.devfest.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionListBean {

	private List<SessionBean> sessionList;

	public List<SessionBean> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<SessionBean> sessionList) {
		this.sessionList = sessionList;
	}

}
