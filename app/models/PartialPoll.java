package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "PARTIAL_POLLS")
public class PartialPoll {

    @Id
    @SequenceGenerator(name = "PARTIAL_POLL_SEQ_GEN", sequenceName = "PARTIAL_POLL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIAL_POLL_SEQ_GEN")
    private Long id;

    @OneToMany
    @JoinColumn(name = "PARTIAL_POLL_ID")
    @NotNull
    private List<PartialPollChoice> pollerChoices;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "POLLSTER")
    private String pollster;

    public PartialPoll() {
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
}
