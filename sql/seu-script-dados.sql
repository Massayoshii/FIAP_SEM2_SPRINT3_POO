INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             10,
             15,
             30,
             'umido'
         );

INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             20,
             25,
             18,
             'seco'
         );

INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             30,
             35,
             8,
             'umido'
         );

INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             40,
             48,
             25,
             'seco'
         );

INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             50,
             57,
             12,
             'umido'
         );

INSERT INTO TRECHO_RODOVIA (
    QUILOMETRO_INICIAL,
    QUILOMETRO_FINAL,
    NIVEL_VEGETACAO_CM,
    TIPO_CLIMA
) VALUES (
             60,
             67,
             3,
             'seco'
         );

INSERT INTO RELATORIO_PRIORIDADE (
    QT_PRIORIDADE_ALTA,
    QT_PRIORIDADE_MEDIA,
    QT_SEM_INTERVENCAO,
    RESUMO,
    DATA_GERACAO
) VALUES (
             2,
             2,
             2,
             'Relatorio inicial: 2 trechos com prioridade alta, 2 com prioridade media e 2 sem necessidade de intervencao.',
             CURRENT_TIMESTAMP
         );

INSERT INTO RELATORIO_PRIORIDADE (
    QT_PRIORIDADE_ALTA,
    QT_PRIORIDADE_MEDIA,
    QT_SEM_INTERVENCAO,
    RESUMO,
    DATA_GERACAO
) VALUES (
             1,
             3,
             2,
             'Segundo relatorio: 1 trecho com prioridade alta, 3 com prioridade media e 2 sem necessidade de intervencao.',
             CURRENT_TIMESTAMP
         );

COMMIT;