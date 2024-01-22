package by.bsu.controller;

import java.io.Writer;
import java.util.Map;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.web.IWebExchange;

public interface IController {
	public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer, Map<String, String[]> parameters)
	            throws Exception;
	  
}
