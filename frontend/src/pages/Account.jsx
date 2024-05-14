import React, { useEffect, useState } from "react";
import Navigation from "../components/Navigation";
import { Button, Col, Container, Row } from "react-bootstrap";
import { APIURL, adminToken } from "../requests/Globals";
import { useSearchParams } from "react-router-dom";
import axios from "axios";

export default function Account() {
  const [user, setUser] = useState({});
  const logOut = async () => {
    // localStorage'dan adminToken'i kaldır
    localStorage.removeItem(adminToken);
    window.location.href = "/";
  };

  useEffect(() => {
    try {
      const token = localStorage.getItem(adminToken);
      const data = axios.get(`${APIURL}/users/${token}`);
      const response = data.data;
      setUser(response);
    } catch (error) {
      console.log(error);
    }
  }, []);

  return (
    <div>
      <div>
        <Navigation />
      </div>
      <Container>
        <Row className="border border-1 rounded border-primary p-3">
          <Col></Col>
          <Col lg={2}>
            <Button variant="danger" onClick={logOut}>
              Log out
            </Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
