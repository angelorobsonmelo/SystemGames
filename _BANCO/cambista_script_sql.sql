CREATE TABLE cambista
(
  seq_cambista serial NOT NULL,
  nome_cambista character varying,
  email_cambista character varying,
  senha_cambista character varying,
  cpf_cambista character varying,
  num_rg_cambista character varying,
  num_contato_cambista character varying,
  cod_tipo_usuario integer,
  cod_usuario integer,
  endereco_cambista character varying,
  complemento_cambista character varying,
  bairro_cambista character varying,
  cidade_cambista character varying,
  cep_cambista character varying,
  uf_cambista character varying,
  num_endereco_cambista character varying,
  apelido_cambista character varying,
  CONSTRAINT cambista_pkey PRIMARY KEY (seq_cambista),
  CONSTRAINT cod_usuario FOREIGN KEY (cod_usuario)
      REFERENCES usuario (seq_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT usuario_cod_tipo_usuario_fkey FOREIGN KEY (cod_tipo_usuario)
      REFERENCES tipo_usuario (seq_tipo_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


CREATE OR REPLACE FUNCTION public.sp_configuracao_cambista_alterar (
  "P_SEQ_CAMBISTA" integer,
  "P_LIMITE_MAX_VENDA_DIARIO" double precision,
  "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision,
  "P_OBSERVACAO" varchar,
  "P_COD_USUARIO" integer,
  "P_COMISSAO_UM" integer,
  "P_COMISSAO_DOIS" integer,
  "P_COMISSAO_TRES" integer
)
RETURNS varchar AS
$body$
DECLARE
P_MSG_RETORNO VARCHAR = '';

BEGIN

  UPDATE CONFIGURACAO_CAMBISTA
  SET LIMITE_MAX_VENDA_DIARIO = $2,  
      LIMITE_MAX_VENDA_INDIVIDUAL = $3,
      OBSERVACAO = $4,
      COD_USUARIO = $5,
      COMISSAO1 = $6,
      COMISSAO2 = $7,
      COMISSAO3 = $8      
  WHERE SEQ_CAMBISTA = $1;

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
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;



CREATE OR REPLACE FUNCTION public.sp_configuracao_cambista_buscar_todos (
  out "P_CS_GERAL" refcursor
)
RETURNS refcursor AS
$body$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR EXECUTE
 'SELECT U.*, CC.* FROM USUARIO U, TIPO_USUARIO TU, CONFIGURACAO_CAMBISTA CC
 WHERE U.COD_TIPO_USUARIO = TU.SEQ_TIPO_USUARIO
 AND CC.COD_USUARIO = U.SEQ_USUARIO
 ORDER BY CC.SEQ_CAMBISTA';
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;


CREATE OR REPLACE FUNCTION public.sp_configuracao_cambista_inserir (
  "P_LIMITE_MAX_VENDA_DIARIO" double precision,
  "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision,
  "P_OBSERVACAO" varchar,
  "P_COD_USUARIO" integer,
  "P_COMISSAO_UM" integer,
  "P_COMISSAO_DOIS" integer,
  "P_COMISSAO_TRES" integer
)
RETURNS varchar AS
$body$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO configuracao_cambista (LIMITE_MAX_VENDA_DIARIO, LIMITE_MAX_VENDA_INDIVIDUAL,
 OBSERVACAO, COD_USUARIO, COMISSAO1, COMISSAO2, COMISSAO3) VALUES ($1, $2, $3, $4, $5, $6, $7);

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
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;


CREATE OR REPLACE FUNCTION public.sp_configuracao_cambista_remover (
  "P_SEQ_CAMBISTA" integer
)
RETURNS varchar AS
$body$
DECLARE
P_MSG_RETORNO VARCHAR = '';

BEGIN

  DELETE FROM CONFIGURACAO_CAMBISTA WHERE SEQ_CAMBISTA = $1;

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
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER
COST 100;





CREATE OR REPLACE FUNCTION sp_cambista_inserir(
    "P_NOME_CAMBISTA" character varying,
    "P_EMAIL_CAMBISTA" character varying,
    "P_SENHA_CAMBISTA" character varying,
    "P_CPF_CAMBISTA" character varying,
    "P_NUM_RG_CAMBISTA" character varying,
    "P_NUM_CONTATO_CAMBISTA" character varying,
    "P_COD_USUARIO" integer,
    "P_ENDERECO_CAMBISTA" character varying,
    "P_NUM_ENDERECO_CAMBISTA" character varying,
    "P_COMPLEMENTO_CAMBISTA" character varying,
    "P_BAIRRO_CAMBISTA" character varying,
    "P_CIDADE_CAMBISTA" character varying,
    "P_CEP_CAMBISTA" character varying,
    "P_UF_CAMBISTA" character varying,
    "P_APELIDO_CAMBISTA" character varying,
    "P_LIMITE_MAX_VENDA_DIARIA" double precision,
    "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision,
    "P_OBSERVACAO" character varying,
    "P_COMISSAO1" double precision,
    "P_COMISSAO2" double precision,
    "P_COMISSAO3" double precision)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';   
 P_SEQUENCIAL INTEGER = 0;
 BEGIN 



 
 INSERT INTO cambista (NOME_CAMBISTA, EMAIL_CAMBISTA, SENHA_CAMBISTA, CPF_CAMBISTA, NUM_RG_CAMBISTA, NUM_CONTATO_CAMBISTA, COD_TIPO_USUARIO, COD_USUARIO,
 ENDERECO_CAMBISTA, NUM_ENDERECO_CAMBISTA, COMPLEMENTO_CAMBISTA, BAIRRO_CAMBISTA, CIDADE_CAMBISTA, CEP_CAMBISTA, UF_CAMBISTA, APELIDO_CAMBISTA)
 VALUES ($1, $2, $3, $4, $5, $6, 3, $7, $8, $9, $10, $11, $12, $13,$14, $15);
 
 P_SEQUENCIAL = currval('cambista_seq_cambista_seq');

  INSERT INTO configuracao_cambista (limite_max_venda_diaria, limite_max_venda_individual, cod_cambista, observacao, comissao1, comissao2, comissao3) 
  VALUES ($16, $17, P_SEQUENCIAL, $18, $19, $20, $21);

 
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
  
  
  CREATE OR REPLACE FUNCTION sp_cambista_pesquisar_por_seq_usuario(
    OUT "P_CS_GERAL" refcursor,
    IN "P_SEQ_USUARIO" integer)
  RETURNS refcursor AS
$BODY$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT C.*, U.*, CC.*
 FROM 
 cambista C
 INNER JOIN usuario U ON U.seq_usuario = C.cod_usuario 
 INNER JOIN configuracao_cambista CC ON CC.cod_cambista = C.seq_cambista 
 WHERE C.cod_usuario = $2; 
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
  
  
  CREATE OR REPLACE FUNCTION sp_cambista_atualizar(
    "P_NOME_CAMBISTA" character varying,
    "P_EMAIL_CAMBISTA" character varying,
    "P_SENHA_CAMBISTA" character varying,
    "P_CPF_CAMBISTA" character varying,
    "P_NUM_RG_CAMBISTA" character varying,
    "P_NUM_CONTATO_CAMBISTA" character varying,
    "P_ENDERECO_CAMBISTA" character varying,
    "P_NUM_ENDERECO_CAMBISTA" character varying,
    "P_COMPLEMENTO_CAMBISTA" character varying,
    "P_BAIRRO_CAMBISTA" character varying,
    "P_CIDADE_CAMBISTA" character varying,
    "P_CEP_CAMBISTA" character varying,
    "P_UF_CAMBISTA" character varying,
    "P_APELIDO_CAMBISTA" character varying,
    "P_LIMITE_MAX_VENDA_DIARIA" double precision,
    "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision,
    "P_OBSERVACAO" character varying,
    "P_COMISSAO1" double precision,
    "P_COMISSAO2" double precision,
    "P_COMISSAO3" double precision,
    "P_SEQ_CAMBISTA" integer)
  RETURNS character varying AS
$BODY$
DECLARE
 P_MSG_RETORNO VARCHAR = '';   
 BEGIN 



 
 UPDATE cambista SET NOME_CAMBISTA = $1, EMAIL_CAMBISTA = $2, SENHA_CAMBISTA = $3, 
 CPF_CAMBISTA = $4, NUM_RG_CAMBISTA = $5, NUM_CONTATO_CAMBISTA = $6,  ENDERECO_CAMBISTA = $7,
  NUM_ENDERECO_CAMBISTA = $8, COMPLEMENTO_CAMBISTA = $9, BAIRRO_CAMBISTA = $10, CIDADE_CAMBISTA = $11, 
  CEP_CAMBISTA = $12, UF_CAMBISTA = $13, APELIDO_CAMBISTA = $14
 WHERE SEQ_CAMBISTA = $21;
 
UPDATE configuracao_cambista SET limite_max_venda_diaria = $15, limite_max_venda_individual = $16, observacao = $17, comissao1 = $18, comissao2 = $19, comissao3 = $20
WHERE COD_CAMBISTA = $21;

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
