package com.piataikin.reminder.model.view;

import com.piataikin.reminder.model.Note;

import java.io.Serializable;
import java.util.Date;

public class NoteView implements Serializable {

    private Long id;
    private String text;
    private Boolean done;
    private Date createdAt;
    private Date updatedAt;

    public NoteView(Note note) {
        this.id = note.getId();
        this.text = note.getText();
        this.done = note.getIsDone();
        this.createdAt = note.getCreatedAt();
        this.updatedAt = note.getUpdatedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
