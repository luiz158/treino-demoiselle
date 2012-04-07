--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auctions; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE auctions (
    id bigint NOT NULL,
    startingprice double precision,
    creation timestamp without time zone NOT NULL,
    sellingprice double precision,
    status integer NOT NULL,
    deadline timestamp without time zone,
    reserveprice double precision,
    mode integer NOT NULL,
    item_id integer NOT NULL,
    bestbid_id bigint
);


ALTER TABLE public.auctions OWNER TO sa_auction5;

--
-- Name: bidaudit; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE bidaudit (
    id bigint NOT NULL,
    host character varying(15),
    session character varying(32),
    datetime timestamp without time zone,
    locale character varying(10),
    encoding character varying(10),
    agent character varying(255),
    user_name character varying(20)
);


ALTER TABLE public.bidaudit OWNER TO sa_auction5;

--
-- Name: bids; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE bids (
    id bigint NOT NULL,
    amount double precision NOT NULL,
    "timestamp" timestamp without time zone NOT NULL,
    login character varying(10) NOT NULL,
    auction_id bigint
);


ALTER TABLE public.bids OWNER TO sa_auction5;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE categories (
    id smallint NOT NULL,
    name character varying(30) NOT NULL,
    active boolean NOT NULL,
    parent_id smallint
);


ALTER TABLE public.categories OWNER TO sa_auction5;

--
-- Name: items; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE items (
    id integer NOT NULL,
    description character varying(50) NOT NULL,
    category_id smallint NOT NULL
);


ALTER TABLE public.items OWNER TO sa_auction5;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: sa_auction5; Tablespace: 
--

CREATE TABLE orders (
    id bigint NOT NULL,
    winningmode integer NOT NULL,
    login character varying(10) NOT NULL,
    date timestamp without time zone NOT NULL,
    auction_id bigint
);


ALTER TABLE public.orders OWNER TO sa_auction5;

--
-- Name: auctions_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE auctions_seq
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.auctions_seq OWNER TO sa_auction5;

--
-- Name: auctions_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('auctions_seq', 50, true);


--
-- Name: bidaudit_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE bidaudit_seq
    START WITH 50
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bidaudit_seq OWNER TO sa_auction5;

--
-- Name: bidaudit_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('bidaudit_seq', 50, false);


--
-- Name: bids_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE bids_seq
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bids_seq OWNER TO sa_auction5;

--
-- Name: bids_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('bids_seq', 150, true);


--
-- Name: categories_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE categories_seq
    START WITH 50
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.categories_seq OWNER TO sa_auction5;

--
-- Name: categories_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('categories_seq', 50, false);


--
-- Name: items_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE items_seq
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.items_seq OWNER TO sa_auction5;

--
-- Name: items_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('items_seq', 50, true);


--
-- Name: orders_seq; Type: SEQUENCE; Schema: public; Owner: sa_auction5
--

CREATE SEQUENCE orders_seq
    INCREMENT BY 50
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.orders_seq OWNER TO sa_auction5;

--
-- Name: orders_seq; Type: SEQUENCE SET; Schema: public; Owner: sa_auction5
--

SELECT pg_catalog.setval('orders_seq', 50, true);


