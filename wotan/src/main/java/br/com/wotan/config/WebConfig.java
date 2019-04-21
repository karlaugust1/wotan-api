package br.com.wotan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.wotan.*")
public class WebConfig extends WebMvcConfigurerAdapter {
	
   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
   }
   
   
   @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**/*").addResourceLocations("/WEB-INF/resources/");
	}
   
  /* @Bean
   public MultipartResolver multipartResolver() {
	   CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	   multipartResolver.setMaxUploadSize(20485760);
	   multipartResolver.setMaxUploadSizePerFile(2048576);
	   return multipartResolver;
   }*/
}
