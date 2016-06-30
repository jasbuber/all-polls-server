package models.forms;

/**
 * Created by Jasbuber on 28/06/2016.
 */
public class PartialPollChoiceForm {

    private Long id;

    private String name;

    private String universalValue;

    private double value;

    private long partialId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversalValue() {
        return universalValue;
    }

    public void setUniversalValue(String universalValue) {
        this.universalValue = universalValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getPartialId() {
        return partialId;
    }

    public void setPartialId(long partialId) {
        this.partialId = partialId;
    }
}
