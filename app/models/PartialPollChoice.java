package models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

/**
 * Created by Jasbuber on 09/06/2016.
 */

@Entity
@Table(name = "PARTIAL_POLL_CHOICES")
public class PartialPollChoice {

    @Id
    @SequenceGenerator(name = "POLLER_CHOICE_SEQ_GEN", sequenceName = "POLLER_CHOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLLER_CHOICE_SEQ_GEN")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLL_CHOICE_ID")
    @NotNull
    PollChoice choice;

    @Column(name = "NAME")
    @NotNull
    private String name;

    public PartialPollChoice() {
    }

    public Long getId() {
        return id;
    }

    public PollChoice getChoice() {
        return choice;
    }

    public String getName() {
        return name;
    }
}
