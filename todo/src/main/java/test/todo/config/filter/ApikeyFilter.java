package test.todo.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.todo.exception.AuthorizedException;
import test.todo.exception.ExceptionResponse;

public class ApikeyFilter implements Filter{
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(notGet(req.getMethod())) {
			try {
				checkApikey(req.getParameter("apikey"));
			}catch(AuthorizedException ex) {
				ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED);
				res.setStatus(HttpStatus.FORBIDDEN.value());
				res.setContentType(MediaType.APPLICATION_JSON_VALUE);
				res.setCharacterEncoding("utf-8");

				ObjectMapper om = new ObjectMapper();
		        om.writeValue(res.getWriter(), exceptionResponse);
		        res.getWriter().close();
			}				
		}
		chain.doFilter(request, response);
	}
	
	private boolean notGet(String httpMethod) {
		if(!httpMethod.equals("GET")) {
			return true;
		}
		return false;
	}
	
	private void checkApikey(String apikey) {
		if(apikey == null || !apikey.equals("123")) {
			throw new AuthorizedException("api key를 확인해주세요.");
		}
	}
}
