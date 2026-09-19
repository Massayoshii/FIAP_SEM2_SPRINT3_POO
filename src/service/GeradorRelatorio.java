package service;

import model.IntervencaoOperacional;
import model.Pulverizacao;
import model.RelatorioPrioridade;
import model.RocadaMecanizada;
import model.TrechoRodovia;

import java.time.LocalDateTime;

public class GeradorRelatorio {

    public RelatorioPrioridade gerarRelatorio(
            TrechoRodovia[] trechos) {

        int quantidadePrioridadeAlta = 0;
        int quantidadePrioridadeMedia = 0;
        int quantidadeSemIntervencao = 0;

        System.out.println("\n=== RELATORIO DE PRIORIDADE ===");

        for (TrechoRodovia trecho : trechos) {

            System.out.println("\n" + trecho);

            if (trecho.getNivelVegetacaoCm() >= 25) {

                quantidadePrioridadeAlta++;

                System.out.println(
                        "PRIORIDADE ALTA -> Rocada mecanizada"
                );

                IntervencaoOperacional intervencao =
                        new RocadaMecanizada();

                intervencao.executarServico();

            } else if (trecho.getNivelVegetacaoCm() >= 12) {

                quantidadePrioridadeMedia++;

                System.out.println(
                        "PRIORIDADE MEDIA -> Pulverizacao"
                );

                IntervencaoOperacional intervencao =
                        new Pulverizacao();

                intervencao.executarServico();

            } else {

                quantidadeSemIntervencao++;

                System.out.println(
                        "Sem necessidade de intervencao"
                );
            }
        }

        String resumo =
                "Relatorio com "
                        + quantidadePrioridadeAlta
                        + " prioridade(s) alta(s), "
                        + quantidadePrioridadeMedia
                        + " prioridade(s) media(s) e "
                        + quantidadeSemIntervencao
                        + " trecho(s) sem intervencao.";

        return new RelatorioPrioridade(
                quantidadePrioridadeAlta,
                quantidadePrioridadeMedia,
                quantidadeSemIntervencao,
                resumo,
                LocalDateTime.now()
        );
    }
}