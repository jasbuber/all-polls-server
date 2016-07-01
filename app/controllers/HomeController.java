package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Poll;
import play.db.jpa.Transactional;
import play.mvc.*;

import repositories.PollRepository;
import services.PollService;

import java.util.List;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @Transactional(readOnly = true)
    public Result index() {
        return ok("Ok!");
    }

    @Transactional(readOnly = true)
    public Result getAvailablePolls() {

        List<Poll> polls = getPollsService().getPollsList();
        return ok(getGson().toJson(polls));
    }

    @Transactional(readOnly = true)
    public Result getPoll(long id) {

        Poll poll = getPollsService().getPoll(id);
        return ok(getGson().toJson(poll));
    }

    private PollService getPollsService() {
        return new PollService(new PollRepository());
    }

    public Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }


}
