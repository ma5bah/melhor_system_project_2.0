import { PrismaClient } from "@prisma/client";
import { prisma } from "../script";

export async function create_storage(name:string) {



    try {

        return await prisma.storage.create({
            data: {
                name: name,
                address: "No Where",
                capacity: 100,
                category: "main",
                inventory: {
                    connect: {
                        id: 1,
                    }
                },

            }
        })
    } catch (error) {
        console.error(error)

    }

}