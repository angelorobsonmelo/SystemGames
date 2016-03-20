CREATE TABLE resultado_jogo
(
  seq_resultado_jogo serial NOT NULL,
  cod_jogo integer,
  resultado_jogo_casa integer,
  resultado_jogo_fora integer,
  CONSTRAINT resultado_jogo_pkey PRIMARY KEY (seq_resultado_jogo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE resultado_jogo
  OWNER TO postgres;



CREATE OR REPLACE FUNCTION sp_resultado_jogo_inserir(
"P_COD_JOGO" integer,
"P_RESULTADO_CASA" integer,
 "P_RESULTADO_FORA" integer)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO resultado_jogo (cod_jogo, resultado_jogo_casa, resultado_jogo_fora) VALUES ($1, $2, $3);

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
  
  
  
  
  CREATE OR REPLACE FUNCTION sp_resultado_jogo_atualizar(
"P_SEQ_RESULTADO_JOGO" integer,
"P_RESULTADO_CASA" integer,
 "P_RESULTADO_FORA" integer)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 UPDATE resultado_jogo SET resultado_jogo_casa = $2, resultado_jogo_fora = $3 WHERE seq_resultado_jogo = $1;

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
  
  
  CREATE OR REPLACE FUNCTION sp_resultado_jogo_remover(
"P_SEQ_RESULTADO_JOGO" integer)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 DELETE FROM resultado_jogo  WHERE seq_resultado_jogo = $1;

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