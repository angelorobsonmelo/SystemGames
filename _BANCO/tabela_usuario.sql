CREATE TABLE public.usuario (
  seq_usuario SERIAL,
  nome_usuario VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  senha VARCHAR NOT NULL,
  cpf VARCHAR NOT NULL,
  num_rg VARCHAR,
  contato VARCHAR NOT NULL,
  cod_tipo_usuario INTEGER NOT NULL,
  endereco VARCHAR NOT NULL,
  num_endereco VARCHAR,
  complemento VARCHAR,
  bairro VARCHAR NOT NULL,
  cidade VARCHAR NOT NULL,
  cep VARCHAR,
  uf VARCHAR NOT NULL,
  nom_login VARCHAR NOT NULL,
  CONSTRAINT pk_seq_usuario PRIMARY KEY(seq_usuario),
  CONSTRAINT usuario_cpf_key UNIQUE(cpf),
  CONSTRAINT usuario_email_key UNIQUE(email),
  CONSTRAINT usuario_nom_login_key UNIQUE(nom_login),
  CONSTRAINT fkcod_tipo_usuario FOREIGN KEY (cod_tipo_usuario)
    REFERENCES public.tipo_usuario(seq_tipo_usuario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    NOT DEFERRABLE
) 
WITH (oids = false);