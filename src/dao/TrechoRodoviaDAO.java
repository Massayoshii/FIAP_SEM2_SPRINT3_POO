package dao;

import db.ConexaoBD;
import model.TrechoRodovia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrechoRodoviaDAO {

    private static final String SQL_INSERT =
            "INSERT INTO TRECHO_RODOVIA " +
                    "(QUILOMETRO_INICIAL, QUILOMETRO_FINAL, " +
                    "NIVEL_VEGETACAO_CM, TIPO_CLIMA) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID =
            "SELECT ID, QUILOMETRO_INICIAL, QUILOMETRO_FINAL, " +
                    "NIVEL_VEGETACAO_CM, TIPO_CLIMA " +
                    "FROM TRECHO_RODOVIA " +
                    "WHERE ID = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT ID, QUILOMETRO_INICIAL, QUILOMETRO_FINAL, " +
                    "NIVEL_VEGETACAO_CM, TIPO_CLIMA " +
                    "FROM TRECHO_RODOVIA " +
                    "ORDER BY ID";

    private static final String SQL_UPDATE =
            "UPDATE TRECHO_RODOVIA SET " +
                    "QUILOMETRO_INICIAL = ?, " +
                    "QUILOMETRO_FINAL = ?, " +
                    "NIVEL_VEGETACAO_CM = ?, " +
                    "TIPO_CLIMA = ? " +
                    "WHERE ID = ?";

    private static final String SQL_DELETE =
            "DELETE FROM TRECHO_RODOVIA WHERE ID = ?";


    public Long inserir(TrechoRodovia trecho)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_INSERT,
                             new String[]{"ID"})) {

            statement.setDouble(
                    1,
                    trecho.getQuilometroInicial()
            );

            statement.setDouble(
                    2,
                    trecho.getQuilometroFinal()
            );

            statement.setDouble(
                    3,
                    trecho.getNivelVegetacaoCm()
            );

            statement.setString(
                    4,
                    trecho.getTipoClima()
            );

            statement.executeUpdate();

            try (ResultSet resultSet =
                         statement.getGeneratedKeys()) {

                if (resultSet.next()) {

                    Long id = resultSet.getLong(1);

                    trecho.setId(id);

                    return id;
                }
            }
        }

        throw new SQLException(
                "Nao foi possivel obter o ID gerado."
        );
    }


    public TrechoRodovia buscarPorId(Long id)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_SELECT_BY_ID)) {

            statement.setLong(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return mapearTrecho(resultSet);
                }
            }
        }

        return null;
    }

    public List<TrechoRodovia> listarTodas()
            throws SQLException {

        List<TrechoRodovia> trechos =
                new ArrayList<>();

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_SELECT_ALL);

             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                trechos.add(
                        mapearTrecho(resultSet)
                );
            }
        }

        return trechos;
    }

    public boolean atualizar(TrechoRodovia trecho)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_UPDATE)) {

            statement.setDouble(
                    1,
                    trecho.getQuilometroInicial()
            );

            statement.setDouble(
                    2,
                    trecho.getQuilometroFinal()
            );

            statement.setDouble(
                    3,
                    trecho.getNivelVegetacaoCm()
            );

            statement.setString(
                    4,
                    trecho.getTipoClima()
            );

            statement.setLong(
                    5,
                    trecho.getId()
            );

            int linhasAfetadas =
                    statement.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

    public boolean deletar(Long id)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_DELETE)) {

            statement.setLong(1, id);

            int linhasAfetadas =
                    statement.executeUpdate();

            return linhasAfetadas > 0;
        }
    }


    private TrechoRodovia mapearTrecho(
            ResultSet resultSet
    ) throws SQLException {

        return new TrechoRodovia(
                resultSet.getLong("ID"),
                resultSet.getDouble("QUILOMETRO_INICIAL"),
                resultSet.getDouble("QUILOMETRO_FINAL"),
                resultSet.getDouble("NIVEL_VEGETACAO_CM"),
                resultSet.getString("TIPO_CLIMA")
        );
    }
}
