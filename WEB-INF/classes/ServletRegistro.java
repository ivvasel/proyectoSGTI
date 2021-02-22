import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ServletRegistro extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st, st2;
        ResultSet rs;
        String SQL, SQL2, nick, nombre, email, pwd, color;
        PrintWriter out;
        try {
            out=res.getWriter();
            res.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            //out.println("<meta charset='utf-8' />");
            out.println("<title>registro</title>");
            out.println("<script src='/proyectoSGTI/web/recursos/javascripts/registro.js'></script>");
            out.println("<link rel='stylesheet' href='web/recursos/estilos/prueba.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/menu.css' type='text/css' media='all'>");
            
            out.println("</head>");        
            
            
            out.println("<body>");
            out.println("<h1> Registro de nuevo usuario </h1>");
            out.println("<form action='registro' method='post' name='registro' onsubmit='return validar()'>");
            out.println("<table>");
            out.println("<tr><td><strong>Usuario</strong></td><td><input name='nick' type='text' /></td></tr>");
            out.println("<tr><td><strong>Nombre</strong></td><td><input name='nombre' type='text' /></td></tr>");
                           
            out.println("<tr><td><strong>Correo electrï¿½nico</strong></td><td><input name='email' type='email' /></td></tr>");
            out.println("<tr><td><strong>Contraseï¿½a</strong></td><td><input name='pdw1' type='password' /></td></tr>");
            out.println("<tr><td><strong>Repita contraseï¿½a</strong></td><td><input name='pdw2' type='password' /></td></tr>");
                        
            out.println("</table>");
            out.println("<table>");
            out.println("<tr><td><input type='submit' value='Registrar'> </td><td><input type='button' value='Cancelar' onclick='history.back()' > </td></tr>");
            
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            
        }
        catch (Exception e) {System.out.println(e);}
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st, st2;
        ResultSet rs;
        String SQL, SQL2, nick, nombre, email, pwd, color;
        PrintWriter out;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st = con.createStatement();
            st2 = con.createStatement();
            
            nick = req.getParameter("nick");
            nombre = req.getParameter("nombre");
            email = req.getParameter("email");
            pwd = req.getParameter("pdw1");
            //color = req.getParameter("Color");

            SQL="SELECT * FROM usuarios WHERE Nick=" + nick;
            rs= st.executeQuery(SQL);
            if(rs.next()) {
                out = res.getWriter();
                res.setContentType("TEXT/HTML");
                out.println("<HTML> <BODY>");
                out.println("El nick introducido ya existe. Por favor, escriba uno diferente");
                res.sendRedirect("registro");
                out.println("</BODY> </HTML>");
            }
            SQL2= "INSERT INTO usuarios (Nick, Nombre, Contraseña) VALUES ('"+nick+"', '"+nombre+"', '"+pwd+"')";
            st2.executeUpdate(SQL2);            
            st.close();
            con.close();

            res.sendRedirect("menu");
        }
        catch (Exception e) {System.out.println(e);}
        /*//AÃ±adir dentro del anterior cuando se solucione problema con la BD
        try{
            res.sendRedirect("menu");
        }catch(Exception e){}*/
    }
}