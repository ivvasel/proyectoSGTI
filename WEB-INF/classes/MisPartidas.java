//Este servlet muestra un menu select con las partidas que tengo pendientes Y ES MI TURNO
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MisPartidas extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        ResultSet rs,rs2,rs3,rs_aux;
        Statement st,st2,st3;
        String SQL,SQL2,SQL3,SQL_aux;
        PrintWriter out;
        HttpSession sesion;
        int idUsuario;
        int idPartida;
        //String nick_mio;
        String rival1;
        String rival2;
        try{
            sesion=(HttpSession) req.getSession(true);
            idUsuario=(int)sesion.getAttribute("idUsuario");
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println(idUsuario);
            //nick_mio=(String)sesion.getAttribute("nick");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            SQL="SELECT IdPartida FROM detallespartida WHERE Activa=1 AND IdJugador="+idUsuario;
            
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            
            out.println("<h1>PARTIDAS DISPONIBLES</h1>");
            out.println("<form action=\"TestMovimiento\">");
            out.println("<select name=\"continuar\">");
            while(rs.next()){
                idPartida=rs.getInt(1);
                SQL2="SELECT Nick FROM usuarios INNER JOIN partidas ON usuarios.IdUsuario=partidas.IdJugador1 WHERE IdPartida="+idPartida; //Obtengo nick jugador1
                st2=con.createStatement();
                rs2=st2.executeQuery(SQL2);
                SQL3="SELECT Nick FROM usuarios INNER JOIN partidas ON usuarios.IdUsuario=partidas.IdJugador2 WHERE IdPartida="+idPartida; //Obtengo nick jugador2
                st3=con.createStatement();
                rs3=st3.executeQuery(SQL3);
                rs2.next();
                rs3.next();                             
                out.println("<option value=\""+idPartida+"\">"+rs2.getString(1)+"VS "+rs3.getString(1)+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"SEGUIR PARTIDA\">");
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

                