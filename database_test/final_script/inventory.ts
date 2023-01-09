import { prisma } from "../script";
export async function create_inventory() {
  try {
    const data=await prisma.inventory.create({
      data: {
        name: "don",
        id: 0,
      },
    });
    console.log(data.name)
  } catch (error) {
    console.error("ERROR : ", error);
  }
}
