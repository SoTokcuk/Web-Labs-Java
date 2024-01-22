package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import by.bsu.dao.AnswerDAO;
import by.bsu.dao.TestDAO;
import by.bsu.entity.Answer;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;


public class AnswersController implements IController {
    private int selectQueryID;

    public AnswersController(int selectQueryID) {
        this.selectQueryID = selectQueryID;
    }

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("today", Calendar.getInstance());
        List<Answer> answers = null;
        switch (selectQueryID) {
            case 1:
                answers = AnswerDAO.getStudentAnswers(Integer.valueOf(parameters.get("student_id")[0])
                        , Integer.valueOf(parameters.get("test_id")[0]));
                ctx.setVariable("answers", answers);
                templateEngine.process("answers/list", ctx, writer);
                break;
            case 2:
                String test_id = parameters.get("test_id")[0];
                String student_id = parameters.get("student_id")[0];
                String answer = parameters.get("answer")[0];
                AnswerDAO.createAnswer(Integer.valueOf(test_id), Integer.valueOf(student_id), answer);
                templateEngine.process("success", ctx, writer);
                break;
            case 3:
                AnswerDAO.updateResult(Integer.valueOf(parameters.get("answer_id")[0]), parameters.get("result")[0]);
                templateEngine.process("success", ctx, writer);
                break;
            case 4:
                AnswerDAO.updateAnswer(Integer.valueOf(parameters.get("answer_id")[0]), parameters.get("answer")[0]);
                templateEngine.process("success", ctx, writer);
                break;
            case 5:
                answers = AnswerDAO.getAnswers();
                ctx.setVariable("answers", answers);
                templateEngine.process("answers/list", ctx, writer);
                break;
            default:
                break;
        }
    }

}
