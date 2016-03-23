CREATE TABLE usuario
(
  seq_usuario serial NOT NULL,
  nome_usuario character varying,
  email character varying,
  senha character varying,
  cpf character varying,
  num_rg character varying,
  num_contato character varying,
  cod_tipo_usuario integer,
  endereco character varying,
  complemento character varying,
  bairro character varying,
  cidade character varying,
  cep character varying,
  uf character varying,
  num_endereco character varying,
  apelido character varying,
  CONSTRAINT usuario_pkey PRIMARY KEY (seq_usuario),
  CONSTRAINT usuario_cod_tipo_usuario_fkey FOREIGN KEY (cod_tipo_usuario)
      REFERENCES tipo_usuario (seq_tipo_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);



CREATE OR REPLACE FUNCTION sp_usuario_autenticar(
    OUT "P_CS_GERAL" refcursor,
    IN "P_APELIDO" character varying,
    IN "P_SENHA" character varying)
  RETURNS refcursor AS
$BODY$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM usuario WHERE APELIDO = $2 AND SENHA = $3;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;