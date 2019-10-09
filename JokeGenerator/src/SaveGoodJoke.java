
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveGoodJoke")
public class SaveGoodJoke extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SaveGoodJoke() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
	   ServletContext application=getServletContext(); 
	   String joke = (String) application.getAttribute("joke");
	   System.out.println(application.getAttribute("joke"));

      Connection connection = null;
      String insertSql = " INSERT INTO MyJokes values (\"" + joke + "\")";
     
      System.out.println(insertSql);
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      response.getWriter().append("<form method=\"post\" action=\"JokeGenerator\">");
		response.getWriter().append("<input type=\"submit\" value=\"Get a new joke!\" /></form>");
		
		response.getWriter().append("<form method=\"post\" action=\"ViewJokes\">");
		response.getWriter().append("<input type=\"submit\" value=\"View Saved Jokes\" /></form>");
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
