import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ponFicha extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        PrintWriter out;
        Connection con;
        ResultSet rs;
        String SQL;
        Statement st;
        String BO1=req.getParameter("BO1");
        String BO2=req.getParameter("BO2");
        String BO3=req.getParameter("BO3");
        String BO4=req.getParameter("BO4");
        String BO5=req.getParameter("BO5");
        String BO6=req.getParameter("BO6");

        int [][] tablero=new int [6][6];
        for(int i=0;i<tablero.length;i++){
            for (int j=0;j<tablero.length;j++){
                tablero[i][j]=0;
            }
        }

        String tablero_actualizado[][]=new String [6][6];
        for(int i=0;i<tablero_actualizado.length;i++){
            for (int j=0;j<tablero_actualizado.length;j++){
                tablero_actualizado[i][j]="";
            }
        }
        

        if(BO1!=null){
            //Boton columna 1 pulsado
            tablero=ponerFicha(tablero, 0);
        }else if (BO2!=null){
            //Boton columna 2 pulsado
            tablero=ponerFicha(tablero,1);
        }else if(BO3!=null){
            //Boton columna 3 pulsado
            tablero=ponerFicha(tablero, 2);
        }else if(BO4!=null){
            //Boton columna 4 pulsado
            tablero=ponerFicha(tablero,3);
        }else if(BO5!=null){
            //Boton columna 5 pulsado
            tablero=ponerFicha(tablero,4);
        }else{
            //Boton columna 6 pulsado
            tablero=ponerFicha(tablero,5);
        }

        //Ahora creamos el tablero actualizado
        for(int i=0;i<tablero_actualizado.length;i++){
            for (int j=0;j<tablero_actualizado.length;j++){
                if(tablero[i][j]==1){
                    tablero_actualizado[i][j]="<div class=" +"\"ficharoja\""+ "></div>";
                }
            }
        }

        try{
        
        //Ahora volvemos a dibujar el tablero con la ficha puesta
        res.setContentType("text/html");
        
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/biblioteca","root", ""); //Conectamos con la BD
        st=con.createStatement();
        SQL=""; //CONSULTA SQL
        rs=st.executeQuery(SQL);
        out=res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel="+"\"stylesheet\"" +"href="+"\"index.css\""+">");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action="+"\"ponFicha\"" + "method="+"\"get\""+">");
        out.println("<table width=" +"\"400\"" +"height=" +"\"40px\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
        +"cellpadding=" +"\"2\"" +">");
        out.println("<tr align=" +"\"center\"" +">");
        for(int i=1;i<7;i++){
            out.println("<td class="+"\"filafichas\""+">"+"<input type=" +"\"submit\"" +"name="+"\"BO"+i+"\"" +"value="+"\"BO"+i+"\""+"class="+
            "\"invisible\""+">");
        }
        out.println("</tr>");
        out.println("</table>"); //Primera fila de botones
        out.println("</form>");

        out.println("<table width=" +"\"400\"" +"height=" +"\"400px\"" +"; border=" +"\"1\"" +"cellspacing=" +"\"2\"" 
        +"cellpadding=" +"\"2\"" +">");
        for (int i=1;i<7;i++){
            out.println("<tr align=" +"\"center\"" +">");
            for (int j=1;j<7;j++){                
                out.println("<td id="+"\"cas"+i+j+"\">"+tablero_actualizado[i-1][j-1]+"</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        
        rs.close();
        st.close();
        con.close();
        out.close();

    }catch(Exception e){
        System.err.println(e);
    }
        
    }

    public int [][] ponerFicha (int [][]tablero, int columna){
        boolean libre=false;
        int i=5;
        while(!libre){
            if((compruebaCasilla(tablero, i, columna))){
                tablero[i][columna]=1;
                libre=true;
            }else{
                i--;
            }
        }
        return tablero;
    }

    public boolean compruebaCasilla (int [][] tablero, int fila, int columna){
        if (tablero[fila][columna]==0){
            return true; //Si es true la casulla está libre
        }else{
            return false;
        }
    }
}