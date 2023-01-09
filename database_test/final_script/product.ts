import { PrismaClient } from "@prisma/client";
import { prisma } from "../script";

export async function create_product(name: string) {
  await prisma.product.create({
    data: {
      name: name,
      category: "general",
      expiry_date:new Date('2023-08-04T08:16:32.716904'),
      need_space: 1,
      price: 12,
      quantity: 100,
      storage: {
        connectOrCreate: {
          where: {
            id: 1212,
          },
          create: {
            address: "",
            capacity: 100000,
            category: "general",
            name: "init",
            inventory: {
              connect: {
                id: 0,
              },
            },
          },
        },
      },
    },
  });
}
