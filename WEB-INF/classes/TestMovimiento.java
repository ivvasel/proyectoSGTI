//Este es al que se accede cuando se crea nueva partida o cuando se vuelve a entrar a partida ya creada
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TestMovimiento extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2,st_aux;
        ResultSet rs,rs2,rs_aux;
        String SQL,SQL2,SQL_aux;
        PrintWriter out;
        int tablero[][]=new int[6][6];
        String tablero_actualizado[][]=new String[6][6];
        String casilla;
        String fila;
        String columna;
        String nick;
        String rival="";
        HttpSession sesion,sesion2;
        int idUsuario_yo;
        String idPartida; 
        boolean botones_visibles[]=new boolean[6];

        try{
            sesion2=(HttpSession)req.getSession(true);
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");

            for(int i=0;i<6;i++){
                botones_visibles[i]=true;
            }

            for(int i=0;i<6;i++){

                for(int j=0;j<6;j++){
                    tablero[i][j]=0;

                    tablero_actualizado[i][j]="<div class='fichavacia '></div>";
                }
            }

            
            idPartida=(String) req.getAttribute("idPartida");
            if(idPartida==null){
                idPartida=req.getParameter("continuar");
            }
            sesion=(HttpSession) req.getSession();
            sesion.setAttribute("idPartida",idPartida);
            nick=(String) sesion.getAttribute("nick");
            sesion.setAttribute("idPartida",idPartida); //Guardo idPartida en variable de sesion para el siguiente servlet

            SQL_aux="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st_aux=con.createStatement();
            rs_aux=st_aux.executeQuery(SQL_aux);
            rs_aux.next();
            idUsuario_yo=rs_aux.getInt(1);

            
            //st_nick=con.createStatement();
            //rs_nick=st_nick.executeQuery(SQL_consulta_nick);
            //rs_nick.next();
            //idUsuario_yo=rs_nick.getInt(1);

            SQL="SELECT movimientos.Casilla FROM movimientos INNER JOIN (partidas INNER JOIN usuarios ON partidas.IdJugador1=usuarios.IdUsuario)"+
            "ON movimientos.IdPartida=partidas.IdPartida WHERE movimientos.IdPartida="+idPartida+" AND movimientos.IdUsuario="+idUsuario_yo;//MIS FICHAS
            st=con.createStatement();
            rs=st.executeQuery(SQL);

            //Tengo donde est�n MIS FICHAS
            while(rs.next()){
                casilla=rs.getString("Casilla");
                fila=casilla.substring(0,1);  
                columna=casilla.substring(1);
                tablero[Integer.parseInt(fila)][Integer.parseInt(columna)]=1;
            }

            SQL2="SELECT movimientos.Casilla, usuarios.Nick FROM movimientos INNER JOIN (partidas INNER JOIN usuarios ON partidas.IdJugador2=usuarios.IdUsuario)"+
            "ON movimientos.IdPartida=partidas.IdPartida WHERE movimientos.IdPartida="+idPartida+" AND movimientos.IdUsuario<>"+idUsuario_yo;
            st2=con.createStatement();
            rs2=st2.executeQuery(SQL2);

            //FICHAS RIVAL
            while(rs2.next()){
                rival=rs2.getString(2);
                casilla=rs2.getString("Casilla");
                fila=casilla.substring(0,1);
                columna=casilla.substring(1);
                tablero[Integer.parseInt(fila)][Integer.parseInt(columna)]=2;
            }

            //Compruebo si est� todo lleno arriba
            for(int i=0;i<6;i++){
                if(tablero[0][i]!=0){
                    botones_visibles[i]=false;
                }
            }

            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    if(tablero[i][j]==1){
                        tablero_actualizado[i][j]="<div class=\"ficharoja\"></div>";
                    }else if (tablero[i][j]==2){
                        tablero_actualizado[i][j]="<div class=\"fichaazul\"></div>";
                    }else{
                        tablero_actualizado[i][j]=tablero_actualizado[i][j];
                    }
                }
            }

            //Ahora pinto la tabla en HTML
            res.setContentType("text/html");
            out=res.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Jugando</title>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/tablero.css'>");            
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/nav.css'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div id='encabezado'>");
                out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                    out.println("CUATRO EN RAYA!");
                out.println("</div>");
                out.println("<div class='colorletra2 colorfondo2 letra1'id='subtitulo'>");
                    out.println(""+nick+" vs "+rival+"");
                out.println("</div>");
            out.println("</div>");

            out.println("<form action="+"\"tabla\" method=\"post\"" +">");
            out.println("<table>");
            out.println("<tr align=" +"\"center\"" +">");
            for(int i=0;i<6;i++){
                if(botones_visibles[i]){
                    out.println("<td class="+"\"filabotones\""+">"+"<input type=" +"\"submit\"" +"name="+"\"BO"+i+"\"" +"value="+"\"BO"+i+"\""+"class="+
                        "\"invisible\""+">");
                }else{
                    out.println("<td class="+"\"filabotones\">");
                }
            }
                out.println("</tr>");
                //out.println("</table>"); //FIN Primera fila de botones
                out.println("</form>");

                //out.println("<table>");   
                for (int i=0;i<6;i++){
                    out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                    for (int j=0;j<6;j++){                
                        out.println("<td>"+tablero_actualizado[i][j]+"</td>");
                    }
                    out.println("</tr>");
                }
                sesion.setAttribute("tablero",tablero); //Envio matriz de ints           
                out.println("</table");

                //CAJA DE LOS PUNTOS
                /*out.println("<table border=\"3\">");
                out.println("<tr><td>TUS PUNTOS</td></tr>");
                out.println("<tr><td>"+100+"</td></tr>");
                out.println("</table>");

                out.println("<table>");
                out.println("<tr><td>PUNTOS DEL CONTRINCANTE</td></tr>");
                out.println("<tr><td>"+100+"</td></tr>");
                out.println("</table>");*/

                out.println("</body></html>");
                rs.close();
                rs2.close();
                st.close();
                st2.close();
                con.close();
                out.close();
            }catch (Exception e){
            System.err.println(e);
        }
    }
}

            