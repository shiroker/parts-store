create table if not exists car_part(
    id bigserial primary key,
    name varchar(255),
    car_marke varchar(255),
    car_model varchar(100),
    car_part_type varchar(100),
    bar_code varchar(255),
    rabatt bigint,
    price numeric(10,4)
);
insert into car_part(id, name, car_marke, car_model, car_part_type, bar_code, rabatt, price)
values (1, 'Frontscheibe', 'BMW', '318i', 'SCHEIBEN', 'GHDK5364GDJHGD456', 1, 23);
commit;
