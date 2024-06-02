package knu.team7.syllabus.core.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonUtil {
    static Gson gson = new Gson();
    public static String toJson(Object payload) {
        return gson.toJson(payload);
    }

    public static JsonObject fromJson(String jsonData) {
        return gson.fromJson(jsonData, JsonObject.class);
    }

    public static JsonObject getAsJsonObject(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonObject(key);
    }

    public static JsonArray getAsJsonArray(JsonObject jsonObject, String key) {
        return jsonObject.getAsJsonArray(key);
    }

    public static String getStringOrNull(JsonObject item, String key) {
        if (item.has(key)) {
            JsonElement element = item.get(key);
            if (!element.isJsonNull()) {
                String value = element.getAsString();
                if (!value.isEmpty()) {
                    return value;
                }
            }
        }
        return null;
    }
}
