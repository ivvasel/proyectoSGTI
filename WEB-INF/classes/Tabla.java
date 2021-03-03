//Este servlet para procesar el movimiento solicitado por el usuario

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Tabla extends HttpServlet{
    public void doPost (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st_aux,st_Actualiza_turno,st_Actualiza_turno2;
        ResultSet rs,rs_aux;
        String SQL,SQL_aux,SQL_Actualiza_turno,SQL_Actualiza_turno2;
        PrintWriter out;
        HttpSession sesion;
        int tablero[][]=new int [6][6];
        String tablero_actualizado[][]=new String [6][6];
        String boton1,boton2,boton3,boton4,boton5,boton6;
        boolean columna_libre;
        int columna;
        int fila;
        String idPartida;
        String nick;
        int idUsuario;
        try{
            fila=5;
            columna_libre=true;
            boton1=req.getParameter("BO1");
            boton2=req.getParameter("BO2");
            boton3=req.getParameter("BO3");
            boton4=req.getParameter("BO4");
            boton5=req.getParameter("BO5");
            boton6=req.getParameter("BO6");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            sesion=(HttpSession) req.getSession();
            tablero=(int[][]) sesion.getAttribute("tablero");
            idPartida=(String)sesion.getAttribute("idPartida");
            nick=(String)sesion.getAttribute("nick");
            SQL_aux="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st_aux=con.createStatement();
            rs_aux=st_aux.executeQuery(SQL_aux);
            rs_aux.next();
            idUsuario=rs_aux.getInt(1);
            st=con.createStatement();
            

            if(boton1!=null){ //Aprieto columna 1
                columna=0;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }
                }
            }else if(boton2!=null){
                columna=1;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    if(!(fila>0)){
                        columna_libre=true;
                    }
                }
                //metodo columna 2
            }else if(boton3!=null){
                columna=2;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    if(!(fila>0)){
                        columna_libre=true;
                    }
                }
                //metodo columna 3
            }else if(boton4!=null){
                columna=3;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    if(!(fila>0)){
                        columna_libre=true;
                    }
                }
                //metodo columna 4
            }else if(boton5!=null){
                columna=4;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    if(!(fila>0)){
                        columna_libre=true;
                    }
                }
                //metodo columna 5
            }else{
                columna=5;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+","+fila+columna+")";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    if(!(fila>0)){
                        columna_libre=true;
                    }
                }
                //metodo columna 6
            }

            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    if(tablero[i][j]==1){
                        tablero_actualizado[i][j]="<div class=\"ficharoja\"></div>";
                    }else if(tablero[i][j]==2){
                        tablero_actualizado[i][j]="<div class=\"fichaazul\"></div>";
                    }else{
                        tablero_actualizado[i][j]="<div class=\"fichavacia\"></div>";
                    }
                }
            }
           
            SQL_Actualiza_turno="UPDATE detallespartida SET Turno=0 WHERE IdJugador="+idUsuario+" AND IdPartida="+idPartida;
            st_Actualiza_turno=con.createStatement();
            st_Actualiza_turno.executeUpdate(SQL_Actualiza_turno);
            
            SQL_Actualiza_turno2="UPDATE detallespartida SET Turno=1 WHERE IdJugador<>"+idUsuario+" AND IdPartida="+idPartida;
            st_Actualiza_turno2=con.createStatement();
            st_Actualiza_turno2.executeUpdate(SQL_Actualiza_turno2);
            
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

            out.println("</tr>");
            out.println("</table>"); //FIN Primera fila de botones
            out.println("</form>");

            //out.println("<h1> TURNO DEL OPONENTE </h1>");
            //Tablero para fichas
            out.println("<table width=" +"\"auto\"" +"height=" +"\"auto\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
                +"cellpadding=" +"\"2\"" +">");   

            for (int i=0;i<6;i++){
                out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                for (int j=0;j<6;j++){                
                    out.println("<td class=\"casillas\" id="+"\"cas"+i+j+"\">"+tablero_actualizado[i][j]+"</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");

            out.println("<nav>");
            out.println("<a href=\"menu\">MENU</a>");
            out.println("</nav>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            System.err.println(e);
        }

    }

}
