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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

import com.binomed.devfest.utils.model.AbstractParcel;

/**
 * @author JefBinomed
 * 
 *         Simple bean for speaker
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpeakerBean extends AbstractParcel implements Parcelable, Comparable<SpeakerBean> {

	public static final Parcelable.Creator<SpeakerBean> CREATOR = new Creator<SpeakerBean>() {

		@Override
		public SpeakerBean[] newArray(int size) {
			return new SpeakerBean[size];
		}

		@Override
		public SpeakerBean createFromParcel(Parcel source) {
			return new SpeakerBean(source);
		}
	};

	private static final int FIELD_NAME = 0;
	private static final int FIELD_PHOTO_URL = 1;
	private static final int FIELD_GPLUS_URL = 2;
	private static final int FIELD_DESC = 3;
	private static final int FIELD_END = -1;

	private String name;
	private String photoUrl;
	private String gPlusUrl;
	private String desc;

	public SpeakerBean() {
		super();
	}

	public SpeakerBean(Parcel parcel) {
		this();
		readFromParcel(parcel);
	}

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
			case FIELD_GPLUS_URL: {
				setgPlusUrl(readString(parcel));
				break;
			}
			case FIELD_NAME: {
				setName(readString(parcel));
				break;
			}
			case FIELD_PHOTO_URL: {
				setPhotoUrl(readString(parcel));
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
		if (getgPlusUrl() != null) {
			dest.writeInt(FIELD_GPLUS_URL);
			writeString(dest, getgPlusUrl());
		}
		if (getName() != null) {
			dest.writeInt(FIELD_NAME);
			writeString(dest, getName());
		}
		if (getPhotoUrl() != null) {
			dest.writeInt(FIELD_PHOTO_URL);
			writeString(dest, getPhotoUrl());
		}
		dest.writeInt(FIELD_END);

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
