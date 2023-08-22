package com.example.demo;


import org.springframework.data.jpa.repository.JpaRepository;
//4
public interface NoteRepository extends JpaRepository<Note, Long> {
}
