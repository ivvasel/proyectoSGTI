import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class InicioSesionForm extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        PrintWriter out;
        
        try {
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1> Introduce tus datos de usuario</h1>");
            out.println("<form action=\"iniciosesion\">");
            out.println("USUARIO: <input type=\"text\" name=\"nick\"><br>");
            out.println("CONTRASEÑA: <input type=\"password\" name=\"password\"><br>");
            out.println("<input type=\"submit\" value=\"INICIAR SESION\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
