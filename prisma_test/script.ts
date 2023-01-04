import { PrismaClient } from '@prisma/client'

const prisma = new PrismaClient({
    log: ['query'],
});




async function main() {
    try {
        const data = await prisma.inventory.create({
            data: {
                name: "masbah"
            }
        })
        console.log(data)
    } catch (error) {
        console.error("ERROR : ", error)
    }
}

main()
    .then(async () => {
        await prisma.$disconnect()
    })
    .catch(async (e) => {
        console.error(e)
        await prisma.$disconnect()
        process.exit(1)
    })