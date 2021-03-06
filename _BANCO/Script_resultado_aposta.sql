CREATE OR REPLACE FUNCTION sp_aposta_alterar_resultado(
    "P_SEQ_APOSTA" integer,
    "P_RESULTADO_APOSTA" character varying)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';   
 BEGIN 
 
  UPDATE aposta
  SET resultado_aposta = $2
  WHERE seq_aposta = $1;
  
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