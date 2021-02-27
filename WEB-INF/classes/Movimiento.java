import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Movimiento extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res){
        Connection con;
        Statement st,st2;
        ResultSet rs,rs2;
        String SQL,SQL2;
        PrintWriter out;
        HttpSession sesion;
        
        String boton1;
        String boton2;
        String boton3;
        String boton4;
        String boton5;
        String boton6;
        String nick;
        
        
        String casilla;
        String fila_string; //Fila en la matriz en formato string
        String columna_string; //Columna en la matriz en formato string
        int fila;
        int columna;
        String tablero [][]=new String [6][6];
        try{
            for(int i=0;i<tablero.length;i++){
                for (int j=0;j<tablero.length;j++){
                    tablero[i][j]="";
                }
            }
            
            sesion=(HttpSession) req.getSession();
            nick=(String) sesion.getAttribute("nick"); //Cojo el nick del usuario actual 
            boton1=req.getParameter("BO1");
            boton2=req.getParameter("BO2");
            boton3=req.getParameter("BO3");
            boton4=req.getParameter("BO4");
            boton5=req.getParameter("BO5");
            boton6=req.getParameter("BO6");
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            
            //CONSULTA PARA SABER CASIllAS OCUPADAS POR EL USUARIO PRINCIPAL LOGGEADO
            SQL="SELECT Numero FROM casillas INNER JOIN movimientos (INNER JOIN partidas (INNER JOIN"+
            "usuarios ON partidas.IdJugador1=usuarios.IdJugador) ON movimientos.IdPartida=partidas.IdPartida"+
            ")ON casillas.IdCasilla=movimientos.IdCasilla WHERE IdPartida=  ";
            
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
            
            //CONSULTA PARA SABER CASILLAS OCUPADAS POR EL CONTRINCANTE
            SQL2="SELECT Numero FROM casillas INNER JOIN movimientos (INNER JOIN partidas (INNER JOIN"+
            "usuarios ON partidas.IdJugador1=usuarios.IdJugador) ON movimientos.IdPartida=partidas.IdPartida"+
            ")ON casillas.IdCasilla=movimientos.IdCasilla WHERE IdPartida=  ";
            st2=con.createStatement();
            rs2=st2.executeQuery(SQL2);
            while(rs2.next()){
                casilla=rs.getString("Numero");
                fila_string=casilla.substring(0,1);
                columna_string=casilla.substring(1);
                fila=Integer.parseInt(fila_string);
                columna=Integer.parseInt(columna_string);
                tablero[fila][columna]="2";
            }
            
            
            
        } catch (Exception e){
            System.err.println(e);
        }
    }
}

        