--
-- Data for Name: auctions; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY auctions (id, startingprice, creation, sellingprice, status, deadline, reserveprice, mode, item_id, bestbid_id) FROM stdin;
6	\N	2009-10-28 17:32:46.013	2273.5999999999999	0	\N	\N	1	7	\N
28	\N	2009-10-28 17:32:46.015	4847	0	\N	\N	1	16	\N
11	\N	2009-10-28 17:32:46.014	8727.7999999999993	0	\N	\N	1	22	\N
24	\N	2009-10-28 17:32:46.015	9241.1000000000004	0	\N	\N	1	10	\N
19	\N	2009-10-28 17:32:46.014	3133.0999999999999	0	\N	\N	1	31	\N
9	\N	2009-10-28 17:32:46.013	1367.5	0	\N	\N	1	28	\N
20	\N	2009-10-28 17:32:46.014	819.10000000000002	0	\N	\N	1	13	\N
16	\N	2009-10-28 17:32:46.014	4153.3999999999996	0	\N	\N	1	4	\N
5	11	2009-10-28 17:32:46.013	7772.6999999999998	0	2009-10-30 04:30:32.40	1157	2	32	\N
22	27.5	2009-10-28 17:32:46.015	8184.8000000000002	0	2009-11-02 09:35:50.116	4026.1999999999998	2	23	55
31	7.9000000000000004	2009-10-28 17:32:46.015	\N	0	2009-11-03 09:09:29.943	2302.8000000000002	0	6	6
2	13.1	2009-10-28 17:32:46.013	\N	0	2009-11-09 01:37:57.616	2816.0999999999999	0	33	104
1	24.100000000000001	2009-10-28 17:32:46.012	\N	0	2009-11-08 13:59:56.642	850.79999999999995	0	21	59
27	5.7000000000000002	2009-10-28 17:32:46.015	5781.1999999999998	0	2009-11-05 17:45:11.177	4889.8000000000002	2	11	61
25	8.4000000000000004	2009-10-28 17:32:46.015	3718.3000000000002	0	2009-11-05 09:35:13.474	4600.1999999999998	2	5	14
8	24.600000000000001	2009-10-28 17:32:46.013	\N	0	2009-11-04 03:10:16.335	3984.1999999999998	0	30	110
14	38.200000000000003	2009-10-28 17:32:46.014	1574.3	0	2009-11-12 08:20:16.818	2043.5999999999999	2	2	21
18	31.100000000000001	2009-10-28 17:32:46.014	\N	0	2009-11-03 01:37:57.366	3057.5	0	3	23
12	7	2009-10-28 17:32:46.014	4605.1000000000004	0	2009-11-04 11:49:28.565	851.29999999999995	2	14	70
21	47.700000000000003	2009-10-28 17:32:46.014	4172.8999999999996	0	2009-11-13 15:02:51.852	898.60000000000002	2	17	31
23	38.700000000000003	2009-10-28 17:32:46.015	\N	0	2009-11-06 16:21:14.556	3569.9000000000001	0	12	78
15	26.199999999999999	2009-10-28 17:32:46.014	6245.8999999999996	0	2009-11-04 19:13:56.268	4459.3000000000002	2	20	35
30	41.200000000000003	2009-10-28 17:32:46.015	\N	0	2009-11-19 04:45:45.819	1065.5999999999999	0	27	126
33	1.1000000000000001	2009-10-28 17:32:46.015	4093.6999999999998	0	2009-11-18 01:32:35.79	4683.1000000000004	2	29	128
13	42.399999999999999	2009-10-28 17:32:46.014	\N	0	2009-11-17 10:50:08.551	1033.5	0	15	41
3	\N	2009-10-28 17:32:46.013	9998.2000000000007	1	\N	\N	1	1	\N
17	10.199999999999999	2009-10-28 17:32:46.014	348.89999999999998	1	2009-11-05 01:42:33.221	3655.4000000000001	2	8	87
29	9.3000000000000007	2009-10-28 17:32:46.015	\N	0	2009-11-02 08:04:21.743	1293.5999999999999	0	18	44
7	\N	2009-10-28 17:32:46.013	6155.5	1	\N	\N	1	19	\N
10	47.899999999999999	2009-10-28 17:32:46.014	\N	0	2009-11-05 21:22:57.011	822	0	24	47
4	\N	2009-10-28 17:32:46.013	2919.9000000000001	1	\N	\N	1	25	\N
32	18.399999999999999	2009-10-28 17:32:46.015	7024.8999999999996	1	2009-11-19 21:26:31.737	2213.5	2	26	119
26	10.5	2009-10-28 17:32:46.015	\N	0	2009-11-19 00:48:38.171	2814.8000000000002	0	9	95
\.


--
-- Data for Name: bidaudit; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY bidaudit (id, host, session, datetime, locale, encoding, agent, user_name) FROM stdin;
\.


