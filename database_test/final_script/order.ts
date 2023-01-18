import { PrismaClient } from "@prisma/client";
import { prisma } from "../script";


export async function create_order() {
  await prisma.order.create({
    data: {
      coupen: " ",
      type: "pending1",
      status: "pending2",
      product_data: {
        createMany: {
          data: [
            {
              product_id: 3,
              product_quantity: 10,
            },
            {
              product_id: 2,
              product_quantity: 10,
            },
          ],
        },
      },
      inventory:{
        connect:{
          id:1
        }
      }
    },
  });
}
