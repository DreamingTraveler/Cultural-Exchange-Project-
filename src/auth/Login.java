package auth;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userData;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isLoginIn(request, response);
	}

	private void isLoginIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getSession().getAttribute("username"));
		if((String)request.getSession().getAttribute("username") != null){
			response.setContentType("application/json");
			response.getWriter().print(userData);
		}
		else{
			response.getWriter().write("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taimei", "root", "taiwan0317");
			Statement stmt = conn.createStatement();
			System.out.printf("DataBase is %s%n", stmt.isClosed()?"closed":"opened");
			searchData(request, conn, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}	
	}
	private void searchData(HttpServletRequest request, Connection conn, HttpServletResponse response)throws SQLException,ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String dbUsername = null;
		String dbPassword = null;
		String dbName = null;
		Blob dbImage = null;
		byte[] bytes = null;
		
		String sql = "select * from account where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			dbUsername = rs.getString("username");
	    	dbPassword = rs.getString("password");
	    	dbName = rs.getString("name");
	    	bytes = rs.getBytes("image");
		}
//		InputStream is = dbImage.getBinaryStream();
//		int imageSize = is.available();
//		byte []b = new byte[imageSize];
//		int index = is.read(b, 0, imageSize);
//		System.out.println(index);
		
		if(username.equals(dbUsername) && password.equals(dbPassword)){
			String imageBytes = DatatypeConverter.printBase64Binary(bytes);
			User user = new User(dbUsername, dbPassword, dbName, imageBytes);
			Gson gson = new Gson();
			userData = gson.toJson(user);
			
			response.setContentType("application/json");
	        response.getWriter().print(userData);
	        request.getSession().setAttribute("username", username);
//	        System.out.print(request.getSession().getAttribute("username"));
//			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().write("");
		}
	}    

}

