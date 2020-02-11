package jsftutorial;

public class TodoItem {
	private int id;
	private String content;

	
	public TodoItem(int id, String content) {
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
}