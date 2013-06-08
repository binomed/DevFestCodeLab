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
package fr.ybonnel.breizhcamppdf;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.ybonnel.breizhcamppdf.model.Jour;
import fr.ybonnel.breizhcamppdf.model.Programme;
import fr.ybonnel.breizhcamppdf.model.Talk;
import fr.ybonnel.breizhcamppdf.model.Track;

public class DataService {

	private Programme programme;

	private static final Map<String, Integer> dureeOfTalks = new HashMap<String, Integer>() {
		{
			put("universite", 180);
			put("quickie", 15);
			put("hands-on", 180);
			put("tools in action", 30);
			put("conference", 60);
			put("lab", 60);
			put("biglab", 120);
			put("keynote", 30);
		}
	};

	private Programme getProgramme() {
		if (programme == null) {
			try {
				// URL url = new URL("file:///D:/sources/Breizhcamp-cfp/conf/breizhcamp.json");
				URL url = new URL("http://cfp.breizhcamp.org/programme");
				URLConnection connection = url.openConnection();
				// programme = gson.fromJson(new InputStreamReader(connection.getInputStream()), Event.class).getProgramme();

				for (Jour jour : getProgramme().getJours()) {
					for (Track track : jour.getTracks()) {
						for (Talk talk : track.getTalks()) {
							if (talk.getTime().length() < 5) {
								talk.setTime("0" + talk.getTime());
							}

							int duree = dureeOfTalks.get(talk.getFormat());
							int hDebut = Integer.parseInt(talk.getTime().split(":")[0]);
							int mDebut = Integer.parseInt(talk.getTime().split(":")[1]);

							int hFin = hDebut;
							int mFin = mDebut + duree;
							while (mFin >= 60) {
								mFin = mFin - 60;
								hFin++;
							}

							talk.setEndTime((hFin < 10 ? "0" : "") + hFin + ":" + (mFin < 10 ? "0" : "") + mFin);

							talk.setTrack(track.getType());
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return programme;
	}

	private List<Talk> talks = null;

	public List<Talk> getTalks() {
		if (talks == null) {
			talks = new ArrayList<Talk>();
			for (Jour jour : getProgramme().getJours()) {
				for (Track track : jour.getTracks()) {
					talks.addAll(track.getTalks());
				}
			}
		}
		return talks;
	}

	private Map<String, List<Talk>> talksByDate;

	public Map<String, List<Talk>> getTalksByDate() {
		if (talksByDate == null) {
			talksByDate = new HashMap<String, List<Talk>>();
			for (Jour jour : getProgramme().getJours()) {
				talksByDate.put(jour.getDate(), new ArrayList<Talk>());
				for (Track track : jour.getTracks()) {
					talksByDate.get(jour.getDate()).addAll(track.getTalks());
				}
			}
		}
		return talksByDate;
	}

	private List<String> rooms;

	public List<String> getRooms() {
		if (rooms == null) {
			Set<String> roomsInSet = new HashSet<String>();
			for (Talk talk : getTalks()) {
				if (talk.getRoom() == null) {
					System.err.println("Talk without room : " + talk.getTitle());
				}
				roomsInSet.add(talk.getRoom());
			}
			rooms = new ArrayList<String>(roomsInSet);
			Collections.sort(rooms);
		}
		return rooms;
	}

	private Map<String, List<String>> creneaux;

	public Map<String, List<String>> getCreneaux() {
		if (creneaux == null) {
			creneaux = new HashMap<String, List<String>>();
			for (Map.Entry<String, List<Talk>> entry : getTalksByDate().entrySet()) {
				Set<String> creneauxForDate = new HashSet<String>();
				for (Talk talk : entry.getValue()) {
					creneauxForDate.add(talk.getTime());
				}
				List<String> creneauxInList = new ArrayList<String>(creneauxForDate);
				Collections.sort(creneauxInList);
				creneaux.put(entry.getKey(), creneauxInList);
			}
		}
		return creneaux;
	}

	public Talk getTalkByDateAndCreneauxAndRoom(String date, String creneau, String room) {
		Talk talkSelected = null;
		for (Talk talk : getTalksByDate().get(date)) {
			if (creneau.equals(talk.getTime()) && room.equals(talk.getRoom())) {
				if (talkSelected != null) {
					throw new RuntimeException("Two talk for " + date + " " + creneau + " " + room);
				}
				talkSelected = talk;
			}
		}
		return talkSelected;
	}
}
