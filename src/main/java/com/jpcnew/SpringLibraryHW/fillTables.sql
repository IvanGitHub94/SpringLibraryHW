create table public.books
(
id serial primary key,
title varchar(100) NOT NULL unique,
author varchar(100) NOT NULL
);

create table public.users (
    id serial primary key,
    surname varchar(50) not null,
    name varchar(50) not null,
    date_birth date not null,
    phone varchar(12) not null,
    email varchar(30) not null unique
);

create table public.bookrent (
  id serial PRIMARY KEY,
  user_email VARCHAR(255) NOT NULL,
  book_title VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_email) REFERENCES public.users(email),
  FOREIGN KEY (book_title) REFERENCES public.books(title)
);

INSERT INTO public.books(title, author)
VALUES ('Недоросль', 'Д. И. Фонвизин');
INSERT INTO public.books(title, author)
VALUES ('Путешествие из Петербурга в Москву', 'А. Н. Радищев');
INSERT INTO public.books(title, author)
VALUES ('Доктор Живаго', 'Б. Л. Пастернак');
INSERT INTO public.books(title, author)
VALUES ('Сестра моя - жизнь', 'Б. Л. Пастернак');

insert into public.users (surname, name, date_birth, phone, email)
values ('Аксенов', 'Иван', '2001-02-15', 89991234567, 'acsivan@mail.ru');

insert into public.users (surname, name, date_birth, phone, email)
values ('Петров', 'Евгений', '1995-06-27', 89997654321, 'petrev@mail.ru');

insert into public.users (surname, name, date_birth, phone, email)
values ('Громова', 'Марина', '2000-10-11', 89991111111, 'gromar@mail.ru');

insert into public.bookrent (user_email, book_title)
values ('acsivan@mail.ru', 'Недоросль'),
('acsivan@mail.ru', 'Путешествие из Петербурга в Москву'),
('petrev@mail.ru', 'Доктор Живаго'),
('gromar@mail.ru', 'Сестра моя - жизнь');

select *
from public.books;

select *
from public.users;

select *
from public.bookrent;