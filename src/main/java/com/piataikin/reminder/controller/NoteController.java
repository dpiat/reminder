package com.piataikin.reminder.controller;

import com.piataikin.reminder.model.Note;
import com.piataikin.reminder.model.view.NoteView;
import com.piataikin.reminder.payload.ApiResponse;
import com.piataikin.reminder.payload.NoteRequest;
import com.piataikin.reminder.repository.NoteRepository;
import com.piataikin.reminder.repository.UserRepository;
import com.piataikin.reminder.security.CurrentUser;
import com.piataikin.reminder.security.UserPrincipal;
import com.piataikin.reminder.service.NoteService;
import com.piataikin.reminder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);


    @GetMapping("/all")
    public ResponseEntity<List<NoteView>> getAllNotes(@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(noteService.findAllNotes(currentUser.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody NoteRequest noteRequest, @CurrentUser UserPrincipal currentUser) {
        Note note = new Note();
        note.setText(noteRequest.getText());
        note.setUser(userService.findById(currentUser.getId()));
        note.setIsDone(false);
        noteService.save(note);
        return ResponseEntity.ok(new ApiResponse(true, "The note have been successfully saved"));
    }

    //rewrite
    @GetMapping("/{note_id}/update")
    public ApiResponse updateNote(@PathVariable Long note_id,
                                  @CurrentUser UserPrincipal currentUser) {
        return noteService.update(note_id, currentUser.getId());
    }
    //rewrite
    /*
    @GetMapping("/{user_id}/forTheDay")
    public ResponseEntity<List<NoteView>> getAllNoteForTheDay(@PathVariable Long user_id) {
        return ResponseEntity.ok(noteService.findAllNotesForTheDay(user_id));
    }
    */
}
