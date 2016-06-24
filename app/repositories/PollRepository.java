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
                "FROM Poll p WHERE p.expirationDate > current_date() OR p.expirationDate IS NULL " +
                "ORDER BY p.createdDate DESC");

        List<Poll> polls = getAllPollsQuery.getResultList();

        return polls;

    }

    public Poll getPoll(long id) {
        Query getPollQuery = JPA.em().createQuery("SELECT p " +
                "FROM Poll p WHERE p.id = :id ");

        getPollQuery.setParameter("id", id);

        Poll poll = (Poll) getPollQuery.getSingleResult();

        return poll;

    }
}
