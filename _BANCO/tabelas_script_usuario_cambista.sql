--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.0
-- Started on 2016-03-21 10:04:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

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
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 175
-- Name: configuracao_cambista_seq_cambista_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE configuracao_cambista_seq_cambista_seq OWNED BY configuracao_cambista.seq_cambista;


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
-- TOC entry 2084 (class 0 OID 0)
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
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 188
-- Name: usuario_seq_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuario_seq_usuario_seq OWNED BY usuario.seq_usuario;


--
-- TOC entry 1955 (class 2604 OID 140039)
-- Name: seq_cambista; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_cambista ALTER COLUMN seq_cambista SET DEFAULT nextval('configuracao_cambista_seq_cambista_seq'::regclass);


--
-- TOC entry 1956 (class 2604 OID 140045)
-- Name: seq_tipo_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_usuario ALTER COLUMN seq_tipo_usuario SET DEFAULT nextval('tipo_usuario_seq_tipo_usuario_seq'::regclass);


--
-- TOC entry 1957 (class 2604 OID 140105)
-- Name: seq_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN seq_usuario SET DEFAULT nextval('usuario_seq_usuario_seq'::regclass);


--
-- TOC entry 1959 (class 2606 OID 140058)
-- Name: pk_seq_cambista; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY configuracao_cambista
    ADD CONSTRAINT pk_seq_cambista PRIMARY KEY (seq_cambista);


--
-- TOC entry 1961 (class 2606 OID 140060)
-- Name: pk_seq_tipo_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_usuario
    ADD CONSTRAINT pk_seq_tipo_usuario PRIMARY KEY (seq_tipo_usuario);


--
-- TOC entry 1963 (class 2606 OID 140110)
-- Name: pk_seq_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_seq_usuario PRIMARY KEY (seq_usuario);


--
-- TOC entry 1965 (class 2606 OID 140112)
-- Name: usuario_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_cpf_key UNIQUE (cpf);


--
-- TOC entry 1967 (class 2606 OID 140114)
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- TOC entry 1968 (class 2606 OID 140120)
-- Name: configuracao_cambista_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY configuracao_cambista
    ADD CONSTRAINT configuracao_cambista_fk FOREIGN KEY (cod_usuario) REFERENCES usuario(seq_usuario);


--
-- TOC entry 1969 (class 2606 OID 140115)
-- Name: fkcod_tipo_usuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fkcod_tipo_usuario FOREIGN KEY (cod_tipo_usuario) REFERENCES tipo_usuario(seq_tipo_usuario);


-- Completed on 2016-03-21 10:04:27

--
-- PostgreSQL database dump complete
--

