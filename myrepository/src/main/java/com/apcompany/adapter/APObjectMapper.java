package com.apcompany.adapter;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;

public class APObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public APObjectMapper() {
		System.out.println("APObjectMapper  init ....");
		// 为该objectmapper设置DateFormate的处理
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// 为该objectmapper对象设置propertiesNameing...
		this.setPropertyNamingStrategy(new PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
		// 为该objectmapper设置null的处理
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object arg0, JsonGenerator arg1,
							SerializerProvider arg2) throws IOException,
							JsonProcessingException {
						arg1.writeString("");
					}
				});
	}
	@Override
	public void writeValue(OutputStream out, Object value) throws IOException,
			JsonGenerationException, JsonMappingException {
		System.out.println("write*****");
		super.writeValue(out, value);
	}
	@Override
	public void writeValue(DataOutput out, Object value) throws IOException {
		System.out.println("***xxx***");
		super.writeValue(out, value);
	}
	
}
