CREATE TABLE users
(
    id    bigint  not null auto_increment,
    username varchar(100) not null,
    password varchar(300) not null unique,

    primary key (id)
);

CREATE TABLE courses
(
    id   bigint  not null auto_increment,
    name varchar(100) not null,
    category varchar(100) not null,

    primary key (id)
);

CREATE TABLE profiles
(
    id    bigint  not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    id_user bigint,

    primary key (id),
    constraint fk_profile_user_id FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE topics
(
    id    bigint  not null auto_increment,
    title varchar(100) not null,
    message varchar(100) not null,
    creation_date datetime not null,
    status tinyint default 0,
    id_autor bigint not null,
    id_course bigint not null,

    primary key (id),
    constraint fk_topic_autor_id FOREIGN KEY (id_autor) REFERENCES profiles(id) ON DELETE CASCADE,
    constraint fk_topic_course_id FOREIGN KEY (id_course) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE answers
(
    id    bigint  not null auto_increment,
    message varchar(100) not null,
    creation_date datetime not null,
    solution tinyint default 0,
    id_autor bigint not null,
    id_topic bigint not null,

    primary key (id),

    constraint fk_answers_autor_id foreign key(id_autor) references profiles(id) ON DELETE CASCADE ,
    constraint fk_answers_topic_id foreign key(id_topic) references topics(id) ON DELETE CASCADE
);


