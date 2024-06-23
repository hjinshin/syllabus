import { authOptions } from "@/lib/authOptions";
import NextAuth from "next-auth";

/**
 * 
 * export interface Profile {
  sub?: string
  name?: string
  email?: string
  image?: string
}
 */

/* use aws cognito */
const handler = NextAuth(authOptions);

export { handler as GET, handler as POST };
