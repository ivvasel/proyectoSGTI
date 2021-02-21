import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PartidaAmigos extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        PrintWriter out;
        String SQL;
        
        try{
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Busca a tus amigos</h1>");
            out.println("<form action=\"busca\">");
            out.println("<input type=\"text\" name=\"nombreContrincante\">");
            out.println("<input type=\"submit\" value=\"BUSCAR JUGADOR\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
