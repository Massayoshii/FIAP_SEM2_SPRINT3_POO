package dao;

import db.ConexaoBD;
import model.RelatorioPrioridade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RelatorioPrioridadeDAO {

    private static final String SQL_INSERT =
            "INSERT INTO RELATORIO_PRIORIDADE " +
                    "(QT_PRIORIDADE_ALTA, QT_PRIORIDADE_MEDIA, " +
                    "QT_SEM_INTERVENCAO, RESUMO, DATA_GERACAO) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_BY_ID =
            "SELECT ID, QT_PRIORIDADE_ALTA, " +
                    "QT_PRIORIDADE_MEDIA, QT_SEM_INTERVENCAO, " +
                    "RESUMO, DATA_GERACAO " +
                    "FROM RELATORIO_PRIORIDADE " +
                    "WHERE ID = ?";

    private static final String SQL_SELECT_ALL =
            "SELECT ID, QT_PRIORIDADE_ALTA, " +
                    "QT_PRIORIDADE_MEDIA, QT_SEM_INTERVENCAO, " +
                    "RESUMO, DATA_GERACAO " +
                    "FROM RELATORIO_PRIORIDADE " +
                    "ORDER BY DATA_GERACAO DESC";

    private static final String SQL_UPDATE =
            "UPDATE RELATORIO_PRIORIDADE SET " +
                    "QT_PRIORIDADE_ALTA = ?, " +
                    "QT_PRIORIDADE_MEDIA = ?, " +
                    "QT_SEM_INTERVENCAO = ?, " +
                    "RESUMO = ?, " +
                    "DATA_GERACAO = ? " +
                    "WHERE ID = ?";

    private static final String SQL_DELETE =
            "DELETE FROM RELATORIO_PRIORIDADE " +
                    "WHERE ID = ?";


    // ==========================================
    // INSERT
    // ==========================================

    public Long inserir(RelatorioPrioridade relatorio)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_INSERT,
                             new String[]{"ID"})) {

            statement.setInt(
                    1,
                    relatorio.getQuantidadePrioridadeAlta()
            );

            statement.setInt(
                    2,
                    relatorio.getQuantidadePrioridadeMedia()
            );

            statement.setInt(
                    3,
                    relatorio.getQuantidadeSemIntervencao()
            );

            statement.setString(
                    4,
                    relatorio.getResumo()
            );

            statement.setTimestamp(
                    5,
                    Timestamp.valueOf(
                            relatorio.getDataGeracao()
                    )
            );

            statement.executeUpdate();

            try (ResultSet resultSet =
                         statement.getGeneratedKeys()) {

                if (resultSet.next()) {

                    Long id = resultSet.getLong(1);

                    relatorio.setId(id);

                    return id;
                }
            }
        }

        throw new SQLException(
                "Nao foi possivel obter o ID do relatorio."
        );
    }


    // ==========================================
    // BUSCAR POR ID
    // ==========================================

    public RelatorioPrioridade buscarPorId(Long id)
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

                    return mapearRelatorio(resultSet);
                }
            }
        }

        return null;
    }


    // ==========================================
    // LISTAR TODOS
    // ==========================================

    public List<RelatorioPrioridade> listarTodas()
            throws SQLException {

        List<RelatorioPrioridade> relatorios =
                new ArrayList<>();

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(
                             SQL_SELECT_ALL);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                RelatorioPrioridade relatorio =
                        mapearRelatorio(resultSet);

                relatorios.add(relatorio);
            }
        }

        return relatorios;
    }


    // ==========================================
    // UPDATE
    // ==========================================

    public boolean atualizar(RelatorioPrioridade relatorio)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_UPDATE)) {

            statement.setInt(
                    1,
                    relatorio.getQuantidadePrioridadeAlta()
            );

            statement.setInt(
                    2,
                    relatorio.getQuantidadePrioridadeMedia()
            );

            statement.setInt(
                    3,
                    relatorio.getQuantidadeSemIntervencao()
            );

            statement.setString(
                    4,
                    relatorio.getResumo()
            );

            statement.setTimestamp(
                    5,
                    Timestamp.valueOf(
                            relatorio.getDataGeracao()
                    )
            );

            statement.setLong(
                    6,
                    relatorio.getId()
            );

            int linhasAfetadas =
                    statement.executeUpdate();

            return linhasAfetadas > 0;
        }
    }


    // ==========================================
    // DELETE
    // ==========================================

    public boolean deletar(Long id)
            throws SQLException {

        Connection connection =
                ConexaoBD.getInstancia().conectar();

        try (PreparedStatement statement =
                     connection.prepareStatement(SQL_DELETE)) {

            statement.setLong(1, id);

            int linhasAfetadas =
                    statement.executeUpdate();

            return linhasAfetadas > 0;
        }
    }


    // ==========================================
    // MAPEAMENTO
    // ==========================================

    private RelatorioPrioridade mapearRelatorio(
            ResultSet resultSet
    ) throws SQLException {

        return new RelatorioPrioridade(
                resultSet.getLong("ID"),

                resultSet.getInt(
                        "QT_PRIORIDADE_ALTA"
                ),

                resultSet.getInt(
                        "QT_PRIORIDADE_MEDIA"
                ),

                resultSet.getInt(
                        "QT_SEM_INTERVENCAO"
                ),

                resultSet.getString(
                        "RESUMO"
                ),

                resultSet.getTimestamp(
                                "DATA_GERACAO"
                        )
                        .toLocalDateTime()
        );
    }
}