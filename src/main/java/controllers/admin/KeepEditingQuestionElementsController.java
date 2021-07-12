package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import repository.dao.entities.Question;
import services.AnswerService;
import services.LinkService;
import services.LiteratureService;
import services.QuestionService;

@RestController
public class KeepEditingQuestionElementsController {

    private QuestionService questionService;

    private LiteratureService literatureService;

    private LinkService linkService;

    private AnswerService answerService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(value = "/admin/keepEditionQuestionDescription")
    public String keepEditionQuestionDescription(
            @RequestParam(value = "questionId") int questionId,
            @RequestParam(value = "questionEditingDescription") String questionEditingDescription
    ){
        questionService.getLazyInstance().updateQuestionById(questionId, questionEditingDescription);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/keepEditionLiteratureDescription")
    public String keepEditionLiteratureDescription(
            @RequestParam(value = "literatureId") int literatureId,
            @RequestParam(value = "literatureEditingDescription") String literatureEditingDescription
    ){
        literatureService.getLazyInstance().updateLiteratureById(literatureId, literatureEditingDescription);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/keepEditionLink")
    public String keepEditionLink(
            @RequestParam(value = "linkId") int linkId,
            @RequestParam(value = "linkEditing") String linkEditing
    ){
        linkService.getLazyInstance().updateLinkById(linkId, linkEditing);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/keepEditionAnswer")
    public String keepEditionAnswer(
            @RequestParam(value = "answerId") int answerId,
            @RequestParam(value = "answerEditingDescription") String answerEditingDescription,
            @RequestParam(value = "answerEditingCorrect") boolean correct
    ){
        answerService.getLazyInstance().updateAnswerById(answerId, answerEditingDescription, correct ? 1 : 0);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

}
