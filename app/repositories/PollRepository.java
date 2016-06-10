package repositories;

import models.Poll;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
public class PollRepository {

    public List<Poll> getPollsList() {
        Query getAllPollsQuery = JPA.em().createQuery("SELECT p " +
                "FROM Poll p ");

        List<Poll> polls = getAllPollsQuery.getResultList();

        return polls;

    }
}
