package com.binomed.devfest.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeakerBean implements Serializable, Parcelable, Comparable<SpeakerBean> {

	private String name;
	private String photoUrl;
	private String gPlusUrl;
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getgPlusUrl() {
		return gPlusUrl;
	}

	public void setgPlusUrl(String gPlusUrl) {
		this.gPlusUrl = gPlusUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(SpeakerBean another) {
		if (another == null) {
			return 1;
		}
		if (another.name == null) {
			return 1;
		}
		if (name == null) {
			return -1;
		}
		return name.compareTo(another.name);
	}

}
