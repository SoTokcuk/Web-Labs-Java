package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.Map;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

public class HomeController implements IController {

	@Override
	public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
		 WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
	        ctx.setVariable("today", Calendar.getInstance());
	          
	        templateEngine.process("home", ctx, writer);
		
	}

}
