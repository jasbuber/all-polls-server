package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.PartialPollChoice;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import views.html.pollster_poll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

/**
 * Created by Jasbuber on 24/06/2016.
 */
public class AdminService {

    public CompletionStage<Result> getHuffingtonPollsByTopic(WSClient ws, String topic){
        WSRequest request = ws.url("http://elections.huffingtonpost.com/pollster/api/polls.json")
                .setQueryParameter("topic", topic);

        return request.get().thenApply(WSResponse::asJson).thenApply(wsResponse -> {

            HashMap<String, List<PartialPollChoice>> results = new HashMap<>();

            for(JsonNode json : wsResponse){
                String pollster = json.get("pollster").asText();
                if(!results.containsKey(pollster)){

                    List<PartialPollChoice> choices = new ArrayList<>();
                    JsonNode questions = json.get("questions");

                    for(JsonNode question : questions){
                        JsonNode qTopic = question.get("topic");

                        if(!qTopic.isNull() && qTopic.asText().equals(topic)){
                            JsonNode responses = question.get("subpopulations").get(0).get("responses");

                            for(JsonNode choice: responses){
                                String name = choice.get("choice").asText();
                                double value = choice.get("value").doubleValue();
                                choices.add(new PartialPollChoice(name, value));
                            }

                            results.put(pollster, choices);
                            break;
                        }
                    }
                }
            }
            return ok(pollster_poll.render(topic, results));
        } );

    }

}
