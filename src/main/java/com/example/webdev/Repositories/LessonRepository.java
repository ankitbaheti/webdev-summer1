package com.example.webdev.Repositories;

import com.example.webdev.Models.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {
}
