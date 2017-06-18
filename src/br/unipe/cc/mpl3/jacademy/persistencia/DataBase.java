package br.unipe.cc.mpl3.jacademy.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * Classe para a conexão com a base de dados em MySQL
 *
 * @author Nitai Charan
 */
public class DataBase implements IDataBase {
    private Connection connection;
    private Statement statement;
    FileInputStream file;
    public DataBase(){
        Properties properties = new Properties();
        try {
            file =new FileInputStream("config/dataBase.properties");
            properties.load(file);
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://" + properties.getProperty("endereco") + ":" + properties.getProperty("porta") + "/" + properties.getProperty("database"), properties.getProperty("usuario"), properties.getProperty("senha"));
           statement = connection.createStatement();
           
           file.close();
           properties.clone();
        } catch (FileNotFoundException ex) {
            System.out.println("Data Base Properties file not found");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Erro: properties full");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Connection of Data Base");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Data Base Driver Manager file not found");
            ex.printStackTrace();
        }finally{
        }
        
        
    } 
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Statement getStatement() {
        return null;
    }

    @Override
    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

}
