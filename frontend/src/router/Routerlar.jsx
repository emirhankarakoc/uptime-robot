import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import About from "../pages/About";
import Register from "../pages/Register";
import Dashboard from "../pages/Dashboard";
import ProjectDashboard from "../pages/ProjectDashboard";
import RequestDashboard from "../pages/RequestDashboard";
import Login from "../pages/Login";
import Account from "../pages/Account";

export default function Routerlar() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/account" element={<Account />} />

      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/projects/:id" element={<ProjectDashboard />} />
      <Route
        path="/projects/:projectId/requests/:requestId"
        element={<RequestDashboard />}
      />

      <Route path="/*" element={<Home />} />
    </Routes>
  );
}
