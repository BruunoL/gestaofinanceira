package dev.bruno.gestaofinanceira.entity.dao;

import dev.bruno.gestaofinanceira.factory.ConnectionFactory;
import dev.bruno.gestaofinanceira.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    
    public void createProduct(Product product) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO products (product_name, product_price, product_profit) "
                    + "values (?,?,?)");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getProductionProfit());
            preparedStatement.execute();

            System.out.println("Produto " +product.getName()+ " criado com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }    
    }
    
    public void updateProduct(Product product){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE products SET product_name = ?, product_price = ?, product_profit = ? WHERE product_id = ?");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setDouble(3, product.getProductionProfit());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.execute();

            System.out.println("Produto " +product.getName()+ " atualizado com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }                
    }
    
    public void deleteProduct(Product product) {
       ConnectionFactory connectionFactory = new ConnectionFactory();
       Connection connection = null;
       PreparedStatement preparedStatement = null;
        
        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id = '"+product.getId()+"'");

            preparedStatement.execute();

            System.out.println("Produto "+product+" deletado com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }
    
    public Product readProduct(int id){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Product product = new Product();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE product_id = '"+id+"'");
            
            if(rs.next()) {
                product.setId(id);
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setProductionProfit(rs.getDouble("product_profit"));
            }
            

            System.out.println("Produto carregado com sucesso!" );
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return product;
    }
    
    public Product readProductByName(String name){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Product product = new Product();
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE product_name = '"+name+"'");
            
            if(rs.next()) {
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setProductionProfit(rs.getDouble("product_profit"));
            }
            

            System.out.println("Produto carregado com sucesso!" );
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return product;
    }
    
    public List<Product> findAllProducts(){
        List<Product> productsList = new ArrayList<>();
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT product_id, product_name, product_price, product_profit FROM products;");
            
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setProductionProfit(rs.getDouble(4));
                productsList.add(product);
            }

            System.out.println("Lista criada com sucesso!");
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }    
        
        return productsList;
    }
    
    public boolean existsProductName(String name) {
        boolean exists = false;
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
                        
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE product_name = '"+name+"'");
            
            if(rs.next()) {
                exists = true;
            }
            
        } catch(SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return exists;
    }
    
}