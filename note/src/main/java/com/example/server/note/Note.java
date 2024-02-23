package com.example.server.note;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min=1, message="Note content can't be empty")
    private String content;
    private String author;

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
