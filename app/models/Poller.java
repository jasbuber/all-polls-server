package models;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "POLLERS")
public class Poller {

    @Id
    @SequenceGenerator(name = "POLLER_SEQ_GEN", sequenceName = "POLLER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLLER_SEQ_GEN")
    private Long id;

    @Column(name = "NAME")
    private String Name;

    public Poller() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}
