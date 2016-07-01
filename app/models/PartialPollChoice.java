package models;

import com.google.gson.annotations.Expose;

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
    @Expose
    private Long id;

    @Expose
    @Column(name = "NAME")
    @NotNull
    private String name;

    @Expose
    @Column(name = "UNIVERSAL_VALUE")
    private String universalValue;

    @Expose
    @Column(name = "VALUE")
    private double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTIAL_POLL_ID")
    @NotNull
    private PartialPoll partial;

    public PartialPollChoice() {
    }

    public PartialPollChoice(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public PartialPollChoice(PartialPoll partial, String name, String universalValue, double value) {
        this.partial = partial;
        this.name = name;
        this.universalValue = universalValue;
        this.value = value;
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

    @Override
    public String toString() {
        return name + " - " + value;
    }

    public double getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUniversalValue(String universalValue) {
        this.universalValue = universalValue;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
