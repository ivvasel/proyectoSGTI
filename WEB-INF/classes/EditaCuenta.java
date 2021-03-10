import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class EditaCuenta extends HttpServlet {
    public void doGet (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st;
        ResultSet rs;
        String SQL, nick, nickAntiguo, nombre, email;
        PrintWriter out;
        HttpSession misesion;
        try {
            misesion=(HttpSession) req.getSession();
            nick=(String) misesion.getAttribute("nick");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st = con.createStatement();
            SQL="SELECT * FROM usuarios WHERE Nick='"+nick+"'";
            rs= st.executeQuery(SQL);
            rs.next();
            nick = rs.getString("nick");
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            
            out=res.getWriter();
            res.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='ISO 8859-1' />");
            out.println("<title>Editar datos</title>");
            //out.println("<script src='/proyectoSGTI/web/recursos/javascripts/registro.js'></script>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/prueba.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/registro.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='/proyectoSGTI/web/recursos/estilos/principal.css' type='text/css' media='all'>");
            out.println("</head>");   
            
            out.println("<body class='colorfondo3'>");

            out.println("<div id='encabezado'>");
                out.println("<div class='colorletra1 colorfondo1 letra1' id='titulo'>");
                    out.println("CUATRO EN RAYA!");
                out.println("</div>");
                out.println("<div class='colorletra2 colorfondo2 letra1'id='subtitulo'>");
                    out.println("Edita tus datos aquí abajo");
                out.println("</div>");
            out.println("</div>");

            out.println("<form action='editardatos' method='post' name='editardatos' onsubmit='return validar()' class='colorfondo2 letra2'>");
            out.println("<table>");
                out.println("<tr><td><strong>Usuario</strong></td><td> '"+nick+"' <br></td></tr>");
                out.println("<tr><td><strong>Nombre</strong></td><td><input name='nombre' type='text'/> <br> (Nombre actual: '"+nombre+"') <br></td></tr>");
                           
                out.println("<tr><td><strong>Correo electrónico</strong></td><td><input name='email' type='email' /> <br> (Email actual: '"+email+"') <br></td></tr>");
                out.println("<tr><td><strong>Contraseña</strong></td><td><input name='pwd1' type='password' /></td></tr>");
                out.println("<tr><td><strong>Repita contraseña</strong></td><td><input name='pwd2' type='password' /></td></tr>");
                        
            out.println("</table>");

            out.println("<div class='botones'>");            
                out.println("<input class ='letra2' type='submit' value='Actualizar'> ");
                out.println("<input class ='letra2' type='button' value='Cancelar' onclick='history.back()' >");            
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            
        }
        catch (Exception e) {System.out.println(e);}
    }

    public void doPost (HttpServletRequest req, HttpServletResponse res) {
        Connection con;
        Statement st, st2;
        ResultSet rs;
        
        String SQL, SQL2, nick, nickNuevo, nombre, email, pwd1, pwd2, color;
        HttpSession misesion;
        PrintWriter out;
        try {
            misesion = (HttpSession) req.getSession();
            nick = (String) misesion.getAttribute("nick");

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/6enraya","root","");
            st = con.createStatement();
            st2 = con.createStatement();
            
            //nickNuevo = req.getParameter("nick");
            nombre = req.getParameter("nombre");
            email = req.getParameter("email");
            pwd1 = req.getParameter("pwd1");
            pwd2 = req.getParameter("pwd2");
            
            out = res.getWriter();

            // if (!nick.isEmpty()){
            //     SQL2="SELECT * FROM usuarios WHERE Nick='"+nickNuevo+"'";
            //     rs= st.executeQuery(SQL2);
            //     if(rs.next()) {
            //     res.setContentType("TEXT/HTML");
            //     out.println("<HTML> <BODY>");
            //     out.println("El nick introducido ya existe. Por favor, escriba uno diferente");
            //     res.sendRedirect("editardatos");
            //     out.println("</BODY> </HTML>");
            //     }
            //     else {
            //     SQL="UPDATE usuarios SET Nick='"+nickNuevo+"' WHERE Nick='"+nick+"'";
            //     st.executeUpdate(SQL);
            //     misesion.setAttribute(nickNuevo,"nick");
            //     }
            // }
            if (!nombre.isEmpty()) {
                SQL="UPDATE usuarios SET Nombre='"+nombre+"' WHERE Nick='"+nick+"'";
                st.executeUpdate(SQL);
            }
            else if (!email.isEmpty()) {
                SQL="UPDATE usuarios SET Email='"+email+"' WHERE Nick='"+nick+"'";
                st.executeUpdate(SQL);
            }
            else if (!pwd1.isEmpty() && !pwd2.isEmpty()) {
                if (!pwd1.equals(pwd2)) {
                    res.setContentType("TEXT/HTML");
                    out.println("<HTML> <BODY>");
                    out.println("Ambas contraseñas no coinciden. Por favor, asegúrese de que son iguales.");
                    res.sendRedirect("editardatos");
                    out.println("</BODY> </HTML>");
                }
                else {
                SQL="UPDATE usuarios SET Contraseña='"+pwd1+"' WHERE Nick='"+nick+"'";
                st.executeUpdate(SQL);
                }
            }
                  
            st.close();
            con.close();

            res.sendRedirect("menu");
        }
        catch (Exception e) {System.out.println(e);}
        /*//Añadir dentro del anterior cuando se solucione problema con la BD
        try{
            res.sendRedirect("menu");
        }catch(Exception e){}*/
    }
}