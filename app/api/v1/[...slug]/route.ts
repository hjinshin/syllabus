import axios from "axios";
import { getToken } from "next-auth/jwt";
import { cookies } from "next/headers";
import { NextRequest } from "next/server";

export async function GET(
  req: Request,
  {
    params,
  }: {
    params: {
      slug: string[];
    };
  },
) {
  const slug = params.slug;
  const endpoint = "/api/v1/" + slug.join("/");
  const url = process.env.API_URL + endpoint + "?" + new URLSearchParams(req.url).toString();
  console.log(`params is ${JSON.stringify(params)}`);
  console.log(`endpoint is ${endpoint}`);

  const jwt = await getToken({
    req: req as NextRequest,
    secret: process.env.JWT_SECRET,
  });

  if (!jwt) {
    return new Response("Unauthorized", { status: 401 });
  }

  const token = jwt.accessToken;
  // console.log(44);
  // console.log(jwt);
  // console.log(req.headers);
  console.log(`token is ${JSON.stringify(token)}`)
  const response = await axios
    .get(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((response) => {
      console.log(response.data);
      return new Response(JSON.stringify(response.data), {
        status: response.status,
      });
    })
    .catch((error) => {
      console.error(error);
      return new Response(error.message, { status: 500 });
    });

  return response;
}

export async function POST(
  req: Request,
  {
    params,
  }: {
    params: {
      slug: string[];
    };
  },
) {
  const slug = params.slug;
  const endpoint = "/api/v1/" + slug.join("/");
  const url = process.env.API_URL + endpoint;
  const body = await req.json();

  const jwt = await getToken({
    req: req as NextRequest,
    secret: process.env.JWT_SECRET,
  });

  if (!jwt) {
    return new Response("Unauthorized", { status: 401 });
  }
  const token = jwt.accessToken;
  // console.log(44);
  // console.log(jwt);
  // // console.log(req.headers);

  // console.log(`POST request to ${endpoint} with body, url is ${url}`);
  // console.log(`token is ${JSON.stringify(token)}`)
  // console.log(body);

  const response = await axios
    .post(url, body, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
    .then((response) => {
      console.log(response.data);
      return new Response(JSON.stringify(response.data), {
        status: response.status,
      });
    })
    .catch((error) => {
      console.error(error);
      return new Response(error.message, { status: 500 });
    });

  return response;
}
