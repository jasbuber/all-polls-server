package models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "POLL_CHOICES")
public class PollChoice {

    @Id
    @SequenceGenerator(name = "POLL_CHOICE_SEQ_GEN", sequenceName = "POLL_CHOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLL_CHOICE_SEQ_GEN")
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    public PollChoice() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
