package com.itaoniu.testserver.provider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class CustomProvider extends JacksonJsonProvider {

	public CustomProvider() {
		super();
		ObjectMapper om = _mapperConfig.getConfiguredMapper();
		if (om == null) {
			om = _mapperConfig.getDefaultMapper();
			_mapperConfig.setMapper(om);
		}
		SerializationConfig sconfig = om.getSerializationConfig();
		if (sconfig != null) {
			DateFormat format=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",Locale.ENGLISH);
			format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
			sconfig.setDateFormat(format);
		}
	}

	public CustomProvider(Annotations... annotationsToUse) {
		super(annotationsToUse);

	}

	public CustomProvider(ObjectMapper mapper, Annotations[] annotationsToUse) {
		super(mapper, annotationsToUse);

	}

	public CustomProvider(ObjectMapper mapper) {
		super(mapper);

	}

}
