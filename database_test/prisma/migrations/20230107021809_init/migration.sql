/*
  Warnings:

  - Added the required column `need_space` to the `Product` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "Product" ADD COLUMN     "need_space" DOUBLE PRECISION NOT NULL;
