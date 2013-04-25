--
-- PostgreSQL database dump
--

-- Started on 2013-04-24 15:16:00 VET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 140 (class 1259 OID 185914)
-- Dependencies: 6
-- Name: areas; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE areas (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_area bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.areas OWNER TO rnsii;

--
-- TOC entry 141 (class 1259 OID 185917)
-- Dependencies: 6 140
-- Name: areas_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE areas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.areas_id_seq OWNER TO rnsii;

--
-- TOC entry 2048 (class 0 OID 0)
-- Dependencies: 141
-- Name: areas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE areas_id_seq OWNED BY areas.id;


--
-- TOC entry 2049 (class 0 OID 0)
-- Dependencies: 141
-- Name: areas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('areas_id_seq', 3, true);


--
-- TOC entry 142 (class 1259 OID 185919)
-- Dependencies: 6
-- Name: arquitecturas; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE arquitecturas (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_arquitectura bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.arquitecturas OWNER TO rnsii;

--
-- TOC entry 143 (class 1259 OID 185922)
-- Dependencies: 6 142
-- Name: arquitecturas_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE arquitecturas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arquitecturas_id_seq OWNER TO rnsii;

--
-- TOC entry 2050 (class 0 OID 0)
-- Dependencies: 143
-- Name: arquitecturas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE arquitecturas_id_seq OWNED BY arquitecturas.id;


--
-- TOC entry 2051 (class 0 OID 0)
-- Dependencies: 143
-- Name: arquitecturas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('arquitecturas_id_seq', 2, true);


--
-- TOC entry 144 (class 1259 OID 185924)
-- Dependencies: 6
-- Name: aspectos_legales; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE aspectos_legales (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_aspecto_legal bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    url character varying(255),
    mod_user bigint
);


ALTER TABLE public.aspectos_legales OWNER TO rnsii;

--
-- TOC entry 145 (class 1259 OID 185930)
-- Dependencies: 6 144
-- Name: aspectos_legales_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE aspectos_legales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.aspectos_legales_id_seq OWNER TO rnsii;

--
-- TOC entry 2052 (class 0 OID 0)
-- Dependencies: 145
-- Name: aspectos_legales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE aspectos_legales_id_seq OWNED BY aspectos_legales.id;


--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 145
-- Name: aspectos_legales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('aspectos_legales_id_seq', 5, true);


--
-- TOC entry 146 (class 1259 OID 185932)
-- Dependencies: 6
-- Name: correos; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE correos (
    id bigint NOT NULL,
    correo character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_correo bigint NOT NULL,
    id_ente bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.correos OWNER TO rnsii;

--
-- TOC entry 147 (class 1259 OID 185935)
-- Dependencies: 6 146
-- Name: correos_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE correos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.correos_id_seq OWNER TO rnsii;

--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 147
-- Name: correos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE correos_id_seq OWNED BY correos.id;


--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 147
-- Name: correos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('correos_id_seq', 43, true);


--
-- TOC entry 148 (class 1259 OID 185937)
-- Dependencies: 6
-- Name: entes; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE entes (
    id bigint NOT NULL,
    direccion character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_padre bigint NOT NULL,
    nombre character varying(255),
    rif character varying(255),
    siglas character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.entes OWNER TO rnsii;

--
-- TOC entry 149 (class 1259 OID 185943)
-- Dependencies: 148 6
-- Name: entes_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE entes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.entes_id_seq OWNER TO rnsii;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 149
-- Name: entes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE entes_id_seq OWNED BY entes.id;


--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 149
-- Name: entes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('entes_id_seq', 1, false);


--
-- TOC entry 150 (class 1259 OID 185945)
-- Dependencies: 6
-- Name: entradas_salidas; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE entradas_salidas (
    id bigint NOT NULL,
    descripcion character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_entrada_salida bigint NOT NULL,
    id_formato bigint NOT NULL,
    id_funcionalidad bigint NOT NULL,
    id_padre bigint NOT NULL,
    id_tipo_dato bigint NOT NULL,
    id_usuario bigint NOT NULL,
    longitud character varying(255),
    nombre character varying(255),
    status integer NOT NULL,
    tipo integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.entradas_salidas OWNER TO rnsii;

--
-- TOC entry 151 (class 1259 OID 185951)
-- Dependencies: 150 6
-- Name: entradas_salidas_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE entradas_salidas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.entradas_salidas_id_seq OWNER TO rnsii;

--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 151
-- Name: entradas_salidas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE entradas_salidas_id_seq OWNED BY entradas_salidas.id;


--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 151
-- Name: entradas_salidas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('entradas_salidas_id_seq', 76, true);


--
-- TOC entry 152 (class 1259 OID 185953)
-- Dependencies: 6
-- Name: estados; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE estados (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_estado bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.estados OWNER TO rnsii;

--
-- TOC entry 153 (class 1259 OID 185956)
-- Dependencies: 152 6
-- Name: estados_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE estados_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.estados_id_seq OWNER TO rnsii;

--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 153
-- Name: estados_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE estados_id_seq OWNED BY estados.id;


--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 153
-- Name: estados_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('estados_id_seq', 2, true);


--
-- TOC entry 154 (class 1259 OID 185958)
-- Dependencies: 6
-- Name: formato; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE formato (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    formato character varying(255),
    id_formato bigint NOT NULL,
    id_tipo_dato bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.formato OWNER TO rnsii;

--
-- TOC entry 155 (class 1259 OID 185961)
-- Dependencies: 6 154
-- Name: formato_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE formato_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.formato_id_seq OWNER TO rnsii;

--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 155
-- Name: formato_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE formato_id_seq OWNED BY formato.id;


--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 155
-- Name: formato_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('formato_id_seq', 17, true);


--
-- TOC entry 156 (class 1259 OID 185963)
-- Dependencies: 6
-- Name: funcionalidades; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE funcionalidades (
    id bigint NOT NULL,
    descripcion character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_funcionalidad bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.funcionalidades OWNER TO rnsii;

--
-- TOC entry 157 (class 1259 OID 185969)
-- Dependencies: 156 6
-- Name: funcionalidades_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE funcionalidades_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.funcionalidades_id_seq OWNER TO rnsii;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 157
-- Name: funcionalidades_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE funcionalidades_id_seq OWNED BY funcionalidades.id;


--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 157
-- Name: funcionalidades_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('funcionalidades_id_seq', 61, true);


--
-- TOC entry 158 (class 1259 OID 185971)
-- Dependencies: 6
-- Name: intercambios; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE intercambios (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_intercambio bigint NOT NULL,
    id_padre bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.intercambios OWNER TO rnsii;

--
-- TOC entry 159 (class 1259 OID 185974)
-- Dependencies: 6 158
-- Name: intercambios_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE intercambios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.intercambios_id_seq OWNER TO rnsii;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 159
-- Name: intercambios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE intercambios_id_seq OWNED BY intercambios.id;


--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 159
-- Name: intercambios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('intercambios_id_seq', 5, true);


--
-- TOC entry 160 (class 1259 OID 185976)
-- Dependencies: 6
-- Name: nacionalidad; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE nacionalidad (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_nacionalidad bigint NOT NULL,
    mod_user bigint NOT NULL,
    nombre character varying(128),
    status integer NOT NULL
);


ALTER TABLE public.nacionalidad OWNER TO rnsii;

--
-- TOC entry 161 (class 1259 OID 185979)
-- Dependencies: 6 160
-- Name: nacionalidad_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE nacionalidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.nacionalidad_id_seq OWNER TO rnsii;

--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 161
-- Name: nacionalidad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE nacionalidad_id_seq OWNED BY nacionalidad.id;


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 161
-- Name: nacionalidad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('nacionalidad_id_seq', 1, false);


--
-- TOC entry 162 (class 1259 OID 185981)
-- Dependencies: 6
-- Name: recuperar_clave; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE recuperar_clave (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_recuperar_clave bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    url character varying(255),
    mod_user bigint
);


ALTER TABLE public.recuperar_clave OWNER TO rnsii;

--
-- TOC entry 163 (class 1259 OID 185984)
-- Dependencies: 162 6
-- Name: recuperar_clave_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE recuperar_clave_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.recuperar_clave_id_seq OWNER TO rnsii;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 163
-- Name: recuperar_clave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE recuperar_clave_id_seq OWNED BY recuperar_clave.id;


--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 163
-- Name: recuperar_clave_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('recuperar_clave_id_seq', 12, true);


--
-- TOC entry 164 (class 1259 OID 185986)
-- Dependencies: 6
-- Name: roles; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE roles (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_rol bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.roles OWNER TO rnsii;

--
-- TOC entry 165 (class 1259 OID 185989)
-- Dependencies: 6 164
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO rnsii;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 165
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 165
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('roles_id_seq', 1, false);


--
-- TOC entry 166 (class 1259 OID 185991)
-- Dependencies: 6
-- Name: sectores; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE sectores (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_sector bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.sectores OWNER TO rnsii;

--
-- TOC entry 167 (class 1259 OID 185994)
-- Dependencies: 166 6
-- Name: sectores_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE sectores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sectores_id_seq OWNER TO rnsii;

--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 167
-- Name: sectores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE sectores_id_seq OWNED BY sectores.id;


--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 167
-- Name: sectores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('sectores_id_seq', 17, true);


--
-- TOC entry 168 (class 1259 OID 185996)
-- Dependencies: 6
-- Name: seguridad; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE seguridad (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_seguridad bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.seguridad OWNER TO rnsii;

--
-- TOC entry 169 (class 1259 OID 185999)
-- Dependencies: 6 168
-- Name: seguridad_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE seguridad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.seguridad_id_seq OWNER TO rnsii;

--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 169
-- Name: seguridad_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE seguridad_id_seq OWNED BY seguridad.id;


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 169
-- Name: seguridad_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('seguridad_id_seq', 3, true);


--
-- TOC entry 170 (class 1259 OID 186001)
-- Dependencies: 6
-- Name: servicios_informacion; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE servicios_informacion (
    id bigint NOT NULL,
    descripcion character varying(765),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_estado bigint NOT NULL,
    id_intercambio bigint NOT NULL,
    id_sector bigint NOT NULL,
    id_seguridad bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    nombre character varying(255),
    publicado boolean NOT NULL,
    responsable character varying(255),
    status integer NOT NULL,
    version character varying(255),
    mod_user bigint
);


ALTER TABLE public.servicios_informacion OWNER TO rnsii;

--
-- TOC entry 171 (class 1259 OID 186007)
-- Dependencies: 6 170
-- Name: servicios_informacion_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE servicios_informacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.servicios_informacion_id_seq OWNER TO rnsii;

--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 171
-- Name: servicios_informacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE servicios_informacion_id_seq OWNED BY servicios_informacion.id;


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 171
-- Name: servicios_informacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('servicios_informacion_id_seq', 74, true);


--
-- TOC entry 172 (class 1259 OID 186009)
-- Dependencies: 6
-- Name: solicitudes_suscripciones; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE solicitudes_suscripciones (
    id bigint NOT NULL,
    cargo character varying(255),
    correo character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente_proveedor bigint NOT NULL,
    id_ente_solicitante bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_solicitud_suscripcion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    leido boolean NOT NULL,
    motivo_proveedor character varying(255),
    motivo_solicitante character varying(255),
    sentencia integer NOT NULL,
    solicitante character varying(255),
    status integer NOT NULL,
    telefono character varying(255),
    mod_user bigint
);


ALTER TABLE public.solicitudes_suscripciones OWNER TO rnsii;

--
-- TOC entry 173 (class 1259 OID 186015)
-- Dependencies: 6 172
-- Name: solicitudes_suscripciones_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE solicitudes_suscripciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.solicitudes_suscripciones_id_seq OWNER TO rnsii;

--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 173
-- Name: solicitudes_suscripciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE solicitudes_suscripciones_id_seq OWNED BY solicitudes_suscripciones.id;


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 173
-- Name: solicitudes_suscripciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('solicitudes_suscripciones_id_seq', 1, false);


--
-- TOC entry 174 (class 1259 OID 186017)
-- Dependencies: 6
-- Name: suscritos; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE suscritos (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_suscrito bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.suscritos OWNER TO rnsii;

--
-- TOC entry 175 (class 1259 OID 186020)
-- Dependencies: 6 174
-- Name: suscritos_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE suscritos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.suscritos_id_seq OWNER TO rnsii;

--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 175
-- Name: suscritos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE suscritos_id_seq OWNED BY suscritos.id;


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 175
-- Name: suscritos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('suscritos_id_seq', 1, false);


--
-- TOC entry 176 (class 1259 OID 186022)
-- Dependencies: 6
-- Name: telefonos; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE telefonos (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_telefono bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    telefono character varying(255),
    mod_user bigint
);


ALTER TABLE public.telefonos OWNER TO rnsii;

--
-- TOC entry 177 (class 1259 OID 186025)
-- Dependencies: 176 6
-- Name: telefonos_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE telefonos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.telefonos_id_seq OWNER TO rnsii;

--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 177
-- Name: telefonos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE telefonos_id_seq OWNED BY telefonos.id;


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 177
-- Name: telefonos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('telefonos_id_seq', 32, true);


--
-- TOC entry 178 (class 1259 OID 186027)
-- Dependencies: 6
-- Name: tipos_datos; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE tipos_datos (
    id bigint NOT NULL,
    descripcion character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_tipo_dato bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    tipo integer NOT NULL,
    haslength boolean,
    hasformatted boolean,
    mod_user bigint
);


ALTER TABLE public.tipos_datos OWNER TO rnsii;

--
-- TOC entry 179 (class 1259 OID 186033)
-- Dependencies: 178 6
-- Name: tipos_datos_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE tipos_datos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.tipos_datos_id_seq OWNER TO rnsii;

--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 179
-- Name: tipos_datos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE tipos_datos_id_seq OWNED BY tipos_datos.id;


--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 179
-- Name: tipos_datos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('tipos_datos_id_seq', 1, false);


--
-- TOC entry 180 (class 1259 OID 186035)
-- Dependencies: 6
-- Name: union_areas_servicios_informacion; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE union_areas_servicios_informacion (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_area bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.union_areas_servicios_informacion OWNER TO rnsii;

--
-- TOC entry 181 (class 1259 OID 186038)
-- Dependencies: 6 180
-- Name: union_areas_servicios_informacion_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE union_areas_servicios_informacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.union_areas_servicios_informacion_id_seq OWNER TO rnsii;

--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 181
-- Name: union_areas_servicios_informacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE union_areas_servicios_informacion_id_seq OWNED BY union_areas_servicios_informacion.id;


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 181
-- Name: union_areas_servicios_informacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('union_areas_servicios_informacion_id_seq', 103, true);


--
-- TOC entry 182 (class 1259 OID 186040)
-- Dependencies: 6
-- Name: union_arquitecturas_servicios_informacion; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE union_arquitecturas_servicios_informacion (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_arquitectura bigint NOT NULL,
    id_servicio_informacion bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.union_arquitecturas_servicios_informacion OWNER TO rnsii;

--
-- TOC entry 183 (class 1259 OID 186043)
-- Dependencies: 182 6
-- Name: union_arquitecturas_servicios_informacion_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE union_arquitecturas_servicios_informacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.union_arquitecturas_servicios_informacion_id_seq OWNER TO rnsii;

--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 183
-- Name: union_arquitecturas_servicios_informacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE union_arquitecturas_servicios_informacion_id_seq OWNED BY union_arquitecturas_servicios_informacion.id;


--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 183
-- Name: union_arquitecturas_servicios_informacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('union_arquitecturas_servicios_informacion_id_seq', 27, true);


--
-- TOC entry 184 (class 1259 OID 186045)
-- Dependencies: 6
-- Name: union_sectores_entes; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE union_sectores_entes (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_sector bigint NOT NULL,
    id_usuario bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.union_sectores_entes OWNER TO rnsii;

--
-- TOC entry 185 (class 1259 OID 186048)
-- Dependencies: 6 184
-- Name: union_sectores_entes_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE union_sectores_entes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.union_sectores_entes_id_seq OWNER TO rnsii;

--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 185
-- Name: union_sectores_entes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE union_sectores_entes_id_seq OWNED BY union_sectores_entes.id;


--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 185
-- Name: union_sectores_entes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('union_sectores_entes_id_seq', 1, false);


--
-- TOC entry 186 (class 1259 OID 186050)
-- Dependencies: 6
-- Name: union_usuarios_roles; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE union_usuarios_roles (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_sector bigint NOT NULL,
    status integer NOT NULL,
    mod_user bigint
);


ALTER TABLE public.union_usuarios_roles OWNER TO rnsii;

--
-- TOC entry 187 (class 1259 OID 186053)
-- Dependencies: 186 6
-- Name: union_usuarios_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE union_usuarios_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.union_usuarios_roles_id_seq OWNER TO rnsii;

--
-- TOC entry 2094 (class 0 OID 0)
-- Dependencies: 187
-- Name: union_usuarios_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE union_usuarios_roles_id_seq OWNED BY union_usuarios_roles.id;


--
-- TOC entry 2095 (class 0 OID 0)
-- Dependencies: 187
-- Name: union_usuarios_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('union_usuarios_roles_id_seq', 1, false);


--
-- TOC entry 188 (class 1259 OID 186055)
-- Dependencies: 6
-- Name: url; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE url (
    id bigint NOT NULL,
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_ente bigint NOT NULL,
    id_url bigint NOT NULL,
    status integer NOT NULL,
    url character varying(255),
    id_servicio_informacion bigint,
    mod_user bigint
);


ALTER TABLE public.url OWNER TO rnsii;

--
-- TOC entry 189 (class 1259 OID 186058)
-- Dependencies: 188 6
-- Name: url_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE url_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.url_id_seq OWNER TO rnsii;

--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 189
-- Name: url_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE url_id_seq OWNED BY url.id;


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 189
-- Name: url_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('url_id_seq', 15, true);


--
-- TOC entry 190 (class 1259 OID 186060)
-- Dependencies: 6
-- Name: usuarios; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE usuarios (
    id bigint NOT NULL,
    apellido character varying(255),
    cedula character varying(255),
    clave character varying(255),
    fecha_creado timestamp without time zone,
    fecha_modificado timestamp without time zone,
    id_correo bigint NOT NULL,
    id_ente bigint NOT NULL,
    id_usuario bigint NOT NULL,
    nombre character varying(255),
    status integer NOT NULL,
    mod_user bigint,
    nacionalidad character varying(1)
);


ALTER TABLE public.usuarios OWNER TO rnsii;

--
-- TOC entry 191 (class 1259 OID 186066)
-- Dependencies: 190 6
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO rnsii;

--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 191
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE usuarios_id_seq OWNED BY usuarios.id;


--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 191
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('usuarios_id_seq', 30, true);


--
-- TOC entry 192 (class 1259 OID 186068)
-- Dependencies: 6
-- Name: visitas; Type: TABLE; Schema: public; Owner: rnsii; Tablespace: 
--

CREATE TABLE visitas (
    id_visita bigint NOT NULL,
    fecha timestamp without time zone,
    id_servicio_informacion bigint NOT NULL,
    ip character varying(255),
    mod_user bigint
);


ALTER TABLE public.visitas OWNER TO rnsii;

--
-- TOC entry 193 (class 1259 OID 186071)
-- Dependencies: 6 192
-- Name: visitas_id_visita_seq; Type: SEQUENCE; Schema: public; Owner: rnsii
--

CREATE SEQUENCE visitas_id_visita_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.visitas_id_visita_seq OWNER TO rnsii;

--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 193
-- Name: visitas_id_visita_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: rnsii
--

ALTER SEQUENCE visitas_id_visita_seq OWNED BY visitas.id_visita;


--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 193
-- Name: visitas_id_visita_seq; Type: SEQUENCE SET; Schema: public; Owner: rnsii
--

SELECT pg_catalog.setval('visitas_id_visita_seq', 61, true);


--
-- TOC entry 1935 (class 2604 OID 186073)
-- Dependencies: 141 140
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY areas ALTER COLUMN id SET DEFAULT nextval('areas_id_seq'::regclass);


--
-- TOC entry 1936 (class 2604 OID 186074)
-- Dependencies: 143 142
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY arquitecturas ALTER COLUMN id SET DEFAULT nextval('arquitecturas_id_seq'::regclass);


--
-- TOC entry 1937 (class 2604 OID 186075)
-- Dependencies: 145 144
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY aspectos_legales ALTER COLUMN id SET DEFAULT nextval('aspectos_legales_id_seq'::regclass);


--
-- TOC entry 1938 (class 2604 OID 186076)
-- Dependencies: 147 146
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY correos ALTER COLUMN id SET DEFAULT nextval('correos_id_seq'::regclass);


--
-- TOC entry 1939 (class 2604 OID 186077)
-- Dependencies: 149 148
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY entes ALTER COLUMN id SET DEFAULT nextval('entes_id_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 186078)
-- Dependencies: 151 150
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY entradas_salidas ALTER COLUMN id SET DEFAULT nextval('entradas_salidas_id_seq'::regclass);


--
-- TOC entry 1941 (class 2604 OID 186079)
-- Dependencies: 153 152
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY estados ALTER COLUMN id SET DEFAULT nextval('estados_id_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 186080)
-- Dependencies: 155 154
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY formato ALTER COLUMN id SET DEFAULT nextval('formato_id_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 186081)
-- Dependencies: 157 156
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY funcionalidades ALTER COLUMN id SET DEFAULT nextval('funcionalidades_id_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 186082)
-- Dependencies: 159 158
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY intercambios ALTER COLUMN id SET DEFAULT nextval('intercambios_id_seq'::regclass);


--
-- TOC entry 1945 (class 2604 OID 186083)
-- Dependencies: 161 160
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY nacionalidad ALTER COLUMN id SET DEFAULT nextval('nacionalidad_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 186084)
-- Dependencies: 163 162
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY recuperar_clave ALTER COLUMN id SET DEFAULT nextval('recuperar_clave_id_seq'::regclass);


--
-- TOC entry 1947 (class 2604 OID 186085)
-- Dependencies: 165 164
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);


--
-- TOC entry 1948 (class 2604 OID 186086)
-- Dependencies: 167 166
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY sectores ALTER COLUMN id SET DEFAULT nextval('sectores_id_seq'::regclass);


--
-- TOC entry 1949 (class 2604 OID 186087)
-- Dependencies: 169 168
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY seguridad ALTER COLUMN id SET DEFAULT nextval('seguridad_id_seq'::regclass);


--
-- TOC entry 1950 (class 2604 OID 186088)
-- Dependencies: 171 170
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY servicios_informacion ALTER COLUMN id SET DEFAULT nextval('servicios_informacion_id_seq'::regclass);


--
-- TOC entry 1951 (class 2604 OID 186089)
-- Dependencies: 173 172
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY solicitudes_suscripciones ALTER COLUMN id SET DEFAULT nextval('solicitudes_suscripciones_id_seq'::regclass);


--
-- TOC entry 1952 (class 2604 OID 186090)
-- Dependencies: 175 174
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY suscritos ALTER COLUMN id SET DEFAULT nextval('suscritos_id_seq'::regclass);


--
-- TOC entry 1953 (class 2604 OID 186091)
-- Dependencies: 177 176
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY telefonos ALTER COLUMN id SET DEFAULT nextval('telefonos_id_seq'::regclass);


--
-- TOC entry 1954 (class 2604 OID 186092)
-- Dependencies: 179 178
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY tipos_datos ALTER COLUMN id SET DEFAULT nextval('tipos_datos_id_seq'::regclass);


--
-- TOC entry 1955 (class 2604 OID 186093)
-- Dependencies: 181 180
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY union_areas_servicios_informacion ALTER COLUMN id SET DEFAULT nextval('union_areas_servicios_informacion_id_seq'::regclass);


--
-- TOC entry 1956 (class 2604 OID 186094)
-- Dependencies: 183 182
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY union_arquitecturas_servicios_informacion ALTER COLUMN id SET DEFAULT nextval('union_arquitecturas_servicios_informacion_id_seq'::regclass);


--
-- TOC entry 1957 (class 2604 OID 186095)
-- Dependencies: 185 184
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY union_sectores_entes ALTER COLUMN id SET DEFAULT nextval('union_sectores_entes_id_seq'::regclass);


--
-- TOC entry 1958 (class 2604 OID 186096)
-- Dependencies: 187 186
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY union_usuarios_roles ALTER COLUMN id SET DEFAULT nextval('union_usuarios_roles_id_seq'::regclass);


--
-- TOC entry 1959 (class 2604 OID 186097)
-- Dependencies: 189 188
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY url ALTER COLUMN id SET DEFAULT nextval('url_id_seq'::regclass);


--
-- TOC entry 1960 (class 2604 OID 186098)
-- Dependencies: 191 190
-- Name: id; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY usuarios ALTER COLUMN id SET DEFAULT nextval('usuarios_id_seq'::regclass);


--
-- TOC entry 1961 (class 2604 OID 186099)
-- Dependencies: 193 192
-- Name: id_visita; Type: DEFAULT; Schema: public; Owner: rnsii
--

ALTER TABLE ONLY visitas ALTER COLUMN id_visita SET DEFAULT nextval('visitas_id_visita_seq'::regclass);


--
-- TOC entry 2016 (class 0 OID 185914)
-- Dependencies: 140
-- Data for Name: areas; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY areas (id, fecha_creado, fecha_modificado, id_area, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	Natural	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	Jurídico	0	0
3	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	Gobierno	0	0
\.


--
-- TOC entry 2017 (class 0 OID 185919)
-- Dependencies: 142
-- Data for Name: arquitecturas; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY arquitecturas (id, fecha_creado, fecha_modificado, id_arquitectura, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	Servicio Web	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	Web Semántica	0	0
\.


--
-- TOC entry 2018 (class 0 OID 185924)
-- Dependencies: 144
-- Data for Name: aspectos_legales; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY aspectos_legales (id, fecha_creado, fecha_modificado, id_aspecto_legal, id_servicio_informacion, id_usuario, nombre, status, url, mod_user) FROM stdin;
2	2012-08-08 15:47:08	2012-08-08 16:06:43	2	9	2	Acuerdo de servicios	2	/archivos/saime/Acuerdo_de_servicios_1344457028943.pdf	0
4	2012-08-27 13:59:27	2012-08-27 13:59:27	4	12	4	xcvxc	0	/archivos/cgr/xcvxc_1346092167592.pdf	0
5	2012-11-19 11:21:31	2012-11-19 11:25:50	5	19	4	h	2	/archivos/cgr/h_1353340291469.pdf	4
3	2012-08-08 16:07:51	2012-11-19 11:30:01	3	10	2	Acuerdo de Servicio	2	/archivos/saime/Acuerdo_de_Servicio_1344458271070.pdf	2
1	2012-08-08 11:12:01	2012-11-19 11:36:25	1	1	7	SLA Canaima	2	/archivos/cnti/SLA_Canaima_1344440521475.pdf	7
\.


--
-- TOC entry 2019 (class 0 OID 185932)
-- Dependencies: 146
-- Data for Name: correos; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY correos (id, correo, fecha_creado, fecha_modificado, id_correo, id_ente, id_servicio_informacion, id_usuario, status, mod_user) FROM stdin;
20	soporte@cgr.gob.ve	2012-08-09 10:21:29	2012-09-18 15:32:03	20	0	12	0	1	4
1	me@me.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	0	0	1	0	0
2	saime@saime.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	0	0	2	0	0
3	seniat@seniat.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	0	0	3	0	0
4	cgr@cgr.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	4	0	0	4	0	0
5	mppeu@mppeu.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	5	0	0	5	0	0
7	cnti@cnti.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	7	0	0	7	0	0
8	inttt@inttt.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	8	0	0	8	0	0
9	mintra@mintra.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	9	0	0	9	0	0
10	ac@ac.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	10	0	0	10	0	0
6	ivss@ivss.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	6	0	0	6	0	0
11	sbolivar@cnti.gob.ve	2012-08-08 11:13:03	2012-08-08 11:13:03	11	0	1	0	0	0
12	me@me.gob.ve	2012-08-08 13:42:57	2012-08-08 13:42:57	12	0	3	0	0	0
13	srodriguez@inttt.gob.ve	2012-08-08 13:44:26	2012-08-08 13:44:26	13	0	2	0	0	0
14	abello@mintra.gob.ve	2012-08-08 14:09:43	2012-08-08 14:09:43	14	0	5	0	0	0
15	fmiranda@ivss.gob.ve	2012-08-08 14:35:24	2012-08-08 14:35:24	15	0	7	0	0	0
16	jpaez@ac.gob.ve	2012-08-08 15:01:41	2012-08-08 15:01:41	16	0	8	0	0	0
17	soporte@saime.gob.ve	2012-08-08 15:51:17	2012-08-08 15:51:17	17	0	9	0	0	0
18	soporte@saime.gob.ve	2012-08-08 16:28:24	2012-08-08 16:28:24	18	0	10	0	0	0
19	soporte@seniat.gob.ve	2012-08-09 10:09:16	2012-08-09 10:09:16	19	0	11	0	0	0
21	soporte@mppeu.gob.ve	2012-08-09 10:29:29	2012-08-09 10:29:29	21	0	13	0	0	0
22	soporte@cgr.gob.ve	2012-08-09 10:21:29	2012-09-18 15:32:10	20	0	12	0	1	4
23	soporte@cgr.gob.ve	2012-08-09 10:21:29	2012-09-18 15:36:45	20	0	12	0	1	4
24	soporte@cgr.gob.ve	2012-08-09 10:21:29	2012-08-09 10:21:29	20	0	12	0	0	0
25	jpereira@cnti.gob.ve	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	22	0	0	11	0	0
26	sdfsdf@sdgh.com	2012-10-23 15:26:58	2012-10-23 15:27:16	23	0	14	0	1	4
27	sdfsdf@sdgh.com	2012-10-23 15:26:58	2012-10-23 15:50:47	23	0	14	0	1	4
28	sdfsdf@sdgh.com	2012-10-23 15:26:58	2012-10-23 15:51:08	23	0	14	0	1	4
29	sdfsdf@sdgh.com	2012-10-23 15:26:58	2012-10-23 15:26:58	23	0	14	0	0	4
30	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 16:08:28	24	0	17	0	1	4
31	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 16:15:14	24	0	17	0	1	4
32	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 16:16:26	24	0	17	0	1	4
33	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 16:18:04	24	0	17	0	1	4
34	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 16:19:07	24	0	17	0	1	4
36	sdfsdf@sdgh.com	2012-10-25 15:42:14	2012-10-25 15:42:14	24	0	17	0	0	4
35	test@cnti.gob.ve	2012-10-25 16:18:38	2012-10-25 16:19:38	25	0	15	0	1	4
37	test@cnti.gob.ve	2012-10-25 16:18:38	2012-10-25 16:18:38	25	0	15	0	0	4
38	cgr@cgr.gob.ve	2012-11-19 11:22:42	2012-11-19 11:23:46	26	0	19	0	1	4
39	cgr@cgr.gob.ve	2012-11-19 11:22:42	2012-11-19 11:24:19	26	0	19	0	1	4
40	cgr@cgr.gob.ve	2012-11-19 11:22:42	2012-11-19 11:22:42	26	0	19	0	0	4
41	wdfk@dfjsh.co	2012-12-18 09:14:21	2012-12-18 09:14:21	27	0	21	0	0	4
42	cnti@cnti.gob.ve	2013-04-24 14:56:41	2013-04-24 14:56:45	28	0	22	0	1	7
43	cnti@cnti.gob.ve	2013-04-24 14:56:41	2013-04-24 14:56:41	28	0	22	0	0	7
\.


--
-- TOC entry 2020 (class 0 OID 185937)
-- Dependencies: 148
-- Data for Name: entes; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY entes (id, direccion, fecha_creado, fecha_modificado, id_ente, id_padre, nombre, rif, siglas, status, mod_user) FROM stdin;
3	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	0	Servicio Nacional Integrado de Administración Aduanera y Tributaria	G-00000000	SENIAT	0	0
4	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	4	0	Contraloría General de la República	G-00000000	CGR	0	0
2	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	0	Servicio Administrativo de Identificación, Migración y Extranjería	G-00000000	SAIME	0	0
1	Sin información.	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	0	Ministerio del Poder Popular para la Educación	G-00000000	ME	0	0
5	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	5	0	Ministerio del Poder Popular para la Educación Universitaria	G-00000000	MPPEU	0	0
6	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	6	0	Instituto Venezolano de los Seguros Sociales	G-00000000	IVSS	0	0
7	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	7	0	Centro Nacional de Tecnologías de Información	G-00000000	CNTI	0	0
8	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	8	0	Instituto Nacional de Transporte y Tránsito Terrestre	G-00000000	INTTT	0	0
9	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	9	0	Ministerio del Poder Popular para el Trabajo	G-00000000	MINTRA	0	0
10	Sin información	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	10	0	Alcaldía de Caracas	G-00000000	AC	0	0
\.


--
-- TOC entry 2021 (class 0 OID 185945)
-- Dependencies: 150
-- Data for Name: entradas_salidas; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY entradas_salidas (id, descripcion, fecha_creado, fecha_modificado, id_entrada_salida, id_formato, id_funcionalidad, id_padre, id_tipo_dato, id_usuario, longitud, nombre, status, tipo, mod_user) FROM stdin;
65	lkñlk	2012-10-25 16:17:16	2012-10-25 16:17:46	50	-1	21	0	8	4	No aplica	hu 	2	0	4
68	Cédula de identidad del ciudadano el cual se le desea obtener la declaración jurada. s	2012-08-09 10:23:14	2012-11-07 14:37:58	25	2	10	0	3	4	8	Cédula de identidad	1	0	4
69	Cédula de identidad del ciudadano el cual se le desea obtener la declaración jurada. 	2012-08-09 10:23:14	2012-08-09 10:23:14	25	2	10	0	3	4	8	Cédula de identidad	0	0	0
67	Código de respuesta de la solicitud..	2012-08-09 10:23:54	2012-11-07 14:38:11	26	-1	10	0	2	4	255	Código de Respuesta	1	1	4
70	Código de respuesta de la solicitud.	2012-08-09 10:23:54	2012-08-09 10:23:54	26	-1	10	0	2	4	255	Código de Respuesta	0	1	0
66	ñkjoiju	2012-10-25 16:17:39	2012-11-19 11:25:46	51	-1	21	0	8	4	No aplica	luoiuo	2	1	4
75	sdf	2012-12-18 11:13:24	2012-12-18 11:27:02	55	5	25	54	5	4	No aplica	sdf	2	0	4
3	Código de error arrojado durante la verificación.	2012-08-08 11:36:40	2012-08-08 11:36:40	3	2	2	0	3	7	6	Código de Error	0	0	0
27	Código de respuesta de la solicitud.	2012-08-09 10:23:54	2012-11-07 14:26:41	26	-1	10	0	2	4	255	Código de Respuesta	1	1	4
1	El serial se encuentra en la parte inferior de su canaimita. Está seguido del código de barras. Está conformado por letras mayúsculas y números. Son 10 caracteres que deberá introducir.	2012-08-08 11:27:06	2012-08-08 11:27:06	1	-1	1	0	2	7	10	Serial	0	0	0
2	Se enviará la palabra SUCCESS si el código del producto no arroja ningún error. Se enviará la palabra ERROR si se encuentra un desperfecto en el equipo junto con la descripción del mismo.	2012-08-08 11:28:45	2012-08-08 11:28:45	2	-1	1	0	2	7	400	Estado de Verificación	0	1	0
4	Fecha de la cita para soporte técnico.	2012-08-08 11:37:03	2012-08-08 11:37:03	4	11	2	0	6	7	No aplica	Fecha de la cita	0	1	0
5	Parámetro cédula de identidad para comparar contra la base de datos y obtener el estado de la licencia.	2012-08-08 13:45:53	2012-08-08 13:45:53	5	-1	3	0	2	8	9	Cédula de Identidad	0	0	0
6	Devuelve el estado de licencia de una persona basado en el número de cédula. Estos estados pueden ser ACTIVO o INACTIVO. ERROR si no existe.	2012-08-08 13:49:57	2012-08-08 13:49:57	6	-1	3	0	2	8	18	Estado de Licencia	0	1	0
7	Número de cédula de identidad del ciudadano al que se le requiere el titulo de bachiller.	2012-08-08 13:53:38	2012-08-08 13:53:38	7	2	4	0	3	1	8	Cédula de identidad	0	0	0
38	dfg	2012-08-15 10:16:40	2012-08-15 10:47:14	31	-1	10	0	2	4	4	Cédula de identidad	1	0	0
8	RIF del organismo a conocer su estado de solvencia laboral.	2012-08-08 14:11:48	2012-08-08 14:11:48	8	-1	5	0	2	9	10	RIF	0	0	0
9	Retorna VERDADERO si el organismo está solvente. Retorna FALSO si no lo está.	2012-08-08 14:12:30	2012-08-08 14:12:30	9	5	5	0	5	9	No aplica	Estado de Solvencia	0	1	0
10	Cédula de identidad de la persona a la cual se le requiere saber si tiene activada o no la pensión por vejez.	2012-08-08 14:36:49	2012-08-08 14:36:49	10	-1	6	0	2	6	10	Cédula de Identidad	0	0	0
11	Indica si la pensión para esta persona está activada o no.	2012-08-08 14:37:30	2012-08-08 14:37:30	11	5	6	0	5	6	No aplica	Estado de Pensión	0	1	0
12	Cédula de identidad del menor de edad.	2012-08-08 15:06:32	2012-08-08 15:06:32	12	-1	7	0	2	10	10	Cédula de Identidad	0	0	0
13	Es el código de respuesta que se genera de acuerdo al estado de la transacción. 	2012-08-08 15:08:38	2012-08-08 15:08:38	13	-1	4	0	2	1	250	Código de Respuesta	0	1	0
14	Devuelve el estado de solicitud del permiso puede ser APROBADO RECHAZADO PENDIENTE o ERROR. Junto con una explicación del motivo.	2012-08-08 15:08:20	2012-08-08 15:08:20	14	-1	7	0	2	10	400	Estado del Permiso de Viaje	0	1	0
16	Número de cédula del ciudadano.	2012-08-09 09:38:05	2012-08-09 09:38:05	16	2	8	0	3	2	8	Número de Cédula	0	0	0
18	Apellidos completos del ciudadano.	2012-08-09 09:39:33	2012-08-09 09:39:33	18	-1	8	0	2	2	255	Apellidos	0	1	0
17	Nombre completo del ciudadano.	2012-08-09 09:38:44	2012-08-09 09:39:40	17	-1	8	0	2	2	255	Nombre	1	1	0
19	Nombre completo del ciudadano.	2012-08-09 09:38:44	2012-08-09 09:38:44	17	-1	8	0	2	2	255	Nombres	0	1	0
20	Fecha de Nacimiento del ciudadano.	2012-08-09 09:40:13	2012-08-09 09:40:13	19	9	8	0	6	2	No aplica	Fecha de Nacimiento	0	1	0
21	Lugar de Nacimiento del ciudadano. 	2012-08-09 09:40:56	2012-08-09 09:41:50	20	-1	8	0	2	2	255	Lugar de Nacimiento	2	1	0
22	Estado civil del ciudadano.	2012-08-09 09:42:17	2012-08-09 09:42:17	21	-1	8	0	2	2	255	Estado civil	0	1	0
23	Cédula de identidad del ciudadano para buscar su correspondiente RIF	2012-08-09 10:11:32	2012-08-09 10:11:32	22	2	9	0	3	3	8	Cédula de identidad	0	0	0
24	Código de Respuesta de la solicitud.	2012-08-09 10:12:19	2012-08-09 10:12:19	23	-1	9	0	2	3	255	Código de Respuesta	0	1	0
29	Cédula de identidad del ciudadano del cual se necesita obtener la Copia Certificada de Título de Universitario.	2012-08-09 10:31:31	2012-08-09 10:39:39	28	2	11	0	3	5	8	Cédula de identidad	1	0	0
32	Cédula de identidad del ciudadano del cual se necesita obtener la Copia Certificada del Título Universitario.	2012-08-09 10:31:31	2012-08-09 10:31:31	28	2	11	0	3	5	8	Cédula de identidad	0	0	0
30	Código de respuesta de la solicitud. 	2012-08-09 10:36:09	2012-08-09 10:40:44	29	-1	11	0	2	5	255	Código de Respuesta	1	1	0
33	Código de respuesta de la solicitud..	2012-08-09 10:36:09	2012-08-09 10:36:09	29	-1	11	0	2	5	255	Código de Respuesta	0	1	0
37	dfg	2012-08-15 10:16:40	2012-08-15 10:17:00	31	-1	10	0	2	4	4	dfgdfg	1	0	0
36	dfg	2012-08-15 10:16:40	2012-08-15 10:16:50	31	-1	10	0	2	4	4	dfgdfg	1	0	0
63	fff	2012-08-27 14:05:39	2012-08-27 14:05:54	49	8	20	48	6	4	No aplica	fffff	1	0	0
39	dfg	2012-08-15 10:16:40	2012-08-15 11:27:07	31	-1	10	0	2	4	4	Cédula de identidad dos	1	0	0
64	fff	2012-08-27 14:05:39	2012-08-27 14:05:39	49	8	20	48	6	4	No aplica	fffff dos	0	0	0
42	fg	2012-08-15 11:27:47	2012-08-15 11:28:19	33	-1	10	0	2	4	5	gf	2	0	0
43	dfg	2012-08-15 11:33:31	2012-08-15 11:33:48	34	1	10	31	3	4	5	fgdf	1	0	0
44	dfg	2012-08-15 11:33:31	2012-08-15 11:34:14	34	1	10	31	3	4	5	fgdf dos	2	0	0
41	dfg	2012-08-15 10:16:40	2012-08-15 11:34:32	31	-1	10	0	1	4	No aplica	Cédula de identidadfg	1	0	0
47	mmm	2012-08-15 11:34:55	2012-08-15 11:35:25	36	-1	10	0	1	4	No aplica	nm	1	1	0
48	jjj	2012-08-15 11:35:14	2012-08-15 11:35:39	37	3	10	36	4	4	7	jjj	1	1	0
50	jjj	2012-08-15 11:35:14	2012-08-15 11:36:29	37	3	10	36	4	4	7	jjj  yyy	2	1	0
51	bbbv	2012-08-15 11:35:55	2012-08-15 11:36:29	38	1	10	36	3	4	6	bngh	2	1	0
49	mmm	2012-08-15 11:34:55	2012-08-15 11:36:29	36	-1	10	0	1	4	No aplica	nm hh	2	1	0
45	ggg	2012-08-15 11:34:07	2012-08-15 11:36:46	35	8	10	31	6	4	No aplica	gggg	2	0	0
46	dfg	2012-08-15 10:16:40	2012-08-15 11:36:46	31	-1	10	0	1	4	No aplica	Cédula de identidadfg yy	2	0	0
40	wer	2012-08-15 11:06:10	2012-08-21 14:10:53	32	1	4	0	3	1	3	ewer	2	1	0
54	sd	2012-08-21 15:44:18	2012-08-21 15:44:18	41	-1	0	0	2	1	3	sd	0	0	0
55	sd	2012-08-21 15:44:37	2012-08-21 15:44:37	42	-1	0	0	2	1	6	sdd	0	0	0
52	sdf	2012-08-21 11:37:59	2012-08-24 12:00:52	39	-1	13	0	2	4	4	sdf	1	0	0
56	jejejejsdf	2012-08-21 11:37:59	2012-08-27 09:42:46	39	-1	13	0	2	4	90	gkaga	2	0	0
61	ssdf	2012-08-27 14:04:12	2012-08-27 14:04:12	47	-1	20	0	2	4	4	sdfsdf	0	1	0
62	sdf	2012-08-27 14:04:59	2012-08-27 14:04:59	48	-1	20	0	1	4	No aplica	dfsd	0	0	0
26	Cédula de identidad del ciudadano el cual se le desea obtener la declaración jurada.	2012-08-09 10:23:14	2012-11-07 14:37:49	25	2	10	0	3	4	8	Cédula de identidad	1	0	4
53	df	2012-08-21 11:38:13	2012-11-19 11:25:42	40	-1	13	0	2	4	4	df	2	1	4
57	fg	2012-08-27 09:42:38	2012-11-19 11:25:42	43	13	13	0	7	4	No aplica	fg	2	0	4
59	d	2012-08-27 09:43:24	2012-11-19 11:25:42	45	-1	13	0	1	4	No aplica	g	2	1	4
15	Certificado del Título de Bachillerato.	2012-08-08 15:09:46	2012-08-08 15:09:46	15	-1	4	0	2	1	1200	Certificado Digital de Título de Bachillerato	0	1	0
25	Registro de Información Fiscal RIF de una persona natural.	2012-08-09 10:13:02	2012-08-09 10:13:02	24	-1	9	0	2	3	500	Documento digital del Registro de Información Fiscal RIF	0	1	0
28	Constancia digital certificada de la declaración jurada de patrimonio del ciudadano consultado.	2012-08-09 10:24:38	2012-08-09 10:24:38	27	-1	10	0	2	4	800	Constancia digital certificada de la declaración jurada de patrimonio	0	1	0
31	Copia Certificada digital del Título de Universitario de un ciudadano. 	2012-08-09 10:37:02	2012-08-09 10:52:14	30	-1	11	0	2	5	500	Copia Certificada digital del Título de Universitario	1	1	0
34	Copia Certificada digital del Título de Universitario de un ciudadano.. 	2012-08-09 10:37:02	2012-08-09 13:00:49	30	-1	11	0	2	5	500	Copia Certificada digital del Título de Universitario	1	1	0
35	Copia Certificada digital del Título Universitario de un ciudadano.. 	2012-08-09 10:37:02	2012-08-09 10:37:02	30	-1	11	0	2	5	500	Copia Certificada digital del Título de Universitario	0	1	0
58	df	2012-08-27 09:43:06	2012-11-19 11:25:42	44	-1	13	0	2	4	500	dft	2	1	4
60	sdf	2012-08-27 14:02:37	2012-08-27 14:02:37	46	-1	20	0	2	4	500	sdf	0	0	0
71	dfg	2012-11-19 11:24:48	2012-11-19 11:25:50	52	-1	23	0	2	4	500	g	2	0	4
72	gfhgfh	2012-11-19 11:25:03	2012-11-19 11:25:50	53	-1	23	0	2	4	500	ghfgh	2	1	4
73	fgh	2012-12-18 11:06:57	2012-12-18 11:10:08	54	-1	25	0	2	4	500	gfh	1	0	4
74	fgh	2012-12-18 11:06:57	2012-12-18 11:27:02	54	-1	25	0	1	4	No aplica	gfh	2	0	4
76	ds	2013-04-24 14:56:24	2013-04-24 14:56:52	56	-1	26	0	2	7	3	ds	2	1	7
\.


--
-- TOC entry 2022 (class 0 OID 185953)
-- Dependencies: 152
-- Data for Name: estados; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY estados (id, fecha_creado, fecha_modificado, id_estado, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	En desarrollo	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	Implementado	0	0
\.


--
-- TOC entry 2023 (class 0 OID 185958)
-- Dependencies: 154
-- Data for Name: formato; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY formato (id, fecha_creado, fecha_modificado, formato, id_formato, id_tipo_dato, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	Con Signo	1	3	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	Sin Signo	2	3	0	0
5	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	True - False	5	5	0	0
6	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	Verdadero - Falso	6	5	0	0
7	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1 - 0	7	5	0	0
8	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	YYYY-MM-DD	8	6	0	0
11	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	DD-MM-YYYY	9	6	0	0
12	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	YYYY-MM-DDThh:mm:ss	10	6	0	0
13	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	DD-MM-YYYYThh:mm:ss	11	6	0	0
14	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	hh:mm:ss	12	7	0	0
15	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	hh:mm	13	7	0	0
16	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	Con Signo	3	4	0	0
17	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	Sin Signo	4	4	0	0
\.


--
-- TOC entry 2024 (class 0 OID 185963)
-- Dependencies: 156
-- Data for Name: funcionalidades; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY funcionalidades (id, descripcion, fecha_creado, fecha_modificado, id_funcionalidad, id_servicio_informacion, id_usuario, nombre, status, mod_user) FROM stdin;
58	343434	2012-11-19 11:24:36	2012-11-19 11:25:50	23	19	4	ewrwer	2	4
1	Esta operación le permite verificar su Canaimita introduciendo el número de Serial.	2012-08-08 11:25:32	2012-08-08 11:25:32	1	1	7	Verificar Canaimita	0	0
31	sdf fg	2012-08-21 11:37:49	2012-08-23 16:13:06	13	15	4	holaperrr	1	0
2	En base al código de error entonces le retorna la fecha de la cita para soporte.	2012-08-08 11:35:58	2012-08-08 11:35:58	2	1	7	Cita por Errores Técnicos	0	0
3	Esta operación recibe un número de cédula y el sistema devuelve el estado de la licencia de la persona o error si no existe la cédula.	2012-08-08 13:45:12	2012-08-08 13:45:12	3	2	8	Obtener Estado de Licencia	0	0
4	Entrega de certificado digital de Título de Bachillerato para los organismos y entes del estado que lo requieran. 	2012-08-08 13:50:13	2012-08-08 13:50:13	4	3	1	Entrega de certificado digital de Título de Bachillerato	0	0
5	Permite conocer el estado de solvencia basándose en el RIF del organismo.	2012-08-08 14:11:19	2012-08-08 14:11:19	5	5	9	Estado de Solvencia	0	0
6	Permite conocer si una persona tiene activa la pensión por vejez o no.	2012-08-08 14:35:52	2012-08-08 14:35:52	6	7	6	Estado de Pensión	0	0
7	Se evalúa conforme a la cédula de identidad del menor si es posible otorgarle el permiso para viajar.	2012-08-08 15:02:40	2012-08-08 15:02:40	7	8	10	Estado del Permiso	0	0
8	Funcionalidad que provee los datos de identificación ciudadanía de cualquier venezolano	2012-08-09 09:36:33	2012-08-09 09:36:33	8	10	2	Datos de identificación ciudadanía	0	0
9	Funcionalidad que provee el Documento digital certificado del Registro de Información Fiscal RIF de una persona natural.	2012-08-09 10:10:42	2012-08-09 10:10:42	9	11	3	Documento digital del Registro de Información Fiscal RIF de una persona natural	0	0
16	Funcionalidad que provee una copia certificada del Título Universitario de un ciudadano.	2012-08-09 10:30:26	2012-08-14 11:02:57	11	13	5	 Copia Certificada del Título Universitario	0	0
11	Funcionalidad que provee una copia certificada del Título de Universitario de un ciudadano.	2012-08-09 10:30:26	2012-08-09 10:38:33	11	13	5	 Copia Certificada de Título de Universitario	1	0
25	sdf fg	2012-08-21 11:37:49	2012-08-23 16:04:43	13	15	4	hola dfgdfg fff	1	0
10	Funcionalidad que provee la constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:22:17	2012-08-14 16:42:15	10	12	4	Constancia digital certificada de la declaración jurada de patrimonio	1	0
13	Verifica si su Canaimita tiene los contenidos actualizados.	2012-08-13 11:42:10	2012-08-13 13:28:22	12	1	7	Contenidos actualizados	2	0
12	Funcionalidad que provee una copia certificada del Título Universitario de un ciudadano.	2012-08-09 10:30:26	2012-08-14 10:51:06	11	13	5	 Copia Certificada del Título Universitario	1	0
14	Funcionalidad que provee una copia certificada del Título Universitario de un ciudadano.	2012-08-09 10:30:26	2012-08-14 11:02:53	11	13	5	 Copia Certificada del Título Universitario	1	0
15	Funcionalidad que provee una copia certificada del Título Universitario de un ciudadano.	2012-08-09 10:30:26	2012-08-14 11:02:57	11	13	5	 Copia Certificada del Título Universitario	1	0
17	Funcionalidad que provee la constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:22:17	2012-08-14 16:42:15	10	12	4	Constancia digital certificada de la declaración jurada de patrimonio	0	0
43	????	2012-08-24 11:41:13	2012-08-24 11:41:41	16	14	4	nueva	1	0
18	sdf	2012-08-21 11:37:49	2012-08-23 15:53:10	13	15	4	sdf	1	0
32	sdf fg	2012-08-21 11:37:49	2012-08-23 16:14:48	13	15	4	holaperrr	1	0
20	sdf fg	2012-08-21 11:37:49	2012-08-23 15:53:18	13	15	4	sdf dos	1	0
21	sdf fg	2012-08-21 11:37:49	2012-08-23 15:57:43	13	15	4	sdf dos g	1	0
39	??????	2012-08-24 11:28:20	2012-08-24 11:28:33	15	14	4	Funcionalidad Primera	1	0
22	sdf fg	2012-08-21 11:37:49	2012-08-23 15:58:15	13	15	4	hola	1	0
33	sdf fg	2012-08-21 11:37:49	2012-08-23 16:16:14	13	15	4	holaperrr  sd	1	0
23	sdf fg	2012-08-21 11:37:49	2012-08-23 15:58:29	13	15	4	holakdfhkdj  dfgdfj g	1	0
24	sdf fg	2012-08-21 11:37:49	2012-08-23 15:59:38	13	15	4	hola	1	0
26	sdf fg	2012-08-21 11:37:49	2012-08-23 16:04:59	13	15	4	hola 	1	0
27	sdf fg	2012-08-21 11:37:49	2012-08-23 16:05:42	13	15	4	hola fre	1	0
34	sdf fg	2012-08-21 11:37:49	2012-08-23 16:16:51	13	15	4	hola perro	1	0
28	sdf fg	2012-08-21 11:37:49	2012-08-23 16:09:21	13	15	4	hola fre	1	0
29	sdf fg	2012-08-21 11:37:49	2012-08-23 16:09:40	13	15	4	hola rana	1	0
30	sdf fg	2012-08-21 11:37:49	2012-08-23 16:12:13	13	15	4	hola rana	1	0
40	??????	2012-08-24 11:28:20	2012-08-24 11:30:37	15	14	4	Funcionalidad Primera	1	0
35	sdf fg	2012-08-21 11:37:49	2012-08-23 16:18:10	13	15	4	hola perro otra vez	1	0
36	sdf fg	2012-08-21 11:37:49	2012-08-23 16:19:02	13	15	4	hola perro	1	0
51	sdf fg	2012-08-21 11:37:49	2012-08-27 09:41:58	13	15	4	hola mundo	1	0
38	gkopawgka	2012-08-24 11:28:20	2012-08-24 11:28:33	15	14	4	Funcionalidad Primera	1	0
41	???	2012-08-24 11:28:20	2012-08-24 11:35:07	15	14	4	Funcionalidad Primera	1	0
44	vawpgawga	2012-08-24 11:41:13	2012-08-24 11:53:16	16	14	4	nueva	1	0
42	Hola	2012-08-24 11:28:20	2012-08-24 11:52:53	15	14	4	Funcionalidad Primera	1	0
37	sdf fg	2012-08-21 11:37:49	2012-08-24 11:56:13	13	15	4	hola perro	1	0
48	sdf fg	2012-08-21 11:37:49	2012-08-24 11:59:59	13	15	4	hola perra	1	0
49	sdf fg	2012-08-21 11:37:49	2012-08-27 09:41:44	13	15	4	hola perra loca	1	0
52	sdf fg	2012-08-21 11:37:49	2012-08-27 09:42:03	13	15	4	hola mundo c	1	0
55	fun dos	2012-08-27 14:02:05	2012-08-27 14:02:05	20	12	4	fun dos	0	0
54	fun dos	2012-08-27 14:02:03	2012-08-27 14:03:27	19	12	4	fun dos	2	0
19	df	2012-08-21 14:51:02	2012-11-19 11:29:11	14	3	1	df	2	1
50	jejej	2012-08-24 12:01:29	2012-10-25 16:18:49	18	15	4	hola perra loca	2	4
46	Hola	2012-08-24 11:28:20	2012-11-19 11:25:37	15	14	4	Funcionalidad Primera jgvfhgftcf 	2	4
47	vawpgawga	2012-08-24 11:41:13	2012-11-19 11:25:37	16	14	4	nueva ghgf ghvghvv  kmljk	2	4
45	awlgawjgajgoa	2012-08-24 11:49:02	2012-11-19 11:25:37	17	14	4	nueva	2	4
53	sdf fg	2012-08-21 11:37:49	2012-11-19 11:25:42	13	15	4	hola mundo 	2	4
56	hkj jkh jk jh	2012-10-25 16:16:58	2012-11-19 11:25:46	21	17	4	lkj j 	2	4
57	Esto es funcional	2012-11-07 09:43:14	2012-11-19 11:36:18	22	18	7	Nueva funcionalidad	2	7
59	djkfhsdjk jksdhfjk  sdjkhf ksdkj hkjsdh jkhsdf kjhsd	2012-12-18 09:15:28	2013-04-09 07:39:47	24	21	4	ajghsdhg	2	4
60	fgh	2012-12-18 10:17:53	2013-04-09 07:39:47	25	21	4	gfh	2	4
61	dsds	2013-04-24 14:56:13	2013-04-24 14:56:52	26	22	7	sds	2	7
\.


--
-- TOC entry 2025 (class 0 OID 185971)
-- Dependencies: 158
-- Data for Name: intercambios; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY intercambios (id, fecha_creado, fecha_modificado, id_intercambio, id_padre, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	0	En Línea	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	0	Fuera de Línea	0	0
3	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	1	Síncrono	0	0
4	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	4	1	Asíncrono	0	0
5	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	5	2	En Lote	0	0
\.


--
-- TOC entry 2026 (class 0 OID 185976)
-- Dependencies: 160
-- Data for Name: nacionalidad; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY nacionalidad (id, fecha_creado, fecha_modificado, id_nacionalidad, mod_user, nombre, status) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	0	V	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	0	E	0
\.


--
-- TOC entry 2027 (class 0 OID 185981)
-- Dependencies: 162
-- Data for Name: recuperar_clave; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY recuperar_clave (id, fecha_creado, fecha_modificado, id_recuperar_clave, id_usuario, status, url, mod_user) FROM stdin;
11	2012-09-21 14:10:02	2012-09-21 14:10:02	1	11	0	8d8fc7c8bf695919549864fd685ea6d9c4ca4238a0b923820dcc509a6f75849b	4
12	2012-09-21 14:14:07	2012-09-21 14:14:07	2	4	0	34d50ba50e7d9c266eea273095d52e84c81e728d9d4c2f636f067f89cc14862c	4
\.


--
-- TOC entry 2028 (class 0 OID 185986)
-- Dependencies: 164
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY roles (id, fecha_creado, fecha_modificado, id_rol, nombre, status, mod_user) FROM stdin;
\.


--
-- TOC entry 2029 (class 0 OID 185991)
-- Dependencies: 166
-- Data for Name: sectores; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY sectores (id, fecha_creado, fecha_modificado, id_sector, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	Ciencia y Tecnología	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	Salud	0	0
3	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	Educación	0	0
4	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	4	Transporte	0	0
5	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	5	Comercio	0	0
6	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	6	Finanzas, Banca y Seguros	0	0
7	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	7	Energía y Minas	0	0
8	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	8	Alimentación	0	0
9	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	9	Agrícola	0	0
10	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	10	Cultura	0	0
11	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	11	Vivienda	0	0
12	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	12	Ambiente y Turismo	0	0
13	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	13	Defensa y Política Exterior	0	0
14	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	14	Protección Social	0	0
15	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	15	Legislativo, Contraloría y Auditoría	0	0
16	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	16	Judicial	0	0
17	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	17	Comunicación e Información	0	0
\.


--
-- TOC entry 2030 (class 0 OID 185996)
-- Dependencies: 168
-- Data for Name: seguridad; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY seguridad (id, fecha_creado, fecha_modificado, id_seguridad, nombre, status, mod_user) FROM stdin;
1	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	Público	0	0
2	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	Irrestricto	0	0
3	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	Autorizable	0	0
\.


--
-- TOC entry 2031 (class 0 OID 186001)
-- Dependencies: 170
-- Data for Name: servicios_informacion; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY servicios_informacion (id, descripcion, fecha_creado, fecha_modificado, id_ente, id_estado, id_intercambio, id_sector, id_seguridad, id_servicio_informacion, id_usuario, nombre, publicado, responsable, status, version, mod_user) FROM stdin;
27	sdfsd f	2012-08-21 09:58:39	2012-10-23 15:26:58	4	1	0	8	0	14	4	fsjdf	f	\N	1	\N	4
7	El INTTT pone a disposición del Estado este servicio para consultar los ciudadanos que poseen licencia vigente.	2012-08-08 11:46:07	2012-08-08 13:43:54.871	8	2	4	4	2	2	8	Licencia de conducir	t	Simón Rodríguez	0	1.3	0
24	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-08-29 15:44:04	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	0
5	El Ministerio del Poder Popular para la Educación otorga un documento digital  certificado del Título de Bachiller.	2012-08-08 11:48:47	2012-08-09 09:43:26	1	2	4	3	1	3	1	Documento Digital Certificado del Título de Bachiller	f	José Montes	1	0.2	0
9	Servicio creado para servir a los distintos organismos del Estado y empresas privadas para conocer si otro organismo cumple con la solvencia laboral.	2012-08-08 14:09:08	2012-08-08 14:12:52	9	2	4	14	3	5	9	Solvencia Laboral	f	Andrés Bello	1	1.34	0
11	Este servicio de información se encarga de informar si una persona ya tiene activada su pensión por vejez.	2012-08-08 14:33:51	2012-08-08 14:38:07	6	2	0	14	0	6	6	Pensión por vejez	f	\N	2	\N	0
12	Este servicio de información se encarga de informar si una persona ya tiene activada su pensión por vejez.	2012-08-08 14:34:18	2012-08-08 14:38:12	6	2	3	14	1	7	6	Pensión por vejez	f	Francisco de Miranda	1	8.7	0
14	La Alcaldía de Caracas otorga el permiso para viaje de menores de edad.	2012-08-08 15:00:03	2012-08-08 15:10:10	10	2	5	4	2	8	10	Permiso para viaje de menores de edad	f	José Antonio Páez	1	5.0	0
16	Servicio que provee los datos de identificación ciudadana de cualquier venezolano	2012-08-08 15:45:47	2012-08-08 16:06:43	2	1	3	16	1	9	2	Identificación de un Ciudadano	f	Soporte Tecnico	2	0.1	0
17	Servicio que provee los datos de identificación ciudadana de cualquier venezolano	2012-08-08 16:06:47	2012-08-08 16:28:24	2	1	3	16	2	10	2	Identificación de un Ciudadano	f	\N	1	4.0	0
18	Servicio que provee los datos de identificación ciudadanía de cualquier venezolano	2012-08-08 16:06:47	2012-08-09 09:42:38	2	2	3	16	2	10	2	Identificación de un Ciudadano	f	Soporte Tecnico	1	4.0	0
21	Servicio que provee el documento digital certificado del Registro de Información Fiscal (RIF) de una persona natural.	2012-08-09 10:08:14	2012-08-09 10:13:18	3	2	3	5	1	11	3	Documento digital del Registro de Información Fiscal de una persona natural 	f	Soporte Técnico	1	0.3	0
1	El CNTI presta un nuevo trámite de verificación de Canaimitas. Con introducir el SERIAL del equipo el CNTI puede verificar remotamente las fallas del mismo y asignar una cita de ser necesario.	2012-08-08 11:11:42	2012-08-08 13:50:55	7	2	3	1	1	1	7	Servicio de Verificación de Canaimitas	f	Simón Bolívar	1	6.8	0
23	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-08-09 10:24:52	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	f	Soporte Técnico	1	0.7	0
2	El INTTT pone a disposición del Estado este servicio para consultar los ciudadanos que poseen licencia vigente.	2012-08-08 11:46:07	2012-08-08 13:44:26	8	2	4	4	2	2	8	Licencia de conducir	f	\N	1	1.3	0
3	El Ministerio del Poder Popular para la Educación otorga una copia certificada del registro de título de educación básica.	2012-08-08 11:48:47	2012-08-08 13:42:57	1	2	4	3	1	3	1	Copia certificada de Registro de Título de Educación Básica	f	\N	1	0.2	0
4	La copia certificada de Registro de Título de Bachiller o Técnico Medio y del Certificado de Educación Básica son documentos que tienen la misma validez que su original.	2012-08-08 11:50:34	2012-08-08 13:41:04	1	2	0	3	0	4	1	La copia certificada de Registro de Título de Bachiller o Técnico Medio	f	\N	2	\N	0
6	El INTTT pone a disposición del Estado este servicio para consultar los ciudadanos que poseen licencia vigente.	2012-08-08 11:46:07	2012-08-08 13:50:37	8	2	4	4	2	2	8	Licencia de conducir	f	Simón Rodríguez	1	1.3	0
25	Servicio que provee una Copia Certificada digital del Título de Universitario de un ciudadano a los organismo y entes que requieran dicho documento.	2012-08-09 10:28:51	2012-08-09 10:37:13	5	2	4	3	1	13	5	Copia Certificada de Título de Universitario	f	Soporte Técnico	1	0.6	0
38	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-09-18 15:49:53	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	4
19	Servicio que provee los datos de identificación ciudadanía de cualquier venezolano	2012-08-08 16:06:47	2013-04-24 14:48:09	2	2	3	16	2	10	2	Identificación de un Ciudadano	t	Soporte Tecnico	1	4.0	2
22	Servicio que provee el documento digital certificado del Registro de Información Fiscal (RIF) de una persona natural.	2012-08-09 10:08:14	2013-04-24 14:49:05	3	2	3	5	1	11	3	Documento digital del Registro de Información Fiscal de una persona natural 	t	Soporte Técnico	1	0.3	3
26	Servicio que provee una Copia Certificada digital del Título de Universitario de un ciudadano a los organismo y entes que requieran dicho documento.	2012-08-09 10:28:51	2013-04-24 14:51:16	5	2	4	3	1	13	5	Copia Certificada del Título Universitario	t	Soporte Técnico	1	0.6	5
13	Este servicio de información se encarga de informar si una persona ya tiene activada su pensión por vejez.	2012-08-08 14:34:18	2013-04-24 14:52:01	6	2	3	14	1	7	6	Pensión por vejez	t	Francisco de Miranda	1	8.7	6
10	Servicio creado para servir a los distintos organismos del Estado y empresas privadas para conocer si otro organismo cumple con la solvencia laboral.	2012-08-08 14:09:08	2013-04-24 14:53:48	9	2	4	14	3	5	9	Solvencia Laboral	t	Andrés Bello	1	1.34	9
15	La Alcaldía de Caracas otorga el permiso para viaje de menores de edad.	2012-08-08 15:00:03	2013-04-24 14:54:26	10	2	5	4	2	8	10	Permiso para viaje de menores de edad	t	José Antonio Páez	1	5.0	10
29	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-08-29 15:47:36	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	0
44	sdf	2012-10-25 15:41:55	2012-10-25 15:42:14	4	1	0	9	0	17	0	sdf	f	\N	1	\N	4
31	jejeje	2012-08-29 16:28:12	2012-08-29 16:28:29	7	2	0	8	0	16	0	Soy nuevo	f	\N	2	\N	7
8	El CNTI presta un nuevo trámite de verificación de Canaimitas. Con introducir el SERIAL del equipo el CNTI puede verificar remotamente las fallas del mismo y asignar una cita de ser necesario.	2012-08-08 11:11:42	2012-08-29 16:33:02	7	2	3	1	1	1	7	Servicio de Verificación de Canaimitas	t	Simón Bolívar	1	6.8	7
33	El CNTI presta un nuevo trámite de verificación de Canaimitas. Con introducir el SERIAL del equipo el CNTI puede verificar remotamente las fallas del mismo y asignar una cita de ser necesario.	2012-08-08 11:11:42	2012-08-29 16:35:35	7	2	3	1	1	1	7	Servicio de Verificación de Canaimitas	t	Simón Bolívar	1	6.8	7
30	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-09-18 15:02:05	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	4
35	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-09-18 15:32:03	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	4
36	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-09-18 15:32:10	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico v	1	0.7	4
37	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-09-18 15:36:45	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	1	0.7	4
40	sdfsd f	2012-08-21 09:58:39	2012-10-23 15:27:16	4	1	0	8	0	14	4	fsjdf	f	dssdfsdf	1	\N	4
41	sdfsd f	2012-08-21 09:58:39	2012-10-23 15:50:47	4	1	0	8	0	14	4	fsjdf	f	dssdfsdf	1	\N	4
42	sdfsd f	2012-08-21 09:58:39	2012-10-23 15:51:08	4	1	0	8	0	14	4	fsjdf	f	dssdfsdf	1	\N	4
45	sdf	2012-10-25 15:41:55	2012-10-25 16:08:28	4	1	0	9	0	17	0	sdf	f	ggggg	1	\N	4
46	sdf	2012-10-25 15:41:55	2012-10-25 16:15:14	4	1	3	9	1	17	0	sdf	f	ggggg	1	25.0	4
55	ghfg hfg 	2012-11-19 11:20:49	2012-11-19 11:23:46	4	1	3	12	2	19	0	test test	f	rr	1	4.0	4
47	sdf	2012-10-25 15:41:55	2012-10-25 16:16:26	4	1	3	9	1	17	0	sdf	f	gggggLL	1	25.0	4
48	sdf	2012-10-25 15:41:55	2012-10-25 16:18:04	4	1	3	9	1	17	0	sdf	f	gjuy	1	25.0	4
28	sdf	2012-08-21 11:37:16	2012-10-25 16:18:38	4	1	0	9	0	15	4	sdf dsd	f	\N	1	\N	4
49	sdf	2012-10-25 15:41:55	2012-10-25 16:19:07	4	1	3	9	1	17	0	sdf	f	gjuyTTT	1	25.0	4
50	sdf	2012-08-21 11:37:16	2012-10-25 16:19:38	4	1	0	9	0	15	4	sdf dsd	f	Soporte Técnico	1	\N	4
54	ghfg hfg 	2012-11-19 11:20:49	2012-11-19 11:22:42	4	1	3	9	1	19	0	test test	f	\N	1	4.0	4
56	ghfg hfg 	2012-11-19 11:20:49	2012-11-19 11:24:19	4	1	3	12	2	19	0	test test	f	ggg	1	4.0	4
51	sdf	2012-10-25 15:41:55	2012-11-19 11:25:46	4	1	3	9	1	17	0	sdf	f	gjuyTTT	2	25.0	4
43	sdfsd f	2012-08-21 09:58:39	2012-11-19 11:25:37	4	1	0	8	0	14	4	fsjdf	f	dssdfsdf	2	\N	4
52	sdf	2012-08-21 11:37:16	2012-11-19 11:25:42	4	1	0	9	0	15	4	sdf dsd	f	Soporte Técnico	2	\N	4
57	ghfg hfg 	2012-11-19 11:20:49	2012-11-19 11:25:50	4	1	3	12	2	19	0	test test	f	ggg	2	4.0	4
53	Probando...	2012-11-07 08:48:37	2012-11-19 11:36:18	7	2	0	8	0	18	0	Nuevo Alimentación	f	\N	2	\N	7
39	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-12-18 09:06:49	4	1	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	f	Soporte Técnico	1	0.7	4
60	dsd msnfsnd ,jsdnf  sdjkhsdkgf sdfkjsdf hsdkf sjkdhfjh sjkdhfkjh sksjdhf jhsdf jkhsdf jhjs dfjklhsdjkfh sjhksdjhf  sdjhkjhsdf sdjd sdjhsdjj dj jkd shd jdjjdjdj jdjdjsd jdjdj	2012-12-18 09:12:01	2012-12-18 09:14:21	4	1	3	9	1	21	0	ds	f	\N	1	1.0	4
58	xc	2012-12-14 10:25:22	2013-04-09 07:21:41	7	1	0	12	0	20	0	cxc	f	\N	2	\N	7
61	dsd msnfsnd ,jsdnf  sdjkhsdkgf sdfkjsdf hsdkf sjkdhfjh sjkdhfkjh sksjdhf jhsdf jkhsdf jhjs dfjklhsdjkfh sjhksdjhf  sdjhkjhsdf sdjd sdjhsdjj dj jkd shd jdjjdjdj jdjdjsd jdjdj	2012-12-18 09:12:01	2013-04-09 07:39:47	4	1	3	9	1	21	0	ds	f	shdhfgsdhgfhjsdg sdfhjgsdfsdfh	2	1.0	4
34	El CNTI presta un nuevo trámite de verificación de Canaimitas. Con introducir el SERIAL del equipo el CNTI puede verificar remotamente las fallas del mismo y asignar una cita de ser necesario.	2012-08-08 11:11:42	2013-04-24 14:45:43	7	2	3	1	1	1	7	Servicio de Verificación de Canaimitas	t	Simón Bolívar	1	6.8	7
62	El CNTI presta un nuevo trámite de verificación de Canaimitas. Con introducir el SERIAL del equipo el CNTI puede verificar remotamente las fallas del mismo y asignar una cita de ser necesario.	2012-08-08 11:11:42	2012-08-29 16:35:36.017	7	2	3	1	1	1	7	Servicio de Verificación de Canaimitas	t	Simón Bolívar	0	6.8	7
20	El Ministerio del Poder Popular para la Educación otorga un documento digital  certificado del Título de Bachiller.	2012-08-08 11:48:47	2013-04-24 14:47:14	1	2	4	3	1	3	1	Documento Digital Certificado del Título de Bachiller	t	José Montes	1	0.2	1
63	El Ministerio del Poder Popular para la Educación otorga un documento digital  certificado del Título de Bachiller.	2012-08-08 11:48:47	2012-08-08 16:01:05.143	1	2	4	3	1	3	1	Documento Digital Certificado del Título de Bachiller	t	José Montes	0	0.2	0
64	Servicio que provee los datos de identificación ciudadanía de cualquier venezolano	2012-08-08 16:06:47	2012-08-09 09:35:27.907	2	2	3	16	2	10	2	Identificación de un Ciudadano	t	Soporte Tecnico	0	4.0	0
65	Servicio que provee el documento digital certificado del Registro de Información Fiscal (RIF) de una persona natural.	2012-08-09 10:08:14	2012-08-09 10:09:16.191	3	2	3	5	1	11	3	Documento digital del Registro de Información Fiscal de una persona natural 	t	Soporte Técnico	0	0.3	0
59	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2013-04-24 14:49:56	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	f	Soporte Técnico	1	0.7	4
67	Servicio que provee una Copia Certificada digital del Título de Universitario de un ciudadano a los organismo y entes que requieran dicho documento.	2012-08-09 10:28:51	2012-08-10 11:44:26.474	5	2	4	3	1	13	5	Copia Certificada del Título Universitario	t	Soporte Técnico	0	0.6	0
68	Este servicio de información se encarga de informar si una persona ya tiene activada su pensión por vejez.	2012-08-08 14:34:18	2012-08-08 14:35:24.208	6	2	3	14	1	7	6	Pensión por vejez	t	Francisco de Miranda	0	8.7	0
69	Servicio creado para servir a los distintos organismos del Estado y empresas privadas para conocer si otro organismo cumple con la solvencia laboral.	2012-08-08 14:09:08	2012-08-08 14:10:33.333	9	2	4	14	3	5	9	Solvencia Laboral	t	Andrés Bello	0	1.34	0
70	La Alcaldía de Caracas otorga el permiso para viaje de menores de edad.	2012-08-08 15:00:03	2012-08-08 15:01:41.558	10	2	5	4	2	8	10	Permiso para viaje de menores de edad	t	José Antonio Páez	0	5.0	0
66	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2013-04-24 14:54:45	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	f	Soporte Técnico	1	0.7	0
71	Servicio de información que provee una constancia digital certificada de la declaración jurada de patrimonio.	2012-08-09 10:20:49	2012-12-18 09:06:49.739	4	2	3	15	1	12	4	Constancia digital certificada de la declaración jurada de patrimonio	t	Soporte Técnico	0	0.7	4
72	sdf	2013-04-24 14:55:38	2013-04-24 14:56:41	7	2	3	2	1	22	0	sdf	f	\N	1	2.0	7
73	sdf	2013-04-24 14:55:38	2013-04-24 14:56:45	7	2	3	2	1	22	0	sdf	f	fsdfsdf	1	2.0	7
74	sdf	2013-04-24 14:55:38	2013-04-24 14:56:52	7	2	3	2	1	22	0	sdf	f	fsdfsdf	2	2.0	7
\.


--
-- TOC entry 2032 (class 0 OID 186009)
-- Dependencies: 172
-- Data for Name: solicitudes_suscripciones; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY solicitudes_suscripciones (id, cargo, correo, fecha_creado, fecha_modificado, id_ente_proveedor, id_ente_solicitante, id_servicio_informacion, id_solicitud_suscripcion, id_usuario, leido, motivo_proveedor, motivo_solicitante, sentencia, solicitante, status, telefono, mod_user) FROM stdin;
\.


--
-- TOC entry 2033 (class 0 OID 186017)
-- Dependencies: 174
-- Data for Name: suscritos; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY suscritos (id, fecha_creado, fecha_modificado, id_ente, id_servicio_informacion, id_suscrito, status, mod_user) FROM stdin;
\.


--
-- TOC entry 2034 (class 0 OID 186022)
-- Dependencies: 176
-- Data for Name: telefonos; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY telefonos (id, fecha_creado, fecha_modificado, id_ente, id_servicio_informacion, id_telefono, id_usuario, status, telefono, mod_user) FROM stdin;
28	2012-11-19 11:22:42	2012-11-19 11:24:19	0	19	15	0	1	2123213213	4
29	2012-11-19 11:22:42	2012-11-19 11:22:42	0	19	15	0	0	4243213214	4
30	2012-12-18 09:14:21	2012-12-18 09:14:21	0	21	16	0	0	2123213456	4
1	2012-08-08 11:13:03	2012-08-08 11:13:03	0	1	1	0	0	2125897476	0
2	2012-08-08 13:42:57	2012-08-08 13:42:57	0	3	2	0	0	2126598745	0
3	2012-08-08 13:44:26	2012-08-08 13:44:26	0	2	3	0	0	4246084231	0
4	2012-08-08 14:09:43	2012-08-08 14:09:43	0	5	4	0	0	2126030302	0
5	2012-08-08 14:35:24	2012-08-08 14:35:24	0	7	5	0	0	4168997139	0
6	2012-08-08 15:01:41	2012-08-08 15:01:41	0	8	6	0	0	2127913465	0
7	2012-08-08 15:51:17	2012-08-08 15:51:17	0	9	7	0	0	2121534564	0
8	2012-08-08 16:28:24	2012-08-08 16:28:24	0	10	8	0	0	2122342342	0
9	2012-08-09 10:09:16	2012-08-09 10:09:16	0	11	9	0	0	2125467878	0
11	2012-08-09 10:29:29	2012-08-09 10:29:29	0	13	11	0	0	2126547897	0
10	2012-08-09 10:21:29	2012-09-18 15:32:03	0	12	10	0	1	2124644898	4
12	2012-08-09 10:21:29	2012-09-18 15:32:10	0	12	10	0	1	2124644898	4
13	2012-08-09 10:21:29	2012-09-18 15:36:45	0	12	10	0	1	2124644898	4
14	2012-08-09 10:21:29	2012-08-09 10:21:29	0	12	10	0	0	2121111111	0
19	2012-10-25 15:42:14	2012-10-25 16:08:28	0	17	13	0	1	4126666666	4
15	2012-10-23 15:26:58	2012-10-23 15:27:16	0	14	12	0	1	2123243423	4
16	2012-10-23 15:26:58	2012-10-23 15:50:47	0	14	12	0	1	2393242323	4
18	2012-10-23 15:26:58	2012-10-23 15:26:58	0	14	12	0	0	2460239878	4
17	2012-10-23 15:26:58	2012-10-23 15:51:08	0	14	12	0	1	4167654321	4
20	2012-10-25 15:42:14	2012-10-25 16:15:14	0	17	13	0	1	2466666665	4
21	2012-10-25 15:42:14	2012-10-25 16:16:26	0	17	13	0	1	2466666665	4
22	2012-10-25 15:42:14	2012-10-25 16:18:04	0	17	13	0	1	4241254789	4
23	2012-10-25 15:42:14	2012-10-25 16:19:07	0	17	13	0	1	4241254789	4
25	2012-10-25 15:42:14	2012-10-25 15:42:14	0	17	13	0	0	4241254111	4
24	2012-10-25 16:18:38	2012-10-25 16:19:38	0	15	14	0	1	4249999999	4
26	2012-10-25 16:18:38	2012-10-25 16:18:38	0	15	14	0	0	4249999222	4
27	2012-11-19 11:22:42	2012-11-19 11:23:46	0	19	15	0	1	2123213213	4
31	2013-04-24 14:56:41	2013-04-24 14:56:45	0	22	17	0	1	2122342342	7
32	2013-04-24 14:56:41	2013-04-24 14:56:41	0	22	17	0	0	2122342342	7
\.


--
-- TOC entry 2035 (class 0 OID 186027)
-- Dependencies: 178
-- Data for Name: tipos_datos; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY tipos_datos (id, descripcion, fecha_creado, fecha_modificado, id_tipo_dato, nombre, status, tipo, haslength, hasformatted, mod_user) FROM stdin;
1	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	Lista de datos simples	0	0	f	f	0
2	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	String	0	1	t	f	0
3	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	Integer	0	1	t	t	0
4	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	4	Decimal	0	1	t	t	0
5	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	5	Boolean	0	1	f	t	0
6	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	6	Date	0	1	f	t	0
7	\N	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	7	Time	0	1	f	t	0
\.


--
-- TOC entry 2036 (class 0 OID 186035)
-- Dependencies: 180
-- Data for Name: union_areas_servicios_informacion; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY union_areas_servicios_informacion (id, fecha_creado, fecha_modificado, id_area, id_servicio_informacion, id_usuario, status, mod_user) FROM stdin;
66	2012-08-09 10:20:49	2012-08-29 15:44:04	1	12	4	1	0
67	2012-08-09 10:20:49	2012-08-29 15:44:04	2	12	4	1	0
68	2012-08-09 10:20:49	2012-08-29 15:47:36	1	12	4	1	0
69	2012-08-09 10:20:49	2012-08-29 15:47:36	2	12	4	1	0
76	2012-08-29 16:28:12	2012-08-29 16:28:12	1	16	7	0	7
79	2012-08-08 11:11:42	2012-08-29 16:35:36	1	1	7	1	7
80	2012-08-08 11:11:42	2012-08-29 16:35:36	1	1	7	0	7
86	2012-10-25 15:41:55	2012-10-25 16:16:07	1	17	4	0	4
87	2012-10-25 15:41:55	2012-10-25 16:16:07	2	17	4	0	4
94	2012-08-09 10:20:49	2012-12-18 09:06:49	1	12	4	1	4
95	2012-08-09 10:20:49	2012-12-18 09:06:49	2	12	4	1	4
96	2012-08-09 10:20:49	2012-12-18 09:06:49	1	12	4	1	4
97	2012-08-09 10:20:49	2012-12-18 09:06:49	2	12	4	1	4
98	2012-08-09 10:20:49	2012-12-18 09:06:49	1	12	4	0	4
99	2012-08-09 10:20:49	2012-12-18 09:06:49	2	12	4	0	4
100	2012-12-18 09:12:01	2012-12-18 09:12:01	1	21	4	0	4
101	2012-12-18 09:12:01	2012-12-18 09:12:01	2	21	4	0	4
3	2012-08-08 11:46:07	2012-08-08 11:46:07	2	2	8	0	0
37	2012-08-08 11:48:47	2012-08-08 15:55:34	1	3	1	1	0
5	2012-08-08 11:50:34	2012-08-08 11:50:34	1	4	1	0	0
6	2012-08-08 11:50:34	2012-08-08 11:50:34	2	4	1	0	0
38	2012-08-08 11:48:47	2012-08-08 15:55:34	2	3	1	1	0
7	2012-08-08 14:09:08	2012-08-08 14:10:33	2	5	9	1	0
8	2012-08-08 14:09:08	2012-08-08 14:10:33	3	5	9	1	0
9	2012-08-08 14:09:08	2012-08-08 14:10:33	2	5	9	0	0
10	2012-08-08 14:09:08	2012-08-08 14:10:33	3	5	9	0	0
11	2012-08-08 14:33:51	2012-08-08 14:33:51	1	6	6	0	0
12	2012-08-08 14:33:51	2012-08-08 14:33:51	2	6	6	0	0
13	2012-08-08 14:33:51	2012-08-08 14:33:51	3	6	6	0	0
14	2012-08-08 14:34:18	2012-08-08 14:34:18	1	7	6	0	0
15	2012-08-08 14:34:18	2012-08-08 14:34:18	2	7	6	0	0
16	2012-08-08 14:34:18	2012-08-08 14:34:18	3	7	6	0	0
17	2012-08-08 15:00:03	2012-08-08 15:00:03	1	8	10	0	0
4	2012-08-08 11:48:47	2012-08-08 15:12:08	1	3	1	1	0
18	2012-08-08 11:48:47	2012-08-08 15:14:09	1	3	1	1	0
19	2012-08-08 11:48:47	2012-08-08 15:14:09	2	3	1	1	0
20	2012-08-08 11:48:47	2012-08-08 15:15:12	1	3	1	1	0
21	2012-08-08 11:48:47	2012-08-08 15:15:12	2	3	1	1	0
61	2012-08-21 11:37:16	2012-08-23 16:04:17	1	15	4	1	0
22	2012-08-08 11:48:47	2012-08-08 15:16:40	2	3	1	1	0
39	2012-08-08 11:48:47	2012-08-08 16:01:05	1	3	1	1	0
40	2012-08-08 11:48:47	2012-08-08 16:01:05	2	3	1	1	0
23	2012-08-08 11:48:47	2012-08-08 15:17:41	1	3	1	1	0
24	2012-08-08 11:48:47	2012-08-08 15:17:41	2	3	1	1	0
41	2012-08-08 11:48:47	2012-08-08 16:01:05	3	3	1	1	0
25	2012-08-08 11:48:47	2012-08-08 15:35:29	1	3	1	1	0
42	2012-08-08 11:48:47	2012-08-08 16:01:05	1	3	1	0	0
26	2012-08-08 11:48:47	2012-08-08 15:39:27	1	3	1	1	0
27	2012-08-08 11:48:47	2012-08-08 15:39:27	2	3	1	1	0
43	2012-08-08 11:48:47	2012-08-08 16:01:05	2	3	1	0	0
28	2012-08-08 11:48:47	2012-08-08 15:44:14	1	3	1	1	0
29	2012-08-08 11:48:47	2012-08-08 15:44:14	2	3	1	1	0
30	2012-08-08 11:48:47	2012-08-08 15:44:14	3	3	1	1	0
1	2012-08-08 11:11:42	2012-08-08 16:21:57	1	1	7	1	0
33	2012-08-08 15:45:47	2012-08-08 15:45:47	2	9	2	0	0
31	2012-08-08 11:48:47	2012-08-08 15:49:09	1	3	1	1	0
32	2012-08-08 11:48:47	2012-08-08 15:49:09	2	3	1	1	0
2	2012-08-08 11:11:42	2012-08-08 16:21:57	2	1	7	1	0
34	2012-08-08 11:48:47	2012-08-08 15:51:36	1	3	1	1	0
35	2012-08-08 11:48:47	2012-08-08 15:51:36	2	3	1	1	0
36	2012-08-08 11:48:47	2012-08-08 15:51:36	3	3	1	1	0
62	2012-08-21 11:37:16	2012-08-23 16:12:01	1	15	4	1	0
44	2012-08-08 16:06:47	2012-08-08 16:28:45	2	10	2	1	0
63	2012-08-21 11:37:16	2012-08-23 16:12:01	1	15	4	0	0
48	2012-08-08 16:06:47	2012-08-09 09:34:48	1	10	2	1	0
49	2012-08-08 16:06:47	2012-08-09 09:34:48	2	10	2	1	0
60	2012-08-21 09:58:39	2012-08-28 12:02:20	1	14	4	1	0
50	2012-08-08 16:06:47	2012-08-09 09:35:27	1	10	2	1	0
51	2012-08-08 16:06:47	2012-08-09 09:35:27	2	10	2	1	0
52	2012-08-08 16:06:47	2012-08-09 09:35:27	1	10	2	0	0
53	2012-08-08 16:06:47	2012-08-09 09:35:27	2	10	2	0	0
54	2012-08-09 10:08:14	2012-08-09 10:08:14	2	11	3	0	0
64	2012-08-21 09:58:39	2012-08-28 12:02:20	1	14	4	0	0
56	2012-08-09 10:28:51	2012-08-09 10:38:06	2	13	5	1	0
57	2012-08-09 10:28:51	2012-08-10 11:41:13	2	13	5	1	0
65	2012-08-21 09:58:39	2012-08-28 12:02:20	2	14	4	0	0
58	2012-08-09 10:28:51	2012-08-10 11:44:26	2	13	5	1	0
59	2012-08-09 10:28:51	2012-08-10 11:44:26	2	13	5	0	0
55	2012-08-09 10:20:49	2012-08-29 15:44:04	2	12	4	1	0
90	2012-11-07 08:48:37	2012-11-07 08:48:37	3	18	7	0	7
70	2012-08-09 10:20:49	2012-08-29 15:47:36	1	12	4	1	0
71	2012-08-09 10:20:49	2012-08-29 15:47:36	2	12	4	1	0
72	2012-08-09 10:20:49	2012-08-29 15:47:36	3	12	4	1	0
91	2012-11-19 11:20:49	2012-11-19 11:23:33	2	19	4	1	4
92	2012-11-19 11:20:49	2012-11-19 11:23:33	2	19	4	0	4
45	2012-08-08 11:11:42	2012-08-29 16:33:02	1	1	7	1	7
46	2012-08-08 11:11:42	2012-08-29 16:33:02	2	1	7	1	7
47	2012-08-08 11:11:42	2012-08-29 16:33:02	3	1	7	1	7
93	2012-12-14 10:25:23	2012-12-14 10:25:23	2	20	7	0	7
77	2012-08-08 11:11:42	2012-08-29 16:35:35	1	1	7	1	7
78	2012-08-08 11:11:42	2012-08-29 16:35:35	2	1	7	1	7
73	2012-08-09 10:20:49	2012-09-18 15:02:06	1	12	4	1	4
74	2012-08-09 10:20:49	2012-09-18 15:02:06	2	12	4	1	4
75	2012-08-09 10:20:49	2012-09-18 15:02:06	3	12	4	1	4
83	2012-08-09 10:20:49	2012-12-18 09:00:45	1	12	4	1	4
84	2012-08-09 10:20:49	2012-12-18 09:00:45	2	12	4	1	4
81	2012-08-09 10:20:49	2012-09-18 15:02:06	1	12	4	1	4
82	2012-08-09 10:20:49	2012-09-18 15:02:06	2	12	4	1	4
85	2012-10-25 15:41:55	2012-10-25 16:16:07	1	17	4	1	4
88	2012-11-07 08:48:37	2012-11-07 08:48:37	1	18	7	0	7
89	2012-11-07 08:48:37	2012-11-07 08:48:37	2	18	7	0	7
102	2013-04-24 14:55:38	2013-04-24 14:56:05	1	22	7	1	7
103	2013-04-24 14:55:38	2013-04-24 14:56:05	1	22	7	0	7
\.


--
-- TOC entry 2037 (class 0 OID 186040)
-- Dependencies: 182
-- Data for Name: union_arquitecturas_servicios_informacion; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY union_arquitecturas_servicios_informacion (id, fecha_creado, fecha_modificado, id_arquitectura, id_servicio_informacion, id_usuario, status, mod_user) FROM stdin;
3	2012-08-08 13:43:55	2012-08-08 13:43:55	1	2	8	0	0
8	2012-08-08 15:48:09	2012-08-08 15:48:09	1	9	2	0	0
13	2012-10-25 16:14:57	2012-10-25 16:14:57	1	17	4	0	4
14	2012-11-19 11:22:26	2012-11-19 11:22:26	1	19	4	0	4
15	2012-11-19 11:23:38	2012-11-19 11:23:38	1	19	4	0	4
16	2012-12-18 09:13:53	2012-12-18 09:13:53	1	21	4	0	4
17	2012-12-18 09:57:58	2012-12-18 09:57:58	1	21	4	0	4
1	2012-08-08 11:12:29	2013-04-24 14:45:43	1	1	7	1	7
18	2012-08-08 11:12:29	2013-04-24 14:45:43	1	1	7	0	7
2	2012-08-08 13:42:25	2013-04-24 14:47:14	1	3	1	1	1
7	2012-08-08 15:12:23	2013-04-24 14:47:14	1	3	1	1	1
19	2012-08-08 13:42:25	2013-04-24 14:47:14	1	3	1	0	1
9	2012-08-08 16:27:57	2013-04-24 14:48:09	2	10	2	1	2
20	2012-08-08 16:27:57	2013-04-24 14:48:09	2	10	2	0	2
10	2012-08-09 10:08:53	2013-04-24 14:49:05	1	11	3	1	3
21	2012-08-09 10:08:53	2013-04-24 14:49:05	1	11	3	0	3
11	2012-08-09 10:21:03	2013-04-24 14:49:56	1	12	4	1	4
22	2012-08-09 10:21:03	2013-04-24 14:49:56	1	12	4	0	4
12	2012-08-09 10:29:04	2013-04-24 14:51:16	1	13	5	1	5
23	2012-08-09 10:29:04	2013-04-24 14:51:16	1	13	5	0	5
5	2012-08-08 14:34:44	2013-04-24 14:52:01	1	7	6	1	6
24	2012-08-08 14:34:44	2013-04-24 14:52:01	1	7	6	0	6
4	2012-08-08 14:09:22	2013-04-24 14:53:48	1	5	9	1	9
25	2012-08-08 14:09:22	2013-04-24 14:53:48	1	5	9	0	9
6	2012-08-08 15:00:15	2013-04-24 14:54:26	1	8	10	1	10
26	2012-08-08 15:00:15	2013-04-24 14:54:26	1	8	10	0	10
27	2013-04-24 14:56:00	2013-04-24 14:56:00	1	22	7	0	7
\.


--
-- TOC entry 2038 (class 0 OID 186045)
-- Dependencies: 184
-- Data for Name: union_sectores_entes; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY union_sectores_entes (id, fecha_creado, fecha_modificado, id_ente, id_sector, id_usuario, status, mod_user) FROM stdin;
\.


--
-- TOC entry 2039 (class 0 OID 186050)
-- Dependencies: 186
-- Data for Name: union_usuarios_roles; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY union_usuarios_roles (id, fecha_creado, fecha_modificado, id_ente, id_sector, status, mod_user) FROM stdin;
\.


--
-- TOC entry 2040 (class 0 OID 186055)
-- Dependencies: 188
-- Data for Name: url; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY url (id, fecha_creado, fecha_modificado, id_ente, id_url, status, url, id_servicio_informacion, mod_user) FROM stdin;
1	2012-10-25 16:14:57	2012-10-25 16:14:57	0	1	0		17	4
2	2012-11-19 11:22:26	2012-11-19 11:23:38	0	2	1		19	4
3	2012-11-19 11:22:26	2012-11-19 11:22:26	0	2	0		19	4
4	2012-12-18 09:13:53	2012-12-18 09:57:58	0	3	1		21	4
5	2012-12-18 09:13:53	2012-12-18 09:13:53	0	3	0	http://www.fds.co	21	4
6	2013-04-24 14:45:43	2013-04-24 14:45:43	0	4	0	http://ser.wsdl	1	7
7	2013-04-24 14:47:14	2013-04-24 14:47:14	0	5	0	http://me.serv.wsdl	3	1
8	2013-04-24 14:48:09	2013-04-24 14:48:09	0	6	0	http://saime.serv.wsdl	10	2
9	2013-04-24 14:49:05	2013-04-24 14:49:05	0	7	0	http://seniat.serv.wsdl	11	3
10	2013-04-24 14:49:56	2013-04-24 14:49:56	0	8	0	http://cgr.serv.wsdl	12	4
11	2013-04-24 14:51:16	2013-04-24 14:51:16	0	9	0	http://mppeu.serv.wsdl	13	5
12	2013-04-24 14:52:01	2013-04-24 14:52:01	0	10	0	http://ivss.serv.wsdl	7	6
13	2013-04-24 14:53:48	2013-04-24 14:53:48	0	11	0	http://mintra.serv.wsdl	5	9
14	2013-04-24 14:54:26	2013-04-24 14:54:26	0	12	0	http://ac.serv.wsdl	8	10
15	2013-04-24 14:56:00	2013-04-24 14:56:00	0	13	0	http://cgr.serv.wsdls	22	7
\.


--
-- TOC entry 2041 (class 0 OID 186060)
-- Dependencies: 190
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY usuarios (id, apellido, cedula, clave, fecha_creado, fecha_modificado, id_correo, id_ente, id_usuario, nombre, status, mod_user, nacionalidad) FROM stdin;
5	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-19 11:32:32	5	5	5	Usuario	1	5	1
13	Requerido	19244318	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-08-30 14:30:37	7	7	7	Requerido	0	7	1
15	Pereira	16724282	e35cf7b66449df565f93c607d5a81d09	2012-02-23 15:20:49.879	2012-09-21 11:06:17	22	3	11	Joaquin	1	11	1
16	Pereira	16724282	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-09-21 10:47:48	22	3	11	Joaquin	0	0	1
17	Usuario	123456988	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 09:30:33	4	4	4	cgr_user	1	4	1
18	cgr_user_apellido	1	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 09:51:17	4	4	4	cgr_user_name	1	4	1
19	cgr_user_apellido	2	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:39:25	4	4	4	cgr_user_name	1	4	1
20	cgr_user_apellido	3	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:41:28	4	4	4	cgr_user_name	1	4	2
1	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	1	1	1	Usuario	0	0	1
2	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	2	2	2	Usuario	0	0	1
3	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	3	3	3	Usuario	0	0	1
4	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-06 16:52:42	4	4	4	Usuario	1	4	1
6	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	6	6	6	Usuario	0	0	1
7	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-08-30 14:27:14	7	7	7	Usuario	1	7	1
8	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	8	8	8	Usuario	0	0	1
9	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	9	9	9	Usuario	0	0	1
10	Usuario	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-02-23 15:20:49.879	10	10	10	Usuario	0	0	1
11	Pereira	0	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-09-21 11:04:49	22	3	11	Joaquin	1	0	1
12	Usuario	16724282	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-08-30 14:30:37	7	7	7	Usuario	1	7	1
21	cgr_user_apellido	3	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:46:41	4	4	4	cgr_user_name	1	4	1
22	cgr_user_apellido	4	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:47:00	4	4	4	cgr_user_name	1	4	1
23	cgr_user_apellido	5	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:51:23	4	4	4	cgr_user_name	1	4	2
24	cgr_user_apellido	56	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:51:30	4	4	4	cgr_user_name	1	4	1
25	cgr_user_apellido	56	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:58:44	4	4	4	cgr_user_name	1	4	2
26	cgr_user_apellido	100	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-07 11:58:52	4	4	4	cgr_user_name	1	4	1
27	cgr_user_apellido	100	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-19 11:26:45	4	4	4	cgr_user_name	1	4	2
28	cgr_user_apellido	100	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-19 11:26:45	4	4	4	cgr_user_name	0	4	1
29	Usuario	111111111	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-19 11:32:41	5	5	5	Usuario	1	5	1
30	Usuario	111111111	e10adc3949ba59abbe56e057f20f883e	2012-02-23 15:20:49.879	2012-11-19 11:32:41	5	5	5	Usuario	0	5	2
\.


--
-- TOC entry 2042 (class 0 OID 186068)
-- Dependencies: 192
-- Data for Name: visitas; Type: TABLE DATA; Schema: public; Owner: rnsii
--

COPY visitas (id_visita, fecha, id_servicio_informacion, ip, mod_user) FROM stdin;
1	2012-08-08 15:54:12.117	7	127.0.0.1	0
2	2012-08-09 09:28:57.424	1	127.0.0.1	0
3	2012-08-09 09:30:01.721	8	127.0.0.1	0
4	2012-08-09 10:14:00.3	11	127.0.0.1	0
5	2012-08-10 16:00:27.529	10	127.0.0.1	0
6	2012-08-16 16:50:45.354	10	127.0.0.1	0
7	2012-08-16 16:53:21.248	8	127.0.0.1	0
8	2012-08-22 11:27:16.514	10	127.0.0.1	0
9	2012-08-22 11:27:31.213	1	127.0.0.1	0
10	2012-08-22 11:27:39.918	7	127.0.0.1	0
11	2012-08-22 11:29:00.568	11	127.0.0.1	0
12	2012-08-23 14:06:46.168	10	127.0.0.1	0
13	2012-08-23 14:44:17.4	1	127.0.0.1	0
14	2012-08-24 13:56:18.448	7	127.0.0.1	0
15	2012-08-24 13:56:30.305	11	127.0.0.1	0
16	2012-08-24 14:33:37.131	10	127.0.0.1	0
17	2012-08-24 14:57:00.593	1	127.0.0.1	0
18	2012-08-27 14:33:32.956	13	127.0.0.1	0
19	2012-09-17 13:52:29.143	5	127.0.0.1	0
20	2012-09-17 13:53:08.989	10	127.0.0.1	0
21	2012-09-17 15:17:29.686	1	127.0.0.1	4
22	2012-09-17 15:18:38.48	7	127.0.0.1	4
23	2012-09-17 15:18:44.894	8	127.0.0.1	4
24	2012-09-26 15:50:46.427	13	127.0.0.1	4
25	2012-10-23 15:01:05.686	7	127.0.0.1	0
26	2012-10-31 09:47:27.178	8	127.0.0.1	0
27	2012-11-13 10:29:34.055	8	127.0.0.1	0
28	2012-11-13 10:29:50.834	7	127.0.0.1	0
29	2012-11-13 10:38:52.568	1	127.0.0.1	0
30	2012-11-13 10:47:50.93	11	127.0.0.1	0
31	2012-11-13 11:15:01.045	10	127.0.0.1	0
32	2012-12-04 14:12:48.07	13	127.0.0.1	4
33	2012-12-04 14:48:50.621	8	127.0.0.1	0
34	2012-12-07 09:43:11.013	10	127.0.0.1	0
35	2012-12-07 10:08:21.953	8	127.0.0.1	0
36	2012-12-07 16:16:06.405	7	127.0.0.1	0
37	2012-12-12 13:24:16.219	13	127.0.0.1	0
38	2012-12-12 13:24:27.74	10	127.0.0.1	0
39	2012-12-14 11:15:15.906	1	127.0.0.1	7
40	2012-12-14 11:17:17.999	10	127.0.0.1	7
41	2012-12-14 11:17:32.335	7	127.0.0.1	7
42	2012-12-17 14:48:36.214	7	127.0.0.1	4
43	2012-12-17 15:39:44.026	1	127.0.0.1	4
44	2012-12-17 16:20:12.987	10	127.0.0.1	0
45	2012-12-17 16:20:43.613	8	127.0.0.1	4
46	2012-12-18 09:22:15.813	2	127.0.0.1	4
47	2012-12-19 09:17:35.841	11	127.0.0.1	10
48	2012-12-19 11:08:40.707	10	127.0.0.1	0
49	2012-12-19 11:40:50.564	1	127.0.0.1	1
50	2012-12-19 16:40:25.951	7	127.0.0.1	0
51	2012-12-19 16:40:47.784	3	127.0.0.1	0
52	2013-03-05 14:36:34.8	13	127.0.0.1	0
53	2013-04-09 07:20:59.174	3	127.0.0.1	0
54	2013-04-09 07:48:52.914	1	127.0.0.1	0
55	2013-04-09 07:49:10.715	2	127.0.0.1	0
56	2013-04-09 07:49:46.021	5	127.0.0.1	0
57	2013-04-09 07:49:59.936	7	127.0.0.1	0
58	2013-04-09 07:50:16.006	8	127.0.0.1	0
59	2013-04-09 07:50:29.422	10	127.0.0.1	0
60	2013-04-09 07:50:46.456	11	127.0.0.1	0
61	2013-04-09 07:51:05.557	13	127.0.0.1	0
\.


--
-- TOC entry 1963 (class 2606 OID 186101)
-- Dependencies: 140 140
-- Name: areas_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY areas
    ADD CONSTRAINT areas_pkey PRIMARY KEY (id);


--
-- TOC entry 1965 (class 2606 OID 186103)
-- Dependencies: 142 142
-- Name: arquitecturas_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY arquitecturas
    ADD CONSTRAINT arquitecturas_pkey PRIMARY KEY (id);


--
-- TOC entry 1967 (class 2606 OID 186105)
-- Dependencies: 144 144
-- Name: aspectos_legales_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY aspectos_legales
    ADD CONSTRAINT aspectos_legales_pkey PRIMARY KEY (id);


--
-- TOC entry 1969 (class 2606 OID 186107)
-- Dependencies: 146 146
-- Name: correos_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY correos
    ADD CONSTRAINT correos_pkey PRIMARY KEY (id);


--
-- TOC entry 1971 (class 2606 OID 186109)
-- Dependencies: 148 148
-- Name: entes_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY entes
    ADD CONSTRAINT entes_pkey PRIMARY KEY (id);


--
-- TOC entry 1973 (class 2606 OID 186111)
-- Dependencies: 150 150
-- Name: entradas_salidas_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY entradas_salidas
    ADD CONSTRAINT entradas_salidas_pkey PRIMARY KEY (id);


--
-- TOC entry 1975 (class 2606 OID 186113)
-- Dependencies: 152 152
-- Name: estados_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY estados
    ADD CONSTRAINT estados_pkey PRIMARY KEY (id);


--
-- TOC entry 1977 (class 2606 OID 186115)
-- Dependencies: 154 154
-- Name: formato_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY formato
    ADD CONSTRAINT formato_pkey PRIMARY KEY (id);


--
-- TOC entry 1979 (class 2606 OID 186117)
-- Dependencies: 156 156
-- Name: funcionalidades_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY funcionalidades
    ADD CONSTRAINT funcionalidades_pkey PRIMARY KEY (id);


--
-- TOC entry 1981 (class 2606 OID 186119)
-- Dependencies: 158 158
-- Name: intercambios_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY intercambios
    ADD CONSTRAINT intercambios_pkey PRIMARY KEY (id);


--
-- TOC entry 1983 (class 2606 OID 186121)
-- Dependencies: 160 160
-- Name: nacionalidad_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY nacionalidad
    ADD CONSTRAINT nacionalidad_pkey PRIMARY KEY (id);


--
-- TOC entry 1985 (class 2606 OID 186123)
-- Dependencies: 162 162
-- Name: recuperar_clave_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY recuperar_clave
    ADD CONSTRAINT recuperar_clave_pkey PRIMARY KEY (id);


--
-- TOC entry 1987 (class 2606 OID 186125)
-- Dependencies: 164 164
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 1989 (class 2606 OID 186127)
-- Dependencies: 166 166
-- Name: sectores_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY sectores
    ADD CONSTRAINT sectores_pkey PRIMARY KEY (id);


--
-- TOC entry 1991 (class 2606 OID 186129)
-- Dependencies: 168 168
-- Name: seguridad_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY seguridad
    ADD CONSTRAINT seguridad_pkey PRIMARY KEY (id);


--
-- TOC entry 1993 (class 2606 OID 186131)
-- Dependencies: 170 170
-- Name: servicios_informacion_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY servicios_informacion
    ADD CONSTRAINT servicios_informacion_pkey PRIMARY KEY (id);


--
-- TOC entry 1995 (class 2606 OID 186133)
-- Dependencies: 172 172
-- Name: solicitudes_suscripciones_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY solicitudes_suscripciones
    ADD CONSTRAINT solicitudes_suscripciones_pkey PRIMARY KEY (id);


--
-- TOC entry 1997 (class 2606 OID 186135)
-- Dependencies: 174 174
-- Name: suscritos_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY suscritos
    ADD CONSTRAINT suscritos_pkey PRIMARY KEY (id);


--
-- TOC entry 1999 (class 2606 OID 186137)
-- Dependencies: 176 176
-- Name: telefonos_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY telefonos
    ADD CONSTRAINT telefonos_pkey PRIMARY KEY (id);


--
-- TOC entry 2001 (class 2606 OID 186139)
-- Dependencies: 178 178
-- Name: tipos_datos_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY tipos_datos
    ADD CONSTRAINT tipos_datos_pkey PRIMARY KEY (id);


--
-- TOC entry 2003 (class 2606 OID 186141)
-- Dependencies: 180 180
-- Name: union_areas_servicios_informacion_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY union_areas_servicios_informacion
    ADD CONSTRAINT union_areas_servicios_informacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2005 (class 2606 OID 186143)
-- Dependencies: 182 182
-- Name: union_arquitecturas_servicios_informacion_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY union_arquitecturas_servicios_informacion
    ADD CONSTRAINT union_arquitecturas_servicios_informacion_pkey PRIMARY KEY (id);


--
-- TOC entry 2007 (class 2606 OID 186145)
-- Dependencies: 184 184
-- Name: union_sectores_entes_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY union_sectores_entes
    ADD CONSTRAINT union_sectores_entes_pkey PRIMARY KEY (id);


--
-- TOC entry 2009 (class 2606 OID 186147)
-- Dependencies: 186 186
-- Name: union_usuarios_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY union_usuarios_roles
    ADD CONSTRAINT union_usuarios_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2011 (class 2606 OID 186149)
-- Dependencies: 188 188
-- Name: url_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY url
    ADD CONSTRAINT url_pkey PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 186151)
-- Dependencies: 190 190
-- Name: usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 2015 (class 2606 OID 186153)
-- Dependencies: 192 192
-- Name: visitas_pkey; Type: CONSTRAINT; Schema: public; Owner: rnsii; Tablespace: 
--

ALTER TABLE ONLY visitas
    ADD CONSTRAINT visitas_pkey PRIMARY KEY (id_visita);


--
-- TOC entry 2047 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-04-24 15:16:00 VET

--
-- PostgreSQL database dump complete
--

