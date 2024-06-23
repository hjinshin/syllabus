import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import Header from "@/components/header";
import { CommandDemo } from "@/components/command";
import Footer from "@/components/footer";
import { Toaster } from "@/components/ui/toaster";
import { getServerSession } from "next-auth/next";
import { redirect } from "next/navigation";
import { authOptions } from "@/lib/authOptions";

const pretendard = localFont({
  src: "../fonts/PretendardVariable.woff2",
  weight: "variable",
  variable: "--font-pretendard",
});

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const session = await getServerSession(authOptions);
  console.log(`session data from component`);
  console.log(session);
  // if (!session) {
  //   fetch("/api/auth/signin")
  // }
  return (
    <html lang="ko">
      <body
        className={`${pretendard.className} relative h-screen w-full flex-col justify-center font-pretendard`}
      >
        <div className="mx-auto flex h-full w-full max-w-5xl flex-col items-center">
          <div className="flex h-full w-full flex-col items-center">
            <Header />
            {children}
            <CommandDemo />
          </div>
          <Footer />
        </div>
        <Toaster />
      </body>
    </html>
  );
}
