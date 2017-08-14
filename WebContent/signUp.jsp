<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="java.io.*,java.util.*, java.sql.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Sign Up</title>
</head>
<body>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");
%>
<p>Username: <%= username%></p>
<p>Password: <%= password%></p>
<button id="backBtn">Click to go back</button>

<script>
  $('#backBtn').click(function(){
	  window.location.href = 'index.jsp';
  })
</script>

<%
try {
    Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taimei", "root", "taiwan0317");
	Statement stmt = conn.createStatement();
	System.out.printf("DataBase is %s%n", stmt.isClosed()?"closed":"opened");
	insertData(request, conn);
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	System.out.println(e);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	System.out.println(e);
}	
%>

<%!
private static void insertData(HttpServletRequest request, Connection conn)throws SQLException,ServletException, IOException{
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	InputStream inputStream = null;
	Part filePart = request.getPart("photo");
	
	if(filePart != null){
		inputStream = filePart.getInputStream();
	}
	
	String sql = "insert into account values(?,?,?,?)";
	PreparedStatement ps = conn.prepareStatement(sql); 
	ps.setString(1, username);
	ps.setString(2, password);
	ps.setString(3, name);
	
	if(inputStream != null){
		ps.setBlob(4, inputStream);
	}
	
	ps.executeUpdate();
	System.out.println("Add data Success");
}    
%>
</body>
</html>