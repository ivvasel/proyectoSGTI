//Este es al que se accede cuando se crea nueva partida o cuando se vuelve a entrar a partida ya creada
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestMovimiento extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL;
        PrintWriter out;
        String tablero[][]=new String [6][6];
        String tablero_actualizado[][]=new String[6][6];
        String casilla;
        String fila_string;
        String columna_string;
        int fila;
        int columna;
        HttpSession sesion;

        try{
            for(int i=0;i<tablero.length;i++){
                for(int j=0;i<tablero.length;j++){
                    tablero[i][j]="";
                    tablero_actualizado[i][j]="";
                }
            }
            sesion=(HttpSession) req.getSession(true);
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            SQL="SELECT casillas.Numero FROM casillas INNER JOIN movimientos ON casillas.IdCasilla="+
            "movimientos.idcasilla WHERE IdPartida=1";
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            while(rs.next()){
                casilla=rs.getString("Numero");
                fila_string=casilla.substring(0,1);
                columna_string=casilla.substring(1);
                fila=Integer.parseInt(fila_string);
                columna=Integer.parseInt(columna_string);//Convertimos indices fila y columna de String a int
                tablero[fila][columna]="1";
            }

            for(int i=0;i<tablero.length;i++){
                for(int j=0;j<tablero.length;j++){
                    if(tablero[i][j].equals("1")){
                        tablero_actualizado[i][j]="<div class=\"ficharoja\"></div>";
                    }
                }
            }

            //Ahora pinto la tabla en HTML
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
            for(int i=1;i<7;i++){
                out.println("<td class="+"\"filabotones\""+">"+"<input type=" +"\"submit\"" +"name="+"\"BO"+i+"\"" +"value="+"\"BO"+i+"\""+"class="+
                    "\"invisible\""+">");
            }
            out.println("</tr>");
            out.println("</table>"); //FIN Primera fila de botones
            out.println("</form>");

            out.println("<table width=" +"\"530px\"" +"height=" +"\"530px\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
                +"cellpadding=" +"\"2\"" +">");   

            for (int i=0;i<tablero.length;i++){
                out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                for (int j=0;j<tablero.length;j++){                
                    out.println("<td class=\"casillas\">"+tablero_actualizado[i][j]+"</td>");
                }
                out.println("</tr>");
            }
            sesion.setAttribute("tablero",tablero_actualizado);
            
            out.println("</table");
            out.println("</body></html>");
            rs.close();
            st.close();
            con.close();
            out.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

            