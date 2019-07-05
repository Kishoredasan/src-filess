import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import file.Filess;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.sql.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Servlet implementation class UserDataServlet
 */
public class Modify extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String s,cont,sts=null;
boolean c;

s= request.getParameter("namer");
cont = request.getParameter("contentr");
Filess f = new Filess();
c= f.modify(s,cont);
if(c == true)
{
sts="file renamed sucessfully";
}
else
{
sts="file not exits or cannot be modified ";
}     
	if(c==true)
	{
        Connection con = null;
        Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         con = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "kishore", "1234");
         con.setAutoCommit(false);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	String d=formatter.format(date);

         stmt = con.createStatement();
         String sql = "UPDATE Fdetails SET lastmodified='"+d+"',filename='"+cont+"' WHERE filename='"+s+"' ;";
         stmt.executeUpdate(sql);
		

         
         stmt.close();
         con.commit();
         con.close();
      } catch (Exception e) {
         out.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
	  }
	  try{
	  
	  JSONObject arr=new JSONObject();
	  JSONArray array=new JSONArray();
	   Class.forName("org.postgresql.Driver");
	   arr.put("status",sts);
	    
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
	  store("Old-file-name",s);
	 store("New-file-name",cont);
	 store("status",sts);
	    /*request.setAttribute("status",sts);
	    RequestDispatcher rd = request.getRequestDispatcher("HI.jsp");
	    rd.forward(request, response);*/
		
	}
	void store(String s,String b)
	{
	 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

SessionFactory factory = meta.getSessionFactoryBuilder().build();
Session session = factory.openSession();
Transaction t = session.beginTransaction(); 
          
    Ops e1=new Ops();  
    e1.setOperation("Modify");  
    e1.setParam_name(s);  
    e1.setParam_value(b);  
	
	session.save(e1);
	t.commit();	
   
	factory.close();
    session.close();  
      }
	void createPdf()
	  {Document document;
		PdfPTable table;
		 try {
		document=new Document();
		PdfWriter.getInstance(document,new FileOutputStream("webapps/kishore/Filedets.pdf"));
       document.open();
       table=new PdfPTable(3);
       table.addCell("Filname");
       table.addCell("size(in bytes)");
	   table.addCell("Lastmodified");
	  
       Class.forName("org.postgresql.Driver");
	    
       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","kishore", "1234");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from Fdetails ;");
       while(rs.next()){
       table.addCell(rs.getString("filename"));
       table.addCell(rs.getString("size"));
	    table.addCell(rs.getString("lastmodified"));
		
		}
		document.add(table);
		 document.close();
       }
	   catch (Exception e) {
         //out.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
		 }
	  }
}