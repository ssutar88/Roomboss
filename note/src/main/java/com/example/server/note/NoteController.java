
package com.example.server.note;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.server.exception.NoteNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note){
        Note savedNote = noteRepository.save(note);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedNote.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getAllNoteById(@PathVariable(value= "id") Long noteId){
        Optional<Note> noteData = noteRepository.findById(noteId);
        if(noteData.isPresent()){
            return new ResponseEntity<>(noteData.get(), HttpStatus.OK);
        }else{
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new NoteNotFoundException("id: "+noteId);
        }
    }

}
