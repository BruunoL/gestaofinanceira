package dev.bruno.gestaofinanceira.entity.dao;

import dev.bruno.gestaofinanceira.entity.Buyer;
import dev.bruno.gestaofinanceira.entity.Payment;
import dev.bruno.gestaofinanceira.entity.Product;
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

public class PaymentDAO {

    public void createPayment(Payment payment, Product product, Buyer buyer) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO payments (buyer_id,"
                    + "payment_value,payment_type,qtde_itens,payment_date,product_id) VALUES (?,?,?,?,?,?)");

            preparedStatement.setInt(1, buyer.getId());
            preparedStatement.setDouble(2, payment.getPaymentValue());
            preparedStatement.setInt(3, payment.getPaymentType().getId());
            preparedStatement.setInt(4, payment.getQtdeItens());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date date = new Date();

            String dataformatada = sdf.format(date);

            preparedStatement.setString(5, dataformatada);

            preparedStatement.setInt(6, product.getId());

            preparedStatement.execute();

            System.out.println("Venda feita com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public void updatePayment(Payment payment, Buyer buyer) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE payments SET buyer_id = ?, "
                    + "payment_value = ?, payment_type = ?, qtde_itens = ?, product_id = ? WHERE payment_id = ?");

            preparedStatement.setInt(1, buyer.getId());
            preparedStatement.setDouble(2, payment.getPaymentValue());
            preparedStatement.setInt(3, payment.getPaymentType().getId());
            preparedStatement.setInt(4, payment.getQtdeItens());
            preparedStatement.setInt(6, payment.getProductId());
            preparedStatement.setInt(7, payment.getId());

            preparedStatement.execute();

