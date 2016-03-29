CREATE OR REPLACE FUNCTION public.sp_jogo_buscar_por_params (
  "P_COD_CAMPEONATO" integer,
  out "P_CS_GERAL" refcursor
)
RETURNS refcursor AS
$body$
DECLARE
 P_CS_GERAL REFCURSOR;
 script varchar(800)='';

BEGIN

	
    
    IF $1  >0 THEN	
      script := script || ' AND J.COD_CAMPEONATO ='|| $1||' '; 
    END IF;
    
   

 OPEN $2 FOR EXECUTE 
  'SELECT J.*, C.*, LA.*, CJ.* FROM JOGO J, CAMPEONATO C, CONFIGURACAO_JOGO CJ, LIMITE_APOSTA LA
  WHERE J.COD_CAMPEONATO = C.SEQ_CAMPEONATO
  AND J.SEQ_JOGO = CJ.COD_JOGO
  AND J.SEQ_JOGO = LA.COD_JOGO
  '|| SCRIPT ||' ORDER BY J.HORA_INICIAL_JOGO';  
  
  

  
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;