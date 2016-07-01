package services;

import models.PartialPoll;
import models.PartialPollChoice;
import models.Poll;
import repositories.PollRepository;

import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
public class PollService {

    PollRepository repository;

    public PollService(PollRepository repository) {
        this.repository = repository;
    }

    public List<Poll> getPollsList() {
        return repository.getPollsList();
    }

    public Poll getPoll(long id) {
        return repository.getPoll(id);
    }

    public PartialPoll getPartialPoll(long id) {
        return repository.getPartialPoll(id);
    }

    public PartialPollChoice getPartialPollChoice(long id) {
        return repository.getPartialPollChoice(id);
    }

    public Poll updatePoll(Poll poll) {
        return repository.updatePoll(poll);
    }

    public PartialPoll updatePartialPoll(PartialPoll poll) {
        return repository.updatePartialPoll(poll);
    }

    public PartialPollChoice updatePartialPollChoice(PartialPollChoice choice) {
        return repository.updatePartialPollChoice(choice);
    }

    public void createPoll(Poll poll) {
        repository.createPoll(poll);
    }

    public void createPartialPoll(PartialPoll partial) {
        repository.createPartialPoll(partial);
    }

    public void createPartialPollChoice(PartialPollChoice choice) {
        repository.createPartialPollChoice(choice);
    }

    public List<Poll> getAllPollsList() {
        return repository.getAllPollsList();
    }
}
