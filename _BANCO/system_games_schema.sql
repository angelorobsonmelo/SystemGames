--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.0
-- Started on 2016-03-10 09:34:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 204 (class 1255 OID 139947)
-- Name: sp_campeonato_atualizar(character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_campeonato_atualizar("P_NOME_CAMPEONATO" character varying, "P_SEQ_CAMPEONATO" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 update campeonato SET NOME_CAMPEONATO = $1 WHERE SEQ_CAMPEONATO = $2;

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
$_$;


ALTER FUNCTION public.sp_campeonato_atualizar("P_NOME_CAMPEONATO" character varying, "P_SEQ_CAMPEONATO" integer) OWNER TO postgres;

--
-- TOC entry 205 (class 1255 OID 139948)
-- Name: sp_campeonato_buscar_por_sequencial(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_campeonato_buscar_por_sequencial(OUT "P_CS_GERAL" refcursor, "P_SEQ_CAMPEONATO" integer) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM campeonato WHERE SEQ_CAMPEONATO = $2;
END;
$_$;


ALTER FUNCTION public.sp_campeonato_buscar_por_sequencial(OUT "P_CS_GERAL" refcursor, "P_SEQ_CAMPEONATO" integer) OWNER TO postgres;

--
-- TOC entry 206 (class 1255 OID 139949)
-- Name: sp_campeonato_buscar_todos(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_campeonato_buscar_todos(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM campeonato;
END;
$_$;


ALTER FUNCTION public.sp_campeonato_buscar_todos(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 207 (class 1255 OID 139950)
-- Name: sp_campeonato_inserir(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_campeonato_inserir("P_NOME_CAMPEONATO" character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO campeonato (NOME_CAMPEONATO) VALUES ($1);

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
$_$;


ALTER FUNCTION public.sp_campeonato_inserir("P_NOME_CAMPEONATO" character varying) OWNER TO postgres;

--
-- TOC entry 208 (class 1255 OID 139951)
-- Name: sp_configuracao_cambista_alterar(integer, double precision, double precision, character varying, integer, integer, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_cambista_alterar("P_SEQ_CAMBISTA" integer, "P_LIMITE_MAX_VENDA_DIARIO" double precision, "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision, "P_OBSERVACAO" character varying, "P_COD_USUARIO" integer, "P_COMISSAO_UM" integer, "P_COMISSAO_DOIS" integer, "P_COMISSAO_TRES" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
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
$_$;


ALTER FUNCTION public.sp_configuracao_cambista_alterar("P_SEQ_CAMBISTA" integer, "P_LIMITE_MAX_VENDA_DIARIO" double precision, "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision, "P_OBSERVACAO" character varying, "P_COD_USUARIO" integer, "P_COMISSAO_UM" integer, "P_COMISSAO_DOIS" integer, "P_COMISSAO_TRES" integer) OWNER TO postgres;

--
-- TOC entry 209 (class 1255 OID 139952)
-- Name: sp_configuracao_cambista_buscar_todos(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_cambista_buscar_todos(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR EXECUTE
 'SELECT U.*, CC.* FROM USUARIO U, TIPO_USUARIO TU, CONFIGURACAO_CAMBISTA CC
 WHERE U.COD_TIPO_USUARIO = TU.SEQ_TIPO_USUARIO
 AND CC.COD_USUARIO = U.SEQ_USUARIO
 ORDER BY CC.SEQ_CAMBISTA';
END;
$_$;


ALTER FUNCTION public.sp_configuracao_cambista_buscar_todos(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 210 (class 1255 OID 139953)
-- Name: sp_configuracao_cambista_inserir(double precision, double precision, character varying, integer, integer, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_cambista_inserir("P_LIMITE_MAX_VENDA_DIARIO" double precision, "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision, "P_OBSERVACAO" character varying, "P_COD_USUARIO" integer, "P_COMISSAO_UM" integer, "P_COMISSAO_DOIS" integer, "P_COMISSAO_TRES" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
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
$_$;


ALTER FUNCTION public.sp_configuracao_cambista_inserir("P_LIMITE_MAX_VENDA_DIARIO" double precision, "P_LIMITE_MAX_VENDA_INDIVIDUAL" double precision, "P_OBSERVACAO" character varying, "P_COD_USUARIO" integer, "P_COMISSAO_UM" integer, "P_COMISSAO_DOIS" integer, "P_COMISSAO_TRES" integer) OWNER TO postgres;

--
-- TOC entry 211 (class 1255 OID 139954)
-- Name: sp_configuracao_cambista_remover(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_cambista_remover("P_SEQ_CAMBISTA" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
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
$_$;


ALTER FUNCTION public.sp_configuracao_cambista_remover("P_SEQ_CAMBISTA" integer) OWNER TO postgres;

--
-- TOC entry 212 (class 1255 OID 139955)
-- Name: sp_configuracao_jogo_atualizar(integer, boolean, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_jogo_atualizar("P_COD_JOGO" integer, "P_FINALIZAR_AUTOMATICAMENTE" boolean, "P_JOGO_FINALIZADO" boolean) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 UPDATE configuracao_jogo SET FINALIZAR_AUTOMATICAMENTE = $2, JOGO_FINALIZADO = $3 WHERE COD_JOGO = $1;

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
$_$;


ALTER FUNCTION public.sp_configuracao_jogo_atualizar("P_COD_JOGO" integer, "P_FINALIZAR_AUTOMATICAMENTE" boolean, "P_JOGO_FINALIZADO" boolean) OWNER TO postgres;

--
-- TOC entry 213 (class 1255 OID 139956)
-- Name: sp_configuracao_jogo_buscar_por_seq_jogo(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_jogo_buscar_por_seq_jogo(OUT "P_CS_GERAL" refcursor, "P_SEQ_JOGO" integer) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM configuracao_jogo WHERE COD_JOGO = $2;
END;
$_$;


ALTER FUNCTION public.sp_configuracao_jogo_buscar_por_seq_jogo(OUT "P_CS_GERAL" refcursor, "P_SEQ_JOGO" integer) OWNER TO postgres;

--
-- TOC entry 214 (class 1255 OID 139957)
-- Name: sp_configuracao_jogo_inserir(integer, boolean, boolean); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_configuracao_jogo_inserir("P_COD_JOGO" integer, "P_FINALIZAR_AUTOMATICAMENTE" boolean, "P_JOGO_FINALIZADO" boolean) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO configuracao_jogo (COD_JOGO, FINALIZAR_AUTOMATICAMENTE, JOGO_FINALIZADO) VALUES ($1, $2, $3);

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
$_$;


ALTER FUNCTION public.sp_configuracao_jogo_inserir("P_COD_JOGO" integer, "P_FINALIZAR_AUTOMATICAMENTE" boolean, "P_JOGO_FINALIZADO" boolean) OWNER TO postgres;

--
-- TOC entry 215 (class 1255 OID 139958)
-- Name: sp_esporte_atualizar(character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_esporte_atualizar("P_NOME_ESPORTE" character varying, "P_SEQ_ESPORTE" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 UPDATE esporte SET NOME_ESPORTE = $1 WHERE SEQ_ESPORTE = $2;

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
$_$;


ALTER FUNCTION public.sp_esporte_atualizar("P_NOME_ESPORTE" character varying, "P_SEQ_ESPORTE" integer) OWNER TO postgres;

--
-- TOC entry 216 (class 1255 OID 139959)
-- Name: sp_esporte_buscar_por_sequencial(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_esporte_buscar_por_sequencial(OUT "P_CS_GERAL" refcursor, "P_SEQ_ESPORTE" integer) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM esporte WHERE SEQ_ESPORTE = $2;
END;
$_$;


ALTER FUNCTION public.sp_esporte_buscar_por_sequencial(OUT "P_CS_GERAL" refcursor, "P_SEQ_ESPORTE" integer) OWNER TO postgres;

--
-- TOC entry 217 (class 1255 OID 139960)
-- Name: sp_esporte_buscar_todos(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_esporte_buscar_todos(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM esporte;
END;
$_$;


ALTER FUNCTION public.sp_esporte_buscar_todos(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 218 (class 1255 OID 139961)
-- Name: sp_esporte_inserir(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_esporte_inserir("P_NOME_ESPORTE" character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO esporte (NOME_ESPORTE) VALUES ($1);

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
$_$;


ALTER FUNCTION public.sp_esporte_inserir("P_NOME_ESPORTE" character varying) OWNER TO postgres;

--
-- TOC entry 219 (class 1255 OID 139962)
-- Name: sp_jogo_atualizar(character varying, integer, integer, date, character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_jogo_atualizar("P_JOGO" character varying, "P_COD_CAMPIONATO" integer, "P_COD_ESPORTE" integer, "P_DATA_JOGO" date, "P_HORA_INCIAL_JOGO" character varying, "P_SEQ_JOGO" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 UPDATE jogo SET JOGO = $1, COD_CAMPEONATO = $2, COD_ESPORTE = $3, DATA_JOGO = $4, HORA_INICIAL_JOGO = $5 WHERE SEQ_JOGO = $6;

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
$_$;


ALTER FUNCTION public.sp_jogo_atualizar("P_JOGO" character varying, "P_COD_CAMPIONATO" integer, "P_COD_ESPORTE" integer, "P_DATA_JOGO" date, "P_HORA_INCIAL_JOGO" character varying, "P_SEQ_JOGO" integer) OWNER TO postgres;

--
-- TOC entry 220 (class 1255 OID 139963)
-- Name: sp_jogo_buscar_todos(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_jogo_buscar_todos(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM jogo;
END;
$_$;


ALTER FUNCTION public.sp_jogo_buscar_todos(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 221 (class 1255 OID 139964)
-- Name: sp_jogo_inserir(character varying, integer, integer, date, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_jogo_inserir("P_JOGO" character varying, "P_COD_CAMPIONATO" integer, "P_COD_ESPORTE" integer, "P_DATA_JOGO" date, "P_HORA_INCIAL_JOGO" character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO jogo (JOGO, COD_CAMPEONATO, COD_ESPORTE, DATA_JOGO, HORA_INICIAL_JOGO) VALUES ($1, $2, $3, $4, $5);

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
$_$;


ALTER FUNCTION public.sp_jogo_inserir("P_JOGO" character varying, "P_COD_CAMPIONATO" integer, "P_COD_ESPORTE" integer, "P_DATA_JOGO" date, "P_HORA_INCIAL_JOGO" character varying) OWNER TO postgres;

--
-- TOC entry 222 (class 1255 OID 139965)
-- Name: sp_jogo_remover(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_jogo_remover("P_SEQ_JOGO" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 DELETE FROM jogo WHERE SEQ_JOGO = $1;

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
$_$;


ALTER FUNCTION public.sp_jogo_remover("P_SEQ_JOGO" integer) OWNER TO postgres;

--
-- TOC entry 223 (class 1255 OID 139966)
-- Name: sp_jogo_retornar_ultimo_sequencial(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_jogo_retornar_ultimo_sequencial(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR SELECT MAX(seq_jogo) AS seq_jogo FROM jogo; 
 
END;
$_$;


ALTER FUNCTION public.sp_jogo_retornar_ultimo_sequencial(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 224 (class 1255 OID 139967)
-- Name: sp_limite_aposta_atualizar(integer, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_limite_aposta_atualizar("P_COD_JOGO" integer, "P_CASA" double precision, "P_EMPATE" double precision, "P_FORA" double precision, "P_GOL_E_MEIO" double precision, "P_DUPLA_CHANCE" double precision, "P_AMBOS" double precision, "P_LIMITE_CASA" double precision, "P_LIMITE_EMPATE" double precision, "P_LIMITE_FORA" double precision, "P_LIMITE_GOL_E_MEIO" double precision, "P_LIMITE_DUPLA_CHANCE" double precision, "P_LIMITE_INVIDUAL" double precision, "P_LIMITE_AMBOS" double precision) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 UPDATE limite_aposta SET CASA = $2, EMPATE = $3, FORA = $4, GOL_E_MEIO = $5, DUPLA_CHANCE =$6, AMBOS = $7, LIMITE_CASA = $8, LIMITE_EMPATE = $9, LIMITE_FORA = $10, LIMITE_GOL_E_MEIO = $11, LIMITE_DUPLA_CHANCE = $12, LIMITE_INDIVIDUAL = $13, LIMITE_AMBOS = $14 WHERE COD_JOGO = $1;

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
$_$;


ALTER FUNCTION public.sp_limite_aposta_atualizar("P_COD_JOGO" integer, "P_CASA" double precision, "P_EMPATE" double precision, "P_FORA" double precision, "P_GOL_E_MEIO" double precision, "P_DUPLA_CHANCE" double precision, "P_AMBOS" double precision, "P_LIMITE_CASA" double precision, "P_LIMITE_EMPATE" double precision, "P_LIMITE_FORA" double precision, "P_LIMITE_GOL_E_MEIO" double precision, "P_LIMITE_DUPLA_CHANCE" double precision, "P_LIMITE_INVIDUAL" double precision, "P_LIMITE_AMBOS" double precision) OWNER TO postgres;

--
-- TOC entry 225 (class 1255 OID 139968)
-- Name: sp_limite_aposta_buscar_por_seq_jogo(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_limite_aposta_buscar_por_seq_jogo(OUT "P_CS_GERAL" refcursor, "P_SEQ_JOGO" integer) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR 
  SELECT * FROM limite_aposta WHERE COD_JOGO = $2;
END;
$_$;


ALTER FUNCTION public.sp_limite_aposta_buscar_por_seq_jogo(OUT "P_CS_GERAL" refcursor, "P_SEQ_JOGO" integer) OWNER TO postgres;

--
-- TOC entry 226 (class 1255 OID 139969)
-- Name: sp_limite_aposta_inserir(integer, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_limite_aposta_inserir("P_COD_JOGO" integer, "P_CASA" double precision, "P_EMPATE" double precision, "P_FORA" double precision, "P_GOL_E_MEIO" double precision, "P_DUPLA_CHANCE" double precision, "P_AMBOS" double precision, "P_LIMITE_CASA" double precision, "P_LIMITE_EMPATE" double precision, "P_LIMITE_FORA" double precision, "P_LIMITE_GOL_E_MEIO" double precision, "P_LIMITE_DUPLA_CHANCE" double precision, "P_LIMITE_INVIDUAL" double precision, "P_LIMITE_AMBOS" double precision) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';
 BEGIN 
 
 INSERT INTO limite_aposta (COD_JOGO, CASA, EMPATE, FORA, GOL_E_MEIO, DUPLA_CHANCE, AMBOS, LIMITE_CASA, LIMITE_EMPATE, LIMITE_FORA, LIMITE_GOL_E_MEIO, LIMITE_DUPLA_CHANCE, LIMITE_INDIVIDUAL, LIMITE_AMBOS) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14);

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
$_$;


ALTER FUNCTION public.sp_limite_aposta_inserir("P_COD_JOGO" integer, "P_CASA" double precision, "P_EMPATE" double precision, "P_FORA" double precision, "P_GOL_E_MEIO" double precision, "P_DUPLA_CHANCE" double precision, "P_AMBOS" double precision, "P_LIMITE_CASA" double precision, "P_LIMITE_EMPATE" double precision, "P_LIMITE_FORA" double precision, "P_LIMITE_GOL_E_MEIO" double precision, "P_LIMITE_DUPLA_CHANCE" double precision, "P_LIMITE_INVIDUAL" double precision, "P_LIMITE_AMBOS" double precision) OWNER TO postgres;

--
-- TOC entry 227 (class 1255 OID 139970)
-- Name: sp_usuario_alterar(integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_usuario_alterar("P_SEQ_USUARIO" integer, "P_NOM_USUARIO" character varying, "P_EMAIL" character varying, "P_SENHA" character varying, "P_CPF" character varying, "P_NUM_RG" character varying, "P_NUM_CELULAR" character varying, "P_COD_TIPO_USUARIO" character varying, "P_ENDERECO" character varying, "P_NUM_ENDERECO" character varying, "P_COMPLEMENTO" character varying, "P_BAIRRO" character varying, "P_CIDADE" character varying, "P_NUM_CEP" character varying, "P_UF" character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
P_MSG_RETORNO VARCHAR = '';

BEGIN

  UPDATE USUARIO
  SET NOME_USUARIO = $2,  
      EMAIL		     = $3,
      SENHA = $4,
      CPF = $5,
      NUM_RG = $6,
      NUM_CELULAR = $7,
      COD_TIPO_USUARIO = $8,
      ENDERECO = $9,
      NUM_ENDERECO = $10,
      COMPLEMENTO = $11,
      BAIRRO = $12,
      CIDADE = $13,
      CEP = $14,
      UF = $15
  WHERE SEQ_USUARIO = $1;

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
$_$;


ALTER FUNCTION public.sp_usuario_alterar("P_SEQ_USUARIO" integer, "P_NOM_USUARIO" character varying, "P_EMAIL" character varying, "P_SENHA" character varying, "P_CPF" character varying, "P_NUM_RG" character varying, "P_NUM_CELULAR" character varying, "P_COD_TIPO_USUARIO" character varying, "P_ENDERECO" character varying, "P_NUM_ENDERECO" character varying, "P_COMPLEMENTO" character varying, "P_BAIRRO" character varying, "P_CIDADE" character varying, "P_NUM_CEP" character varying, "P_UF" character varying) OWNER TO postgres;

--
-- TOC entry 228 (class 1255 OID 139971)
-- Name: sp_usuario_buscar_todos(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_usuario_buscar_todos(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;

BEGIN

OPEN $1 FOR EXECUTE
  'SELECT U.*, CC.* FROM USUARIO U, TIPO_USUARIO TU, CONFIGURACAO_CAMBISTA CC
 WHERE U.COD_TIPO_USUARIO = TU.SEQ_TIPO_USUARIO
 AND CC.COD_USUARIO = U.SEQ_USUARIO
 ORDER BY U.NOME_USUARIO';
END;
$_$;


ALTER FUNCTION public.sp_usuario_buscar_todos(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 229 (class 1255 OID 139972)
-- Name: sp_usuario_consultar_por_parametros(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_usuario_consultar_por_parametros(OUT "P_CS_GERAL" refcursor) RETURNS refcursor
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_CS_GERAL REFCURSOR;
BEGIN
	
 OPEN $1 FOR EXECUTE 
 'SELECT U.*, CC.* FROM USUARIO U, TIPO_USUARIO TU, CONFIGURACAO_CAMBISTA CC
 WHERE U.COD_TIPO_USUARIO = TU.SEQ_TIPO_USUARIO
 AND CC.COD_USUARIO = U.SEQ_USUARIO
 ' || SCRIPT || ' ORDER BY U.NOME_USUARIO';
 
END;
$_$;


ALTER FUNCTION public.sp_usuario_consultar_por_parametros(OUT "P_CS_GERAL" refcursor) OWNER TO postgres;

--
-- TOC entry 231 (class 1255 OID 140126)
-- Name: sp_usuario_inserir(character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer, character varying, character varying, character varying, character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_usuario_inserir("P_NOM_USUARIO" character varying, "P_EMAIL" character varying, "P_LOGIN" character varying, "P_SENHA" character varying, "P_CPF" character varying, "P_NUM_RG" character varying, "P_NUM_CONTATO" character varying, "P_COD_TIPO_USUARIO" integer, "P_ENDERECO" character varying, "P_NUM_ENDERECO" character varying, "P_COMPLEMENTO" character varying, "P_BAIRRO" character varying, "P_CIDADE" character varying, "P_NUM_CEP" character varying, "P_UF" character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
 P_MSG_RETORNO VARCHAR = '';   
 P_SEQUENCIAL INTEGER = 0;
 BEGIN 
 
 INSERT INTO usuario (NOME_USUARIO, EMAIL, NOM_LOGIN, SENHA, CPF, NUM_RG, CONTATO, COD_TIPO_USUARIO,
 ENDERECO, NUM_ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, CEP, UF)
 VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15);
 
 P_SEQUENCIAL = currval('usuario_seq_usuario_seq');
 P_MSG_RETORNO = 'OK' ||'SEQ_USUARIO' || P_SEQUENCIAL;
 
 RETURN P_MSG_RETORNO;
 EXCEPTION 
 WHEN UNIQUE_VIOLATION THEN
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO;
 WHEN INTEGRITY_CONSTRAINT_VIOLATION THEN 
 P_MSG_RETORNO = SQLERRM;
 RETURN P_MSG_RETORNO; 
 END;
$_$;


ALTER FUNCTION public.sp_usuario_inserir("P_NOM_USUARIO" character varying, "P_EMAIL" character varying, "P_LOGIN" character varying, "P_SENHA" character varying, "P_CPF" character varying, "P_NUM_RG" character varying, "P_NUM_CONTATO" character varying, "P_COD_TIPO_USUARIO" integer, "P_ENDERECO" character varying, "P_NUM_ENDERECO" character varying, "P_COMPLEMENTO" character varying, "P_BAIRRO" character varying, "P_CIDADE" character varying, "P_NUM_CEP" character varying, "P_UF" character varying) OWNER TO postgres;

--
-- TOC entry 230 (class 1255 OID 139974)
-- Name: sp_usuario_remover(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION sp_usuario_remover("P_SEQ_USUARIO" integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $_$
DECLARE
P_MSG_RETORNO VARCHAR = '';

BEGIN

  DELETE FROM USUARIO WHERE SEQ_USUARIO = $1;

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
$_$;


ALTER FUNCTION public.sp_usuario_remover("P_SEQ_USUARIO" integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 139975)
-- Name: campeonato; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE campeonato (
    seq_campeonato integer NOT NULL,
    nome_campeonato character varying
);


ALTER TABLE campeonato OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 139981)
-- Name: campionato_seq_campionato_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE campionato_seq_campionato_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE campionato_seq_campionato_seq OWNER TO postgres;

--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 173
-- Name: campionato_seq_campionato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE campionato_seq_campionato_seq OWNED BY campeonato.seq_campeonato;


--
-- TOC entry 174 (class 1259 OID 139983)
-- Name: configuracao_cambista; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE configuracao_cambista (
    seq_cambista integer NOT NULL,
    limite_max_venda_diario double precision NOT NULL,
    limite_max_venda_individual double precision,
    observacao character varying,
    cod_usuario integer NOT NULL,
    comissao1 integer NOT NULL,
    comissao2 integer NOT NULL,
    comissao3 integer NOT NULL
);


ALTER TABLE configuracao_cambista OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 139989)
-- Name: configuracao_cambista_seq_cambista_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE configuracao_cambista_seq_cambista_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configuracao_cambista_seq_cambista_seq OWNER TO postgres;

--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 175
-- Name: configuracao_cambista_seq_cambista_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE configuracao_cambista_seq_cambista_seq OWNED BY configuracao_cambista.seq_cambista;


--
-- TOC entry 176 (class 1259 OID 139991)
-- Name: configuracao_jogo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE configuracao_jogo (
    seq_configuracao_jogo integer NOT NULL,
    cod_jogo integer,
    finalizar_automaticamente boolean,
    jogo_finalizado boolean
);


ALTER TABLE configuracao_jogo OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 139994)
-- Name: configuracao_jogo_seq_configuracao_jogo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE configuracao_jogo_seq_configuracao_jogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE configuracao_jogo_seq_configuracao_jogo_seq OWNER TO postgres;

--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 177
-- Name: configuracao_jogo_seq_configuracao_jogo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE configuracao_jogo_seq_configuracao_jogo_seq OWNED BY configuracao_jogo.seq_configuracao_jogo;


--
-- TOC entry 178 (class 1259 OID 139996)
-- Name: esporte; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE esporte (
    seq_esporte integer NOT NULL,
    nome_esporte character varying
);


ALTER TABLE esporte OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 140002)
-- Name: esporte_seq_esporte_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE esporte_seq_esporte_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE esporte_seq_esporte_seq OWNER TO postgres;

--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 179
-- Name: esporte_seq_esporte_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE esporte_seq_esporte_seq OWNED BY esporte.seq_esporte;


--
-- TOC entry 180 (class 1259 OID 140004)
-- Name: jogo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE jogo (
    seq_jogo integer NOT NULL,
    jogo character varying,
    cod_campeonato integer,
    cod_esporte integer,
    data_jogo date,
    hora_inicial_jogo character varying
);


ALTER TABLE jogo OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 140010)
-- Name: jogo_seq_jogo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE jogo_seq_jogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE jogo_seq_jogo_seq OWNER TO postgres;

--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 181
-- Name: jogo_seq_jogo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE jogo_seq_jogo_seq OWNED BY jogo.seq_jogo;


--
-- TOC entry 182 (class 1259 OID 140012)
-- Name: limite_aposta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE limite_aposta (
    seq_limite_aposta integer NOT NULL,
    cod_jogo integer,
    casa double precision,
    empate double precision,
    fora double precision,
    gol_e_meio double precision,
    dupla_chance double precision,
    ambos double precision,
    limite_casa double precision,
    limite_empate double precision,
    limite_fora double precision,
    limite_gol_e_meio double precision,
    limite_dupla_chance double precision,
    limite_individual double precision,
    limite_ambos double precision
);


ALTER TABLE limite_aposta OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 140015)
-- Name: limite_aposta_seq_limite_aposta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE limite_aposta_seq_limite_aposta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE limite_aposta_seq_limite_aposta_seq OWNER TO postgres;

--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 183
-- Name: limite_aposta_seq_limite_aposta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE limite_aposta_seq_limite_aposta_seq OWNED BY limite_aposta.seq_limite_aposta;


--
-- TOC entry 184 (class 1259 OID 140017)
-- Name: resultado_jogo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE resultado_jogo (
    seq_resultado_jogo integer NOT NULL,
    cod_jogo integer,
    resultado_jogo_casa integer,
    resultado_jogo_fora integer
);


ALTER TABLE resultado_jogo OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 140020)
-- Name: resultado_jogo_seq_resultado_jogo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE resultado_jogo_seq_resultado_jogo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE resultado_jogo_seq_resultado_jogo_seq OWNER TO postgres;

--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 185
-- Name: resultado_jogo_seq_resultado_jogo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE resultado_jogo_seq_resultado_jogo_seq OWNED BY resultado_jogo.seq_resultado_jogo;


--
-- TOC entry 186 (class 1259 OID 140022)
-- Name: tipo_usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_usuario (
    seq_tipo_usuario integer NOT NULL,
    nome_tipo_usuario character varying NOT NULL
);


ALTER TABLE tipo_usuario OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 140028)
-- Name: tipo_usuario_seq_tipo_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipo_usuario_seq_tipo_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_usuario_seq_tipo_usuario_seq OWNER TO postgres;

--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 187
-- Name: tipo_usuario_seq_tipo_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipo_usuario_seq_tipo_usuario_seq OWNED BY tipo_usuario.seq_tipo_usuario;


--
-- TOC entry 189 (class 1259 OID 140102)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    seq_usuario integer NOT NULL,
    nome_usuario character varying NOT NULL,
    email character varying NOT NULL,
    nom_login character varying NOT NULL,
    senha character varying NOT NULL,
    cpf character varying NOT NULL,
    num_rg character varying,
    contato character varying NOT NULL,
    cod_tipo_usuario integer NOT NULL,
    endereco character varying NOT NULL,
    num_endereco character varying,
    complemento character varying,
    bairro character varying NOT NULL,
    cidade character varying NOT NULL,
    cep character varying,
    uf character varying NOT NULL
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 140100)
-- Name: usuario_seq_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuario_seq_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuario_seq_usuario_seq OWNER TO postgres;

--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 188
-- Name: usuario_seq_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_seq_usuario_seq OWNED BY usuario.seq_usuario;


--
-- TOC entry 1964 (class 2604 OID 140038)
-- Name: seq_campeonato; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY campeonato ALTER COLUMN seq_campeonato SET DEFAULT nextval('campionato_seq_campionato_seq'::regclass);


--
-- TOC entry 1965 (class 2604 OID 140039)
-- Name: seq_cambista; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_cambista ALTER COLUMN seq_cambista SET DEFAULT nextval('configuracao_cambista_seq_cambista_seq'::regclass);


--
-- TOC entry 1966 (class 2604 OID 140040)
-- Name: seq_configuracao_jogo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_jogo ALTER COLUMN seq_configuracao_jogo SET DEFAULT nextval('configuracao_jogo_seq_configuracao_jogo_seq'::regclass);


--
-- TOC entry 1967 (class 2604 OID 140041)
-- Name: seq_esporte; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY esporte ALTER COLUMN seq_esporte SET DEFAULT nextval('esporte_seq_esporte_seq'::regclass);


--
-- TOC entry 1968 (class 2604 OID 140042)
-- Name: seq_jogo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo ALTER COLUMN seq_jogo SET DEFAULT nextval('jogo_seq_jogo_seq'::regclass);


--
-- TOC entry 1969 (class 2604 OID 140043)
-- Name: seq_limite_aposta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY limite_aposta ALTER COLUMN seq_limite_aposta SET DEFAULT nextval('limite_aposta_seq_limite_aposta_seq'::regclass);


--
-- TOC entry 1970 (class 2604 OID 140044)
-- Name: seq_resultado_jogo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY resultado_jogo ALTER COLUMN seq_resultado_jogo SET DEFAULT nextval('resultado_jogo_seq_resultado_jogo_seq'::regclass);


--
-- TOC entry 1971 (class 2604 OID 140045)
-- Name: seq_tipo_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_usuario ALTER COLUMN seq_tipo_usuario SET DEFAULT nextval('tipo_usuario_seq_tipo_usuario_seq'::regclass);


--
-- TOC entry 1972 (class 2604 OID 140105)
-- Name: seq_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN seq_usuario SET DEFAULT nextval('usuario_seq_usuario_seq'::regclass);


--
-- TOC entry 1974 (class 2606 OID 140048)
-- Name: campionato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY campeonato
    ADD CONSTRAINT campionato_pkey PRIMARY KEY (seq_campeonato);


--
-- TOC entry 1978 (class 2606 OID 140050)
-- Name: configuracao_jogo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY configuracao_jogo
    ADD CONSTRAINT configuracao_jogo_pkey PRIMARY KEY (seq_configuracao_jogo);


--
-- TOC entry 1980 (class 2606 OID 140052)
-- Name: esporte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY esporte
    ADD CONSTRAINT esporte_pkey PRIMARY KEY (seq_esporte);


--
-- TOC entry 1982 (class 2606 OID 140054)
-- Name: jogo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_pkey PRIMARY KEY (seq_jogo);


--
-- TOC entry 1984 (class 2606 OID 140056)
-- Name: limite_aposta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY limite_aposta
    ADD CONSTRAINT limite_aposta_pkey PRIMARY KEY (seq_limite_aposta);


--
-- TOC entry 1976 (class 2606 OID 140058)
-- Name: pk_seq_cambista; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY configuracao_cambista
    ADD CONSTRAINT pk_seq_cambista PRIMARY KEY (seq_cambista);


--
-- TOC entry 1988 (class 2606 OID 140060)
-- Name: pk_seq_tipo_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_usuario
    ADD CONSTRAINT pk_seq_tipo_usuario PRIMARY KEY (seq_tipo_usuario);


--
-- TOC entry 1990 (class 2606 OID 140110)
-- Name: pk_seq_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_seq_usuario PRIMARY KEY (seq_usuario);


--
-- TOC entry 1986 (class 2606 OID 140064)
-- Name: resultado_jogo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY resultado_jogo
    ADD CONSTRAINT resultado_jogo_pkey PRIMARY KEY (seq_resultado_jogo);


--
-- TOC entry 1992 (class 2606 OID 140112)
-- Name: usuario_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_cpf_key UNIQUE (cpf);


--
-- TOC entry 1994 (class 2606 OID 140114)
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 1995 (class 2606 OID 140120)
-- Name: configuracao_cambista_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_cambista
    ADD CONSTRAINT configuracao_cambista_fk FOREIGN KEY (cod_usuario) REFERENCES usuario(seq_usuario);


--
-- TOC entry 1996 (class 2606 OID 140069)
-- Name: configuracao_jogo_cod_jogo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_jogo
    ADD CONSTRAINT configuracao_jogo_cod_jogo_fkey FOREIGN KEY (cod_jogo) REFERENCES jogo(seq_jogo) ON DELETE CASCADE;


--
-- TOC entry 2000 (class 2606 OID 140115)
-- Name: fkcod_tipo_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fkcod_tipo_usuario FOREIGN KEY (cod_tipo_usuario) REFERENCES tipo_usuario(seq_tipo_usuario);


--
-- TOC entry 1997 (class 2606 OID 140084)
-- Name: jogo_cod_campionato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_cod_campionato_fkey FOREIGN KEY (cod_campeonato) REFERENCES campeonato(seq_campeonato);


--
-- TOC entry 1998 (class 2606 OID 140089)
-- Name: jogo_cod_esporte_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jogo
    ADD CONSTRAINT jogo_cod_esporte_fkey FOREIGN KEY (cod_esporte) REFERENCES esporte(seq_esporte);


--
-- TOC entry 1999 (class 2606 OID 140094)
-- Name: limite_aposta_cod_jogo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY limite_aposta
    ADD CONSTRAINT limite_aposta_cod_jogo_fkey FOREIGN KEY (cod_jogo) REFERENCES jogo(seq_jogo) ON DELETE CASCADE;


--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-03-10 09:34:43

--
-- PostgreSQL database dump complete
--

