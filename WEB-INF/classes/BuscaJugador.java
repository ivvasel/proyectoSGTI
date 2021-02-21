import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class BuscaJugador extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        PrintWriter out;
        String SQL;
        String nombreContrincante;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            nombreContrincante=req.getParameter("nombreContrincante");
            SQL="SELECT * FROM usuarios WHERE Nick='"+nombreContrincante+"'";
            rs=st.executeQuery(SQL);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Listado de amigos</h1>");
            out.println("<form action=\"\">");
            out.println("<select name=\"nombreAmigo\">");
            while(rs.next()){
                out.println("<option>"+rs.getString("Nick")+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"INICIAR PARTIDA\">");
            out.println("</form>");
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

