import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletRegistro extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st, st2;
        ResultSet rs;
        String SQL, SQL2, nick, nombre, email, pwd, color;
        PrintWriter out;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st = con.createStatement();
            st2 = con.createStatement();
            
            nick = req.getParameter("Nick");
            nombre = req.getParameter("Nombre");
            email = req.getParameter("Email");
            pwd = req.getParameter("Pass");
            color = req.getParameter("Color");

            SQL="SELECT * FROM usuarios WHERE Nick=" + nick;
            rs= st.executeQuery(SQL);
            if(!rs.next()) {
                out = res.getWriter();
                res.setContentType("TEXT/HTML");
                out.println("<HTML> <BODY>");
                out.println("El nick introducido ya existe. Por favor, escriba uno diferente");
                res.sendRedirect("ServletFormRegistro");
                out.println("</BODY> </HTML>");
            }
            SQL2= "INSERT INTO usuarios (Nick, Nombre, Email, Contraseña, Color) VALUES (nick, nombre, email, pwd, color)";
            st2.executeUpdate(SQL2);
            
            st.close();
            con.close();
        }
        catch (Exception e) {System.out.println(e);}
    }
}