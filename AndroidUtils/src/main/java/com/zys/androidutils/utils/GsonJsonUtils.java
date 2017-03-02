package com.billy.androidutils.utils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 * 该类作用及功能说明
 */
public class GsonJsonUtils {
	public static Map<String, Object> parseJson2Map(String json) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();
		JsonReader reader = new JsonReader(new StringReader(json));
		map = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	public static String parseObj2Json(Object object) throws Exception {
		if (object == null) {
			return "";
		}
		Gson g = new GsonBuilder().create();
		String json = g.toJson(object, object.getClass());
		return json;
	}

	/**
	 * JSON转成指定对象
	 * @param json
	 */
	public static <T> T parseJson2Obj(String json, TypeToken<T> typeToken) throws Exception {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new StringReader(json));
		return gson.fromJson(reader, typeToken.getType());
	}
}
