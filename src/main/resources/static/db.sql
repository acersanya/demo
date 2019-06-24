create table public.cinema
(
  name      text   not null,
  address   text   not null,
  cinema_id serial not null
    constraint cinema_pk
      primary key
);

alter table public.cinema
  owner to postgres;

create table public.movie
(
  name     text      not null,
  movie_id serial    not null
    constraint movie_pk
      primary key,
  duration timestamp not null
);

alter table public.movie
  owner to postgres;

create table public.movie_cinema
(
  cinema_id integer not null
    constraint cinema_fk
      references cinema,
  movie_id  integer not null
    constraint movie_fk
      references movie
);

alter table public.movie_cinema
  owner to postgres;

create index fki_fk_movie
  on public.movie_cinema (movie_id);


create table public.session
(
  session_id serial    not null
    constraint session_pk
      primary key,
  movie      serial    not null
    constraint movie_fk
      references movie,
  "end"      timestamp not null,
  start      timestamp not null
);

alter table public.session
  owner to postgres;

create index fki_movie_fk
  on public.session (movie);

create table public.ticket
(
  price      numeric not null,
  client     text    not null,
  session_id serial  not null
    constraint session_fk
      references session,
  ticket_id  serial  not null
    constraint ticket_pk
      primary key
);

alter table public.ticket
  owner to postgres;

create index fki_session_fk
  on public.ticket (session_id);
