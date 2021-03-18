package com.ssafy.kpop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.kpop.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor{

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	private static final String HEADER_AUTH = "access_token";
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}
}
