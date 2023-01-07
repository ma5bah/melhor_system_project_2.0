import { PrismaClient,type_of_employee } from "@prisma/client";
import { Logger } from "@nestjs/common";
const prisma = new PrismaClient({
  log: [
    {
      emit: "event",
      level: "query",
    },
  ],
});

async function create_employee() {
  try {
    await prisma.employee.create({
      data: {
        inventory: {
          connect: {
            id: 0,
          },
        },
        role:type_of_employee.manager,
        email: "masbah@gmail.com",
        password: "123456",
        contact: "01311807889",
      },
    });
  } catch (error) {
    console.error("ERROR : ", error);
  }
}
async function create_inventory() {
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
async function employee_login() {
  try {
    const employee_data = await prisma.employee.findFirst({
      where: {
        AND: [
          {
            email: "masbah@gmail.com",
          },
          {
            password: "123456",
          },
        ],
      },
    });
    console.log("data" + employee_data?.email);
  } catch (error) {}
}

async function main() {
  try {
    // await create_inventory();
    await create_employee();
    await employee_login();
  } catch (error) {
    console.error("ERROR : ", error);
  }
}
main()
  .then(async () => {
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });

prisma.$on("query",async (evt) => {
  await Logger.log(evt.query, "QUERY");
  await Logger.log(evt.params, "PARAM");
  // Logger.log(evt)
  // Logger.log(evt,"DURATION")
  //   console.log("Query: " + evt.query + "\nParams: " + evt.params);
  //   console.log("Duration: " + evt.duration + "ms");
});
