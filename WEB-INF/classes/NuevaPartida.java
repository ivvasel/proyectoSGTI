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
        
        try{
            nombreContrincante="";
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            st2=con.createStatement();
            SQL="SELECT * FROM usuarios WHERE buscaPartida=1";
            rs=st.executeQuery(SQL);
            if(!rs.next()){
               // SQL2="INSERT INTO usuarios (buscaPartida) VALUES (1) WHERE usuarios.Nick='DA'";
                SQL2="UPDATE usuarios SET buscaPartida=1 WHERE Nick='DAVID'";
                st2.executeUpdate(SQL2);
            }else{
                nombreContrincante=rs.getString(2);
            }
            SQL2="UPDATE usuarios SET buscaPartida=1 WHERE Nick='DAVID'";
            st2.executeUpdate(SQL2);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"menu.css\">");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>¿Desea iniciar partida contra "+nombreContrincante+"?</h1>");
            out.println("<nav>");
            out.println("<a href=\"\" class=\"nav-enlace\">SÍ</a>");
            out.println("<a href=\"\" class=\"nav-enlace\">NO</a>");
            out.println("</nav>");
            out.println("</body>");
            out.println("</html>");
            rs.close();
            st.close();
            st2.close();
            con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            