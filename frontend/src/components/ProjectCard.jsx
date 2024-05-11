import React, { useState } from "react";
import { Col, Container, Row } from "react-bootstrap";

export default function ProjectCard(props) {
  const [project, setProject] = useState(props.project);

  return (
    <div className="border my-2 p-2 border-2 border-black  rounded-3">
      <Container>
        <Row>
          <Col xl={10}>{project.id}</Col>
          <Col>
            <Row>
              <Col>guncelle</Col>
              <Col>sil</Col>
            </Row>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
