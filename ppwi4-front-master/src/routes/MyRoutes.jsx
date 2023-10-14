import React from "react";
import { Outlet } from "react-router-dom";
import Layout from "../components/Layout/Layout";

const MyRoutes = () => {
    return (
        <Layout>
            <Outlet/>
        </Layout>
    )
}

export default MyRoutes;