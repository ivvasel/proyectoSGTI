import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PartidaAmigos extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        PrintWriter out;
        String SQL;
        
        try{
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                out.println("<title>6 en raya</title>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
            out.println("<head>");
            out.println("<body>");
            out.println("<div id='encabezado'>");
                        out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                             out.println("CUATRO EN RAYA!");
                        out.println("</div>");
                        out.println("<div class='colorletra2 colorfondo2 letra1' id='subtitulo'>");
                            out.println("Comenzando una partida con un amigo... ¡Suerte!");
                        out.println("</div>");
                        out.println("<div class='colorletra3 colorfondo1 letra1' id='menubotones'>");
                            out.println("<nav>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='nueva' > VOLVER A INICIAR PARTIDA </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='menu'> VOLVER AL MENÚ </a>");
                            out.println("</nav>");
                        out.println("</div>");
                    out.println("</div>");
            out.println("<h1>Busca a tus amigos</h1>");
            out.println("<form action=\"busca\">");
            out.println("<input type=\"text\" name=\"nombreContrincante\">");
            out.println("<input type=\"submit\" value=\"BUSCAR JUGADOR\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
