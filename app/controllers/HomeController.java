package controllers;

import com.google.gson.Gson;
import models.Poll;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.*;

import services.PollService;
import views.html.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        return ok(new Gson().toJson(polls));
    }

    @Transactional(readOnly = true)
    public Result getPoll(long id) {

        Poll poll = getPollsService().getPoll(id);
        return ok(new Gson().toJson(poll));
    }

    private PollService getPollsService() {
        return new PollService();
    }


}
