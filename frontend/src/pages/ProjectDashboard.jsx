import React, { useEffect, useState } from "react";
import Navigation from "../components/Navigation";
import { useParams } from "react-router-dom";
import axios from "axios";
import { APIURL } from "../requests/Globals";
import { Row, Spinner } from "react-bootstrap";

export default function ProjectDashboard() {
  const { id } = useParams();

  const [project, setProject] = useState({});
  useEffect(() => {
    const fetchProjectById = async () => {
      try {
        const response = await axios.get(`${APIURL}/projects/${id}`);
        const data = response.data;
        setProject(data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProjectById();
  }, [id]); // endpoints bağımlılığını kaldır

  if (project.requests === null) {
    return <div>No endpoints available</div>; // endpoints null veya boşsa, mesajı göster
  }

  if (!project) {
    return <Spinner />;
  }
  return (
    <div>
      <Navigation />
      <div className="container fluid bg-primary border border-0 rounded p-3 flex-column">
        <h3>Project Dashboard</h3>
        <p>Mail address for crashes: {project.userMail}</p>
        <div>
          <h4>Endpoints for request</h4>
          {project.requests.id}
        </div>
      </div>
    </div>
  );
}
