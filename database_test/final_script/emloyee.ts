import { PrismaClient, type_of_employee } from "@prisma/client";
import { prisma } from "../script";

export async function employee_login() {
    try {
        const employee_data = await prisma.employee.findFirst({
            where: {
                email: "masbah@gmail.com",
                password: "123456"
            },
        });
        console.log("data " + employee_data?.email);
    } catch (error) { }
}

export async function create_employee(email: string) {
    try {
        await prisma.employee.create({
            data: {
                inventory: {
                    connect: {
                        id: 0,
                    },
                },
                email: email,
                password: "123456",
                contact: "01311807889",
            },
        });
    } catch (error) {
        console.error("ERROR : ", error);
    }
}