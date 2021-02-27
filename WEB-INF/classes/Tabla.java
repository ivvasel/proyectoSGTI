import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Tabla extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        String tablero[][]=new String [6][6];
        String BO1=req.getParameter("BO1");
        String BO2=req.getParameter("BO2");
        String BO3=req.getParameter("BO3");
        String BO4=req.getParameter("BO4");
        String BO5=req.getParameter("BO5");
        String BO6=req.getParameter("BO6");

        for (int i=0;i<tablero.length;i++){ //Inicio matriz de las fichas
            for (int j=0;j<tablero.length;j++){
                tablero[i][j]="";
            }
        }

        /* for (int i=0;i<3;i++){
        for (int j=0;j<tablero.length;j++){
        tablero[i][j]="<div class=\"ficharoja\"></div>";
        }
        }*/

        out.println("<html>");
        out.println("<head>");
        out.println("<link rel="+"\"stylesheet\"" +"href="+"\"index.css\""+">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action="+"\"tabla\"" +">");
        out.println("<table width=" +"\"auto\"" +"height=" +"\"auto\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
            +"cellpadding=" +"\"2\"" +">");
        out.println("<tr align=" +"\"center\"" +">");

        /* for(int i=1; i<7; i++){
        out.println("<td><button id=" +"\"col"+i+"\"" + ">Ficha aqui"
        +"</button></td>");
        }*/

        for(int i=1;i<7;i++){
            out.println("<td class="+"\"filabotones\""+">"+"<input type=" +"\"submit\"" +"name="+"\"BO"+i+"\"" +"value="+"\"BO"+i+"\""+"class="+
                "\"invisible\""+">");
        }
        out.println("</tr>");
        out.println("</table>"); //FIN Primera fila de botones
        out.println("</form>");

        //Tablero para fichas
        out.println("<table width=" +"\"530px\"" +"height=" +"\"530px\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
            +"cellpadding=" +"\"2\"" +">");   

        for (int i=1;i<7;i++){
            out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
            for (int j=1;j<7;j++){                
                out.println("<td class=\"casillas\" id="+"\"cas"+i+j+"\">"+tablero[i-1][j-1]+"</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
    
    
}