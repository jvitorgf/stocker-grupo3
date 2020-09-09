--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-1.pgdg18.04+1)
-- Dumped by pg_dump version 12.4 (Ubuntu 12.4-1.pgdg18.04+1)

-- Started on 2020-09-09 13:40:17 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 202 (class 1259 OID 16440)
-- Name: item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item (
    id integer NOT NULL,
    nome character varying NOT NULL,
    descricao character varying,
    valor numeric(6,2) NOT NULL
);


ALTER TABLE public.item OWNER TO postgres;

--
-- TOC entry 2931 (class 0 OID 16440)
-- Dependencies: 202
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.item (id, nome, descricao, valor) VALUES (1, 'Arroz', 'Alimento', 5.00);
INSERT INTO public.item (id, nome, descricao, valor) VALUES (2, 'Feijão', 'teste', 2.00);


--
-- TOC entry 2804 (class 2606 OID 16447)
-- Name: item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


-- Completed on 2020-09-09 13:40:17 -03

--
-- PostgreSQL database dump complete
--

