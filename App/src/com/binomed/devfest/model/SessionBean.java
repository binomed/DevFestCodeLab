package com.binomed.devfest.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionBean implements Serializable, Parcelable, Comparable<SessionBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String imgType;
	private String[] speaker;
	private String level;
	private String room;
	private String lang;
	private String title;
	private String desc;
	private String startTime;
	private String endTime;

	/*
	 * public SessionBean(@JsonProperty("type") String type // , @JsonProperty("imgType") String imgType // , @JsonProperty("speaker") String speaker // , @JsonProperty("level") String level // , @JsonProperty("room") String room // , @JsonProperty("lang") String lang // , @JsonProperty("title")
	 * String title // , @JsonProperty("desc") String desc // / ) { super(); this.type = type; this.imgType = imgType; this.speaker = speaker; this.level = level; this.room = room; this.lang = lang; this.title = title; this.desc = desc; }
	 */

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String[] getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String[] speaker) {
		this.speaker = speaker;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(SessionBean another) {
		if (another == null) {
			return 1;
		}
		if (another.startTime == null) {
			return 1;
		}
		if (startTime == null) {
			return -1;
		}
		return startTime.compareTo(another.startTime);
	}

}
