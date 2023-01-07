-- CreateEnum
CREATE TYPE "product_category" AS ENUM ('paper_made', 'liquid', 'general');

-- CreateEnum
CREATE TYPE "type_of_employee" AS ENUM ('admin', 'manager');

-- CreateEnum
CREATE TYPE "type_of_order" AS ENUM ('buy', 'sell');

-- CreateEnum
CREATE TYPE "type_of_order_status" AS ENUM ('pending', 'processing', 'delivered');

-- CreateTable
CREATE TABLE "Inventory" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,

    CONSTRAINT "Inventory_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Employee" (
    "id" SERIAL NOT NULL,
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "contact" TEXT NOT NULL,
    "role" "type_of_employee" NOT NULL,
    "inventory_id" INTEGER NOT NULL,

    CONSTRAINT "Employee_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Storage" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "address" TEXT NOT NULL,
    "capacity" INTEGER NOT NULL,
    "category" "product_category" NOT NULL,
    "inventory_id" INTEGER NOT NULL,

    CONSTRAINT "Storage_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Product" (
    "id" SERIAL NOT NULL,
    "name" TEXT NOT NULL,
    "category" "product_category" NOT NULL,
    "quantity" INTEGER NOT NULL,
    "price" DOUBLE PRECISION NOT NULL,
    "expiry_date" TIMESTAMP(3) NOT NULL,
    "storage_id" INTEGER NOT NULL,

    CONSTRAINT "Product_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ProductAndSupplier" (
    "product_id" INTEGER NOT NULL,
    "supplier_id" INTEGER NOT NULL
);

-- CreateTable
CREATE TABLE "Supplier" (
    "id" SERIAL NOT NULL,
    "username" TEXT NOT NULL,
    "company_name" TEXT NOT NULL,
    "contact" TEXT NOT NULL,
    "address" TEXT NOT NULL,

    CONSTRAINT "Supplier_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ProductAndOrder" (
    "product_quantity" DOUBLE PRECISION NOT NULL,
    "product_id" INTEGER NOT NULL,
    "order_id" INTEGER NOT NULL
);

-- CreateTable
CREATE TABLE "Order" (
    "id" SERIAL NOT NULL,
    "type" "type_of_order" NOT NULL,
    "coupen" TEXT NOT NULL,
    "status" "type_of_order_status" NOT NULL,
    "inventory_id" INTEGER NOT NULL,

    CONSTRAINT "Order_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Payment" (
    "id" SERIAL NOT NULL,
    "transection_id" TEXT NOT NULL,
    "total_amount" DOUBLE PRECISION NOT NULL,
    "medium" TEXT NOT NULL,
    "order_id" INTEGER NOT NULL,

    CONSTRAINT "Payment_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "Employee_email_key" ON "Employee"("email");

-- CreateIndex
CREATE UNIQUE INDEX "ProductAndSupplier_product_id_supplier_id_key" ON "ProductAndSupplier"("product_id", "supplier_id");

-- CreateIndex
CREATE UNIQUE INDEX "Supplier_username_key" ON "Supplier"("username");

-- CreateIndex
CREATE UNIQUE INDEX "ProductAndOrder_product_id_order_id_key" ON "ProductAndOrder"("product_id", "order_id");

-- CreateIndex
CREATE UNIQUE INDEX "Order_inventory_id_key" ON "Order"("inventory_id");

-- CreateIndex
CREATE UNIQUE INDEX "Payment_order_id_key" ON "Payment"("order_id");

-- AddForeignKey
ALTER TABLE "Employee" ADD CONSTRAINT "Employee_inventory_id_fkey" FOREIGN KEY ("inventory_id") REFERENCES "Inventory"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Storage" ADD CONSTRAINT "Storage_inventory_id_fkey" FOREIGN KEY ("inventory_id") REFERENCES "Inventory"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Product" ADD CONSTRAINT "Product_storage_id_fkey" FOREIGN KEY ("storage_id") REFERENCES "Storage"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProductAndSupplier" ADD CONSTRAINT "ProductAndSupplier_product_id_fkey" FOREIGN KEY ("product_id") REFERENCES "Product"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProductAndSupplier" ADD CONSTRAINT "ProductAndSupplier_supplier_id_fkey" FOREIGN KEY ("supplier_id") REFERENCES "Supplier"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProductAndOrder" ADD CONSTRAINT "ProductAndOrder_product_id_fkey" FOREIGN KEY ("product_id") REFERENCES "Product"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProductAndOrder" ADD CONSTRAINT "ProductAndOrder_order_id_fkey" FOREIGN KEY ("order_id") REFERENCES "Order"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Order" ADD CONSTRAINT "Order_inventory_id_fkey" FOREIGN KEY ("inventory_id") REFERENCES "Inventory"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Payment" ADD CONSTRAINT "Payment_order_id_fkey" FOREIGN KEY ("order_id") REFERENCES "Order"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
