import { getTimeTables } from "@/lib/dummy-data-provider";
import { DataTable } from "./data-table";
import { columns } from "./columns";

const Title = () => (
  <h1 className="text-xl font-semibold text-neutral-700">강의 찾기</h1>
);

const Finder = async () => {
  const timeTables = await getTimeTables();
  console.log(timeTables);
  return (
    <main className="container h-full w-full max-w-5xl flex-col px-8 py-5 space-y-4">
      <Title />
      <DataTable columns={columns} data={timeTables} />
    </main>
  );
};

export default Finder;
