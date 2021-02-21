import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class InicioSes extends HttpServlet {
    public void doPost (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        ResultSet rs;
        Statement st, st2;
        String SQL, SQL2, nick, pwd;
        PrintWriter out;
        
       try {
           nick = req.getParameter("nick");
           pwd = req.getParameter("pwd");
           
           Class.forName("com.mysql.jdbc.Driver");
           con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
           st = con.createStatement();
           st2 = con.createStatement();
           
           SQL = "SELECT * FROM usuarios WHERE Nick=" +nick+ "AND Contraseña=" +pwd;
           rs = st.executeQuery(SQL);
           
           if(rs.next())
               res.sendRedirect("PagInicio");
           else {
                out = res.getWriter();
                res.setContentType("text/HTML");
                out.println("<HTML> <BODY> La combinación de usuario y contraseña no es correcta. Por favor, vuelva a intentarlo <BR>");
                out.println("<INPUT TYPE=BUTTON VALUE=Volver a intentar ACTION=InicioSesForm>");
                out.println("</BODY> </HTML>");
                out.close();}        
                
           rs.close();
           st.close();
           con.close();
        }
        catch (Exception e) {System.out.println(e);}
    }
}