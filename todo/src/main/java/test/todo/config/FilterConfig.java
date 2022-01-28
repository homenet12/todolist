package test.todo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import test.todo.config.filter.ApikeyFilter;

@Configuration
public class FilterConfig implements WebMvcConfigurer{

	@Bean
	public FilterRegistrationBean<ApikeyFilter> apikeyFilter() {
		FilterRegistrationBean<ApikeyFilter> filter = new FilterRegistrationBean<>(new ApikeyFilter());
		filter.setOrder(0);
		filter.addUrlPatterns("/*");
		filter.setName("apikeyFilter");
		return filter;
	}
}
