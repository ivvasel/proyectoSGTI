import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class InicioSesionForm extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        PrintWriter out;
        
        try {
            res.setContentType("text/html");
            out=res.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8' />");
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
                    out.println("Inicio de sesión");
                    out.println("</div>");
                out.println("</div>");
                
                out.println("<form method='get' action='iniciosesion' name='login' onsubmit='return validar()' class='colorfondo2 letra2'> ");
                    out.println("<table>");
                        out.println("<tr><td><strong>Usuario</strong></td><td><input type='text' name='nick'></td></tr>");
                        out.println("<tr><td><strong>Contraseña</strong></td><td><input type='password' name='password'></td></tr>");		
                    out.println("</table>");
            
                    out.println("<div class='botones'>    ");        
                        out.println("<input class ='letra2' type='submit' value='Iniciar Sesión'> ");
                        out.println("<input class ='letra2' type='button' value='Cancelar' onclick='history.back()' >	");
                    out.println("</div>");
                out.println("</form>");
            out.println("</body>");
            out.println("</html>");


            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
