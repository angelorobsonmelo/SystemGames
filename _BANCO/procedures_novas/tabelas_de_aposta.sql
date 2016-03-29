CREATE TABLE public.aposta (
  seq_aposta SERIAL,
  cod_cambista INTEGER,
  nome_apostador VARCHAR,
  val_apostado DOUBLE PRECISION,
  val_comissao DOUBLE PRECISION,
  val_retorno_possivel DOUBLE PRECISION,
  dth_inclusao_aposta TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT aposta_pkey PRIMARY KEY(seq_aposta),
  CONSTRAINT cod_cambista_fk FOREIGN KEY (cod_cambista)
    REFERENCES public.cambista(seq_cambista)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE
) 
WITH (oids = false);


CREATE TABLE public.jogos_apostado (
  seq_jogo_apostado SERIAL,
  cod_aposta INTEGER,
  data_jogo VARCHAR,
  hora_jogo VARCHAR,
  cod_jogo INTEGER,
  tipo_aposta VARCHAR,
  val_taxa DOUBLE PRECISION,
  CONSTRAINT jogo_apostado PRIMARY KEY(seq_jogo_apostado),
  CONSTRAINT cod_aposta_fk FOREIGN KEY (cod_aposta)
    REFERENCES public.aposta(seq_aposta)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE,
  CONSTRAINT cod_jogo_apostado_fk FOREIGN KEY (cod_jogo)
    REFERENCES public.jogo(seq_jogo)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    NOT DEFERRABLE
) 
WITH (oids = false);