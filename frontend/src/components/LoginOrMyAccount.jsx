import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import { APIURL, adminToken } from "../requests/Globals";
import axios from "axios";

export default function LoginOrMyAccount() {
  const [tokenVerification, setTokenVerification] = useState(false);

  useEffect(() => {
    const verifyToken = async () => {
      try {
        const token = localStorage.getItem(adminToken);

        if (token) {
          const response = await axios.post(
            `${APIURL}/accounts/verification/token`,
            {
              token,
            }
          );
          setTokenVerification(true);
          console.log("token ekleme islemi basarili.");
        }
        localStorage.setItem("emirhankarakocuptimerobot", token);
      } catch (error) {
        console.error("Error verifying token:", error);
      }
    };

    verifyToken();
  }, []);

  if (tokenVerification == true) {
    return (
      <div>
        <Container>
          <Row>
            <Col lg={12}>
              <a href="/account" className="text-decoration-none">
                <h2>My Account</h2>
              </a>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
  if (tokenVerification == false) {
    return (
      <div>
        <Container>
          <Row className="my-2">
            <Col>
              <a href="/login" className="text-decoration-none">
                Login
              </a>
            </Col>
            <Col>
              <a href="/register" className="text-decoration-none">
                Register
              </a>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
