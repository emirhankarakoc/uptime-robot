import React, { useEffect } from "react";
import { Col, Row } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import LoginOrMyAccount from "./LoginOrMyAccount";
import { adminToken } from "../requests/Globals";

export default function Navigation() {
  useEffect(() => {
    // Temizleme fonksiyonunu tanımla
    const cleanup = () => {
      localStorage.removeItem(adminToken); // localStorage'dan ilgili anahtarı sil
    };

    // Component dismount olduğunda cleanup fonksiyonunu çağır
    return () => {
      cleanup();
    };
  }, []);

  return (
    <div>
      <Container className="p-3 my-3 flex">
        <Row>
          <Col xl={8}>
            <Row>
              <Col>
                <a href="/" className=" text-decoration-none ">
                  <h2>Homepage</h2>
                </a>
              </Col>
              <Col>
                <a href="/about" className="text-decoration-none ">
                  <h2>About</h2>
                </a>
              </Col>
            </Row>
          </Col>
          <Col xl={4}>
            <LoginOrMyAccount />
          </Col>
        </Row>
      </Container>
    </div>
  );
}
