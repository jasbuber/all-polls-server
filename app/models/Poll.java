package models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterJoinTable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jasbuber on 09/06/2016.
 */
@Entity
@Table(name = "POLLS")
@FilterDef(name="only_active")
public class Poll {

    @Id
    @SequenceGenerator(name = "POLL_SEQ_GEN", sequenceName = "POLL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLL_SEQ_GEN")
    @Expose
    private Long id;

    @OneToMany
    @JoinColumn(name = "POLL_ID")
    @NotNull
    @Expose
    @Filter(name="only_active", condition="IS_ACTIVE='Y'")
    List<PartialPoll> partialPolls = new ArrayList<>();

    @Expose
    @Column(name = "TOPIC")
    String topic;

    @Expose
    @Column(name = "REMOTE_IDENTIFIER")
    String remoteId;

    @Column(name = "EXPIRATION_DATE")
    Date expirationDate;

    @Expose
    @Column(name = "LOCATION")
    String location;

    @Expose
    @Column(name = "CREATED_DATE")
    Date createdDate = new Date();

    @Column(name = "IS_ACTIVE")
    char isActive = 'N';

    public Poll() {
    }

    public Poll(String topic, String remoteId, String location) {
        this.topic = topic;
        this.remoteId = remoteId;
        this.location = location;
    }

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

    public String getLocation() {
        return location;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void activate() {
        this.isActive = 'Y';
    }

    public void deactivate() {
        this.isActive = 'N';
    }

    public boolean isActive() {
        return isActive == 'Y';
    }
}
