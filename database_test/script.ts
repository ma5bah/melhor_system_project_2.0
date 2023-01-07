import {  PrismaClient } from "@prisma/client";
import { Logger } from "@nestjs/common";
import { create_employee, employee_login } from "./final_script/emloyee";
import { create_inventory } from "./final_script/inventory";
export const prisma = new PrismaClient({
  log: [
    {
      emit: "event",
      level: "query",
    },
  ],
});




async function main() {
  try {

    









    // await create_inventory();
    await create_employee("masbahuddin65@gmail.com");
    // await employee_login();
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

prisma.$on("query", async (evt) => {
  await Logger.log(evt.query, "QUERY");
  await Logger.debug(evt.params, "PARAM");
  // Logger.log(evt)
  // Logger.log(evt,"DURATION")
  //   console.log("Query: " + evt.query + "\nParams: " + evt.params);
  //   console.log("Duration: " + evt.duration + "ms");
});
