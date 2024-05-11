import React, { useEffect, useState } from "react";
import Navigation from "../components/Navigation";
import { useParams } from "react-router-dom";
import axios from "axios";
import { APIURL } from "../requests/Globals";
import { Button, Row, Spinner } from "react-bootstrap";
import RequestCard from "../components/RequestCard";

export default function ProjectDashboard() {
  const { id } = useParams();

  const [project, setProject] = useState({});
  const [endpoints, setEndpoints] = useState([]);

  useEffect(() => {
    const fetchProjectById = async () => {
      try {
        const response = await axios.get(`${APIURL}/projects/${id}`);
        const data = response.data;
        setEndpoints(data.requests);
        setProject(data);
        console.log(endpoints);
      } catch (error) {
        console.log(error);
      }
    };
    fetchProjectById();
  }, []);

  if (endpoints.length === 0) {
    return (
      <div>
        <div>
          <Navigation />
        </div>
        <div className="container fluid d-flex justify-content-center align-content-center flex-column  ">
          <div className="d-flex justify-content-center align-content-center">
            No endpoints available
          </div>
          <div className="d-flex justify-content-center align-content-center">
            <Button href="/dashboard"> Click for back</Button>
          </div>
          <div className=" my-3 d-flex justify-content-center align-content-center">
            <Button href="/projects/create" className="bg-warning">
              or create a new one?
            </Button>
          </div>
        </div>
      </div>
    );
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
          <div>
            {endpoints.map((endpoint, index) => (
              <div key={index}>
                <RequestCard endpoint={endpoint} />
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
