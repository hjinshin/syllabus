import { AuthOptions } from "next-auth";
import CognitoProvider from "next-auth/providers/cognito";

export const authOptions: AuthOptions = {
  providers: [
    CognitoProvider({
      clientId: process.env.COGNITO_CLIENT_ID!,
      clientSecret: process.env.COGNITO_CLIENT_SECRET!,
      issuer: process.env.COGNITO_ISSUER,
    }),
  ],
  callbacks: {
    async jwt({ token, account, profile }) {
      console.log(22222);
      console.log(token, account, profile);
      console.log(`access_token22: ${account?.access_token}`);
      if (account) {
        console.log(55);
        console.log({
          ...token,
          accessToken: account.access_token,
          sub: profile?.sub,
        });
        return {
          ...token,
          accessToken: account.access_token,
          sub: profile?.sub,
        };
      }
      return token;
    },
    async session({ session, token }) {
      // jwt 콜백에서 반환된 토큰 값을 세션에 추가
      let _session: any = session;
      _session.accessToken = token.accessToken;
      _session.sub = token.sub;
      return _session;
    },
  },
};