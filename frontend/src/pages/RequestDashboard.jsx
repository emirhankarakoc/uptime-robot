import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navigation from "../components/Navigation";
import axios from "axios";
import { APIURL } from "../requests/Globals";
import { Spinner, Button, Row, Col } from "react-bootstrap";

export default function RequestDashboard(props) {
  const { projectId, requestId } = useParams();

  const [request, setRequest] = useState({});

  useEffect(() => {
    const fetchRequest = async () => {
      try {
        const data = await axios.get(`${APIURL}/requests/${requestId}`);
        const response = data.data;
        setRequest(response);
      } catch (error) {
        console.log(error);
      }
    };
    fetchRequest();
  }, []);

  const handleStatusChange = async () => {
    try {
      // Durum down ise, status'u live yap
      if (request.status) {
        await axios.put(`${APIURL}/requests/status/live/${requestId}`);
      } else {
        // Durum live ise, status'u down yap
        await axios.put(`${APIURL}/requests/${requestId}`, { status: true });
        //kapat metodu simdilik yok.
      }
      // Durumu yenile
      const updatedData = await axios.get(`${APIURL}/requests/${requestId}`);
      setRequest(updatedData.data);
    } catch (error) {
      console.log(error);
    }
  };

  if (!request) {
    return (
      <div>
        <div>
          <Navigation />
        </div>
        <div className="d-flex justify-content-center  align-content-center  my-5">
          <Spinner />
        </div>
      </div>
    );
  }

  return (
    <div>
      <div>
        <Navigation />
      </div>
      <div className="container fluid bg-warning border border-0 rounded flex-column p-3  ">
        <h5>Title: {request.title}</h5>
        <Row className="my-1">
          <Col>Url: {request.url}</Col>
          <Col>
            <Button>Change URL</Button>
          </Col>
        </Row>
        <Row className="my-1">
          <Col> HTTPMethod: {request.httpMethod}</Col>

          <Col>
            <Button> Change method</Button>
          </Col>
        </Row>

        <Row className="my-1">
          <Col>Status: {request.status ? "DOWN" : "LIVE"} </Col>
          <Col>
            <Button onClick={handleStatusChange}>
              {request.status ? "Re-Activate" : "Down"}
            </Button>
          </Col>
        </Row>

        <Row className="my-1">
          <Col>Body: {request.body}</Col>
          <Col>addbody</Col>
        </Row>
        {/* last attempts log */}
      </div>
      <div className="container bg-dark border border-0 rounded p-3 my-3">
        <h5 className="text-white"> loglar burada olacak.</h5>
      </div>
    </div>
  );
}
