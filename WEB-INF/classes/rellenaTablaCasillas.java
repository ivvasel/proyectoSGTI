
//Programa que rellena la tabla Casillas de la Base de Datos
import java.io.*;
import java.sql.*;

public class rellenaTablaCasillas{
    public static void main (String [] args){
        Connection con;
        Statement st;
        String SQL;
     
   
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st=con.createStatement();
            for(int i=0;i<7;i++){
                for(int j=0;j<7;j++){
                    SQL="INSERT INTO casillas (IdCasilla,Numero) VALUES ('"+i+j+"')";
                    st.executeUpdate(SQL);                    
                }
            }
            System.out.println("OPERACION COMPLETADA");
            st.close();
            con.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}