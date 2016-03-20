CREATE OR REPLACE FUNCTION sp_jogo_buscar_todos(OUT "P_CS_GERAL" refcursor)
  RETURNS refcursor AS
$BODY$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT
J.seq_jogo, J.jogo, J.cod_campeonato, J.cod_esporte, J.data_jogo, J.hora_inicial_jogo,
C.nome_campeonato,
E.nome_esporte,
CJ.seq_configuracao_jogo, CJ.finalizar_automaticamente, CJ.jogo_finalizado,
LA.seq_limite_aposta, LA.casa, LA.empate, LA.fora, LA.gol_e_meio, LA.dupla_chance, LA.ambos, LA.limite_casa, LA.limite_empate, LA.limite_fora, LA.limite_gol_e_meio, LA.limite_dupla_chance, LA.limite_individual, LA.limite_ambos, 
RJ.seq_resultado_jogo, RJ.resultado_jogo_casa, RJ.resultado_jogo_fora
FROM
jogo J
INNER JOIN campeonato C ON C.seq_campeonato = J.cod_campeonato
INNER JOIN esporte E ON E.seq_esporte = J.cod_esporte
INNER JOIN configuracao_jogo CJ ON CJ.COD_JOGO  = J.SEQ_JOGO
INNER JOIN limite_aposta LA ON LA.COD_JOGO = J.SEQ_JOGO
INNER JOIN resultado_jogo RJ ON RJ.cod_jogo = J.SEQ_JOGO;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
  
  
  CREATE OR REPLACE FUNCTION sp_jogo_remover(
    "P_SEQ_JOGO" integer)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 DELETE FROM jogo WHERE seq_jogo = $1;

 P_MSG_RETORNO = 'OK';
 RETURN P_MSG_RETORNO;
 EXCEPTION 
 WHEN UNIQUE_VIOLATION THEN
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO;
 WHEN INTEGRITY_CONSTRAINT_VIOLATION THEN 
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO; 
 END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
  
  
  CREATE OR REPLACE FUNCTION sp_jogo_inserir(
    "P_JOGO" character varying,
    "P_COD_CAMPIONATO" integer,
    "P_COD_ESPORTE" integer,
    "P_DATA_JOGO" date,
    "P_HORA_INCIAL_JOGO" character varying)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 P_SEQUENCIAL_JOGO INTEGER = NULL;
 BEGIN 
 
 INSERT INTO jogo (JOGO, COD_CAMPEONATO, COD_ESPORTE, DATA_JOGO, HORA_INICIAL_JOGO) VALUES ($1, $2, $3, $4, $5);

 P_SEQUENCIAL_JOGO = MAX(seq_jogo) FROM jogo; 

 INSERT INTO resultado_jogo (cod_jogo) VALUES (P_SEQUENCIAL_JOGO);

 

 P_MSG_RETORNO = 'OK';
 RETURN P_MSG_RETORNO;
 EXCEPTION 
 WHEN UNIQUE_VIOLATION THEN
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO;
 WHEN INTEGRITY_CONSTRAINT_VIOLATION THEN 
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO; 
 END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;