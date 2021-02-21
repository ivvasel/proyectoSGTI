import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class CreaPartida extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2;
        ResultSet rs;
        PrintWriter out;
        String SQL,SQL2;
        String nickcontrincante;
        HttpSession misesion;
        int idUsuarioActual;
        int idAmigo;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            nickcontrincante=req.getParameter("nombreAmigo");
            SQL="SELECT IdUsuario FROM usuarios WHERE Nick='"+nickcontrincante+"'";
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            idAmigo=rs.getInt("IdUsuario");
            misesion=req.getSession(true);
            idUsuarioActual=(int) misesion.getAttribute("idUsuario");
            SQL2="INSERT INTO partidas (IdJugador1,IdJugador2,Activa) VALUES ("+idUsuarioActual+","+idAmigo+",1)";
            st2=con.createStatement();
            st2.executeUpdate(SQL2);
            res.sendRedirect("servlets/tabla"); 
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            
            