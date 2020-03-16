create table if not exists item_table
(
    id bigserial not null
        constraint item_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    description varchar(255) not null,
    name varchar(255) not null,
    quantity integer not null,
    thumbnail varchar(255) not null
);

create table if not exists permission_table
(
    id bigserial not null
        constraint permission_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    http_method varchar(255),
    path_uri varchar(255)
);

create table if not exists role_table
(
    id bigserial not null
        constraint role_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255)
);

create table if not exists role_permission_table
(
    id bigserial not null
        constraint role_permission_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    permission_id bigint
        constraint fkgf59mj95pjwxsdfexq30cl9cf
            references permission_table,
    role_id bigint
        constraint fksk9o3j7044ppjafxofn06eqm9
            references role_table
);

create table if not exists user_table
(
    id bigserial not null
        constraint user_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null
        constraint uk_en3wad7p8qfu8pcmh62gvef6v
            unique
);

create table if not exists user_role_table
(
    id bigserial not null
        constraint user_role_table_pkey
            primary key,
    created_at timestamp,
    updated_at timestamp,
    role_id bigint
        constraint fkl8ioo1wxmieriir8betxwagg3
            references role_table,
    user_id bigint
        constraint fkbdp7o20pgggxrxs498xb9x2lg
            references user_table
);