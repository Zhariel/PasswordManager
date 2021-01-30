import java.time.LocalDate;

public class Metadata {
    private LocalDate dateCreation;
    private int duration;
    private String comment;

    public Metadata(LocalDate dateCreation, int duration, String comment) {
        this.dateCreation = dateCreation;
        this.duration = duration;
        this.comment = comment;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}