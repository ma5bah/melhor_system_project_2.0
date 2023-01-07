import { category_of_product, PrismaClient, product_category } from "@prisma/client";
import { prisma } from "../script";

export async function create_product(name: string) {
    await prisma.product.create({
        data: {
            name: name,
            category: product_category.general
        }
    })

}