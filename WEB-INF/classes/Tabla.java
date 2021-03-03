//Este servlet para procesar el movimiento solicitado por el usuario

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Tabla extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL;
        PrintWriter out;
        HttpSession sesion;
        String tablero[][];
        String boton1,boton2,boton3,boton4,boton5,boton6;
        boolean columna_libre;
        try{
            boton1=req.getParameter("BO1");
            boton2=req.getParameter("BO2");
            boton3=req.getParameter("BO3");
            boton4=req.getParameter("BO4");
            boton5=req.getParameter("BO5");
            boton6=req.getParameter("BO6");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            sesion=(HttpSession) req.getSession();
            tablero=(String[][]) sesion.getAttribute("tablero");

            if(boton1!=null){
                //metodo columna 1
            }else if(boton2!=null){
                //metodo columna 2
            }else if(boton3!=null){
                //metodo columna 3
            }else if(boton4!=null){
                //metodo columna 4
            }else if(boton5!=null){
                //metodo columna 5
            }else{
                //metodo columna 6
            }

            res.setContentType("text/html");
            out=res.getWriter();

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

            /* for(int i=1;i<7;i++){
            out.println("<td class="+"\"filabotones\""+">"+"<input type=" +"\"submit\"" +"name="+"\"BO"+i+"\"" +"value="+"\"BO"+i+"\""+"class="+
            "\"invisible\""+">");
            }*/
            out.println("</tr>");
            out.println("</table>"); //FIN Primera fila de botones
            out.println("</form>");
            
            //out.println("<h1> TURNO DEL OPONENTE </h1>");
            //Tablero para fichas
            out.println("<table width=" +"\"auto\"" +"height=" +"\"auto\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
                +"cellpadding=" +"\"2\"" +">");   

            for (int i=1;i<7;i++){
                out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                for (int j=1;j<7;j++){                
                    out.println("<td class=\"casillas\" id="+"\"cas"+i+j+"\">"+tablero[i-1][j-1]+"</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");
            
            out.println("<nav>");
            out.println("<a href=\"\">VOLVER A MIS PARTIDAS</a>");
            out.println("</nav>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            System.err.println(e);

        }

    }
    
}
