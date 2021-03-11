//Este servlet para procesar el movimiento solicitado por el usuario

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Tabla extends HttpServlet{

    public void doPost (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st_aux,st_Actualiza_turno,st_Actualiza_turno2,st_actualiza_activa,st_actualiza_activa2;
        ResultSet rs,rs_aux;
        String SQL,SQL_aux,SQL_Actualiza_turno,SQL_Actualiza_turno2,SQL_actualiza_activa,SQL_actualiza_activa2;
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
        boolean terminada;
        boolean botones_visibles[]=new boolean[6];
        int puntos1, puntos2;
        try{
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
        out.println("<div class='colorletra2 colorfondo2 letra1' id='subtitulo'>");
            out.println("TURNO TERMINADO");
        out.println("</div>");
                out.println("<div class='colorletra2 colorfondo1 letra1'>");
                    out.println("<nav>");
                        out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='listapartidas'> MIS PARTIDAS </a>");
                        out.println("<a id='nav-enlace' class='letra2 colorfondo2 colorletra2' href='menu'> VOLVER AL MEN� </a>");
                    out.println("</nav>");
                out.println("</div>");
            out.println("</div>");


            for (int i=0;i<6;i++){
                botones_visibles[i]=true;
            }

            terminada=false;
            fila=5;
            columna_libre=true;
            boton1=req.getParameter("BO0");
            boton2=req.getParameter("BO1");
            boton3=req.getParameter("BO2");
            boton4=req.getParameter("BO3");
            boton5=req.getParameter("BO4");
            boton6=req.getParameter("BO5");
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            sesion=(HttpSession) req.getSession();
            tablero=(int[][]) sesion.getAttribute("tablero");//Recupero tablero de servlet anterior
            idPartida=(String)sesion.getAttribute("idPartida");
            nick=(String)sesion.getAttribute("nick");
            SQL_aux="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            st_aux=con.createStatement();
            rs_aux=st_aux.executeQuery(SQL_aux);
            rs_aux.next();
            idUsuario=rs_aux.getInt(1);
            st=con.createStatement();

            if(boton1!=null){ //Aprieto columna 0
                columna=0;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
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
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    
                }
                //metodo columna 2
            }else if(boton3!=null){
                columna=2;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                   
                }
                //metodo columna 3
            }else if(boton4!=null){
                columna=3;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    
                }
                //metodo columna 4
            }else if(boton5!=null){
                columna=4;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
                    }

                    
                }
                //metodo columna 5
            }else{
                columna=5;
                while (columna_libre==true){
                    if(tablero[fila][columna]==0){
                        tablero[fila][columna]=1;
                        SQL="INSERT INTO movimientos (IdPartida,IdUsuario,Casilla) VALUES ("+idPartida+","+idUsuario+",'"+fila+columna+"')";
                        st.executeUpdate(SQL);
                        columna_libre=false;
                    }else{
                        fila--;
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

            //Compruebo si ya se ha rellenado todo
            for(int i=0;i<6;i++){
                if(tablero[0][i]!=0){
                    botones_visibles[i]=false;
                }
            }

            int k=0;
            if((!(botones_visibles[k])) && (!(botones_visibles[k+1])) && (!(botones_visibles[k+2])) && (!(botones_visibles[k+3])) && (!(botones_visibles[k+4])) && (!(botones_visibles[k+5]))){
                terminada=true;
            }

            if(terminada==true){
                puntos1=Puntos(tablero,1);
                puntos2=Puntos(tablero,2);
                SQL_actualiza_activa="UPDATE detallespartida SET Activa=0, Puntos="+puntos1+" WHERE IdJugador="+idUsuario+" AND IdPartida="+idPartida;
                st_actualiza_activa=con.createStatement();
                st_actualiza_activa.executeUpdate(SQL_actualiza_activa);
                
                SQL_actualiza_activa2="UPDATE detallespartida SET Activa=0, Puntos="+puntos2+" WHERE IdJugador<>"+idUsuario+" AND IdPartida="+idPartida;
                st_actualiza_activa2=con.createStatement();
                st_actualiza_activa2.executeUpdate(SQL_actualiza_activa2);
                


                out.println("<h1>LA PARTIDA HA TERMINADO</h1>");
                out.println("<table>");   

                for (int i=0;i<6;i++){
                    out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                    for (int j=0;j<6;j++){                
                        out.println("<td class=\"casillas\" id="+"\"cas"+i+j+"\">"+tablero_actualizado[i][j]+"</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");

                //CAJA DE LOS PUNTOS
                out.println("<table >");
                out.println("<tr><td>TUS PUNTOS</td></tr>");
                out.println("<tr><td>"+puntos1+"</td></tr>");
                out.println("</table>");

                out.println("<table border=\"3\">");
                out.println("<tr><td>PUNTOS DEL CONTRINCANTE</td></tr>");
                out.println("<tr><td>"+puntos2+"</td></tr>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }else{

                SQL_Actualiza_turno="UPDATE detallespartida SET Turno=0 WHERE IdJugador="+idUsuario+" AND IdPartida="+idPartida;
                st_Actualiza_turno=con.createStatement();
                st_Actualiza_turno.executeUpdate(SQL_Actualiza_turno);

                SQL_Actualiza_turno2="UPDATE detallespartida SET Turno=1 WHERE IdJugador<>"+idUsuario+" AND IdPartida="+idPartida;
                st_Actualiza_turno2=con.createStatement();
                st_Actualiza_turno2.executeUpdate(SQL_Actualiza_turno2);

                

                //out.println("<h1> TURNO DEL OPONENTE </h1>");
                //Tablero para fichas
                out.println("<table >");   

                for (int i=0;i<6;i++){
                    out.println("<tr class=\"filatablero\" align=" +"\"center\"" +">");
                    for (int j=0;j<6;j++){                
                        out.println("<td class=\"casillas\" id="+"\"cas"+i+j+"\">"+tablero_actualizado[i][j]+"</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e){
            System.err.println(e);
        }

    }



    //M�todo para calcular los puntos al final de la p�rtida
    public int Puntos(int [][] tablero, int jugador) {
        //int tablero[][]=new int [6][6];
        int contador1=0;
        int Puntos1 = 0;
        //FOR para jugador 1
            //Comprueba los puntos por filas
        for(int i=0;i<6;i++){
            Puntos1=Puntos1 + calculapuntos(contador1); //por si acaso �ltima no cogida bien
            contador1=0;            
            for(int j=0;j<6;j++){
                if(tablero[i][j]==jugador){ //Comprueba puntos jugador1
                    contador1++ ;
                    System.out.println("Secuencia en filas: "+i+j+" "+contador1);
                    continue;
                }else{
                    Puntos1=Puntos1 + calculapuntos(contador1); //Calcula puntos en relaci�n al n�mero de fichas consecutivas en una misma fila
                    contador1=0;
                }
                
            }
        }
            //Comprueba los puntos por columnas
            for(int j=0;j<6;j++){
                Puntos1=Puntos1 + calculapuntos(contador1); //por si acaso �ltima no cogida bien
                contador1=0;
                for(int i=0;i<6;i++){
                    if(tablero[i][j]==jugador){ //Comprueba puntos jugador1
                        contador1++ ;
                        System.out.println("Secuencia en columna: "+i+j+" "+contador1);
                        continue;
                    }else{
                        Puntos1=Puntos1 + calculapuntos(contador1); //Calcula puntos en relaci�n al n�mero de fichas consecutivas
                        contador1=0;
                    }
                }
            }
            Puntos1=Puntos1 + calculapuntos(contador1); //por si acaso �ltima no cogida bien
            contador1=0;            
            //Comprueba los puntos de la daigonal descendente
            for(int i=0, j=0; i<6 && j<6; i++,j++){
                if(tablero[i][j]==jugador){ //Comprueba puntos jugador1
                    contador1++ ;
                    System.out.println("Secuencia en diagonal desc: "+i+j+" "+contador1);    
                    continue;        
                
                }else{
                    Puntos1=Puntos1 + calculapuntos(contador1); //Calcula puntos en relaci�n al n�mero de fichas consecutivas
                    contador1=0;                    
                }   
            }
            Puntos1=Puntos1 + calculapuntos(contador1); //por si acaso �ltima no cogida bien
            contador1=0;
            //Comprueba los puntos de la diagonal ascendente

            for(int i=5, j=0; i>=0 && j<6; i--,j++){
                if(tablero[i][j]==jugador){ //Comprueba puntos jugador1
                    contador1++ ;
                    System.out.println("Secuencia en diagonal ascendet: "+i+j+" "+contador1);
                    continue;            
                
                }else{
                    Puntos1=Puntos1 + calculapuntos(contador1); //Calcula puntos en relaci�n al n�mero de fichas consecutivas
                    contador1=0;
                    
                }                
                
            }    
            Puntos1=Puntos1 + calculapuntos(contador1); //por si acaso �ltima no cogida bien
            contador1=0;
            return Puntos1;

    }

    public int calculapuntos(int contador){
        System.out.println("Dentro del metodo " +contador);
        int puntos = 0;
        if(contador==6){
            puntos = 3;
        }else if(contador==5){
            puntos = 2;
        }else if(contador==4){
            puntos = 1;
        }
        System.out.println("Puntos devueltos: "+puntos);
        return puntos;
    }

}

