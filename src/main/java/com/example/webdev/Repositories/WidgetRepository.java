package com.example.webdev.Repositories;

import com.example.webdev.Models.Widget;
import org.springframework.data.repository.CrudRepository;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
}
