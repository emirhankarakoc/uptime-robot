import React, { useEffect } from "react";
import Navigation from "../components/Navigation";
import { Button, Col, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function Home() {
  useEffect(() => {
    const onClick = () => {
      let token = localStorage.getItem("robottoken");
      if (token == null) {
        window.location.href = "/register";
      } else {
        window.location.href = "/dashboard";
      }
      console.log(token);
    };
  }, []);
  const onClick = () => {
    let token = localStorage.getItem("robottoken");
    if (token == null) {
      window.location.href = "/register";
    } else {
      window.location.href = "/dashboard";
    }
    console.log(token);
  };
  return (
    <div>
      <Navigation />
      <Container
        style={{ height: "100vh" }}
        fluid
        className="d-flex align-items-center justify-content-center"
      >
        <Row className="d-flex align-items-center justify-content-center">
          <Col>
            <Row>resim koyulacak. bir ara...</Row>
            <Row>
              <h1>Welcome to Request Robot</h1>
            </Row>
          </Col>
          <Col className="d-flex align-items-center justify-content-center">
            <Button onClick={onClick}>Explore</Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
