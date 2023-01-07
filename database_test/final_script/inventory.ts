import { PrismaClient, type_of_employee } from "@prisma/client";
import { prisma } from "../script";
export async function create_inventory() {
  try {
    await prisma.inventory.create({
      data: {
        name: "don_masbah",
        id: 0,
      },
    });
  } catch (error) {
    console.error("ERROR : ", error);
  }
}
