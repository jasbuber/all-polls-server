package services;

import models.Poll;
import play.db.jpa.JPA;
import repositories.PollRepository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
public class PollService {

    public List<Poll> getPollsList() {
        return new PollRepository().getPollsList();
    }

    public Poll getPoll(long id) {
        return new PollRepository().getPoll(id);
    }
}
