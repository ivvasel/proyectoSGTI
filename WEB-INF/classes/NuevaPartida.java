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
        HttpSession sesion;
        String idUsuario_yo;
        
        try{
            //sesion=(HttpSession) req.getSession();
            //idUsuario_yo=(String)sesion.getAttribute("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            //SQL="UPDATE usuarios SET buscaPartida=1 WHERE IdUsuario="+idUsuario_yo;
            //st.executeUpdate(SQL);            
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
            out.println("<h1>�Con qui�n desea iniciar una partida?</h1>");
            out.println("<nav>");
            out.println("<a href=\"nueva/aleatoria\" class=\"nav-enlace\">VER TODOS LOS USUARIOS </a>");
            out.println("<a href=\"amigos\" class=\"nav-enlace\">JUGAR CON UN AMIGO </a>");
            out.println("</nav>");
            out.println("</body>");
            out.println("</html>"); 
           
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            