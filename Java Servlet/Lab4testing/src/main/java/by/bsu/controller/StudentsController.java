package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import by.bsu.dao.StudentDAO;
import by.bsu.dao.TestDAO;
import by.bsu.entity.Student;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;


public class StudentsController implements IController {
	private int selectQueryID;

	public StudentsController(int selectQueryID) {
		this.selectQueryID = selectQueryID;
	}

	@Override
	public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
		 WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
	        ctx.setVariable("today", Calendar.getInstance());
			List<Student> students = null;
			switch (selectQueryID) {
				case 1:
					String name = parameters.get("name")[0];
					StudentDAO.createStudent(name);
					templateEngine.process("success", ctx, writer);
					break;
				case 2:
					String id = parameters.get("id")[0];
					name = parameters.get("name")[0];
					StudentDAO.setTestForStudent(Integer.valueOf(id), name);
					templateEngine.process("success", ctx, writer);
					break;
				case 3:
					students = StudentDAO.getStudents();
					ctx.setVariable("students", students);
					templateEngine.process("students/list", ctx, writer);
					break;
				default:
					break;
			}
	}

}
