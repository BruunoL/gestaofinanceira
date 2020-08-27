 package dev.bruno.gestaofinanceira;

import dev.bruno.gestaofinanceira.factory.ConnectionFactory;
import dev.bruno.gestaofinanceira.interfaces.MainFrame;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class GestaoFinanceira {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory connection = new ConnectionFactory();
        if (!connection.initialize()) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer a conexão com o banco de dados.", "Erro ao abrir o progama", JOptionPane.ERROR_MESSAGE);
            return;
        } 

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    
    //FAZER OS DOIS PAINEIS DE ALTERAR
    //FAZER A PARTE DO CHECK GERAL DOS DADOS(TODOS OS ANOS)
    //FAZER AS TABELAAS POR DIA/MES/ANO
}