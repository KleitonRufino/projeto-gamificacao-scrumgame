package com.service.impl;

import org.springframework.stereotype.Service;

import com.service.Message;

@Service
public class MessageImpl implements Message {

	@Override
	public String getMessage() {
		return "JSF + Spring Integration";
	}

}