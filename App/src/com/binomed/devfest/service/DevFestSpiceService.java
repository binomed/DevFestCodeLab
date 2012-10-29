package com.binomed.devfest.service;

import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidContentService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.json.jackson.JacksonObjectPersisterFactory;

public class DevFestSpiceService extends SpringAndroidContentService {

	@Override
	public CacheManager createCacheManager(Application application) {
		CacheManager cacheManager = new CacheManager();

		JacksonObjectPersisterFactory inJSonFileObjectPersisterFactory = new JacksonObjectPersisterFactory(application);

		inJSonFileObjectPersisterFactory.setAsyncSaveEnabled(true);

		cacheManager.addPersister(inJSonFileObjectPersisterFactory);
		return cacheManager;
	}

	@Override
	public RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// find more complete examples in RoboSpice Motivation app
		// to enable Gzip compression and setting request timeouts.

		// web services support json responses
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate.getMessageConverters();

		listHttpMessageConverters.add(jsonConverter);
		listHttpMessageConverters.add(formHttpMessageConverter);
		listHttpMessageConverters.add(stringHttpMessageConverter);
		restTemplate.setMessageConverters(listHttpMessageConverters);
		return restTemplate;
	}

}
