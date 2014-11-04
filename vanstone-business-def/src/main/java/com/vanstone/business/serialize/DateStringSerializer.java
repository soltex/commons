/**
 * 
 */
package com.vanstone.business.serialize;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vanstone.business.CommonDateUtil;

/**
 * @author shipeng
 */
public class DateStringSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		if (jsonElement != null) {
			return CommonDateUtil.string2Date(jsonElement.getAsString(), "yyyy-MM-dd");
		}
		return null;
	}

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
		if (date != null) {
			return new JsonPrimitive(CommonDateUtil.date2String(date, "yyyy-MM-dd"));
		}
		return null;
	}
	
}