            System.out.println("Pagamento do " + payment.getId() + " atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public void deletePayment(Payment payment) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM payments WHERE payment_id = '" + payment.getId() + "'");

            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(preparedStatement);
        }
    }

    public Payment readPayment(int id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        Payment payment = new Payment();

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_id = '" + id + "'");

            if (rs.next()) {
                payment.setId(id);
                payment.setBuyerId(rs.getInt("buyer_id"));
                payment.setPaymentValue(rs.getDouble("payment_value"));
                payment.setPaymentType(rs.getInt("payment_type"));
                payment.setQtdeItens(rs.getInt("qtde_itens"));
                payment.setDate(rs.getString("payment_date"));
                payment.setProductId(rs.getInt("product_id"));
            }

            System.out.println("Pagamento carregado com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return payment;
    }

    public List<Payment> findAllPayments() {
        List<Payment> paymentsList = new ArrayList<>();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT payment_id, buyer_id, payment_value, "
                    + "payment_type, qtde_itens, payment_date, product_id FROM payments;");

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt(1));
                payment.setBuyerId(rs.getInt(2));
                payment.setPaymentValue(rs.getDouble(3));
                payment.setPaymentType(rs.getInt(4));
                payment.setQtdeItens(rs.getInt(5));
                payment.setDate(rs.getString(6));
                payment.setProductId(rs.getInt(7));
                paymentsList.add(payment);
            }

            System.out.println("Lista criada com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return paymentsList;
    }

    public double getQtdeItensPayment(int productId, String searchType) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        int value = 0;

        switch (searchType) {
            case "Dia":
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    Date date = new Date();

                    String dataformatada = sdf.format(date);

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE product_id = '" + productId + "' AND payment_date LIKE '" + dataformatada + "%'");

                    while (rs.next()) {
                        value += rs.getInt("qtde_itens");
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

                switch (day) {
                    case 1:
                        value += calcDayByQtdeItens(dayMonth, productId);
                        break;
                    case 2:
                        int daysOfWeek = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId);
                        value += daysOfWeek;
                        break;
                    case 3:
                        int daysOfWeek2 = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId)
                                + calcDayByQtdeItens(dayMonth - 2, productId);
                        value += daysOfWeek2;
                        break;
                     case 4:
                        int daysOfWeek3 = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId)
                                + calcDayByQtdeItens(dayMonth - 2, productId)
                                + calcDayByQtdeItens(dayMonth - 3, productId);
                        value += daysOfWeek3;
                        break;
                    case 5:
                        int daysOfWeek4 = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId)
                                + calcDayByQtdeItens(dayMonth - 2, productId)
                                + calcDayByQtdeItens(dayMonth - 3, productId)
                                + calcDayByQtdeItens(dayMonth - 4, productId);
                        value += daysOfWeek4;
                        break;
                    case 6:
                        int daysOfWeek5 = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId)
                                + calcDayByQtdeItens(dayMonth - 2, productId) 
                                + calcDayByQtdeItens(dayMonth - 3, productId)
                                + calcDayByQtdeItens(dayMonth - 4, productId)
                                + calcDayByQtdeItens(dayMonth - 5, productId);
                        value += daysOfWeek5;
                        break;
                    case 7:
                        int daysOfWeek6 = calcDayByQtdeItens(dayMonth, productId) 
                                + calcDayByQtdeItens(dayMonth - 1, productId)
                                + calcDayByQtdeItens(dayMonth - 2, productId) 
                                + calcDayByQtdeItens(dayMonth - 3, productId)
                                + calcDayByQtdeItens(dayMonth - 4, productId)
                                + calcDayByQtdeItens(dayMonth - 5, productId)
                                + calcDayByQtdeItens(dayMonth - 6, productId);
                        value += daysOfWeek6;
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE product_id = '" + productId + "' AND payment_date LIKE '%/" + dataformatada + "%'");

                    while (rs.next()) {
                        value += rs.getInt("qtde_itens");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE product_id = '" + productId + "' AND payment_date LIKE '%/" + dataformatada + "%'");

                    while (rs.next()) {
                        value += rs.getInt("qtde_itens");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments;");

                    while (rs.next()) {
                        value += rs.getInt("qtde_itens");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
        }
        return value;
    }

    public double getValuePaymentByType(int type_id, String searchType) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        double value = 0;

        switch (searchType) {
            case "Dia":
                try {
                    connection = connectionFactory.getConnection();
                    statement = connection.createStatement();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
                    Date date = new Date();

                    String dataformatada = sdf.format(date);

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_type = '" + type_id + "' AND payment_date LIKE '" + dataformatada + "%';");

                    while (rs.next()) {
                        value += rs.getDouble("payment_value");
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

                switch (day) {
                    case 1:
                        value += calcDayByType(dayMonth, type_id);
                        break;
                    case 2:
                        double daysOfWeek = calcDayByType(dayMonth, type_id) 
                                + calcDayByType(dayMonth - 1, type_id);
                        value += daysOfWeek;
                        break;
                    case 3:
                        double daysOfWeek2 = calcDayByType(dayMonth, type_id) 
                                + calcDayByType(dayMonth - 1, type_id)
                                + calcDayByType(dayMonth - 2, type_id);
                        value += daysOfWeek2;
                        break;
                    case 4:
                        double daysOfWeek3 = calcDayByType(dayMonth, type_id)
                                + calcDayByType(dayMonth - 1, type_id)
                                + calcDayByType(dayMonth - 2, type_id) 
                                + calcDayByType(dayMonth - 3, type_id);
                        value += daysOfWeek3;
                        break;
                    case 5:
                        double daysOfWeek4 = calcDayByType(dayMonth, type_id)
                                + calcDayByType(dayMonth - 1, type_id)
                                + calcDayByType(dayMonth - 2, type_id)
                                + calcDayByType(dayMonth - 3, type_id)
                                + calcDayByType(dayMonth - 4, type_id);
                        value += daysOfWeek4;
                        break;
                    case 6:
                        double daysOfWeek5 = calcDayByType(dayMonth, type_id)
                                + calcDayByType(dayMonth - 1, type_id)
                                + calcDayByType(dayMonth - 2, type_id)
                                + calcDayByType(dayMonth - 3, type_id)
                                + calcDayByType(dayMonth - 4, type_id)
                                + calcDayByType(dayMonth - 5, type_id);
                        value += daysOfWeek5;
                        break;
                    case 7:
                        double daysOfWeek6 = calcDayByType(dayMonth, type_id)
                                + calcDayByType(dayMonth - 1, type_id)
                                + calcDayByType(dayMonth - 2, type_id)
                                + calcDayByType(dayMonth - 3, type_id)
                                + calcDayByType(dayMonth - 4, type_id)
                                + calcDayByType(dayMonth - 5, type_id)
                                + calcDayByType(dayMonth - 6, type_id);
                        value += daysOfWeek6;
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_type = '" + type_id + "' AND payment_date LIKE '%" + dataformatada + "%';");

                    while (rs.next()) {
                        value += rs.getDouble("payment_value");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_type = '" + type_id + "' AND payment_date LIKE '%/" + dataformatada + "%';");

                    while (rs.next()) {
                        value += rs.getDouble("payment_value");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_type = '" + type_id + "';");

                    while (rs.next()) {
                        value += rs.getDouble("payment_value");
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    connectionFactory.closeConnection(connection);
                    connectionFactory.closeStatement(statement);
                }
                break;
        }

        return value;
    }

    public String getBuyerNameById(int id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        String name = "Inexistente!";

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE buyer_id = '" + id + "';");

            while (rs.next()) {
                Buyer buyer = new BuyerDAO().readBuyer(id);
                name = buyer.getName();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return name;
    }

    public String getProductNameByID(int id) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;
        String name = "Inexistente";

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE product_id = '" + id + "';");

            while (rs.next()) {
                Product product = new ProductDAO().readProduct(id);
                name = product.getName();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }
        return name;
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_date LIKE '%" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("payment_value");
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
                int year = cal.get(Calendar.YEAR);
                
                switch (day) {
                    case 1:
                        somarSaldos += calcDay(dayMonth);
                        break;
                    case 2:
                        double daysOfWeek = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1);
                        somarSaldos += daysOfWeek;
                        break;
                    case 3:
                        double daysOfWeek2 = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1)
                                + calcDay(dayMonth - 2);
                        somarSaldos += daysOfWeek2;
                        break;
                    case 4:
                        double daysOfWeek3 = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1)
                                + calcDay(dayMonth - 2)
                                + calcDay(dayMonth - 3);
                        somarSaldos += daysOfWeek3;
                        break;
                    case 5:
                        double daysOfWeek4 = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1) 
                                + calcDay(dayMonth - 2) 
                                + calcDay(dayMonth - 3)
                                + calcDay(dayMonth - 4);
                        somarSaldos += daysOfWeek4;
                        break;
                    case 6:
                        double daysOfWeek5 = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1) 
                                + calcDay(dayMonth - 2) 
                                + calcDay(dayMonth - 3)
                                + calcDay(dayMonth - 4)
                                + calcDay(dayMonth - 5);
                        somarSaldos += daysOfWeek5;
                        break;
                    case 7:
                        double daysOfWeek6 = calcDay(dayMonth) 
                                + calcDay(dayMonth - 1) 
                                + calcDay(dayMonth - 2) 
                                + calcDay(dayMonth - 3)
                                + calcDay(dayMonth - 4)
                                + calcDay(dayMonth - 5)
                                + calcDay(dayMonth - 6);
                        somarSaldos += daysOfWeek6;
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_date LIKE '%" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("payment_value");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_date LIKE '%/" + dataformatada + "%'");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("payment_value");
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

                    ResultSet rs = statement.executeQuery("SELECT * FROM payments;");

                    while (rs.next()) {
                        somarSaldos += rs.getDouble("payment_value");
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

    private double calcDay(int day) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        double somarSaldos = 0;
        
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yy");
        Date date = new Date();

        String mesFormatado = mes.format(date);
        String anoFormatado = ano.format(date);
        
        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_date LIKE '" + day + "%' "
                    + "AND payment_date LIKE '%/"+anoFormatado+"%' AND payment_date LIKE '%/"+mesFormatado+"/%';");

            while (rs.next()) {
                somarSaldos += rs.getDouble("payment_value");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return somarSaldos;
    }

    private double calcDayByType(int day, int type_id) {
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

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE payment_type = '" + type_id + "' "
                    + "AND payment_date LIKE '" + day + "%' AND payment_date LIKE '%/"+anoFormatado+"%' AND payment_date LIKE '%/"+mesFormatado+"/%';");

            while (rs.next()) {
                value += rs.getDouble("payment_value");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return value;
    }

    private int calcDayByQtdeItens(int day, int productId) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = null;
        Statement statement = null;

        int qtdeItens = 0;
        
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat ano = new SimpleDateFormat("yy");
        Date date = new Date();

        String mesFormatado = mes.format(date);
        String anoFormatado = ano.format(date);

        try {
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM payments WHERE product_id = '" + productId + "' "
                    + "AND payment_date LIKE '" + day + "%' AND payment_date LIKE '%/"+anoFormatado+"%' AND payment_date LIKE '%/"+mesFormatado+"/%';");

            while (rs.next()) {
                qtdeItens += rs.getInt("qtde_itens");
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            connectionFactory.closeConnection(connection);
            connectionFactory.closeStatement(statement);
        }

        return qtdeItens;
    }

}
