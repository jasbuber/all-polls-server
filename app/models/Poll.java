package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "POLLS")
public class Poll {

    @Id
    @SequenceGenerator(name = "POLL_SEQ_GEN", sequenceName = "POLL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLL_SEQ_GEN")
    private Long id;

    @OneToMany
    @JoinColumn(name = "POLL_ID")
    @NotNull
    List<PartialPoll> partialPolls;

    @Column(name = "TOPIC")
    String topic;

    @Column(name = "REMOTE_IDENTIFIER")
    String remoteId;

    @Column(name = "EXPIRATION_DATE")
    Date expirationDate;

    public Poll() {}

    public Long getId() {
        return id;
    }

    public List<PartialPoll> getPartialPolls() {
        return partialPolls;
    }

    public String getTopic() {
        return topic;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getRemoteId() {
        return remoteId;
    }
}
