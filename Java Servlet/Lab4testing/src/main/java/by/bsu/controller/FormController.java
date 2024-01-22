package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.Map;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

public class FormController implements IController {
    private int formID;

	public FormController(int formID) {
		this.formID = formID;
	}

	@Override
	public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
		 WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
	        ctx.setVariable("today", Calendar.getInstance());

            switch (formID) {
                case 1:
                    templateEngine.process("forms/createTest", ctx, writer);
                    break;
				case 2:
					templateEngine.process("forms/updateResult", ctx, writer);
					break;
				case 3:
					templateEngine.process("forms/setTestForStudent", ctx, writer);
					break;
				case 4:
					templateEngine.process("forms/createStudent", ctx, writer);
					break;
				case 5:
					templateEngine.process("forms/getQuestionsByTestID", ctx, writer);
					break;
				case 6:
					templateEngine.process("forms/createQuestion", ctx, writer);
					break;
				case 7:
					templateEngine.process("forms/updateAnswer", ctx, writer);
					break;
				case 8:
					templateEngine.process("forms/getStudentAnswers", ctx, writer);
					break;
				case 9:
					templateEngine.process("forms/createAnswer", ctx, writer);
					break;
			}

	          
	        
		
	}

}