--
-- Data for Name: bids; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY bids (id, amount, "timestamp", login, auction_id) FROM stdin;
1	9	2009-10-28 17:32:46.11	berta	31
2	9.3000000000000007	2009-10-28 17:33:31.121	bart	31
3	9.6999999999999993	2009-10-28 17:34:16.129	frank	31
4	10.800000000000001	2009-10-28 17:35:01.138	donald	31
5	11.9	2009-10-28 17:35:46.145	john	31
6	12.5	2009-10-28 17:36:31.156	bart	31
7	9.3000000000000007	2009-10-28 17:32:46.162	john	25
8	9.5999999999999996	2009-10-28 17:33:31.167	berta	25
9	10	2009-10-28 17:34:16.173	frank	25
10	10.699999999999999	2009-10-28 17:35:01.178	frank	25
11	12.300000000000001	2009-10-28 17:35:46.185	berta	25
12	12.4	2009-10-28 17:36:31.19	donald	25
13	13	2009-10-28 17:37:16.197	berta	25
14	15.300000000000001	2009-10-28 17:38:01.202	john	25
15	40.100000000000001	2009-10-28 17:32:46.209	bart	14
16	41.200000000000003	2009-10-28 17:33:31.214	berta	14
17	41.399999999999999	2009-10-28 17:34:16.219	donald	14
18	42.600000000000001	2009-10-28 17:35:01.227	donald	14
19	48.799999999999997	2009-10-28 17:35:46.239	bart	14
20	49.5	2009-10-28 17:36:31.251	bart	14
21	51.5	2009-10-28 17:37:16.263	donald	14
22	34.700000000000003	2009-10-28 17:32:46.275	john	18
23	39.5	2009-10-28 17:33:31.287	john	18
24	48.799999999999997	2009-10-28 17:32:46.294	john	21
25	49.200000000000003	2009-10-28 17:33:31.299	bart	21
26	51.700000000000003	2009-10-28 17:34:16.311	bart	21
27	56	2009-10-28 17:35:01.323	donald	21
28	67.099999999999994	2009-10-28 17:35:46.329	berta	21
29	69.799999999999997	2009-10-28 17:36:31.336	frank	21
30	82.099999999999994	2009-10-28 17:37:16.347	john	21
31	87.299999999999997	2009-10-28 17:38:01.359	berta	21
32	27.899999999999999	2009-10-28 17:32:46.364	donald	15
33	32.200000000000003	2009-10-28 17:33:31.371	frank	15
34	34.200000000000003	2009-10-28 17:34:16.376	bart	15
35	36.799999999999997	2009-10-28 17:35:01.383	donald	15
36	48.299999999999997	2009-10-28 17:32:46.388	berta	13
37	53.299999999999997	2009-10-28 17:33:31.395	frank	13
38	61.100000000000001	2009-10-28 17:34:16.399	frank	13
39	72.299999999999997	2009-10-28 17:35:01.407	bart	13
40	79.700000000000003	2009-10-28 17:35:46.413	berta	13
41	81.299999999999997	2009-10-28 17:36:31.419	frank	13
42	9.5999999999999996	2009-10-28 17:32:46.425	bart	29
43	10.1	2009-10-28 17:33:31.431	john	29
44	11.1	2009-10-28 17:34:16.437	berta	29
45	57.200000000000003	2009-10-28 17:32:46.446	donald	10
46	62.299999999999997	2009-10-28 17:33:31.454	bart	10
47	70.799999999999997	2009-10-28 17:34:16.461	berta	10
48	30.100000000000001	2009-10-28 17:32:46.473	donald	22
49	32.700000000000003	2009-10-28 17:33:31.478	bart	22
50	34.5	2009-10-28 17:34:16.485	john	22
51	39.799999999999997	2009-10-28 17:35:01.491	john	22
52	40.799999999999997	2009-10-28 17:35:46.497	bart	22
53	47	2009-10-28 17:36:31.501	donald	22
54	54.600000000000001	2009-10-28 17:37:16.51	john	22
55	58.5	2009-10-28 17:38:01.515	john	22
56	27.399999999999999	2009-10-28 17:32:46.526	berta	1
57	30.800000000000001	2009-10-28 17:33:31.533	bart	1
58	36.100000000000001	2009-10-28 17:34:16.539	bart	1
59	39.200000000000003	2009-10-28 17:35:01.543	frank	1
60	5.7999999999999998	2009-10-28 17:32:46.55	donald	27
61	6.2000000000000002	2009-10-28 17:33:31.554	donald	27
62	7	2009-10-28 17:32:46.558	berta	12
63	7.2000000000000002	2009-10-28 17:33:31.563	john	12
64	7.7000000000000002	2009-10-28 17:34:16.567	frank	12
65	9.0999999999999996	2009-10-28 17:35:01.571	donald	12
66	9.5999999999999996	2009-10-28 17:35:46.575	donald	12
67	10.300000000000001	2009-10-28 17:36:31.581	berta	12
68	12.1	2009-10-28 17:37:16.585	berta	12
69	12.199999999999999	2009-10-28 17:38:01.589	donald	12
70	14.300000000000001	2009-10-28 17:38:46.593	bart	12
71	43.200000000000003	2009-10-28 17:32:46.599	donald	23
72	47.399999999999999	2009-10-28 17:33:31.605	frank	23
73	53.899999999999999	2009-10-28 17:34:16.609	donald	23
74	63.200000000000003	2009-10-28 17:35:01.613	donald	23
75	67.200000000000003	2009-10-28 17:35:46.617	bart	23
76	70.599999999999994	2009-10-28 17:36:31.621	bart	23
77	80.700000000000003	2009-10-28 17:37:16.625	berta	23
78	93.700000000000003	2009-10-28 17:38:01.629	donald	23
79	10.4	2009-10-28 17:32:46.633	john	17
80	10.800000000000001	2009-10-28 17:33:31.637	frank	17
81	10.9	2009-10-28 17:34:16.641	berta	17
82	11.9	2009-10-28 17:35:01.645	bart	17
83	12.699999999999999	2009-10-28 17:35:46.649	berta	17
84	14.199999999999999	2009-10-28 17:36:31.653	bart	17
85	16	2009-10-28 17:37:16.657	frank	17
86	17.800000000000001	2009-10-28 17:38:01.661	frank	17
87	18.199999999999999	2009-10-28 17:38:46.665	john	17
88	11.699999999999999	2009-10-28 17:32:46.669	john	26
89	12.5	2009-10-28 17:33:31.673	donald	26
90	14.5	2009-10-28 17:34:16.677	frank	26
91	14.5	2009-10-28 17:35:01.681	frank	26
92	15.199999999999999	2009-10-28 17:35:46.684	berta	26
93	17.899999999999999	2009-10-28 17:36:31.689	berta	26
94	18.100000000000001	2009-10-28 17:37:16.699	frank	26
95	18.600000000000001	2009-10-28 17:38:01.703	donald	26
96	13.800000000000001	2009-10-28 17:32:46.717	john	2
97	14.9	2009-10-28 17:33:31.72	donald	2
98	15.4	2009-10-28 17:34:16.725	bart	2
99	17.300000000000001	2009-10-28 17:35:01.729	donald	2
100	18.600000000000001	2009-10-28 17:35:46.732	bart	2
101	19.100000000000001	2009-10-28 17:36:31.737	bart	2
102	19.100000000000001	2009-10-28 17:37:16.742	bart	2
103	19.300000000000001	2009-10-28 17:38:01.745	bart	2
104	22.800000000000001	2009-10-28 17:38:46.749	berta	2
105	27	2009-10-28 17:32:46.753	berta	8
106	27.899999999999999	2009-10-28 17:33:31.756	donald	8
107	32.799999999999997	2009-10-28 17:34:16.76	john	8
108	37	2009-10-28 17:35:01.765	frank	8
109	38.5	2009-10-28 17:35:46.768	berta	8
110	43.299999999999997	2009-10-28 17:36:31.773	john	8
111	20.800000000000001	2009-10-28 17:32:46.776	frank	32
112	24.5	2009-10-28 17:33:31.779	john	32
113	25.699999999999999	2009-10-28 17:34:16.782	donald	32
114	29.100000000000001	2009-10-28 17:35:01.785	bart	32
115	32	2009-10-28 17:35:46.788	bart	32
116	32.299999999999997	2009-10-28 17:36:31.792	bart	32
117	37	2009-10-28 17:37:16.795	john	32
118	39.700000000000003	2009-10-28 17:38:01.798	donald	32
119	41.299999999999997	2009-10-28 17:38:46.801	bart	32
120	48	2009-10-28 17:32:46.804	berta	30
121	55.799999999999997	2009-10-28 17:33:31.81	donald	30
126	82.299999999999997	2009-10-28 17:37:16.824	frank	30
122	60.899999999999999	2009-10-28 17:34:16.813	bart	30
127	1.1000000000000001	2009-10-28 17:32:46.828	donald	33
123	71.799999999999997	2009-10-28 17:35:01.816	bart	30
128	1.2	2009-10-28 17:33:31.831	frank	33
124	73.200000000000003	2009-10-28 17:35:46.819	frank	30
125	82.200000000000003	2009-10-28 17:36:31.822	john	30
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY categories (id, name, active, parent_id) FROM stdin;
200	Electronics	t	\N
202	iPod & MP3 Players	t	200
100	Computers & Networking	t	\N
203	Televisions	t	200
201	DVD & Home Theater	t	200
102	Laptops	t	100
101	Desktops	t	100
\.


