import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PrePartidaIniciada extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st_aux;
        ResultSet rs,rs_aux;
        String SQL,SQL_aux;
        PrintWriter out;
        HttpSession sesion;
        String nick;
        int idUsuario;
        int idPartida;
        
        try{
            sesion=(HttpSession)req.getSession();
            nick=(String)sesion.getAttribute("nick");
            //idUsuario=(int)sesion.getAttribute("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            SQL_aux="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st=con.createStatement();
            st_aux=con.createStatement();
            rs_aux=st_aux.executeQuery(SQL_aux); 
            //out.println(rs2);     
            rs_aux.next();     
            idUsuario=rs_aux.getInt(1);
            
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            SQL="SELECT * FROM partidas WHERE IdJugador1="+idUsuario+ " ORDER BY IDPARTIDA DESC";
            out.println(idUsuario);   
            rs=st.executeQuery(SQL);
            rs.next();
            idPartida=rs.getInt(1);
            
            rs.close();
            st.close();
                 
            out.println(idPartida);
            
            out.println("<h1> COMIENZA LA PARTIDA...</h1>");
            out.println("<form action=\"TestMovimiento\">");
            out.println("<input type=\"submit\" value=\""+idPartida+"\" name=\"continuar\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            rs_aux.close();
            //rs.close();
            st_aux.close();
            //st.close();
            con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            