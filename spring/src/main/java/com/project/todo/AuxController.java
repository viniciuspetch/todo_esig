package com.project.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@CrossOrigin
@RestController
public class AuxController {
	@Autowired
	private TodoItemRepository todoItemRepository;

	@PostMapping("/items")
	public TodoItem createSingle(@RequestBody TodoItem todoItem) {
		System.out.println(todoItem.getId());
		System.out.println(todoItem.getContent());
		System.out.println(todoItem.getChecked());
		return todoItemRepository.save(todoItem);
	}

	@GetMapping("/items")
	public List<TodoItem> readAll() {
		Iterable<TodoItem> itemListIterable = todoItemRepository.findAll();
		List<TodoItem> itemList = new ArrayList<>();
		itemListIterable.forEach(itemList::add);
		Collections.sort(itemList, new Comparator<TodoItem>() {
			@Override
			public int compare(TodoItem item1, TodoItem item2) {
				return Integer.compare(item2.getId(), item1.getId());
			}
		});

		return itemList;
	}

	@PutMapping("/items/{id}")
	public TodoItem editSingle(@RequestBody TodoItem todoItem, @PathVariable int id) {
		todoItem.setId(id);
		System.out.println(todoItem.getId());
		System.out.println(todoItem.getContent());
		System.out.println(todoItem.getChecked());
		return todoItemRepository.save(todoItem);
	}
	
	@DeleteMapping("/items/{id}")
	public void deleteSingle(@PathVariable int id) {
		todoItemRepository.deleteById(id);
	}
}