--
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY items (id, description, category_id) FROM stdin;
21	4GB 2.8" LCD Touch Screen MP3	202
33	Panasonic Viera TC-P42C1 42" Plasma	203
1	FAST PRO DELL P4 2.8 GHZ DESKTOP COMPUTER PC	101
25	Apple iPod touch 2nd Generation 8GB	202
32	New 7 Inch Portable TV 16	203
7	Dell Optiplex GX270 P4 2.8 Ghz 512MB	101
19	Dell 3400mp DLP Projector	201
30	Sharp 32 LCD LED 1080p 120hz RS232	203
28	New Toshiba 32AV502R 32" 720p LCD	203
24	Apple iPod NANO 4GB Silver 2ND Gen	202
22	New 8GB MP3 MP4 MP5 Player 	202
14	Dell Latitude C600 Laptop P3 850MHz 10GB 256MB	102
15	HD 1080i Projector HDMI	201
2	COMPAQ P4 2.0 GHZ DESKTOP PC COMPUTER 17" LCD	101
20	Nbox HD Media USB SD TV Player	201
4	HP DC5100 SFF P4HT 2.8GHz 1GB 40GB DVD/CD-RW	101
8	HP Pavilion G60 G60T Laptop Notebook	102
3	Visionman VBBA-1A7811 Barebones AMD64 X2 4200+	101
31	Samsung 40''/1080P/LCD HD	203
13	Panasonic Toughbook 29 FULLY Rugged 1.6GHz	102
17	SONY DVP-NS700H 1080p DVD PLAYER	201
23	New 1.8" 16GB USB 3rd Slim MP3 MP4	202
12	Dell Studio 17 Laptop 4G 320G Bluetooth HDMI	102
10	Sony Vaio Laptop VGN-NS325J/T J/S 3GB 250GB	102
5	IBM ThinkCentre M50 8187 P4 2.8GHz 256Mb 40Gb	101
9	IBM THINKPAD T60 LAPTOP 2GHz Core Duo 100GB	102
11	Compaq Evo N800c Series PP2130 Laptop	102
16	Philips 1080P HDMi DVD Player	201
18	Philips DVP3040 Scan DVD w/ DivX	201
27	52" Philips LCD 1080p 120 HZ HDTV	203
6	IBM Netvista 8319410 P4 2.4Ghz 512MB 40GB	101
26	Samsung 24" LCD TV HDTV FLAT T240HD	203
29	Sharp AQUOS LC52D82U 52" LCD HDTV	203
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: sa_auction5
--

