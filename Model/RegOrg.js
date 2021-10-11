const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const regOrg = async (req, res, next) => {
  const error = validationResult(req);

  if (!error) {
    return res.json({ status: 412, error: error.array() });
  }
  try {
    conn.query(
      "SELECT * from organisation WHERE Org_name=?",
      [req.body.Org_name],
      function (error, results, fields) {
        if (error) {
          console.log(error);
          res.json({
            status: false,
            message: "there are some error with query",
          });
        } else if (results.length != 0) {
          console.log("here");
          res.json({
            status: false,
            message: "Organization Name already exists",

            value: true,
          });
        } else {
          console.log("[[[[[[[[[[[[[[[[[[[[[[[[[[");
          console.log(req.body.Org_lat);
          console.log(req.body.Org_long);

          console.log(req.body.Org_long);

          console.log(req.body.Org_long);

          console.log(req.body.Org_long);

          console.log(req.body.Org_long);

          console.log(req.body.Org_long);

          conn.query(
            "INSERT INTO organisation(Org_id,Org_name,Org_address,org_description,Org_phn,Org_lat,Org_long) VALUES (?,?,?,?,?,?,?)",
            [
              req.body.Org_id,
              req.body.Org_name,
              req.body.Org_address,
              req.body.org_description,
              req.body.Org_phn,
              req.body.Org_lat,
              req.body.Org_long,
            ],
            function (error, results_org, fields) {
              if (error) {
                console.log(error);
                res.json({
                  status: false,
                  message: "there are some error with query",
                  value: false,
                });
              } else {
                console.log(results_org);
                res.json({
                  status: true,
                  message: "success",
                  value: true,
                });
              }
            }
          );
        }
      }
    );
  } catch (err) {
    next(err);
  }
};
module.exports = regOrg;
