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