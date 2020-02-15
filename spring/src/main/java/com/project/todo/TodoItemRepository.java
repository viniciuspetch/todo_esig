package com.project.todo;

import org.springframework.data.repository.CrudRepository;

import com.project.todo.TodoItem;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {

}