PGDMP  4                    |            tourismAgency    15.7    16.3 '    /           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            0           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            1           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            2           1262    24974    tourismAgency    DATABASE     �   CREATE DATABASE "tourismAgency" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE "tourismAgency";
                postgres    false            �            1259    25028    feature    TABLE     a   CREATE TABLE public.feature (
    feature_id integer NOT NULL,
    feature_name text NOT NULL
);
    DROP TABLE public.feature;
       public         heap    postgres    false            �            1259    25027    feature_feature_id_seq    SEQUENCE     �   ALTER TABLE public.feature ALTER COLUMN feature_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.feature_feature_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    24978    hotel    TABLE       CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text,
    hotel_phoneno text,
    hotel_star integer,
    hotel_room_id integer,
    hotel_pensiontype_id integer,
    hotel_features_id integer[]
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25043    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    24984    pension    TABLE     i   CREATE TABLE public.pension (
    pensiontype_id integer NOT NULL,
    pensiontype_name text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    25003    pension_pensiontype_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pensiontype_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pensiontype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    25052    reservation    TABLE     l  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_customer_name text NOT NULL,
    reservation_hotel_id integer NOT NULL,
    reservation_str_date date NOT NULL,
    reservartion_fnsh_date date NOT NULL,
    reservation_adult_number integer NOT NULL,
    reservation_child_number integer,
    reservation_total_price bigint
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25051    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    227            �            1259    24987    room    TABLE     �   CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_name text NOT NULL,
    room_price bigint NOT NULL,
    room_stock integer
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25011    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    24981    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_name text NOT NULL,
    season_str_date date NOT NULL,
    season_fnsh_date date NOT NULL,
    season_rate_multiplier numeric
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    25019    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    24975    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL,
    user_full_name text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    24993    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            )          0    25028    feature 
   TABLE DATA           ;   COPY public.feature (feature_id, feature_name) FROM stdin;
    public          postgres    false    224   X,                  0    24978    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phoneno, hotel_star, hotel_room_id, hotel_pensiontype_id, hotel_features_id) FROM stdin;
    public          postgres    false    215   �,       "          0    24984    pension 
   TABLE DATA           C   COPY public.pension (pensiontype_id, pensiontype_name) FROM stdin;
    public          postgres    false    217   -       ,          0    25052    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, reservation_customer_name, reservation_hotel_id, reservation_str_date, reservartion_fnsh_date, reservation_adult_number, reservation_child_number, reservation_total_price) FROM stdin;
    public          postgres    false    227   �-       #          0    24987    room 
   TABLE DATA           J   COPY public.room (room_id, room_name, room_price, room_stock) FROM stdin;
    public          postgres    false    218   �-       !          0    24981    season 
   TABLE DATA           s   COPY public.season (season_id, season_name, season_str_date, season_fnsh_date, season_rate_multiplier) FROM stdin;
    public          postgres    false    216   V.                 0    24975    user 
   TABLE DATA           ^   COPY public."user" (user_id, user_name, user_password, user_role, user_full_name) FROM stdin;
    public          postgres    false    214   �.       3           0    0    feature_feature_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.feature_feature_id_seq', 6, true);
          public          postgres    false    223            4           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 1, true);
          public          postgres    false    225            5           0    0    pension_pensiontype_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.pension_pensiontype_id_seq', 7, true);
          public          postgres    false    220            6           0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 1, true);
          public          postgres    false    226            7           0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 5, true);
          public          postgres    false    221            8           0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 2, true);
          public          postgres    false    222            9           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 4, true);
          public          postgres    false    219            �           2606    25034    feature feature_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.feature
    ADD CONSTRAINT feature_pkey PRIMARY KEY (feature_id);
 >   ALTER TABLE ONLY public.feature DROP CONSTRAINT feature_pkey;
       public            postgres    false    224            �           2606    25050    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    215            �           2606    25010    pension pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pensiontype_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    217            �           2606    25058    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    227            �           2606    25018    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    218            �           2606    25026    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    216            �           2606    25000    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    214            )   X   x�3�t+JMUH,���K�2�����2���/I�Qp��K�L-JO�2�42�7W���UN-*�LN�2����u��2�p����� )��          O   x�3�ʬ�/V(J��,���=2�Ǒ�$X sH�M���K���Զ40517��0535�4�4�4�6�1������ ���      "   k   x�3��))JTp��Q��K�)-�,K�2�D�s���*8�&f�%�p�p��U��g��q�rz$���f�^��%
N�)\朮@SR2�ҁv$�g��p��qqq S�&D      ,   7   x�3��N�K�S�<�1'���*NCN##]s]CS�(n�ihj``����� �8&      #   e   x�5�A
�  ���+|A����TG/I������i�u	��=81�Aj"��-,������U�$�Ň���3��v0�ч�v]�%�0\6��z_�G� �}��      !   D   x�3�.��M-RNM,�ϋ��4202�50�50�2-ALC=S.#��̼�Z��!B�1�i T���� -��         \   x�3�t��K��4 NG_O?�@�������l.cNǜ��"NC �t���tu��)D٘��X�e��Z��Z�iEA���˹b���� ���     