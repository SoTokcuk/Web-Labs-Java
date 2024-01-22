package by.bsu.controller;

import java.io.Writer;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import by.bsu.dao.QuestionDAO;
import by.bsu.entity.Question;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;


public class QuestionsController implements IController {
    private int selectQueryID;

    public QuestionsController(int selectQueryID) {
        this.selectQueryID = selectQueryID;
    }

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, Map<String, String[]> parameters) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("today", Calendar.getInstance());
        List<Question> questions = null;
        switch (selectQueryID) {
            case 1:
                questions = QuestionDAO.getQuestions();
                ctx.setVariable("questions", questions);
                templateEngine.process("questions/list", ctx, writer);
                break;
            case 2:
                String id = parameters.get("test_id")[0];
                String description = parameters.get("description")[0];
                String subject = parameters.get("subject")[0];
                QuestionDAO.createQuestion(Integer.valueOf(id), description, subject);
                templateEngine.process("success", ctx, writer);
                break;
            case 3:
                questions = QuestionDAO.getQuestionsByTestID(Integer.valueOf(parameters.get("test_id")[0]));
                ctx.setVariable("questions", questions);
                templateEngine.process("questions/list", ctx, writer);
                break;
            default:
                break;
        }
    }

}
