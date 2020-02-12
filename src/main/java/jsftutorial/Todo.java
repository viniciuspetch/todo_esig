package jsftutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Todo {
	public Connection conn;
	private List<TodoItem> itemList;
	private String newContent;
	
	private int editId;
	private String editContent;	

	public void updateTodoItemList() {
		itemList = new ArrayList<TodoItem>();

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:postgresql://localhost:5432/todo";
		Properties props = new Properties();
		props.setProperty("user", "postgres");
		props.setProperty("password", "postgres");
		try {
			conn = DriverManager.getConnection(url, props);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM items");
			while (rs.next()) {
				//System.out.println(rs.getString(1));
				//System.out.println(rs.getString(2));
				itemList.add(new TodoItem(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO items (content) VALUES (?)");
			st.setString(1, newContent);
			int rowsCreated = st.executeUpdate();
			System.out.println(rowsCreated);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateTodoItemList();
	}

	public void update() {
		System.out.println("update " + Integer.toString(editId) + " " + editContent);
		try {
			PreparedStatement st = conn.prepareStatement("UPDATE items SET content=? WHERE id=?");
			st.setString(1, editContent);
			st.setInt(2, editId);
			int rowsEdited = st.executeUpdate();
			System.out.println(rowsEdited);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editId = -1;
		editContent = "";
		updateTodoItemList();
	}

	public void delete(int id) {
		System.out.println("delete " + Integer.toString(id));
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM items WHERE id = ?");
			st.setInt(1, id);
			int rowsDeleted = st.executeUpdate();
			System.out.println(rowsDeleted);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
