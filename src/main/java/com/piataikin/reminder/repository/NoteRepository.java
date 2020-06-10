package com.piataikin.reminder.repository;

import com.piataikin.reminder.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoteRepository  extends JpaRepository<Note, Long> {
    List<Note> findAllByUserId(Long user_id);

    @Modifying
    @Transactional
    @Query("update Note t set t.isDone = :value where t.id = :id")
    int updateIsDone(@Param("id") Long id, @Param("value") boolean value);
}
