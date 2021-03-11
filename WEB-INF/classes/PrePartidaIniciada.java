import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PrePartidaIniciada extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st_aux,st_actualiza,st_actualiza2;
        ResultSet rs,rs_aux;
        String SQL,SQL_aux,SQL_actualiza,SQL_actualiza2;
        PrintWriter out;
        HttpSession sesion;
        String nick;
        int idUsuario;
        int idPartida;
        int idRival;
        
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
            //out.println("<html>");
           // out.println("<body>");
            SQL="SELECT * FROM partidas WHERE IdJugador1="+idUsuario+ " ORDER BY IDPARTIDA DESC";
            rs=st.executeQuery(SQL);
            rs.next();
            idPartida=rs.getInt(1);  
            idRival=rs.getInt(3);//Cojo id Jugador2
            rs.close();
            st.close();
            
            SQL_actualiza="INSERT INTO detallespartida (IdPartida,IdJugador,Turno,Activa) VALUES ("+idPartida+","+idUsuario+",1,1)";
            st_actualiza=con.createStatement();
            st_actualiza.executeUpdate(SQL_actualiza);            
            
            SQL_actualiza2="INSERT INTO detallespartida (IdPartida,IdJugador,Turno,Activa) VALUES ("+idPartida+","+idRival+",0,1)";
            st_actualiza2=con.createStatement();
            st_actualiza2.executeUpdate(SQL_actualiza2);
            
            String idPartida_str=Integer.toString(idPartida);
            req.setAttribute("idPartida",idPartida_str);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/TestMovimiento");
            dispatcher.forward(req,res);
            //out.println("<h1> COMIENZA LA PARTIDA...</h1>");
            //out.println("<form action=\"TestMovimiento\">");
            //out.println("<h2>Pulsa el botón para comenzar: </h2>");
            //out.println("<input type=\"submit\" value=\""+idPartida+"\" name=\"continuar\">");
           // out.println("</form>");
            //out.println("</body>");
            //out.println("</html>");
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

            