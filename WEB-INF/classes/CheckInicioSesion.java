import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class CheckInicioSesion extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        PrintWriter out;
        String SQL;
        String nick;
        String pass;
        
        HttpSession misesion;
        
        
        try{
            nick=req.getParameter("nick");
            pass=req.getParameter("password");
            
            misesion=req.getSession(true);
            
            res.setContentType("text/html");
            out=res.getWriter();
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            
            
            
            SQL="SELECT * FROM usuarios WHERE Nick='"+nick+"' AND Contrase�a='"+pass+"'";
            rs=st.executeQuery(SQL);
            
            if(rs.next()){
                misesion.setAttribute("nick",nick);
                res.sendRedirect("menu");
            }else{
                out.println("<html><body>");
                out.println("Los datos no son correctos. Por favor, vuelva a intentarlo <br>");
                out.println("<a href=\"iniciosesionform\">VOLVER A INTENTAR </a>");
                out.println("</body>");
                out.println("</html>");
            }
            
            rs.close();
            st.close();
            con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

                
                
               
            