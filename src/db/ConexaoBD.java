package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private static final String HOST = "oracle.fiap.com.br";
    private static final String PORT = "1521";
    private static final String SID = "ORCL";

    private static final String USER = "SEU_USUARIO";
    private static final String PASSWORD = "SUA_SENHA";

    private static final String URL =
            "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID;

    private Connection connection;

    private static ConexaoBD instancia;

    private ConexaoBD() {
    }

    public static ConexaoBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }

        return instancia;
    }

    public Connection conectar() throws SQLException {

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
        }

        return connection;
    }

    public void desconectar() throws SQLException {

        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    public boolean estaConectado() throws SQLException {
        return connection != null && !connection.isClosed();
    }
}