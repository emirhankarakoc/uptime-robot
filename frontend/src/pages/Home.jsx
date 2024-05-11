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
        className="d-flex   justify-content-center"
      >
        <Row className="d-flex  justify-content-center">
          <Col>
            <Row>
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Robot-clip-art-book-covers-feJCV3-clipart.png/202px-Robot-clip-art-book-covers-feJCV3-clipart.png"
                alt="Request Robot"
              />
            </Row>
            <Row>
              <h1>Welcome to Request Robot</h1>
            </Row>
          </Col>
          <Col className="d-flex align-items-center justify-content-center">
            <Button onClick={onClick}>Getting Started</Button>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
