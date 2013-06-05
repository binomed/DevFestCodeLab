/*
 * Copyright 2013- Yan Bonnel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ybonnel.breizhcamppdf.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import android.os.Parcel;
import android.os.Parcelable;

import com.binomed.breizhcamp.utils.model.AbstractParcel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Talk extends AbstractParcel implements Parcelable, Comparable<Talk> {

	public static final Parcelable.Creator<Talk> CREATOR = new Creator<Talk>() {

		@Override
		public Talk[] newArray(int size) {
			return new Talk[size];
		}

		@Override
		public Talk createFromParcel(Parcel source) {
			return new Talk(source);
		}
	};

	private static final int FIELD_ID = 0;
	private static final int FIELD_TIME = 1;
	private static final int FIELD_FORMAT = 2;
	private static final int FIELD_TITLE = 3;
	private static final int FIELD_ROOM = 4;
	private static final int FIELD_TRACK = 5;
	private static final int FIELD_END_TIME = 6;
	private static final int FIELD_END = -1;

	private String id;
	private String time;
	private String format;
	private String title;
	private String room;
	private String track;
	private String endTime;

	public Talk() {
		super();
	}

	public Talk(Parcel parcel) {
		this();
		readFromParcel(parcel);
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getFormat() {
		return format;
	}

	public String getTitle() {
		return title;
	}

	public String getRoom() {
		return room;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	private void readFromParcel(Parcel parcel) {
		boolean end = false;
		int code = 0;
		while (!end) {
			code = parcel.readInt();
			switch (code) {
			case FIELD_END_TIME: {
				setEndTime(readString(parcel));
				break;
			}
			case FIELD_TIME: {
				setTime(readString(parcel));
				break;
			}
			case FIELD_FORMAT: {
				setFormat(readString(parcel));
				break;
			}
			case FIELD_ID: {
				setId(readString(parcel));
				break;
			}
			case FIELD_TRACK: {
				setTrack(readString(parcel));
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
		if (getEndTime() != null) {
			dest.writeInt(FIELD_END_TIME);
			writeString(dest, getEndTime());
		}
		if (getTime() != null) {
			dest.writeInt(FIELD_TIME);
			writeString(dest, getTime());
		}
		if (getFormat() != null) {
			dest.writeInt(FIELD_FORMAT);
			writeString(dest, getFormat());
		}
		if (getId() != null) {
			dest.writeInt(FIELD_ID);
			writeString(dest, getId());
		}
		if (getTrack() != null) {
			dest.writeInt(FIELD_TRACK);
			writeString(dest, getTrack());
		}
		if (getRoom() != null) {
			dest.writeInt(FIELD_ROOM);
			writeString(dest, getRoom());
		}
		if (getTitle() != null) {
			dest.writeInt(FIELD_TITLE);
			writeString(dest, getTitle());
		}
		dest.writeInt(FIELD_END);

	}

	@Override
	public int compareTo(Talk another) {
		if (another == null) {
			return 1;
		}
		if (another.time == null) {
			return 1;
		}
		if (time == null) {
			return -1;
		}
		return time.compareTo(another.time);
	}
}
