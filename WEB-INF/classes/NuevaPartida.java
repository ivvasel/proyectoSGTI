import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class NuevaPartida extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2;
        ResultSet rs;
        String SQL,SQL2;
        PrintWriter out;
        String nombreContrincante;
        HttpSession sesion;
        String idUsuario_yo;
        
        try{
            //sesion=(HttpSession) req.getSession();
            //idUsuario_yo=(String)sesion.getAttribute("idUsuario");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            //SQL="UPDATE usuarios SET buscaPartida=1 WHERE IdUsuario="+idUsuario_yo;
            //st.executeUpdate(SQL);            
            nombreContrincante="";            
           // SQL="SELECT * FROM usuarios WHERE buscaPartida=1";
            //rs=st.executeQuery(SQL);
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                out.println("<title>6 en raya</title>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
                out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
                
                out.println("</head>");
                out.println("<body>");
                    out.println("<div id='encabezado'>");
                        out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                             out.println("CUATRO EN RAYA!");
                        out.println("</div>");
                        out.println("<div class='colorletra2 colorfondo2 letra1' id='subtitulo'>");
                            out.println("Empieza una nueva partida. ¿A qué esperas?");
                        out.println("</div>");
                        out.println("<div class='colorletra3 colorfondo1 letra1' id='menubotones'>");
                            out.println("<nav>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='aleatoria' > VER TODOS LOS USUARIOS </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='amigos'> JUGAR CON UN AMIGO </a>");
                            out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='menu'> VOLVER AL MENÚ </a>");
                            out.println("</nav>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("<h1 class = 'letra1 ' id='subtitulo'> ¿Con quién quieres iniciar una partida nueva? </h1>");
                out.println("</body></html>");
           
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            