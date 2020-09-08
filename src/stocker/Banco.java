/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocker;

import java.sql.*;


/**
 *
 * @author jvitorgf
 */
public class Banco {
    
    public static Connection connection = null;
    private Statement stdados = null;
    private ResultSet rsdados = null;
    private PreparedStatement pstdados = null;
    private CallableStatement cstdados = null;
    
    
    public boolean Banco(){
        try {
            String usuario = "postgres";
            String senha = "teste";

            Class.forName("org.postgresql.Driver");  //para acesso ao banco de dados Postgres
            String urlconexao = "jdbc:postgresql://127.0.0.1/banco-stocker"; 
            connection = DriverManager.getConnection(urlconexao, usuario, senha);
            connection.setAutoCommit(false);//configuracao necessaria para confirmacao ou nao de alteracoes no banco de dados.
            DatabaseMetaData dbmt = connection.getMetaData();
            System.out.println("Banco conectado com sucesso");
        } catch (ClassNotFoundException erro) {
            System.out.println("Falha ao carregar o driver JDBC/ODBC." + erro);
            return false;
        } catch (SQLException erro) {
            System.out.println("Falha na conexao, comando sql = " + erro);
           
            return false;
        }
        return true;
    }


    
    

    
    public void insereItem (int id,String nome, String desc,double valor) {
       
        try {
            
            String sqldml = "INSERT INTO item (id,nome,descricao,valor) "
                    + "values (?,?,?,?)";
            
            int tipo1 = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            
            pstdados = connection.prepareStatement(sqldml, tipo1, concorrencia);
            
            pstdados.setInt(1, id);
            pstdados.setString(2, nome);
            pstdados.setString(3, desc);
            pstdados.setDouble(4, valor);
   
            int resposta = pstdados.executeUpdate();
            connection.commit();
            System.out.println("Resposta do P-Update = " + resposta);
            System.out.println("Item inserido com sucesso.");

        } catch (SQLException erro) {
            System.out.println("Erro ao inserir = " + erro);
        }
    }
  
}
