import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Export extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Document document;
		PdfPTable table;
		 try {
		document=new Document();
		PdfWriter.getInstance(document,new FileOutputStream("webapps/kishore/Fileops.pdf"));
       document.open();
       table=new PdfPTable(3);
       table.addCell("Operation");
       table.addCell("Paramname");
	   table.addCell("Paramvalue");
	  
       Class.forName("org.postgresql.Driver");
	    
       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","kishore", "1234");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from Operations ;");
       while(rs.next()){
       table.addCell(rs.getString("Operation"));
       table.addCell(rs.getString("param_name"));
	    table.addCell(rs.getString("param_value"));
		
		}
		document.add(table);
		 document.close();
       }
	   catch (Exception e) {
         out.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
		 }
		 response.sendRedirect("pdf.jsp");
	  
	  
	   
		
	}
}