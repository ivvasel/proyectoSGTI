import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ServletInicioSes extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out;

        try {
            res.setContentType("text/html");
            out = res.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='ISO 8859-1' />");
            out.println("<title>Login</title>");
            out.println("<script src='web/recursos/javascripts/login.js'></script>");
            out.println("<link rel='stylesheet' href='web/recursos/estilos/principal.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='web/recursos/estilos/login.css' type='text/css' media='all'>");
            out.println("</head>");

            out.println("<body class='colorfondo3'>");

            out.println("<div id='encabezado'>");
            out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
            out.println("CUATRO EN RAYA!");
            out.println("</div>");
            out.println("<div class='colorletra2 colorfondo2 letra1'id='subtitulo'>");
            out.println("Inicio de sesi?n");
            out.println("</div>");
            out.println("</div>");

            out.println("<form method='post' action='login' name='iniciosesion' onsubmit='return validar()' class='colorfondo2 letra2'> ");
            out.println("<table>");
            out.println("<tr><td><strong>Usuario</strong></td><td><input type='text' name='nick'></td></tr>");
            out.println("<tr><td><strong>Contrase?a</strong></td><td><input type='password' name='password'></td></tr>");
            out.println("</table>");

            out.println("<div class='botones'>    ");
            out.println("<input class ='letra2' type='submit' value='Iniciar Sesion'> ");
            out.println("<input class ='letra2' type='button' value='Cancelar' onclick='history.back()' >	");
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st,st2;
        ResultSet rs,rs2;
        PrintWriter out;
        String SQL,SQL_aux;
        String nick;
        String pass;
        int idUsuario;

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
            st2=con.createStatement();

            
            SQL="SELECT * FROM usuarios WHERE Nick='"+nick+"' AND Contraseña='"+pass+"'";
            rs=st.executeQuery(SQL);

            if(rs.next()){
                misesion.setAttribute("nick",nick);
                SQL_aux="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
                rs2=st2.executeQuery(SQL_aux);
                rs2.next();
                idUsuario=rs2.getInt(1);
                misesion.setAttribute("idUsuario",idUsuario);
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