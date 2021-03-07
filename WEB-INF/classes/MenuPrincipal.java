import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MenuPrincipal extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL;
        PrintWriter out;
        HttpSession misesion;
        String nick;
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            //con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            misesion=(HttpSession) req.getSession();
            nick=(String) misesion.getAttribute("nick");
            out=res.getWriter();
            res.setContentType("text/html");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                out.println("<title>Menu</title>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
                out.println("</head>");
                
                out.println("<body>");
                    out.println("<div id='encabezado'>");
                        out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                             out.println("CUATRO EN RAYA!");
                        out.println("</div>");
                        out.println("<div class='colorfondo2 '>");
                            out.println("<nav>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo1 colorletra2' href='nueva' > INICIAR PARTIDA </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo1 colorletra2' href='listapartidas'> MIS PARTIDAS </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo1 colorletra2' href='estadisticas'> ESTADISTICAS </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo1 colorletra2' href='index.html'> SALIR </a>");
                            out.println("</nav>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("<h1 class = 'letra1 ' id='subtitulo'>BIENVENIDO "+nick+"!</h1>");
                out.println("<img src='/proyectoSGTI/web/recursos/imagenes/tablero.jfif'>");
                out.println("</body></html>");

            //con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }//Fin doGet
}//Fin clase