package by.bsu.servlet;

import java.util.HashMap;
import java.util.Map;

import by.bsu.controller.*;
import org.thymeleaf.web.IWebRequest;



public class ControllerMappings {


    private static Map<String, IController> controllersByURL;


    static {
        controllersByURL = new HashMap<String, IController>();
        controllersByURL.put("/", new HomeController());

        controllersByURL.put("/welcome/tests/list", new TestsController(1));
        controllersByURL.put("/welcome/createTest", new TestsController(2));

        controllersByURL.put("/welcome/createStudent", new StudentsController(1));
        controllersByURL.put("/welcome/setTestForStudent", new StudentsController(2));
        controllersByURL.put("/welcome/students/list", new StudentsController(3));

        controllersByURL.put("/welcome/getStudentAnswers", new AnswersController(1));
        controllersByURL.put("/welcome/createAnswer", new AnswersController(2));
        controllersByURL.put("/welcome/updateResult", new AnswersController(3));
        controllersByURL.put("/welcome/updateAnswer", new AnswersController(4));

        controllersByURL.put("/welcome/questions/list", new QuestionsController(1));
        controllersByURL.put("/welcome/createQuestion", new QuestionsController(2));
        controllersByURL.put("/welcome/getQuestionsByTestID", new QuestionsController(3));

        controllersByURL.put("/welcome/tests/create", new FormController(1));
        controllersByURL.put("/welcome/answers/update_result", new FormController(2));
        controllersByURL.put("/welcome/students/set", new FormController(3));
        controllersByURL.put("/welcome/students/create", new FormController(4));
        controllersByURL.put("/welcome/questions/list_by_id", new FormController(5));
        controllersByURL.put("/welcome/questions/create", new FormController(6));
        controllersByURL.put("/welcome/answers/update_answer", new FormController(7));
        controllersByURL.put("/welcome/answers/list_by_student", new FormController(8));
        controllersByURL.put("/welcome/answers/create", new FormController(9));

    }
    

    
    public static IController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        System.out.println(path);
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }


}
