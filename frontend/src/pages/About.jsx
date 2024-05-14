import React from "react";
import Navigation from "../components/Navigation";

export default function About() {
  return (
    <div>
      <div>
        <Navigation />
      </div>
      <div className="container">
        <h2>Welcome Emirhan KARAKOC's Uptime Robot Project.!</h2>
        <p>
          This project was created in May 2024. Actually it works very simple.
          <br />
          All codes on github.com/emirhankarakoc/uptime-robot. PR's are welcome.
          <br />
          First: You must register the system. <br />
          Second: Create a project. <br />
          3- Create requests <br />
          4- Set endpoints for requests in backend.(Must be get (for now...)).
          <br />
          5- Add e-mail to get notification.
          <br />
          6- Enjoy. When your projects go down, we will notice you with e-mail.
          Any error, anything else than 2XX, we will notice and remove our
          request list. You can re-add request list from dashboard.
          <br />
        </p>
      </div>
    </div>
  );
}
