package repositories;

import models.PartialPoll;
import models.PartialPollChoice;
import models.Poll;
import org.hibernate.Session;
import play.db.jpa.JPA;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
public class PollRepository {

    public List<Poll> getPollsList() {

        Session session = JPA.em().unwrap(org.hibernate.Session.class);
        session.enableFilter("only_active");

        Query getAllPollsQuery = JPA.em().createQuery("SELECT p " +
                "FROM Poll p " +
                "WHERE (p.expirationDate > current_date() OR p.expirationDate IS NULL) AND p.isActive = 'Y' " +
                "ORDER BY p.createdDate DESC");


        return (List<Poll>) getAllPollsQuery.getResultList();
    }

    public List<Poll> getAllPollsList() {
        Query getAllPollsQuery = JPA.em().createQuery("SELECT p " +
                "FROM Poll p " +
                "ORDER BY p.createdDate DESC");

        return (List<Poll>) getAllPollsQuery.getResultList();
    }

    public Poll getPoll(long id) {
        return JPA.em().find(Poll.class, id);
    }

    public PartialPoll getPartialPoll(long id) {
        return JPA.em().find(PartialPoll.class, id);
    }

    public PartialPollChoice getPartialPollChoice(long id) {
        return JPA.em().find(PartialPollChoice.class, id);
    }

    public PartialPoll updatePartialPoll(PartialPoll poll) {
        return JPA.em().merge(poll);
    }

    public Poll updatePoll(Poll poll) {
        return JPA.em().merge(poll);
    }

    public PartialPollChoice updatePartialPollChoice(PartialPollChoice choice) {
        return JPA.em().merge(choice);
    }

    public void createPoll(Poll poll) {
        JPA.em().persist(poll);
    }

    public void createPartialPoll(PartialPoll partial) {
        JPA.em().persist(partial);
    }

    public void createPartialPollChoice(PartialPollChoice choice) {
        JPA.em().persist(choice);
    }
}
