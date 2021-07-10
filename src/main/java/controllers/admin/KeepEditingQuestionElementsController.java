package controllers.admin;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepEditingQuestionElementsController {

    @PostMapping(value = "/admin/keepEditionQuestionDescription")
    public String keepEditionQuestionDescription(
            @RequestParam(value = "questionId") int questionId,
            @RequestParam(value = "questionEditingDescription") String questionEditingDescription
    ){
        System.out.println(questionId);
        System.out.println(questionEditingDescription);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/keepEditionLiteratureDescription")
    public String keepEditionLiteratureDescription(
            @RequestParam(value = "literatureId") int literatureId,
            @RequestParam(value = "literatureEditingDescription") String literatureEditingDescription
    ){
        System.out.println(literatureId);
        System.out.println(literatureEditingDescription);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

    @PostMapping(value = "/admin/keepEditionLink")
    public String keepEditionLink(
            @RequestParam(value = "linkId") int linkId,
            @RequestParam(value = "linkEditing") String linkEditing
    ){
        System.out.println(linkId);
        System.out.println(linkEditing);

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
        System.out.println(answerId);
        System.out.println(answerEditingDescription);
        System.out.println(correct);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", true);
        return jsonObject.toString();
    }

}
