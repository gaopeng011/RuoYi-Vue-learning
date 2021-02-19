package com.ruoyi.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Json转换工具
 *
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class JsonTool {

	/**
	 * 转换为 Map<String,Object> Object可谓基础数据类型 即可为 复杂的 List<Map> ,数组等类型 但 Java
	 * 数据类型 int 转变额为 double类型
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonToObject(String jsonString) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(jsonString, new TypeToken<Map<String, Object>>() {
		}.getType());
	}

	/**
	 * 解析简单的Map<String,Object> Object 基础数据类型 如 String ,int 等
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonStringToObject(String jsonString) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(jsonString, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	/**
	 * String类型解析成简单Map<String,String>
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, String> jsonStringToMapStr(String jsonString) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(jsonString, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	/**
	 * String类型解析成简单Map<String,Object>
	 *
	 * @param jsonString
	 * @return
	 */
	public static Map<String, Object> jsonStringToMapObjectStr(String jsonString) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.fromJson(jsonString, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	public static <T> T jsonToMapBean(String jsonString, Type type) {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, type);
	}

	/**
	 * 转换为
	 *
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> jsonToListMap(String jsonString) {
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		List<Map<String, Object>> retMap = gson.fromJson(jsonString, new TypeToken<List<Map<String, Object>>>() {
		}.getType());
		return retMap;
	}

	/**
	 * Map 转换为Json 对象
	 *
	 * @param fromMap
	 * @return
	 */
	public static String mapToJson(Object object) {
		return new GsonBuilder().enableComplexMapKeySerialization().create().toJson(object);

	}

}
