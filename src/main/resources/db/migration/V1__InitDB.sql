create table homeworks (
                        id  bigserial not null,
                        is_done boolean default false not null,
                        title varchar(255) not null,
                        user_id int8 not null, primary key (id));
create table users (
                    id  bigserial not null,
                    first_name varchar(255) not null,
                    last_name varchar(255) not null,
                    primary key (id));

alter table homeworks add constraint FK_homeworks_to_users foreign key (user_id) references users;

insert into users (first_name, last_name) VALUES ('Test', 'Testov');
insert into homeworks(title, user_id) VALUES ('Test homework', 1);