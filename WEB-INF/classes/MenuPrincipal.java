import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MenuPrincipal extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL;
        PrintWriter out;
        HttpSession misesion;
        String nick;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            misesion=(HttpSession) req.getSession();
            nick=(String) misesion.getAttribute("nick");
            out=res.getWriter();
            res.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"menu.css\">");
            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>�BIENVENIDO "+nick+"!</h1>");
            out.println("<nav >");
            out.println("<a href=\"nueva\" class=\"nav-enlace\"> INICIAR PARTIDA </a>");
            out.println("<a href=\"#\" class=\"nav-enlace\"> MIS PARTIDAS </a>");
            out.println("<a href=\"#\" class=\"nav-enlace\"> ESTADISTICAS </a>");
            out.println("<a href=\"index.html\" class=\"nav-enlace\"> SALIR </a>");
            out.println("</nav>");
            out.println("<img src=\"images/tablero.jfif\">");
            out.println("</body></html>");
            //con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }//Fin doGet
}//Fin clase