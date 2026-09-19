package model;

import java.time.LocalDateTime;

public class RelatorioPrioridade {

    private Long id;
    private int quantidadePrioridadeAlta;
    private int quantidadePrioridadeMedia;
    private int quantidadeSemIntervencao;
    private String resumo;
    private LocalDateTime dataGeracao;

    public RelatorioPrioridade(
            int quantidadePrioridadeAlta,
            int quantidadePrioridadeMedia,
            int quantidadeSemIntervencao,
            String resumo,
            LocalDateTime dataGeracao) {

        this.quantidadePrioridadeAlta = quantidadePrioridadeAlta;
        this.quantidadePrioridadeMedia = quantidadePrioridadeMedia;
        this.quantidadeSemIntervencao = quantidadeSemIntervencao;
        this.resumo = resumo;
        this.dataGeracao = dataGeracao;
    }

    public RelatorioPrioridade(
            Long id,
            int quantidadePrioridadeAlta,
            int quantidadePrioridadeMedia,
            int quantidadeSemIntervencao,
            String resumo,
            LocalDateTime dataGeracao) {

        this.id = id;
        this.quantidadePrioridadeAlta = quantidadePrioridadeAlta;
        this.quantidadePrioridadeMedia = quantidadePrioridadeMedia;
        this.quantidadeSemIntervencao = quantidadeSemIntervencao;
        this.resumo = resumo;
        this.dataGeracao = dataGeracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadePrioridadeAlta() {
        return quantidadePrioridadeAlta;
    }

    public int getQuantidadePrioridadeMedia() {
        return quantidadePrioridadeMedia;
    }

    public int getQuantidadeSemIntervencao() {
        return quantidadeSemIntervencao;
    }

    public String getResumo() {
        return resumo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    @Override
    public String toString() {
        return "Relatório ID: " + id
                + " | Alta: " + quantidadePrioridadeAlta
                + " | Média: " + quantidadePrioridadeMedia
                + " | Sem intervenção: " + quantidadeSemIntervencao
                + " | Data: " + dataGeracao
                + " | Resumo: " + resumo;
    }
}