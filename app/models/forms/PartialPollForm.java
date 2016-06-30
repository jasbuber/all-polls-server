package models.forms;

import models.PartialPoll;

import java.util.List;

/**
 * Created by Jasbuber on 28/06/2016.
 */
public class PartialPollForm {

    private Long id;

    private String provider;

    private String pollster;

    private long pollId;

    public PartialPollForm(){

    }

    public PartialPollForm(PartialPoll poll){
        this.id = poll.getId();
        this.provider = poll.getProvider();
        this.pollster = poll.getPollster();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPollster() {
        return pollster;
    }

    public void setPollster(String pollster) {
        this.pollster = pollster;
    }

    public long getPollId() {
        return pollId;
    }

    public void setPollId(long pollId) {
        this.pollId = pollId;
    }
}
