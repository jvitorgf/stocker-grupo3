/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocker;

import java.sql.*;
import javax.swing.JOptionPane;

//teste
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
    //teste 123

    public boolean Banco(){
 
        try {
            String usuario = "admin";
            String senha = "1234";

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


    
    

    
    public int insereItem (int id,String nome, String desc,double valor) {
       int reposta = 0;
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
   
            reposta = pstdados.executeUpdate();
            connection.commit();
            System.out.println("Reposta do P-Update = " + reposta);
            System.out.println("Item inserido com sucesso.");

        } catch (SQLException erro) {
            System.out.println("Erro ao inserir = " + erro);
        }
        return reposta;
    }
    
    public int editaItem(int idb,int id,String nome, String desc,double valor){
    
        int reposta = 0;
        try {
            String sqldml = "UPDATE item SET nome = '"+nome+"',id = "+id+
                    ",descricao = '"+desc+"',valor = "+valor+" "
                    + "WHERE id = '"+idb+"'";
            
            
            int tipo1 = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqldml, tipo1, concorrencia);
            
           
            reposta = pstdados.executeUpdate();
            connection.commit();
            System.out.println("Reposta do P-Update = " + reposta);

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar = " + erro);
        }
        return reposta;
    }
    
    
    
    public int excluiItem(int idb){
    
        int reposta = 0;
        try {
            String sqldml = "DELETE FROM item"+" WHERE id = '"+idb+"'";
            
            
            int tipo1 = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqldml, tipo1, concorrencia);
            
           
            reposta = pstdados.executeUpdate();
            connection.commit();
            System.out.println("Reposta do P-Update = " + reposta);

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar = " + erro);
        }
        return reposta;
    }
    
    
    public String[] consultaItem(int idb) {
        try {

            String sqldml = "SELECT * FROM item "
                    + "WHERE id = '" +idb+ "'";

            int tipo1 = ResultSet.TYPE_SCROLL_SENSITIVE;
            int concorrencia = ResultSet.CONCUR_UPDATABLE;
            pstdados = connection.prepareStatement(sqldml, tipo1, concorrencia);
            ResultSet ts = pstdados.executeQuery();
            ts.last();
            String[] a = new String[4];
         
            for (int i = 0; i < 4; i++) {
                a[i] = new String();
                a[i] = ts.getString(i+1);
    
      
            }
            
            return a;

        } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro: item não existe !");
        }
        return null;

    }
  
}
