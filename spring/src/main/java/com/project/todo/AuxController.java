package com.project.todo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuxController {
	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@RequestMapping("/api")
	public TodoItem readSingle() {
		TodoItem item = new TodoItem();
		item.setId(7);
		item.setContent("test");
		return item;
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
}