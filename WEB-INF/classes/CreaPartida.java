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
        int idUsuarioActual=0;
        int idAmigo=0;
        
        try{
            res.setContentType("text/html");
            out=res.getWriter();
                out.println("<HTML> <BODY>");
            misesion=(HttpSession) req.getSession();
            nick=(String)misesion.getAttribute("nick");

                out.println(nick+"<br>");

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            nickcontrincante=req.getParameter("contrincante");

                out.println(nickcontrincante+"<br>");

            SQL="SELECT  * FROM usuarios WHERE Nick='"+nickcontrincante+"'";
            //out.println(SQL);
            st=con.createStatement();
            //out.println("OK");
            rs=st.executeQuery(SQL);

            rs.next();
            idAmigo=rs.getInt(1);
            out.println("idAmigo ="+idAmigo+"<br>");
            
            //out.println(idAmigo);
            SQL3="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st2=con.createStatement();
            rs2=st2.executeQuery(SQL3); 
                //out.println(rs2);     
            rs2.next();     
            idUsuarioActual=rs2.getInt(1);
                out.println("idPropio ="+idUsuarioActual+"<br>");
                out.println("OK<br>");
            //idUsuarioActual=(int) misesion.getAttribute("idUsuario");
            SQL2="INSERT INTO partidas (IdJugador1,IdJugador2,Activa) VALUES ("+idUsuarioActual+","+idAmigo+",1)";
            st3=con.createStatement();
            st3.executeUpdate(SQL2);
            
            out.println("</BODY> </HTML>");
            rs.close();
            rs2.close();
            st.close();
            st3.close();
            st2.close();
            con.close();
           // out.close();
            
            res.sendRedirect("../tabla"); 
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            
            