COPY orders (id, winningmode, login, date, auction_id) FROM stdin;
1	1	frank	2009-10-28 17:32:46.842	3
2	1	donald	2009-10-28 17:32:46.855	17
3	1	john	2009-10-28 17:32:46.863	7
4	1	frank	2009-10-28 17:32:46.869	4
5	1	john	2009-10-28 17:32:46.875	32
\.


--
-- Name: auctions_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY auctions
    ADD CONSTRAINT auctions_pkey PRIMARY KEY (id);


--
-- Name: bidaudit_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY bidaudit
    ADD CONSTRAINT bidaudit_pkey PRIMARY KEY (id);


--
-- Name: bids_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY bids
    ADD CONSTRAINT bids_pkey PRIMARY KEY (id);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: items_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- Name: orders_pkey; Type: CONSTRAINT; Schema: public; Owner: sa_auction5; Tablespace: 
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: fk_auctions_bestbid_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY auctions
    ADD CONSTRAINT fk_auctions_bestbid_id FOREIGN KEY (bestbid_id) REFERENCES bids(id);


--
-- Name: fk_auctions_item_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY auctions
    ADD CONSTRAINT fk_auctions_item_id FOREIGN KEY (item_id) REFERENCES items(id);


--
-- Name: fk_bids_auction_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY bids
    ADD CONSTRAINT fk_bids_auction_id FOREIGN KEY (auction_id) REFERENCES auctions(id);


--
-- Name: fk_categories_parent_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT fk_categories_parent_id FOREIGN KEY (parent_id) REFERENCES categories(id);


--
-- Name: fk_items_category_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY items
    ADD CONSTRAINT fk_items_category_id FOREIGN KEY (category_id) REFERENCES categories(id);


--
-- Name: fk_orders_auction_id; Type: FK CONSTRAINT; Schema: public; Owner: sa_auction5
--

ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_orders_auction_id FOREIGN KEY (auction_id) REFERENCES auctions(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

