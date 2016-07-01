package models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.*;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "PARTIAL_POLLS")
public class PartialPoll {

    @Id
    @SequenceGenerator(name = "PARTIAL_POLL_SEQ_GEN", sequenceName = "PARTIAL_POLL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIAL_POLL_SEQ_GEN")
    @Expose
    private Long id;

    @OneToMany
    @JoinColumn(name = "PARTIAL_POLL_ID")
    @NotNull
    @Expose
    private List<PartialPollChoice> pollerChoices = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POLL_ID")
    @NotNull
    private Poll poll;

    @Expose
    @Column(name = "PROVIDER")
    private String provider;

    @Expose
    @Column(name = "POLLSTER")
    private String pollster;

    @Column(name = "IS_ACTIVE")
    char isActive = 'N';

    public PartialPoll() {
    }

    public PartialPoll(Poll poll, String provider, String pollster) {
        this.poll = poll;
        this.provider = provider;
        this.pollster = pollster;
    }

    public Long getId() {
        return id;
    }

    public String getProvider() {
        return provider;
    }

    public String getPollster() {
        return pollster;
    }

    public List<PartialPollChoice> getPollerChoices() {
        return pollerChoices;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setPollster(String pollster) {
        this.pollster = pollster;
    }

    public void activate() {
        this.isActive = 'Y';
    }

    public void deactivate() {
        this.isActive = 'N';
    }

    public Poll getPoll() {
        return poll;
    }

    public boolean isActive() {
        return this.isActive == 'Y';
    }
}
