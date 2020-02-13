package jsftutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

@ManagedBean
@SessionScoped
public class Todo {
	public Connection conn;
	private List<TodoItem> itemList;
	private String newContent;
	
	private int editId;
	private String editContent;

	public void testHibernate() {
		System.out.println("test lmao");
		/*
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TodoItem newItem = new TodoItem();
		newItem.setContent("hibernate");
		entityManager.getTransaction().begin();
		entityManager.persist(newItem);
		entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
	    */
		/*
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<TodoItem> newList = entityManager.createQuery("select e from TodoItem e").getResultList();
		
		for (TodoItem item : newList) {
	        System.out.println(item.getContent());
	    }
		
	    entityManager.close();
	    entityManagerFactory.close();
	    */
	}

	public void updateTodoItemList() {		
		itemList = new ArrayList<TodoItem>();
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<TodoItem> newList = entityManager.createQuery("select e from TodoItem e").getResultList();
		
		for (TodoItem item : newList) {
			itemList.add(item);
	    }
		
	    entityManager.close();
	    entityManagerFactory.close();
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
		editId = -1;
		updateTodoItemList();
	}

	public List<TodoItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TodoItem> itemList) {
		this.itemList = itemList;
	}

	public String refresh() {
		updateTodoItemList();
		return "index";
	}

	public void create() {
		System.out.println("create " + newContent);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TodoItem newItem = new TodoItem();
		newItem.setContent(newContent);
		entityManager.getTransaction().begin();
		entityManager.persist(newItem);
		entityManager.getTransaction().commit();
	    entityManager.close();
	    entityManagerFactory.close();
		
	    newContent = null;
	    
		updateTodoItemList();
	}

	public void update() {
		System.out.println("update " + Integer.toString(editId) + " " + editContent);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();		
		
		TodoItem updateItem = new TodoItem();
		updateItem.setId(editId);
		updateItem.setContent(editContent);
		
		entityManager.getTransaction().begin();
		entityManager.merge(updateItem);
		entityManager.getTransaction().commit();
		
	    entityManager.close();
	    entityManagerFactory.close();
		
	    editId = -1;
		editContent = null;
		
		updateTodoItemList();
	}

	public void delete(int id) {
		System.out.println("delete " + Integer.toString(id));

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("todo");
		EntityManager entityManager = entityManagerFactory.createEntityManager();		
		
		TodoItem foundItem = entityManager.find(TodoItem.class, id);
		System.out.println(foundItem.getContent());
		
		entityManager.getTransaction().begin();
		entityManager.remove(foundItem);
		entityManager.getTransaction().commit();
		
	    entityManager.close();
	    entityManagerFactory.close();
		
		updateTodoItemList();
	}
	
	public String toUpdate(int id, String content) {
		editId = id;
		editContent = content;
		
		return "index";
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public String getEditContent() {
		return editContent;
	}

	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}
}
