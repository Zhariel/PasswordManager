package fr.esgi.java.passwordmanager.models;

import java.time.LocalDate;

/**Class Metadata
 * Class contains date of creation of one site, commentary and duration before a reminder for change password.
 * **/

public class Metadata {

    private LocalDate dateCreation;
    private String duration;
    private String comment;

    public Metadata(LocalDate dateCreation, String duration, String comment) {

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "\nMetadata{" +
                "\n dateCreation=" + dateCreation +
                ",\n duration=" + duration +
                ",\n comment='" + comment + '\'' +
                "\n}";
    }
}