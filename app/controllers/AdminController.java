package controllers;

import com.google.inject.Inject;
import models.*;
import models.forms.PartialPollChoiceForm;
import models.forms.PartialPollForm;
import models.forms.PollForm;
import play.Routes;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.ws.WSClient;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.PollRepository;
import services.AdminService;
import services.PollService;
import views.html.partial_poll_view;
import views.html.poll_view;
import views.html.polls_list;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * Created by Jasbuber on 24/06/2016.
 */
public class AdminController extends Controller {

    @Inject
    WSClient ws;

    @Inject
    play.data.FormFactory formFactory;

    public Result authorize(String token) {

        if (new AdminService().isTokenValid(token)) {
            session("token", token);
            return redirect(routes.AdminController.showPollsList());
        } else {
            return ok("permission denied");
        }
    }

    private boolean isTokenValid() {
        return new AdminService().isTokenValid(session("token"));
    }

    public CompletionStage<Result> getHuffingtonPollsByTopic(String topic) {
        if (!isTokenValid()) {
            return null;
        }
        return new AdminService().getHuffingtonPollsByTopic(ws, topic);
    }

    @Transactional(readOnly = true)
    public Result showPollsList() {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        List<Poll> polls = new PollService(new PollRepository()).getAllPollsList();

        Form<PollForm> form = formFactory.form(PollForm.class);
        return ok(polls_list.render(polls, form));
    }

    @Transactional(readOnly = true)
    public Result showPoll(long id) {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        Poll poll = new PollService(new PollRepository()).getPoll(id);

        Form<PartialPollForm> form = formFactory.form(PartialPollForm.class);

        return ok(poll_view.render(poll, form));
    }

    @Transactional(readOnly = true)
    public Result showPartialPoll(long id) {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PartialPoll poll = new PollService(new PollRepository()).getPartialPoll(id);

        Form<PartialPollForm> form = formFactory.form(PartialPollForm.class);

        Form<PartialPollChoiceForm> formChoice = formFactory.form(PartialPollChoiceForm.class);

        PartialPollChoiceForm choiceData = new PartialPollChoiceForm();
        choiceData.setValue(0);

        return ok(partial_poll_view.render(form.fill(new PartialPollForm(poll)), poll.getPollerChoices(), formChoice.fill(choiceData)));
    }

    @Transactional
    public Result updatePartialPoll() {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        Form<PartialPollForm> form = formFactory.form(PartialPollForm.class);

        Form<PartialPollChoiceForm> formChoice = formFactory.form(PartialPollChoiceForm.class);

        PartialPollForm formData = form.bindFromRequest(request()).get();

        PollService service = new PollService(new PollRepository());

        PartialPoll poll = service.getPartialPoll(formData.getId());

        poll.setPollster(formData.getPollster());
        poll.setProvider(formData.getProvider());

        service.updatePartialPoll(poll);

        return ok(partial_poll_view.render(form.fill(new PartialPollForm(poll)), poll.getPollerChoices(), formChoice));
    }

    @Transactional
    public Result ajaxUpdatePartialPollChoice(Long choiceId, String name, String universalValue, Double value) {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        PartialPollChoice choice = service.getPartialPollChoice(choiceId);
        choice.setName(name);
        choice.setUniversalValue(universalValue);
        choice.setValue(value);

        service.updatePartialPollChoice(choice);

        return ok("success");
    }

    @Transactional
    public Result createPoll() {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        Form<PollForm> formPoll = formFactory.form(PollForm.class);

        PollForm formData = formPoll.bindFromRequest(request()).get();

        Poll poll = new Poll(formData.getTopic(), formData.getRemoteId(), formData.getLocation());

        service.createPoll(poll);

        return redirect(routes.AdminController.showPollsList());
    }

    @Transactional
    public Result ajaxSwitchPollActive(long id) {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        Poll poll = service.getPoll(id);

        if (poll.isActive()) {
            poll.deactivate();
        } else {
            poll.activate();
        }

        service.updatePoll(poll);

        return ok("success");
    }

    @Transactional
    public Result createPartialPoll() {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        Form<PartialPollForm> formPoll = formFactory.form(PartialPollForm.class);

        PartialPollForm formData = formPoll.bindFromRequest(request()).get();

        Poll parent = service.getPoll(formData.getPollId());

        PartialPoll partial = new PartialPoll(parent, formData.getProvider(), formData.getPollster());

        service.createPartialPoll(partial);

        return redirect(routes.AdminController.showPoll(parent.getId()));
    }

    @Transactional
    public Result ajaxSwitchPartialPollActive(long id) {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        PartialPoll poll = service.getPartialPoll(id);

        if (poll.isActive()) {
            poll.deactivate();
        } else {
            poll.activate();
        }

        service.updatePartialPoll(poll);

        return ok("success");
    }

    @Transactional
    public Result createPartialChoice() {

        if (!isTokenValid()) {
            return ok("permission denied");
        }

        PollService service = new PollService(new PollRepository());

        Form<PartialPollChoiceForm> formChoice = formFactory.form(PartialPollChoiceForm.class);

        PartialPollChoiceForm formData = formChoice.bindFromRequest(request()).get();

        PartialPoll parent = service.getPartialPoll(formData.getPartialId());

        PartialPollChoice choice = new PartialPollChoice(parent, formData.getName(), formData.getUniversalValue(),
                formData.getValue());

        service.createPartialPollChoice(choice);

        return redirect(routes.AdminController.showPartialPoll(parent.getId()));
    }

    public Result javascriptRoutes() {
        response().setContentType("text/javascript");
        return ok(
                Routes.javascriptRouter("jsRoutes",
                        routes.javascript.AdminController.ajaxSwitchPollActive(),
                        routes.javascript.AdminController.ajaxSwitchPartialPollActive(),
                        routes.javascript.AdminController.ajaxUpdatePartialPollChoice()
                )
        );
    }


}
