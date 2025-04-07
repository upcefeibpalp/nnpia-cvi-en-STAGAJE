import { createRootRoute, createRoute, createRouter } from "@tanstack/react-router";
import App from "./App.tsx"
import UsersTablePage from "./pages/UsersTablePage.tsx";
import NewUserPage from "./pages/NewUserPage.tsx";
import Home from "./pages/Home.tsx";

const rootRoute = createRootRoute({component: App});

const homeRoute = createRoute({
    getParentRoute: () => rootRoute,
    path: "/",
    component: Home
})

const usersTableRoute = createRoute({
    getParentRoute: () => rootRoute,
    path: "/users",
    component: UsersTablePage
});

const newUserRoute = createRoute({
    getParentRoute: () => rootRoute,
    path: "/register-user",
    component: NewUserPage
})

const routeTree = rootRoute.addChildren([homeRoute, usersTableRoute, newUserRoute])

export const router = createRouter({routeTree})