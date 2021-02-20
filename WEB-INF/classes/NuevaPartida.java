import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class NuevaPartida extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2;
        ResultSet rs;
        String SQL,SQL2;
        PrintWriter out;
        String nombreContrincante;
        
        
        try{
            nombreContrincante="";
            
           // SQL="SELECT * FROM usuarios WHERE buscaPartida=1";
            //rs=st.executeQuery(SQL);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"menu.css\">");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>¿Con quién desea iniciar una partida?</h1>");
            out.println("<nav>");
            out.println("<a href=\"nueva/aleatoria\" class=\"nav-enlace\">CONTRINCANTES DISPONIBLES </a>");
            out.println("<a href=\"amigos\" class=\"nav-enlace\">JUGAR CON UN AMIGO </a>");
            out.println("</nav>");
            out.println("</body>");
            out.println("</html>"); 
           
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            