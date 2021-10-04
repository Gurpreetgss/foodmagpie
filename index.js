"use strict";
const express = require("express");
const bodyparser = require("body-parser");
const cors = require("cors");
const route = require("./routes/routes");

const app = express();

// PORT
const PORT = process.env.PORT || 4000;
app.use(cors());
// Middleware
app.use(
  bodyparser.urlencoded({
    limit: "50mb",
    extended: true,
    parameterLimit: 1000000,
  })
);

app.get("/", (req, res) => {
  res.json({ message: "API Working" });
});
app.use("/api", route);

app.listen(PORT, (req, res) => {
  console.log(`Server Started at PORT ${PORT}`);
});
