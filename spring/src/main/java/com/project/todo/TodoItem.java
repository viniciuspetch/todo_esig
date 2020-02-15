package com.project.todo;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "items")
public class TodoItem {
	@Id
	@GeneratedValue
	private int id;
	private String content;
	private Boolean checked;

	public void set(int id, String content) {
		this.setId(id);
		this.setContent(content);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}