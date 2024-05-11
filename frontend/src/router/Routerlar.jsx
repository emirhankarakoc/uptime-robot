import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import About from "../pages/About";
import Register from "../pages/Register";
import Dashboard from "../pages/Dashboard";
import ProjectDashboard from "../pages/ProjectDashboard";

export default function Routerlar() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />

      <Route path="/register" element={<Register />} />
      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/projects/:id" element={<ProjectDashboard />} />

      <Route path="/*" element={<Home />} />
    </Routes>
  );
}
