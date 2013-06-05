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
package com.binomed.breizhcamp.utils;

public final class BreizhCampCst {

	// ACRA

	public static final String ACRA_FROM_KEY = "dGl1am9aWURkT3VyNGZlY2hrOS1rSkE6MQ";

	// URLS
	private static final String URL_PREFIX = "http://cfp.breizhcamp.org/";
	public static final String URL_PROGRAMME = URL_PREFIX + "programme";
	public static final String URL_SPEAKERS = URL_PREFIX + "accepted/speakers";
	public static final String URL_TALK_DETAIL = URL_PREFIX + "accepted/talk/{talkId}";

	// MONGO
	private static final String MONGO_KEY = "508aea53e4b0a116b0905727";

	// MONGO DB
	private static final String MONGO_DATA_BASE_NAME = "devfest_code_lab";
	private static final String MONGO_DATA_BASE_USERNAME = "binomed";
	private static final String MONGO_DATA_BASE_PWD = "devfest";
	private static final String MONGO_TABLE_SESSIONS = "sessions";
	private static final String MONGO_TABLE_SPEAKERS = "speakers";

	// MONGO URLS
	private static final String MONGO_URL_PREFIX = "https://api.mongolab.com/api/1/databases/";
	private static final String MONGO_URL_KEY = "apiKey=" + MONGO_KEY;
	private static final String MONGO_URL_SESSIONS = MONGO_URL_PREFIX + MONGO_DATA_BASE_NAME + "/collections/" + MONGO_TABLE_SESSIONS + "?" + MONGO_URL_KEY;
	public static final String MONGO_URL_SPEAKERS = MONGO_URL_PREFIX + MONGO_DATA_BASE_NAME + "/collections/" + MONGO_TABLE_SPEAKERS + "?" + MONGO_URL_KEY;
	public static final String MONGO_URL_SESSION_SPEAKERS = MONGO_URL_PREFIX + MONGO_DATA_BASE_NAME + "/collections/" + MONGO_TABLE_SPEAKERS + "?" + MONGO_URL_KEY + "&q={query}";
	public static final String MONGO_URL_QUERY = MONGO_URL_SESSIONS + "&q={query}";
	public static final String QUERY_ANDROID = "{$or:[{\"type\":\"All\"},{\"type\":\"Android\"}]}";
	public static final String QUERY_WEB = "{$or:[{\"type\":\"All\"},{\"type\":\"Web\"}]}";
	public static final String QUERY_CLOUD = "{$or:[{\"type\":\"All\"},{\"type\":\"Cloud\"}]}";
	public static final String QUERY_CODELAB = "{$or:[{\"type\":\"All\"},{\"type\":\"CodeLab\"}]}";

	// REQUEST CONSTANTS
	public static final int TYPE_ANDROID = 1;
	public static final int TYPE_WEB = 2;
	public static final int TYPE_CLOUD = 3;
	public static final int TYPE_CODELAB = 4;

	// PAGE INFOS INDEX
	public static final int TYPE_PLACE = 0;
	public static final int TYPE_CAR = 1;
	public static final int TYPE_SLEEP = 2;
	public static final int TYPE_AFTER_PARTY = 3;

	// INTENTS
	public static final String EXTRA_INTENT_TYPE_URL = "com.binomed.breizhcamp.typeUrl";
	public static final String EXTRA_INTENT_SESSION = "com.binomed.breizhcamp.session";

}
