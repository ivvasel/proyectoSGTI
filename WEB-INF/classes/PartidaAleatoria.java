import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class PartidaAleatoria extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        PrintWriter out;
        String SQL;
        HttpSession sesion;
        int idUsuario_yo;

        try{
            sesion=(HttpSession)req.getSession();
            idUsuario_yo=(int)sesion.getAttribute("idUsuario");

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            SQL="SELECT * FROM usuarios WHERE IdUsuario<>"+idUsuario_yo;
            rs=st.executeQuery(SQL);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                out.println("<title>6 en raya</title>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
            //out.println("<link rel=\"stylesheet\" href=\"menu.css\">");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='encabezado'>");
                        out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                             out.println("CUATRO EN RAYA!");
                        out.println("</div>");
                        out.println("<div class='colorletra2 colorfondo2 letra1' id='subtitulo'>");
                            out.println("Comenzando una partida aleatoria... ¡Suerte!");
                        out.println("</div>");
                        out.println("<div class='colorletra3 colorfondo1 letra1' id='menubotones'>");
                            out.println("<nav>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='nueva' > VOLVER A INICIAR PARTIDA </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='menu'> VOLVER AL MENÚ </a>");
                            out.println("</nav>");
                        out.println("</div>");
                    out.println("</div>");
            out.println("<h1>¿Contra quién quieres jugar?</h1>");
            out.println("<form action=\"nueva/crea\">");
            out.println("<select name=\"contrincante\">");
            while(rs.next()){
                out.println("<option>"+rs.getString("Nick")+"</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"INICIAR PARTIDA\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            rs.close();
            st.close();
            con.close();
            out.close();

        }catch (Exception e){
            System.err.println(e);
        }
    }
}
