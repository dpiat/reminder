package com.piataikin.reminder.payload;

import com.piataikin.reminder.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NoteRequest {
    @NotBlank
    @Size(min = 1, max = 200)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
