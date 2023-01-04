-- AlterTable
CREATE SEQUENCE inventory_id_seq;
ALTER TABLE "Inventory" ALTER COLUMN "id" SET DEFAULT nextval('inventory_id_seq');
ALTER SEQUENCE inventory_id_seq OWNED BY "Inventory"."id";
