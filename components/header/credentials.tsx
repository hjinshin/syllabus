import { getServerSession } from "next-auth/next";
import Link from "next/link";

const Credentials = async () => {
  const session = await getServerSession();

  if (session) {
    return (
      <div className="flex flex-row items-center space-x-1">
        <p className="text-xs font-medium text-neutral-500">
          {session.user?.name}
        </p>
        <Link href="/api/auth/signout">
          <p className="text-xs text-neutral-500">
            {!session ? `로그아웃` : `로그인`}
          </p>
        </Link>
      </div>
    );
  }
};

export default Credentials;
