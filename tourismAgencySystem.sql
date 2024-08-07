PGDMP                      |            tourismAgencySystem    15.7    16.3 "    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    25105    tourismAgencySystem    DATABASE     �   CREATE DATABASE "tourismAgencySystem" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 %   DROP DATABASE "tourismAgencySystem";
                postgres    false            �            1259    25106    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_carpark boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_roomservice boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25111    Hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Hotel_hotel_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    214            �            1259    25112    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type text NOT NULL,
    pension_factor double precision NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    25117    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    25118    reservation    TABLE     s  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count bigint NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25123    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    25124    room    TABLE     �  CREATE TABLE public.room (
    room_id bigint NOT NULL,
    hotel_name text NOT NULL,
    pension_type text NOT NULL,
    room_type text NOT NULL,
    stock bigint NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity bigint NOT NULL,
    square_meter text NOT NULL,
    tv boolean NOT NULL,
    minibar boolean NOT NULL,
    konsol boolean NOT NULL,
    kasa boolean NOT NULL,
    projeksiyon boolean NOT NULL,
    hotel_id bigint NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25129    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    25130    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    baslangic date NOT NULL,
    bitis date NOT NULL,
    season_factor double precision NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    25133    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    25134    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25139    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224                      0    25106    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_carpark, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_roomservice) FROM stdin;
    public          postgres    false    214   �)                 0    25112    pension 
   TABLE DATA           U   COPY public.pension (pension_id, hotel_id, pension_type, pension_factor) FROM stdin;
    public          postgres    false    216    +                 0    25118    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone) FROM stdin;
    public          postgres    false    218   �+                 0    25124    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_name, pension_type, room_type, stock, adult_price, child_price, bed_capacity, square_meter, tv, minibar, konsol, kasa, projeksiyon, hotel_id) FROM stdin;
    public          postgres    false    220    -                  0    25130    season 
   TABLE DATA           V   COPY public.season (season_id, hotel_id, baslangic, bitis, season_factor) FROM stdin;
    public          postgres    false    222   u.       "          0    25134    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    224   �.       *           0    0    Hotel_hotel_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Hotel_hotel_id_seq"', 11, true);
          public          postgres    false    215            +           0    0    pension_pension_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.pension_pension_id_seq', 21, true);
          public          postgres    false    217            ,           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 16, true);
          public          postgres    false    219            -           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 22, true);
          public          postgres    false    221            .           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 19, true);
          public          postgres    false    223            /           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 18, true);
          public          postgres    false    225                       2606    25141    hotel Hotel_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT "Hotel_pkey" PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotel DROP CONSTRAINT "Hotel_pkey";
       public            postgres    false    214            �           2606    25143    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    216            �           2606    25145    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    218            �           2606    25147    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    220            �           2606    25149    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    222            �           2606    25151    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    224               [  x�m�MN�0�דSdRU�vb�h�PŏT�6nc��G���	8K�p�½��Ui<{��Oo� �����s+��5LLnw�b�jϚK5X�lH�f����Y8��+J`&�r2�D�UOU��+��&����2�t���t��z���*�q+9l����
��	�L�`O�ja),jQ�%��»0^(ץ$�0��R8rt�d0^��Ƿ��n���A�����g�(\�\h^Ƴ��-,��o�$ӌ�ެ\?���4޷P�kn9�l�^�.�d!/�DCh(��T0V|����7T���$��2L�f��y����F:�w���</���_aT����� ��?6Σ         �   x�u�=�@F�S�	�� ����&jab3Q	+$HL8��5gP��g������2���U��yߋ�,�\z� u<��k�.f����"r�B�Nj�z���^�u,!��GyD�6z*���k�h�Ĕ�Vv�؁N�:�N(��K
;nc�~8�U�M�m�|��,.��1xF���6$$��e+�F�0��o�         ;  x�eQIj1<�^1�0F�ZFssN~A ٣��,a�����?,�x���Tu���$�K����B�� �j}��>��� ǁ���_�:_������R�6���@��W]����!x�}�'���r�'�}z����AR�N$}V�|&�,��9	
c峗ߟ6D�q�3��'��4<��bp�J�v�bUsG����ݞ�顙�[�;�"%X�A�� G@2Z��T<�B��ﳷ˹e��1�����:|���˘�?��Ir��\�9���n�V��*V����+s�)k�x_!� t�         E  x���KN�0���>��,K��x����͔��%�A�2�d�3PH�"�Q�,2�&��s�V/��3+KՔp�Dz��g�n������@d�oo� �k�^�pwhwےΰ���T01��+�� ���G��^I"�V;�t*-�|�:���=�{S敄�S��J�C��$$�#k5?��L.@L�ֵZJz*�aMǺY@u!q?IP8�)� <?H��}�T�X�MƇ�Gqy��1Z���B�'���?��>����ߵ9Ȉ_�h����5�0��8n~ܥ��!�UA���N���Yv1¿%+7x���+
]����AkN�!_��à          y   x�}�K�0C��.�0�|�2�?�0�(�&�X =�-7��Z9�9�5��@��NL�q�*�Ş�$���.ukN���i{=X'~ʭc��g��Kx�tM��]�n�:}�u���C�(�:���� �ދD�      "   T   x�3�LL����4 NG�ˈ3;5/*�[��_���ehʙ�������Ќ�*�2/� ]�9gjRQ)��gI~AQb6�x� %�     