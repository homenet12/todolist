package test.todo.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.todo.exception.AuthorizedException;
import test.todo.exception.ExceptionResponse;

@WebFilter(urlPatterns = {"/todos", "/todos/*"})
public class ApikeyFilter extends OncePerRequestFilter{
	
	@Value("${api-key}")
	private String apikey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(notGet(request.getMethod())) {
			try {
				checkApikey(request.getParameter("apikey"));
				filterChain.doFilter(request, response);
			}catch(AuthorizedException ex) {
				ExceptionResponse exceptionResponse = new ExceptionResponse(401, "Not Authorized");
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setCharacterEncoding("utf-8");
				
				ObjectMapper om = new ObjectMapper();
		        om.writeValue(response.getWriter(), exceptionResponse);
			}
		}else {
			filterChain.doFilter(request, response);
		}
	}

	private boolean notGet(String httpMethod) {
		if(!httpMethod.equals("GET")) {
			return true;
		}
		return false;
	}
	
	private void checkApikey(String apikey) {
		if(apikey == null || !apikey.equals(this.apikey)) {
			throw new AuthorizedException("api key를 확인해주세요.");
		}
	}
}
