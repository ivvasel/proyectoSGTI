import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EstadisticasPartidas extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2,st3;
        ResultSet rs,rs2,rs3;
        String SQL,SQL2,SQL3;
        PrintWriter out;
        HttpSession sesion;
        String nick;
        int idUsuario;
        int totalJugadas;
        int totalGanadas;
        int totalPerdidas;
        
        try{
            sesion=(HttpSession) req.getSession(true);
            nick=(String) sesion.getAttribute("nick");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            SQL="SELECT COUNT(IdPartida) FROM partidas WHERE IdJugador1="+nick+"OR IdJugador2="+
            nick;
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            totalJugadas=rs.getInt("ganadas");
            
            
            
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ESTADISTICAS DE "+nick+"</h1>");
            out.println("<table>");
            
            /*out.println("<tr><td>PARTIDAS JUGADAS</td><td>"+totalJugadas+"</td></tr>");
            out.println("<tr><td>PARTIDAS GANADAS</td><td>"+totalGanadas+"</td></tr>");
            out.println("<tr><td>PARTIDAS PERDIDAS</td><td>"+totalPerdidas+"</td></tr>");*/
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            
            rs.close();
            st.close();
            con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

        
            
            
            