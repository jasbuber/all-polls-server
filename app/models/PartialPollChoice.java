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
    @SequenceGenerator(name = "PARTIAL_CHOICE_SEQ_GEN", sequenceName = "PARTIAL_CHOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTIAL_CHOICE_SEQ_GEN")
    private Long id;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "UNIVERSAL_VALUE")
    private String universalValue;

    public PartialPollChoice() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUniversalValue() {
        return universalValue;
    }
}
