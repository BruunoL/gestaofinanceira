package dev.bruno.gestaofinanceira.entity.dao;

import dev.bruno.gestaofinanceira.entity.Shopping;
import dev.bruno.gestaofinanceira.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShoppingDAO {

    public void createBuy(Shopping shopping) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO shopping (buy_name, buy_value, qtde_itens,"
                    + "buy_type, buy_date, receiver_name) VALUES (?,?,?,?,?,?)");

            preparedStatement.setString(1, shopping.getName());
            preparedStatement.setDouble(2, shopping.getValue());
            preparedStatement.setInt(3, shopping.getQtdeItens());
            preparedStatement.setInt(4, shopping.getPaymentType().getId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date = new Date();

            String dataformatada = sdf.format(date);
            preparedStatement.setString(5, dataformatada);
            preparedStatement.setString(6, shopping.getReceiverName());
            preparedStatement.execute();

            System.out.println("Compra " + shopping.getName() + " feita com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public void updateBuy(Shopping shopping) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE shopping SET buy_name = ?, buy_value = ?, "
                    + "qtde_itens = ?, buy_type = ?, receiver_name = ? WHERE buy_id = ?");

            preparedStatement.setString(1, shopping.getName());
            preparedStatement.setDouble(2, shopping.getValue());
            preparedStatement.setInt(3, shopping.getQtdeItens());
            preparedStatement.setInt(4, shopping.getPaymentType().getId());
            preparedStatement.setString(5, shopping.getReceiverName());
            preparedStatement.setInt(6, shopping.getId());
            preparedStatement.execute();

            System.out.println("Compra " + shopping.getName() + " atualizada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public void deleteBuy(Shopping buy) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM shopping WHERE buy_id = '" + buy.getId() + "'");

            preparedStatement.execute();

            System.out.println("Compra " + buy.getId() + " deletada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public Shopping readBuy(int id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Shopping shopping = new Shopping();

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM shopping WHERE buy_id = '" + id + "'");

            if (rs.next()) {
                shopping.setId(id);
                shopping.setName(rs.getString("buy_name"));
                shopping.setValue(rs.getDouble("buy_value"));
                shopping.setQtdeIens(rs.getInt("qtde_itens"));
                shopping.setPaymentType(rs.getInt("buy_type"));
                shopping.setDate(rs.getString("buy_date"));
                shopping.setReceiverName(rs.getString("receiver_name"));
            }

            System.out.println("Compra carregada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return shopping;
    }

    public List<Shopping> findAllShoppings() {
        List<Shopping> shoppingList = new ArrayList<>();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT buy_id, buy_name,buy_value,qtde_itens,buy_type,buy_date"
                    + ",receiver_name FROM shopping;");

            while (rs.next()) {
                Shopping buy = new Shopping();

                buy.setId(rs.getInt(1));
                buy.setName(rs.getString(2));
                buy.setValue(rs.getDouble(3));
                buy.setQtdeIens(rs.getInt(4));
                buy.setPaymentType(rs.getInt(5));
                buy.setDate(rs.getString(6));
                buy.setReceiverName(rs.getString(7));

                shoppingList.add(buy);
            }

            System.out.println("Lista criada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return shoppingList;
    }

    public double getSaldo(String searchType) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        double somarSaldos = 0;

        switch (searchType) {
            case "Dia":
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    Date date = new Date();

                    String dataformatada = sdf.format(date);

                    ResultSet rs = statement.executeQuery("SELECT * FROM shopping WHERE buy_date LIKE '" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("buy_value");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
            case "Semana":
                Date dateT = new Date();

                Calendar cal = Calendar.getInstance();
                cal.setTime(dateT);
                int day = cal.get(Calendar.DAY_OF_WEEK);
                int dayMonth = cal.get(Calendar.DAY_OF_MONTH);

                switch(day) {
                    case 1:
                        somarSaldos += getSaldoByDay(dayMonth);
                        break;
                    case 2:
                        double total = getSaldoByDay(dayMonth) + getSaldoByDay(dayMonth - 1);
                        somarSaldos += total;
                        break;
                    case 3:
                        double total2 = getSaldoByDay(dayMonth) 
                                + getSaldoByDay(dayMonth - 1)
                                + getSaldoByDay(dayMonth - 2);
                        somarSaldos += total2;
                        break;
                    case 4:
                        double total3 = getSaldoByDay(dayMonth) 
                                + getSaldoByDay(dayMonth - 1)
                                + getSaldoByDay(dayMonth - 2)
                                + getSaldoByDay(dayMonth - 3);
                        somarSaldos += total3;
                        break;
                     case 5:
                        double total4 = getSaldoByDay(dayMonth) 
                                + getSaldoByDay(dayMonth - 1)
                                + getSaldoByDay(dayMonth - 2)
                                + getSaldoByDay(dayMonth - 3)
                                + getSaldoByDay(dayMonth - 4);
                        somarSaldos += total4;
                        break;
                    case 6:
                        double total5 = getSaldoByDay(dayMonth) 
                                + getSaldoByDay(dayMonth - 1)
                                + getSaldoByDay(dayMonth - 2)
                                + getSaldoByDay(dayMonth - 3)
                                + getSaldoByDay(dayMonth - 4)
                                + getSaldoByDay(dayMonth - 5);
                        somarSaldos += total5;
                        break;
                    case 7:
                        double total6 = getSaldoByDay(dayMonth) 
                                + getSaldoByDay(dayMonth - 1)
                                + getSaldoByDay(dayMonth - 2)
                                + getSaldoByDay(dayMonth - 3)
                                + getSaldoByDay(dayMonth - 4)
                                + getSaldoByDay(dayMonth - 5)
                                + getSaldoByDay(dayMonth - 6);
                        somarSaldos += total6;
                        break;
                }
                break;
            case "Mês":
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
                    Date date = new Date();

                    String dataformatada = sdf.format(date);

                    ResultSet rs = statement.executeQuery("SELECT * FROM shopping WHERE buy_date LIKE '%" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("buy_value");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
            case "Ano":
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    SimpleDateFormat sdf = new SimpleDateFormat("yy");
                    Date date = new Date();

                    String dataformatada = sdf.format(date);

                    ResultSet rs = statement.executeQuery("SELECT * FROM shopping WHERE buy_date LIKE '%/" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("buy_value");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
            default:
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery("SELECT * FROM shopping;");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("buy_value");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
        }
        return somarSaldos;
    }

    private double getSaldoByDay(int day) {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        double value = 0;
        
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yy");
        Date date = new Date();

        String mesFormatado = mes.format(date);
        String anoFormatado = ano.format(date);

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM shopping WHERE buy_date LIKE '" + day + "%'"
                    + " AND buy_date LIKE '%/"+mesFormatado+"/%' AND buy_date LIKE '%/"+anoFormatado+"%';");

            while (rs.next()) {
                value += rs.getDouble("buy_value");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return value;
    }

}
