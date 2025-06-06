

create table public.shedlock
(
    name       varchar(64) not null
        primary key,
    lock_until timestamp(3),
    locked_at  timestamp(3),
    locked_by  varchar(255)
);

comment on table public.shedlock is '스케줄 중복 처리 방지를 위한 Lock 정보를 담고 있는 테이블';

alter table public.shedlock
    owner to jpademo;



create table public.account
(
    account_id    bigint    default nextval('cid'::regclass) not null
        constraint account_pk
            primary key,
    email         varchar(50),
    name          varchar(50),
    created       timestamp default now(),
    password      varchar(100),
    refresh_token varchar(100)
);

comment on table public.account is '계정';

comment on column public.account.account_id is '계정ID';

comment on column public.account.email is '계정 이메일';

comment on column public.account.name is '계정명';

comment on column public.account.created is '등록일시';

comment on column public.account.password is 'Password';

comment on column public.account.refresh_token is 'Refresh Token';

alter table public.account
    owner to jpademo;




create table public.charge_place
(
    place_id   bigint    default nextval('cid'::regclass) not null
        constraint charge_place_pk
            primary key,
    place_name varchar(50),
    latitude   double precision,
    longitude  double precision,
    created    timestamp default now()
);

comment on table public.charge_place is '충전소 정보';

comment on column public.charge_place.place_id is '충전소 ID';

comment on column public.charge_place.place_name is '충전소명';

comment on column public.charge_place.latitude is '위도';

comment on column public.charge_place.longitude is '경도';

comment on column public.charge_place.created is '등록일시';

alter table public.charge_place
    owner to jpademo;


create table public.charge_station
(
    station_id    bigint    default nextval('cid'::regclass) not null
        constraint charge_station_pk
            primary key,
    station_name  varchar(50),
    charge_box_id varchar(50),
    created       timestamp default now(),
    place_id      bigint
        constraint charge_station_charge_place_place_id_fk
            references public.charge_place
);

comment on table public.charge_station is '충전기 정보';

comment on column public.charge_station.station_id is '충전기 ID';

comment on column public.charge_station.station_name is '충전기명';

comment on column public.charge_station.charge_box_id is 'Charge Box Id';

comment on column public.charge_station.created is '등록일시';

comment on column public.charge_station.place_id is '충전소 ID';

alter table public.charge_station
    owner to jpademo;


create table public.ocpp_connector
(
    id           bigint    default nextval('cid'::regclass) not null
        constraint ocpp_connector_pk
            primary key,
    connector_id integer,
    station_id   bigint
        constraint ocpp_connector_charge_station_station_id_fk
            references public.charge_station,
    created      timestamp default now()
);

comment on table public.ocpp_connector is 'Connector';

comment on column public.ocpp_connector.id is 'ID';

comment on column public.ocpp_connector.connector_id is 'Connector ID';

comment on column public.ocpp_connector.station_id is '충전기 ID';

comment on column public.ocpp_connector.created is '등록일시';

alter table public.ocpp_connector
    owner to jpademo;

create unique index ocpp_connector_station_id_connector_id_uindex
    on public.ocpp_connector (station_id, connector_id);



