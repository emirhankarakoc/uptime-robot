import React, { useState } from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import axios from "axios";
import { APIURL, adminToken } from "../requests/Globals";
import Navigation from "../components/Navigation";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${APIURL}/accounts/login`, {
        username,
        password,
      });
      const token = response.data;
      localStorage.setItem(adminToken, token);

      setErrorMessage("Redirecting");

      setTimeout(() => {
        if (token) {
          window.location.href = "/dashboard";
        }
      }, 1000);
    } catch (error) {
      setErrorMessage("Error");
    }
  };

  return (
    <div>
      <Navigation />
      <Container>
        <Row className="justify-content-md-center mt-5">
          <Col md={6}>
            <h2 className="text-center mb-4">Login</h2>
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="formBasicUsername">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="emirhangaming2020"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </Form.Group>

              <Form.Group controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  placeholder="emirhangaming2020's password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
              </Form.Group>

              {errorMessage && (
                <Form.Text className="text-danger mx-5 my-2  ">
                  {errorMessage}
                </Form.Text>
              )}
              <div className="d-flex justify-content-center align-content-center">
                <Button variant="primary" type="submit" className="mt-3 mx-5 ">
                  Login
                </Button>
              </div>
              <div className="d-flex justify-content-center  align-content-center ">
                <p>Use simple username and passwords. This is side project.</p>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
