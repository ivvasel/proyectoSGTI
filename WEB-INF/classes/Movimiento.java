import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Movimiento extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL;
        PrintWriter out;
        HttpSession sesion;
        
        String boton1;
        String boton2;
        String boton3;
        String boton4;
        String boton5;
        String boton6;
        String nick;
        
        
        try{
            
            sesion=(HttpSession) req.getSession();
            nick=(String) sesion.getAttribute("nick"); //Cojo el nick del usuario actual 
            boton1=req.getParameter("BO1");
            boton2=req.getParameter("BO2");
            boton3=req.getParameter("BO3");
            boton4=req.getParameter("BO4");
            boton5=req.getParameter("BO5");
            boton6=req.getParameter("BO6");
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            
        } catch (Exception e){
            System.err.println(e);
        }
    }
}

        