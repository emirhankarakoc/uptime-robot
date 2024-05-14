import React, { useEffect, useState } from "react";
import Navigation from "../components/Navigation";
import axios from "axios";
import { APIURL, adminToken } from "../requests/Globals";
import { Button, Col, Row } from "react-bootstrap";
import ProjectCard from "../components/ProjectCard";

export default function Dashboard() {
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const token = localStorage.getItem(adminToken);
        const response = await axios.get(`${APIURL}/projects/all/${token}`);
        setProjects(response.data); // response.data olarak ayarlanmalı
      } catch (error) {
        console.log(error);
      }
    };

    fetchProjects();
  }, []);

  console.log(projects); // burada console.log kullanılmalı, çünkü setProjects asenkron bir işlem ve hemen güncellenmeyebilir

  if (projects.length === 0) {
    return (
      <div>
        <Navigation />
        <div className="container border border-0 rounded-3 fluid bg-warning d-flex justify-content-start flex-column p-3">
          <h3>Create a new one</h3>
        </div>
      </div>
    );
  }

  return (
    <div>
      <Navigation />
      <div className="container border border-0 rounded-3 fluid bg-primary d-flex justify-content-start flex-column p-3">
        <h3>Select Project</h3>
        <div>
          {projects.map((project, index) => (
            <Row>
              <Button href={`/projects/${project.id}`}>
                <ProjectCard key={index} project={project} />
              </Button>
            </Row>
          ))}
        </div>
      </div>
    </div>
  );
}
