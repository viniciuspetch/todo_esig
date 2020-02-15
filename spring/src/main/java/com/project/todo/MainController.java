package com.project.todo;

import org.springframework.ui.Model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private TodoItemRepository todoItemRepository;

	/*
	 * @GetMapping(path="/lmao") // Map ONLY POST Requests public @ResponseBody
	 * String addNewUser () { // @ResponseBody means the returned String is the
	 * response, not a view name // @RequestParam means it is a parameter from the
	 * GET or POST request
	 * 
	 * TodoItem item = new TodoItem(); item.setContent("content");
	 * todoItemRepository.save(item); return "Saved"; }
	 * 
	 * @GetMapping(path="/all") public @ResponseBody Iterable<TodoItem>
	 * getAllUsers() { // This returns a JSON or XML with the users for (TodoItem i
	 * : todoItemRepository.findAll()) { System.out.println(i.getContent()); }
	 * return todoItemRepository.findAll(); }
	 * 
	 * @GetMapping(path="/create") public @ResponseBody Iterable<TodoItem> create()
	 * { // This returns a JSON or XML with the users for (TodoItem i :
	 * todoItemRepository.findAll()) { System.out.println(i.getContent()); } return
	 * todoItemRepository.findAll(); }
	 * 
	 * @GetMapping(path="/read") public @ResponseBody Iterable<TodoItem> read() { //
	 * This returns a JSON or XML with the users for (TodoItem i :
	 * todoItemRepository.findAll()) { System.out.println(i.getContent()); } return
	 * todoItemRepository.findAll(); }
	 * 
	 * @GetMapping(path="/update") public @ResponseBody Iterable<TodoItem> update()
	 * { // This returns a JSON or XML with the users for (TodoItem i :
	 * todoItemRepository.findAll()) { System.out.println(i.getContent()); } return
	 * todoItemRepository.findAll(); }
	 * 
	 * @GetMapping(path="/delete") public String delete() { return "hello"; }
	 * 
	 * @GetMapping(path="/hello") public String hello(Model model) {
	 * model.addAttribute("name", "partner"); return "hello"; }
	 */
	@RequestMapping(path = "/hello")
	public String hello(Model model) {
		Iterable<TodoItem> itemList = todoItemRepository.findAll();
		
		model.addAttribute("name", "partner");
		model.addAttribute("todoItem", new TodoItem());
		model.addAttribute("itemList", itemList);
		return "hello";
	}

	@GetMapping(path = "/createTodoItem")
	public String createTodoItem(@ModelAttribute TodoItem todoItem, Model model) {
		todoItemRepository.save(todoItem);
		return hello(model);
	}
}