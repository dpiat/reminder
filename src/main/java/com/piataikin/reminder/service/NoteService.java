package com.piataikin.reminder.service;

import com.piataikin.reminder.exception.AppException;
import com.piataikin.reminder.model.Note;
import com.piataikin.reminder.model.view.NoteView;
import com.piataikin.reminder.payload.ApiResponse;
import com.piataikin.reminder.repository.NoteRepository;
import com.piataikin.reminder.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public void save(Note note) {
        noteRepository.save(note);
    }


    public List<NoteView> findAllNotes(Long user_id) {
        return map(noteRepository.findAllByUserId(user_id));
    }

    private List<NoteView> map(List<Note> notes) {
        return notes.stream()
                .map(NoteView::new)
                .collect(toList());
    }

    public ApiResponse update(Long note_id, Long user_id) {
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new AppException("Note has note found"));
        if (note.getUser().getId().equals(user_id)) {
            boolean notIsDone = !note.getIsDone();
            noteRepository.updateIsDone(note_id, notIsDone);
            return new ApiResponse(true, "Note updated successfully");
        } else {
            return new ApiResponse(false, "You are not own");
        }
    }
}
