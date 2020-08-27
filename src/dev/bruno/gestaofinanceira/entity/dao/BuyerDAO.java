package dev.bruno.gestaofinanceira.entity.dao;

import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Product;
import dev.bruno.gestaofinanceira.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BuyerDAO {
    
    public void createBuyer(Buyer buyer) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO buyers (buyer_name, buyer_cnpj) "
                    + "values (?,?)");

            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getCnpj());
            preparedStatement.execute();

            System.out.println("Comprador " +buyer.getName()+ " criado com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }    
    }
    
    public void updateBuyer(Buyer buyer){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE buyers SET buyer_name = ?, buyer_cnpj = ? WHERE buyer_id = ?");

            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getCnpj());
            preparedStatement.setInt(3, buyer.getId());
            preparedStatement.execute();

            System.out.println("Dados do comprador " +buyer.getName()+ " atualizados com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }                
    }
    
    public void deleteBuyer(Buyer buyer) {
       ConnectionFactory connectionFactory = new ConnectionFactory();
       Connection connection = null;
       PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM buyers WHERE buyer_id = '"+buyer.getId()+"'");

            preparedStatement.execute();
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }
    
    public Buyer readBuyer(int id){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Buyer buyer = new Buyer();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM buyers WHERE buyer_id = '"+id+"'");
            
            if(rs.next()) {
                buyer.setId(id);
                buyer.setName(rs.getString("buyer_name"));
                buyer.setCnpj(rs.getString("buyer_cnpj"));
            }
            
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return buyer;
    }
    
    public Buyer readBuyerByName(String name) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Buyer buyer = new Buyer();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM buyers WHERE buyer_name = '"+name+"'");
            
            if(rs.next()) {
                buyer.setId(rs.getInt("buyer_id"));
                buyer.setName(rs.getString("buyer_name"));
                buyer.setCnpj(rs.getString("buyer_cnpj"));
            }
            

            System.out.println("Dados do comprador lidos com sucesso!" );
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return buyer;
    }
    
    public List<Buyer> findAllBuyers(){
        List<Buyer> buyersList = new ArrayList<>();
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT buyer_id, buyer_name, buyer_cnpj FROM buyers;");
            
            while(rs.next()) {
                Buyer buyer = new Buyer();
                buyer.setId(rs.getInt(1));
                buyer.setName(rs.getString(2));
                buyer.setCnpj(rs.getString(3));
                buyersList.add(buyer);
            }

            System.out.println("Compradores carregados com sucesso !");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }    
        
        return buyersList;
    }
    
    public boolean existsClientByName(String name) {
         boolean exists = false;
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Buyer buyer = new Buyer();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM buyers WHERE buyer_name = '"+name+"'");
            
            if(rs.next()) {
                exists = true;
            }
            
            System.out.println("Dados do comprador lidos com sucesso!" );
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return exists;
    }
    
    public boolean existsCpnj(String cnpj) {
        boolean exists = false;
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Buyer buyer = new Buyer();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM buyers WHERE buyer_cnpj = '"+cnpj+"'");
            
            if(rs.next()) {
                exists = true;
            }
            
            System.out.println("Dados do comprador lidos com sucesso!" );
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return exists;
    }
    
    public void insertRowsInTable(JTable table){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM buyers;");
            
            int col = rs.getMetaData().getColumnCount();
            
            while(rs.next()) {
                Object[] rows = new Object[col];
                for (int i = 1; i <= col; i++) {
                    rows[i-1] = rs.getObject(i);
                }
                ((DefaultTableModel)table.getModel()).insertRow(rs.getRow() -1, rows);
            }

        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }    
    }
    
}