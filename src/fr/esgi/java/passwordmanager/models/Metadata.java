package fr.esgi.java.passwordmanager.models;

import java.util.Date;

public class Metadata {
    Date dateCreation;
    int duration;
    String comment;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
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

    @Override
    public String toString() {
        return "Metadata{" +
                "dateCreation=" + dateCreation +
                ", duration=" + duration +
                ", comment='" + comment + '\'' +
                '}';
    }
}
