package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
public class PartialPoll {

    @Id
    @SequenceGenerator(name = "PARTIAL_POLL_SEQ_GEN", sequenceName = "PARTIAL_POLL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIAL_POLL_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLLER_ID")
    @NotNull
    Poller poller;

    @OneToMany
    @JoinColumn(name = "PARTIAL_POLL_ID")
    @NotNull
    List<PartialPollChoice> pollerChoices;

    public PartialPoll() {
    }

    public Long getId() {
        return id;
    }
}
