package com.binomed.devfest.utils;

public final class DevFestCst {

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

	// INTENTS
	public static final String EXTRA_INTENT_TYPE_URL = "com.binomed.devfest.typeUrl";

}
