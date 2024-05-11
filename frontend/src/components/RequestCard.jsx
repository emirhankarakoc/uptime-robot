import React, { useState } from "react";
import { Button } from "react-bootstrap";

export default function RequestCard(props) {
  const [endpoint, setEndpoint] = useState(props.endpoint);

  return (
    <div className="container border  border-warning rounded p-3">
      <div className="row">
        <Button
          href={`/projects/${endpoint.projectId}/requests/${endpoint.id}`}
        >
          <h3>
            Title: {endpoint.title}, URL: {endpoint.url},Method:
            {endpoint.httpMethod}
          </h3>
        </Button>
      </div>
    </div>
  );
}
