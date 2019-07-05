import java.sql.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Tabledata extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try{
	  
	  JSONObject arr=new JSONObject();
	  JSONArray array=new JSONArray();
	   Class.forName("org.postgresql.Driver");
       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","kishore", "1234");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from Fdetails ;");
       while(rs.next()){
	   JSONObject jobj = new JSONObject();
       jobj.put("filename",rs.getString("filename"));
       jobj.put("size",rs.getString("size"));
	    jobj.put("lastmodified",rs.getString("lastmodified"));
		array.add(jobj);
		}
		arr.put("row",array);
	  //response.setContentType("application/json");
    out.print(arr);
    //response.getWriter().write(arr);
}
catch(Exception e)
{ 
out.print("error"+e);
}	
}}