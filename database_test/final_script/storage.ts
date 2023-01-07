import { PrismaClient, product_category } from "@prisma/client";
import { prisma } from "../script";

export async function create_storage(name: string) {



    try {

        return await prisma.storage.create({
            data: {
                name: "general",
                address: "No Where",
                capacity: 100,
                category: product_category.general,
                inventory: {
                    connect: {
                        id: 0,
                    }
                },

            }
        })
    } catch (error) {


    }

}