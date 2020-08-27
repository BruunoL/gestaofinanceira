 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.bruno.gestaofinanceira.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author bruno
 */
public class ConnectionFactory {
    
    protected static final String TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS `products` "
            + "(product_id INT auto_increment, product_name VARCHAR(15) not null, product_price DOUBLE not null, "
            + "product_profit DOUBLE not null, "
            + "PRIMARY KEY (product_id));";
   
    protected static final String TABLE_BUYERS = "CREATE TABLE IF NOT EXISTS `buyers` "
            + "(buyer_id INT auto_increment, buyer_name VARCHAR(100) not null, buyer_cnpj VARCHAR(30), "
            + "PRIMARY KEY (buyer_id));";
    
    protected static final String TABLE_PAYMENTS = "CREATE TABLE IF NOT EXISTS `payments` "
            + "(payment_id INT auto_increment not null, buyer_id INT, payment_value DOUBLE not null, "
            + "payment_type TINYINT not null, qtde_itens TINYINT not null, payment_date VARCHAR(20) not null, product_id INT, "
            + "PRIMARY KEY (payment_id), FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE SET NULL, "
            + "FOREIGN KEY (`buyer_id`) REFERENCES `buyers`(`buyer_id`) ON DELETE SET NULL);";
    
     protected static final String TABLE_SHOPPING = "CREATE TABLE IF NOT EXISTS `shopping` "
            + "(buy_id INT auto_increment not null, buy_name VARCHAR(50) not null, buy_value DOUBLE not null, "
            + "qtde_itens TINYINT not null, buy_type TINYINT not null, buy_date VARCHAR(20) not null, receiver_name VARCHAR(50) not null, "
            + "PRIMARY KEY (buy_id));";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/gestaofinanceira", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public boolean initialize() {
        Connection connection = null;
        Statement createTableStatement = null;
        
        try {
            connection = getConnection();
            System.out.println("Conexao estabelecida com sucesso!");
            
            createTableStatement = connection.createStatement();
            System.out.println("Criando tabela `products`");
            createTableStatement.executeUpdate(TABLE_PRODUCTS);

            System.out.println("Criando tabela `buyers`");
            createTableStatement.executeUpdate(TABLE_BUYERS);
            
            System.out.println("Criando tabela `payments`");
            createTableStatement.executeUpdate(TABLE_PAYMENTS);
            
            System.out.println("Criando tabela `shopping`");
            createTableStatement.executeUpdate(TABLE_SHOPPING);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            closeConnection(connection);
            closeStatement(createTableStatement);
        }
    }
    
    public void closeConnection(Connection statement) {
        if (statement == null) {
            return;
        } try {
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void closeStatement(Statement statement) {
        if (statement == null) {
            return;
		}
	try {
            statement.close();
	} catch (SQLException e) {
	}
    }
}