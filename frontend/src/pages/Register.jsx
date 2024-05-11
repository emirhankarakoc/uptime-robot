import React, { useEffect } from "react";
import Navigation from "../components/Navigation";
import { Button } from "react-bootstrap";
import { adminToken } from "../requests/Globals";

export default function Register() {
  useEffect(() => {}, []);
  const loadToken = () => {
    localStorage.setItem("robottoken", adminToken);
  };

  return (
    <div>
      <div>
        <Navigation />

        <div className="d-flex justify-content-center  align-items-center ">
          <Button onClick={loadToken}> Yalandan token yukle.</Button>
        </div>
      </div>
    </div>
  );
}
