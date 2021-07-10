package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.*;
import services.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class TestManagementAdminController {

    private TopicService topicService;

    private TestService testService;

    private QuestionService questionService;

    private AnswerService answerService;

    private LiteratureService literatureService;

    private LinkService linkService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping(value = "/admin/addTest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String addTest(
            //возникает java.lang.NumberFormatException: For input string: "", если topicId - int
            @RequestParam(name = "topicId", required = false) String topicId,
            @RequestParam(name = "topicName", required = false) String topicName,
            @RequestParam(name = "topicDescription", required = false) String topicDescription,
            @RequestParam(name = "testName") String testName,
            @RequestParam(name = "testDescription", required = false) String testDescription
    ){

        System.out.println("___________________________________");
        System.out.println("topicID= " + topicId);
        System.out.println("topicName= " + topicName);
        System.out.println("topicDescription= " + topicDescription);
        System.out.println("testName= " + testName);
        System.out.println("topicDescription= " + testDescription);
        System.out.println("___________________________________");

        Topic topic;
        if(topicId.isEmpty()){
            topic = new Topic();
            topic.setName(topicName);
            topic.setDescription(topicDescription);
        } else {
            topic = topicService.getLazyInstance().getTopicById(Integer.parseInt(topicId));
        }
        topicService.getLazyInstance().createTopic(topic);
        Test test = new Test();
        test.setName(testName);
        test.setDescription(testDescription);
        test.setTopic(topic);
        testService.getLazyInstance().createTest(test);

        System.out.println("TEST ID: " + test.getTestId());

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        jsonObject.addProperty("newTestId", test.getTestId());
//        jsonObject.addProperty("message", "Новый ");
        return jsonObject.toString();
    }


    @PostMapping(value = "/admin/isUniqueTestName")
    @ResponseBody
    public String checkLogin(@RequestParam(value = "name", required = false, defaultValue = "undefined") String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("unique", !testService.getLazyInstance().isTestWithName(name));
        return jsonObject.toString();
    }


    @PostMapping(value = "/admin/filling_out_the_test")
    public ModelAndView fillingTest(ModelAndView modelAndView, int questionNumber, String testId, int answersNumber, int literatureNumber, String linksNumber) {
        modelAndView.setViewName("admin/questionsPages4Test");
        modelAndView.addObject("questionNumber", questionNumber);
        modelAndView.addObject("test", testService.getEagerInstance().getTestById(Integer.parseInt(testId)));
        modelAndView.addObject("answersNumber", answersNumber);
        modelAndView.addObject("literatureNumber", literatureNumber);
        modelAndView.addObject("linksNumber", Arrays.stream(linksNumber.split(",")).map(String::trim).collect(Collectors.toList()));

        return modelAndView;
    }

    @PostMapping(value = "/admin/addQuestion")
    @ResponseBody
    public String checkLogin(
            @RequestParam(value = "testId") int testId,
            @RequestParam(value = "questionNumber") int questionNumber,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "answers[]") String[] answers,
            @RequestParam(value = "corrects[]") String[] corrects,
            @RequestParam(value = "literature[]", required = false) String[] literature,
            @RequestParam(value = "links[]", required = false) String[] links,
            @RequestParam(value = "linkCipher[]") String[] linkCipher
    ) {
        System.out.println("___________________________________");
        System.out.println(questionNumber);
        System.out.println(description);
        System.out.println(Arrays.toString(answers));
        System.out.println(Arrays.toString(corrects));
        System.out.println(Arrays.toString(literature));
        System.out.println(Arrays.toString(links));
        System.out.println(Arrays.toString(linkCipher));
        System.out.println("___________________________________");

        Question question = new Question();
        question.setDescription(description);
        question.setTest(testService.getEagerInstance().getTestById(testId));
        questionService.getLazyInstance().createQuestion(question);

        for (int i = 0; i < answers.length; i++) {
            Answer answer = new Answer();
            answer.setDescription(answers[i]);
            answer.setCorrect(Integer.parseInt(corrects[i]));
            answer.setQuestion(question);
            answerService.getLazyInstance().createAnswer(answer);
        }

        if(literature != null) {
            int linkCount = 0;
            for (int i = 0; i < literature.length; i++) {
                Literature newLiterature = new Literature();
                newLiterature.setDescription(literature[i]);
                newLiterature.setQuestion(question);
                literatureService.getLazyInstance().createLiterature(newLiterature);
                for (int j = 0; j < Integer.parseInt(linkCipher[i]); j++) {
                    Link link = new Link();
                    link.setLink(links[linkCount++]);
                    link.setLiterature(newLiterature);
                    linkService.getLazyInstance().createLink(link);
                }
            }
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @GetMapping(value = "/admin/editFirstQuestion4Test")
    public ModelAndView editFirstQuestion(ModelAndView modelAndView, String testId){

        Test test = testService.getEagerInstance().getTestById(Integer.parseInt(testId));
        modelAndView.addObject("test", test);
        if(test.getQuestions().size() == 0){
            modelAndView.setViewName("admin/emptyTestPage");
        } else {
            Question question = test.getQuestions().stream().findFirst().get();
            modelAndView.setViewName("admin/editQuestionPage");
            modelAndView.addObject("question", question);
            modelAndView.addObject("questionNumber", 1);
        }

        return modelAndView;
    }

    @GetMapping(value = "/admin/editAnyQuestion4Test")
    public ModelAndView editQuestion(ModelAndView modelAndView, int testId, int questionId, int questionNumber){
        config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    static void config4EditingQuestionPage(ModelAndView modelAndView, int testId, int questionId, int questionNumber, TestService testService) {
        Test test = testService.getEagerInstance().getTestById(testId);
        Question question = test.getQuestions().stream().filter(p->p.getQuestionId()==questionId).findFirst().get();
        modelAndView.setViewName("admin/editQuestionPage");
        modelAndView.addObject("test", test);
        modelAndView.addObject("question", question);
        modelAndView.addObject("questionNumber", questionNumber);
    }

    @GetMapping(value = "/admin/emptyTest")
    public ModelAndView emptyTest(ModelAndView modelAndView, int testId){
        Test test = testService.getEagerInstance().getTestById(testId);
        modelAndView.setViewName("admin/emptyTestPage");
        modelAndView.addObject("test", test);
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteTest")
    @ResponseBody
    public String deleteTest(@RequestParam(name = "testId") Integer testId){

        System.out.println(testId);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }


}
