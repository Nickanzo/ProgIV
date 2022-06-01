package ifc.edu.br.mvcookielogin.resources; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nicoe
 */
public class sqlAccess{
    
    public static void main(String[] args) {
    
        if (validaLogin("admin","123") == true) {
            System.out.println("Funciona");
        }        
    }         
    
    /****************************************************
     *** MODIFICAR COM ROTA DO ARQUIVO E TABELA DE BD ***
     ****************************************************
    public static final String URL = "jdbc:sqlite:C:\\Dir\\Project\\database.bd";    
    public static final String Tabela = "login";
    */
    public static final String URL = "jdbc:sqlite:G:\\ProgIV\\mvCookieLogin\\sqlite.db";
    public static final String Tabela = "login";
    
    public static boolean validaLogin(String user, String pass){  
        //Declaração de Objetos para conexão SQL
        Connection con;
        Statement st;
        ResultSet result;
        //Variáveis de validação
        String validaUser, validaPass;        
        //Query de seleção no banco
        String query = "SELECT * FROM " + Tabela + 
                       " WHERE user = '" + user + 
                       "' and pass = '"  + pass + "'";    
        System.out.println(System.getProperty("user.dir"));
        //Conexão e consulta no Banco
        try {
            con = DriverManager.getConnection(URL);
            st = con.createStatement();
            result = st.executeQuery(query);
            while (result.next()) {
                //Passa o resultado para as variáveis de validação
                validaUser = result.getString("user");
                validaPass = result.getString("pass");
                //Caso as variáveis coincidam, retorna verdadeiro
                if (validaUser.equals(user) && validaPass.equals(pass)){                                        
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.print("Erro no SQL: " + ex.getMessage());
        }        
        return false;
    }
    
}
