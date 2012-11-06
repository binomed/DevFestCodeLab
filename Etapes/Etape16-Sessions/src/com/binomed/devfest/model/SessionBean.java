/*
 * Copyright (C) 2012 Binomed (http://blog.binomed.fr)
 *
 * Licensed under the Eclipse Public License - v 1.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC 
 * LICENSE ("AGREEMENT"). ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM 
 * CONSTITUTES RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 */
package com.binomed.devfest.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

import com.binomed.devfest.utils.model.AbstractParcel;

/**
 * @author JefBinomed
 * 
 *         Simple Bean for session
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionBean extends AbstractParcel implements Parcelable, Comparable<SessionBean> {

	public static final Parcelable.Creator<SessionBean> CREATOR = new Creator<SessionBean>() {

		@Override
		public SessionBean[] newArray(int size) {
			return new SessionBean[size];
		}

		@Override
		public SessionBean createFromParcel(Parcel source) {
			return new SessionBean(source);
		}
	};

	private static final int FIELD_TYPE = 0;
	private static final int FIELD_IMG_TYPE = 1;
	private static final int FIELD_SPEAKER = 2;
	private static final int FIELD_LEVEL = 3;
	private static final int FIELD_ROOM = 4;
	private static final int FIELD_LANG = 5;
	private static final int FIELD_TITLE = 6;
	private static final int FIELD_DESC = 7;
	private static final int FIELD_START_TIME = 8;
	private static final int FIELD_END_TIME = 9;
	private static final int FIELD_END = -1;

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

	public SessionBean() {
		super();
	}

	public SessionBean(Parcel parcel) {
		this();
		readFromParcel(parcel);
	}

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

	private void readFromParcel(Parcel parcel) {
		boolean end = false;
		int code = 0;
		while (!end) {
			code = parcel.readInt();
			switch (code) {
			case FIELD_DESC: {
				setDesc(readString(parcel));
				break;
			}
			case FIELD_END_TIME: {
				setEndTime(readString(parcel));
				break;
			}
			case FIELD_START_TIME: {
				setStartTime(readString(parcel));
				break;
			}
			case FIELD_IMG_TYPE: {
				setImgType(readString(parcel));
				break;
			}
			case FIELD_LANG: {
				setLang(readString(parcel));
				break;
			}
			case FIELD_LEVEL: {
				setLevel(readString(parcel));
				break;
			}
			case FIELD_ROOM: {
				setRoom(readString(parcel));
				break;
			}
			case FIELD_TITLE: {
				setTitle(readString(parcel));
				break;
			}
			case FIELD_TYPE: {
				setType(readString(parcel));
				break;
			}
			case FIELD_SPEAKER: {
				ArrayList<String> speakerList = new ArrayList<String>();
				parcel.readStringList(speakerList);
				setSpeaker(speakerList.toArray(new String[speakerList.size()]));
				break;
			}
			case FIELD_END: {
				end = true;
				break;
			}
			default:
				break;
			}
		}

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (getDesc() != null) {
			dest.writeInt(FIELD_DESC);
			writeString(dest, getDesc());
		}
		if (getEndTime() != null) {
			dest.writeInt(FIELD_END_TIME);
			writeString(dest, getEndTime());
		}
		if (getStartTime() != null) {
			dest.writeInt(FIELD_START_TIME);
			writeString(dest, getStartTime());
		}
		if (getImgType() != null) {
			dest.writeInt(FIELD_IMG_TYPE);
			writeString(dest, getImgType());
		}
		if (getLang() != null) {
			dest.writeInt(FIELD_LANG);
			writeString(dest, getLang());
		}
		if (getLevel() != null) {
			dest.writeInt(FIELD_LEVEL);
			writeString(dest, getLevel());
		}
		if (getRoom() != null) {
			dest.writeInt(FIELD_ROOM);
			writeString(dest, getRoom());
		}
		if (getTitle() != null) {
			dest.writeInt(FIELD_TITLE);
			writeString(dest, getTitle());
		}
		if (getType() != null) {
			dest.writeInt(FIELD_TYPE);
			writeString(dest, getType());
		}
		if (getSpeaker() != null && getSpeaker().length > 0) {
			dest.writeInt(FIELD_SPEAKER);
			dest.writeStringArray(getSpeaker());
		}
		dest.writeInt(FIELD_END);

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
