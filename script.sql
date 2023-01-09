create type product_category as enum ('paper_made', 'liquid', 'general');

alter type product_category owner to "user";

create type type_of_employee as enum ('admin', 'manager');

alter type type_of_employee owner to "user";

create type type_of_order as enum ('buy', 'sell');

alter type type_of_order owner to "user";

create type type_of_order_status as enum ('pending', 'processing', 'delivered');

alter type type_of_order_status owner to "user";

create table _prisma_migrations
(
    id                  varchar(36)                            not null
        primary key,
    checksum            varchar(64)                            not null,
    finished_at         timestamp with time zone,
    migration_name      varchar(255)                           not null,
    logs                text,
    rolled_back_at      timestamp with time zone,
    started_at          timestamp with time zone default now() not null,
    applied_steps_count integer                  default 0     not null
);

alter table _prisma_migrations
    owner to "user";

create table "Inventory"
(
    id   serial
        primary key,
    name text not null
);

alter table "Inventory"
    owner to "user";

create table "Employee"
(
    id           serial
        primary key,
    email        text                                                 not null,
    password     text                                                 not null,
    contact      text                                                 not null,
    role         type_of_employee default 'manager'::type_of_employee not null,
    inventory_id integer                                              not null
        references "Inventory"
            on update cascade on delete restrict
);

alter table "Employee"
    owner to "user";

create unique index "Employee_email_key"
    on "Employee" (email);

create table "Storage"
(
    id           serial
        primary key,
    name         text             not null,
    address      text             not null,
    capacity     integer          not null,
    category     product_category not null,
    inventory_id integer          not null
        references "Inventory"
            on update cascade on delete restrict
);

alter table "Storage"
    owner to "user";

create table "Product"
(
    id          serial
        primary key,
    name        text             not null,
    category    product_category not null,
    quantity    integer          not null,
    price       double precision not null,
    expiry_date timestamp(3)     not null,
    storage_id  integer          not null
        references "Storage"
            on update cascade on delete restrict,
    need_space  double precision not null
);

alter table "Product"
    owner to "user";

create table "Supplier"
(
    id           serial
        primary key,
    username     text not null,
    company_name text not null,
    contact      text not null,
    address      text not null
);

alter table "Supplier"
    owner to "user";

create table "ProductAndSupplier"
(
    product_id  integer not null
        references "Product"
            on update cascade on delete restrict,
    supplier_id integer not null
        references "Supplier"
            on update cascade on delete restrict
);

alter table "ProductAndSupplier"
    owner to "user";

create unique index "ProductAndSupplier_product_id_supplier_id_key"
    on "ProductAndSupplier" (product_id, supplier_id);

create unique index "Supplier_username_key"
    on "Supplier" (username);

create table "Order"
(
    id           serial
        primary key,
    type         type_of_order        not null,
    coupen       text                 not null,
    status       type_of_order_status not null,
    inventory_id integer              not null
        references "Inventory"
            on update cascade on delete restrict
);

alter table "Order"
    owner to "user";

create table "ProductAndOrder"
(
    product_quantity double precision not null,
    product_id       integer          not null
        references "Product"
            on update cascade on delete restrict,
    order_id         integer          not null
        references "Order"
            on update cascade on delete restrict
);

alter table "ProductAndOrder"
    owner to "user";

create unique index "ProductAndOrder_product_id_order_id_key"
    on "ProductAndOrder" (product_id, order_id);

create unique index "Order_inventory_id_key"
    on "Order" (inventory_id);

create table "Payment"
(
    id             serial
        primary key,
    transection_id text             not null,
    total_amount   double precision not null,
    medium         text             not null,
    order_id       integer          not null
        references "Order"
            on update cascade on delete restrict
);

alter table "Payment"
    owner to "user";

create unique index "Payment_order_id_key"
    on "Payment" (order_id);


