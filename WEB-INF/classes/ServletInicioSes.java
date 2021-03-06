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
            out.println("<meta charset='utf-8' />");
            out.println("<title>Login</title>");
            out.println("<script src='web/recursos/javascripts/login.js'></script>");
            out.println(
                    "<link rel='stylesheet' href='web/recursos/estilos/principal.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='web/recursos/estilos/login.css' type='text/css' media='all'>");
            out.println("</head>");

            out.println("<body class='colorfondo3'>");

            out.println("<div id='encabezado'>");
            out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
            out.println("CUATRO EN RAYA!");
            out.println("</div>");
            out.println("<div class='colorletra2 colorfondo2 letra1'id='subtitulo'>");
            out.println("Inicio de sesion");
            out.println("</div>");
            out.println("</div>");

            out.println(
                    "<form method='post' action='iniciosesion' name='iniciosesion' onsubmit='return validar()' class='colorfondo2 letra2'> ");
            out.println("<table>");
            out.println("<tr><td><strong>Usuario</strong></td><td><input type='text' name='nick'></td></tr>");
            out.println(
                    "<tr><td><strong>Contrasena</strong></td><td><input type='password' name='password'></td></tr>");
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
        ResultSet rs;
        Statement st, st2;
        String SQL, SQL2, nick, pwd;
        PrintWriter out;

        try {
            nick = req.getParameter("nick");
            pwd = req.getParameter("pwd");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya", "root", "");
            st = con.createStatement();
            st2 = con.createStatement();

            SQL = "SELECT * FROM usuarios WHERE Nick=" + nick + "AND Contrase�a=" + pwd;
            rs = st.executeQuery(SQL);

            if (rs.next())
                res.sendRedirect("PagInicio");
            else {
                out = res.getWriter();
                res.setContentType("text/HTML");
                out.println(
                        "<HTML> <BODY> La combinación de usuario y contraseña no es correcta. Por favor, vuelva a intentarlo <BR>");
                out.println("<INPUT TYPE=BUTTON VALUE=Volver a intentar ACTION=ServletInicioSes>");
                out.println("</BODY> </HTML>");
                out.close();
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}