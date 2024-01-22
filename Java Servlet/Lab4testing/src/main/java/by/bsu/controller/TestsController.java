package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import by.bsu.dao.TestDAO;
import by.bsu.entity.Test;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;


public class TestsController implements IController {
	private int selectQueryID;

	public TestsController(int selectQueryID) {
		this.selectQueryID = selectQueryID;
	}

	@Override
	public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
		 WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
	        ctx.setVariable("today", Calendar.getInstance());
			List<Test> tests = null;
			switch (selectQueryID) {
				case 1:
					tests = TestDAO.getTests();
					ctx.setVariable("tests", tests);
					templateEngine.process("tests/list", ctx, writer);
					break;
				case 2:
					String name = parameters.get("name")[0];
					TestDAO.createTest(name);
					templateEngine.process("success", ctx, writer);
					break;
				default:
					break;
			}
	}

}
