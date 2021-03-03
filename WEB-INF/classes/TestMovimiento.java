//Este es al que se accede cuando se crea nueva partida o cuando se vuelve a entrar a partida ya creada
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestMovimiento extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2,st_nick;
        ResultSet rs,rs2,rs_nick;
        String SQL,SQL2,SQL_consulta_nick;
        PrintWriter out;
        String tablero[][]=new String [6][6];
        String tablero_actualizado[][]=new String[6][6];
        String casilla;
        String fila;
        String columna;
        String nick;
        HttpSession sesion;
        int idUsuario_yo;

        try{
            for(int i=0;i<tablero.length;i++){
                for(int j=0;i<tablero.length;j++){
                    tablero[i][j]="";
                    tablero_actualizado[i][j]="";
                }
            }
            sesion=(HttpSession) req.getSession(true);
            nick=(String) sesion.getAttribute("nick");
            idUsuario_yo=(int) sesion.getAttribute("idUsuario");
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            //st_nick=con.createStatement();
            //rs_nick=st_nick.executeQuery(SQL_consulta_nick);
            //rs_nick.next();
            //idUsuario_yo=rs_nick.getInt(1);
            SQL="SELECT movimientos.Casilla FROM movimientos INNER JOIN (partidas INNER JOIN usuarios ON partidas.IdJugador1=usuarios.IdUsuario)"+
            "ON movimientos.IdPartida=partidas.IdPartida WHERE movimientos.IdPartida=1 AND movimientos.IdUsuario="+idUsuario_yo;
            st=con.createStatement();
            rs=st.executeQuery(SQL);
            //Tengo donde están MIS FICHAS
            while(rs.next()){
                casilla=rs.getString("Casilla");
                fila=casilla.substring(0,1);  
                columna=casilla.substring(1);
                tablero[Integer.parseInt(fila)][Integer.parseInt(columna)]="1";
            }

            SQL2="SELECT movimientos.Casilla FROM movimientos INNER JOIN (partidas INNER JOIN usuarios ON partidas.IdJugador2=usuarios.IdUsuario)"+
            "ON movimientos.IdPartida=partidas.IdPartida WHERE movimientos.IdPartida=1 AND movimientos.IdUsuario=63";
            st2=con.createStatement();
            rs2=st2.executeQuery(SQL2);
            
            while(rs2.next()){
                casilla=rs.getString("Casilla");
                fila=casilla.substring(0,1);
                columna=casilla.substring(1);
                tablero[Integer.parseInt(fila)][Integer.parseInt(columna)]="2";
            }
            
            for(int i=0;i<tablero.length;i++){
                for(int j=0;j<tablero.length;j++){
                    if(tablero[i][j].equals("1")){
                        tablero_actualizado[i][j]="<div class=\"ficharoja\"></div>";
                    }else if (tablero[i][j].equals("2")){
                        tablero_actualizado[i][j]="<div class=\"fichaazul\"></div>";
                    }else{
                        tablero_actualizado[i][j]="";
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
            sesion.setAttribute("tablero",tablero);
            
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

            