CREATE TABLE configuracao_cambista
(
  seq_configuracao_cambista integer NOT NULL DEFAULT nextval('configuracao_cambista_configuracao_cambista_seq'::regclass),
  limite_max_venda_diaria double precision,
  limite_max_venda_individual character varying,
  cod_cambista integer,
  observacao character varying,
  comissao1 double precision,
  comissao2 double precision,
  comissao3 double precision,
  CONSTRAINT configuracao_cambista_pkey PRIMARY KEY (seq_configuracao_cambista),
  CONSTRAINT configuracao_cambista_cod_cambista_fkey FOREIGN KEY (cod_cambista)
      REFERENCES cambista (seq_cambista) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE configuracao_cambista
  OWNER TO postgres;