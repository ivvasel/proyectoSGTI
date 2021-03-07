import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class EstadisticasJug extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        ResultSet rs;
        Statement st;
        String SQL;

        PrintWriter out;
        HttpSession sesion;
        int idUsuario;

        try {
            sesion = (HttpSession) req.getSession(true);
            idUsuario = (int) sesion.getAttribute("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            SQL="SELECT * FROM historicopartidas WHERE idUsuario= '"+idUsuario+"'";
            rs = st.executeQuery(SQL);
            rs.next();
            out = res.getWriter();
            res.setContentType("text/html");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<meta charset='ISO 8859-1' />");
                    out.println("<title>Estadisticas</title>");
                    out.println("<script src='web/recursos/javascripts/login.js'></script>");
                    out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
                    out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
                out.println("</head>");

                out.println("<body>");
                    out.println("<div id='encabezado'>");
                        out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                            out.println("CUATRO EN RAYA!");
                        out.println("</div>");
                        out.println("<div class='colorletra2 colorfondo2 letra1' id='subtitulo'>");
                            out.println("Mis estadísticas");
                        out.println("</div>");
                        out.println("<div class='colorletra3 colorfondo1 letra1' id='menubotones'>");
                            out.println("<nav>");
                                out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='historicoPartidas'> Histórico de partidas </a>");
                                out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='historicoJugador'> Histórico de jugadores </a>");
                                out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='menu'> Menú principal </a>");
                            out.println("</nav>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class='colorletra2 letra1' id='mensajeEstad'>");
                        out.println("A continuación puedes observar los número totales de partidas jugadas, ganadas y perdidas. Además, podrás observar la suma total de puntos que has conseguido hasta ahora!");
                    out.println("</div>");
                out.println("<table>");                
                out.println("<tr> <td> Partidas ganadas </td><td> '"+ rs.getString("Ganadas")+"' </td></tr>");
                out.println("<tr> <td> Partidas perdidas </td><td> '"+ rs.getString("Perdidas")+"' </td></tr>");
                out.println("<tr> <td> Puntos totales </td><td> '"+ rs.getString("PuntosTotales")+"' </td></tr>");
                
                out.println("</table>");

                out.println("</body></html>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}