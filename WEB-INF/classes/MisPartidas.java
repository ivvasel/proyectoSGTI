//Este servlet muestra un menu select con las partidas que tengo pendientes Y ES MI TURNO
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MisPartidas extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        ResultSet rs,rs2;
        Statement st,st2;
        String SQL,SQL2;
        PrintWriter out;
        HttpSession sesion;
        int idUsuario;
        int idPartida;
        try{
            sesion=(HttpSession) req.getSession(true);
            idUsuario=(int)sesion.getAttribute("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            SQL="SELECT IdPartida FROM partidas WHERE IdJugador="+idUsuario+"AND Turno=1";
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>PARTIDAS DISPONIBLES</h1>");
            out.println("<form action=\"\">");
            out.println("<select name=\"partida_continua\">");
            while(rs.next()){
                idPartida=rs.getInt(1);
                out.println("<option>Partida con orden número "+idPartida+"</option>");
            }
            out.println("</select>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            rs.close();
            st.close();
            con.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

                