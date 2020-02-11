package jsftutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.IOException;
import java.sql.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Todo {
	public Connection conn;
	private List<TodoItem> itemList;

	@PostConstruct
	public void init() {
		System.out.println("init");
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
			while(rs.next()) {				
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				itemList.add(new TodoItem(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TodoItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<TodoItem> itemList) {
		this.itemList = itemList;
	}
	
	public void create() {
		System.out.println("create");
		//return "index2";
	}

	public void update() {
		System.out.println("update");
	}
	
	public void delete() {
		System.out.println("delete");
	}
}
