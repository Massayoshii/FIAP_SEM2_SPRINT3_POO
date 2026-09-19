package main;

import dao.RelatorioPrioridadeDAO;
import dao.TrechoRodoviaDAO;
import db.ConexaoBD;
import model.RelatorioPrioridade;
import model.TrechoRodovia;
import service.GeradorRelatorio;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConexaoBD conexao =
                ConexaoBD.getInstancia();

        try {

            // ==========================================
            // 1. TESTE DE CONEXAO
            // ==========================================

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "        TESTE DE CONEXAO COM ORACLE"
            );

            System.out.println(
                    "=========================================="
            );

            conexao.conectar();

            System.out.println(
                    "Conexao realizada com sucesso!"
            );

            System.out.println(
                    "Banco conectado: "
                            + conexao.estaConectado()
            );


            // ==========================================
            // 2. INSTANCIANDO DAOs
            // ==========================================

            TrechoRodoviaDAO trechoDAO =
                    new TrechoRodoviaDAO();

            RelatorioPrioridadeDAO relatorioDAO =
                    new RelatorioPrioridadeDAO();


            // ==========================================
            // 3. INSERINDO TRECHOS
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "             INSERINDO TRECHOS"
            );

            System.out.println(
                    "=========================================="
            );

            TrechoRodovia trecho1 =
                    new TrechoRodovia(
                            10,
                            15,
                            30,
                            "umido"
                    );

            TrechoRodovia trecho2 =
                    new TrechoRodovia(
                            20,
                            25,
                            18,
                            "seco"
                    );

            TrechoRodovia trecho3 =
                    new TrechoRodovia(
                            30,
                            35,
                            8,
                            "seco"
                    );

            Long idTrecho1 =
                    trechoDAO.inserir(trecho1);

            Long idTrecho2 =
                    trechoDAO.inserir(trecho2);

            Long idTrecho3 =
                    trechoDAO.inserir(trecho3);

            System.out.println(
                    "Trecho 1 inserido. ID: "
                            + idTrecho1
            );

            System.out.println(
                    "Trecho 2 inserido. ID: "
                            + idTrecho2
            );

            System.out.println(
                    "Trecho 3 inserido. ID: "
                            + idTrecho3
            );


            // ==========================================
            // 4. LISTAR TRECHOS
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "          TRECHOS CADASTRADOS"
            );

            System.out.println(
                    "=========================================="
            );

            List<TrechoRodovia> trechos =
                    trechoDAO.listarTodas();

            for (TrechoRodovia trecho : trechos) {

                System.out.println(
                        "ID: "
                                + trecho.getId()
                                + " | "
                                + trecho
                );
            }


            // ==========================================
            // 5. BUSCAR TRECHO POR ID
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "          BUSCANDO TRECHO POR ID"
            );

            System.out.println(
                    "=========================================="
            );

            TrechoRodovia trechoEncontrado =
                    trechoDAO.buscarPorId(idTrecho1);

            if (trechoEncontrado != null) {

                System.out.println(
                        "Trecho encontrado:"
                );

                System.out.println(
                        trechoEncontrado
                );

            } else {

                System.out.println(
                        "Trecho nao encontrado."
                );
            }


            // ==========================================
            // 6. UPDATE TRECHO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "             ATUALIZANDO TRECHO"
            );

            System.out.println(
                    "=========================================="
            );

            trechoEncontrado.registrarCrescimento(10);

            boolean atualizacaoRealizada =
                    trechoDAO.atualizar(
                            trechoEncontrado
                    );

            System.out.println(
                    "Trecho atualizado? "
                            + atualizacaoRealizada
            );


            // ==========================================
            // 7. BUSCAR TRECHOS ATUALIZADOS DO BANCO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "       BUSCANDO TRECHOS DO BANCO"
            );

            System.out.println(
                    "=========================================="
            );

            TrechoRodovia trechoAtualizado =
                    trechoDAO.buscarPorId(idTrecho1);

            TrechoRodovia trecho2DoBanco =
                    trechoDAO.buscarPorId(idTrecho2);

            TrechoRodovia trecho3DoBanco =
                    trechoDAO.buscarPorId(idTrecho3);

            System.out.println(
                    "\nTrecho 1:"
            );

            System.out.println(
                    trechoAtualizado
            );

            System.out.println(
                    "\nTrecho 2:"
            );

            System.out.println(
                    trecho2DoBanco
            );

            System.out.println(
                    "\nTrecho 3:"
            );

            System.out.println(
                    trecho3DoBanco
            );


            // ==========================================
            // 8. GERAR RELATORIO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "             GERANDO RELATORIO"
            );

            System.out.println(
                    "=========================================="
            );

            GeradorRelatorio gerador =
                    new GeradorRelatorio();

            TrechoRodovia[] trechosParaRelatorio = {
                    trechoAtualizado,
                    trecho2DoBanco,
                    trecho3DoBanco
            };

            RelatorioPrioridade relatorio =
                    gerador.gerarRelatorio(
                            trechosParaRelatorio
                    );


            // ==========================================
            // 9. SALVAR RELATORIO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "          SALVANDO RELATORIO"
            );

            System.out.println(
                    "=========================================="
            );

            Long idRelatorio =
                    relatorioDAO.inserir(
                            relatorio
                    );

            System.out.println(
                    "Relatorio salvo com ID: "
                            + idRelatorio
            );


            // ==========================================
            // 10. BUSCAR RELATORIO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "          BUSCANDO RELATORIO"
            );

            System.out.println(
                    "=========================================="
            );

            RelatorioPrioridade relatorioEncontrado =
                    relatorioDAO.buscarPorId(
                            idRelatorio
                    );

            if (relatorioEncontrado != null) {

                System.out.println(
                        relatorioEncontrado
                );

            } else {

                System.out.println(
                        "Relatorio nao encontrado."
                );
            }


            // ==========================================
            // 11. UPDATE RELATORIO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "          ATUALIZANDO RELATORIO"
            );

            System.out.println(
                    "=========================================="
            );

            boolean relatorioAtualizado =
                    relatorioDAO.atualizar(
                            relatorioEncontrado
                    );

            System.out.println(
                    "Relatorio atualizado? "
                            + relatorioAtualizado
            );


            // ==========================================
            // 12. CONFIRMAR UPDATE DO RELATORIO
            // ==========================================

            RelatorioPrioridade relatorioAposUpdate =
                    relatorioDAO.buscarPorId(
                            idRelatorio
                    );

            System.out.println(
                    "\nRelatorio apos atualizacao:"
            );

            System.out.println(
                    relatorioAposUpdate
            );


            // ==========================================
            // 13. HISTORICO DE RELATORIOS
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "       HISTORICO DE RELATORIOS"
            );

            System.out.println(
                    "=========================================="
            );

            List<RelatorioPrioridade> relatorios =
                    relatorioDAO.listarTodas();

            for (RelatorioPrioridade item :
                    relatorios) {

                System.out.println(item);
            }


            // ==========================================
            // 14. DELETE TRECHO
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "             DELETANDO TRECHO"
            );

            System.out.println(
                    "=========================================="
            );

            boolean deletado =
                    trechoDAO.deletar(
                            idTrecho3
                    );

            System.out.println(
                    "Trecho deletado? "
                            + deletado
            );


            // ==========================================
            // 15. CONFIRMAR DELETE
            // ==========================================

            TrechoRodovia trechoDeletado =
                    trechoDAO.buscarPorId(
                            idTrecho3
                    );

            if (trechoDeletado == null) {

                System.out.println(
                        "Exclusao confirmada."
                );

            } else {

                System.out.println(
                        "Trecho ainda existe."
                );
            }


            // ==========================================
            // FINAL
            // ==========================================

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "        TESTE FINALIZADO COM SUCESSO"
            );

            System.out.println(
                    "=========================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "\n=========================================="
            );

            System.out.println(
                    "             ERRO NA EXECUCAO"
            );

            System.out.println(
                    "=========================================="
            );

            e.printStackTrace();

        } finally {

            try {

                conexao.desconectar();

                System.out.println(
                        "\nConexao encerrada."
                );

            } catch (Exception e) {

                System.out.println(
                        "Erro ao encerrar conexao."
                );

                e.printStackTrace();
            }
        }
    }
}