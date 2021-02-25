import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class InicioSesForm extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out;

        try {
            res.setContentType("text/html");
            out = res.getWriter();
            
            out.println("<HTML><BODY>");
            out.println("<TITLE> Indica tu usuario y contraseña a continuación </TITLE> <BR>");
            out.println("<FORM ACTION=\"InicioSesion\">");
            out.println("Usuario: <INPUT TYPE=\"TEXT\" NAME=\"nick\"> <BR>");
            out.println("Contraseña: <INPUT TYPE=\"PASSWORD\" NAME=\"pwd\"> <BR>");
            out.println("<INPUT TYPE=\"SUBMIT\" VALUE=\"Aceptar\">");
            out.println("</FORM></BODY></HTML>");
            
            out.close();
        }
        catch (Exception e) {System.err.println(e);}
    }
}