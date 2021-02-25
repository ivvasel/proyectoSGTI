import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class CreaPartida extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2,st3;
        ResultSet rs,rs2;
        PrintWriter out;
        String SQL,SQL2,SQL3;
        String nick;
        String nickcontrincante;
        HttpSession misesion;
        int idUsuarioActual;
        int idAmigo;
        
        try{
            res.setContentType("text/html");
            out=res.getWriter();
            //out.println("<html>");
            misesion=(HttpSession) req.getSession();
            nick=(String)misesion.getAttribute("nick");
           // out.println(nick+"OK");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            nickcontrincante=req.getParameter("contrincante");
           // out.println(nickcontrincante);
            SQL="SELECT * FROM usuarios WHERE Nick='"+nickcontrincante+"'";
            //out.println(SQL);
            st=con.createStatement();
            //out.println("OK");
            rs=st.executeQuery(SQL);
            //out.println("OK");
            idAmigo=rs.getInt("IdUsuario");
           // out.println(idAmigo);
            //out.println(idAmigo);
            SQL3="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st3=con.createStatement();
            rs2=st3.executeQuery(SQL3);
            //out.println("OK");
            //out.println("<html>");
            
            idUsuarioActual=rs.getInt("IdUsuario");
            //idUsuarioActual=(int) misesion.getAttribute("idUsuario");
            SQL2="INSERT INTO partidas (IdJugador1,IdJugador2,Activa) VALUES ("+idUsuarioActual+","+idAmigo+",1)";
            st2=con.createStatement();
            st2.executeUpdate(SQL2);
            
            
            rs.close();
            rs2.close();
            st.close();
            st2.close();
            st3.close();
            con.close();
           // out.close();
            
            res.sendRedirect("tabla"); 
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            
            