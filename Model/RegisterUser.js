const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const register = async (req, res, next) => {
  const error = validationResult(req);

  if (!error) {
    return res.json({ status: 412, error: error.array() });
  }
  try {
    const salt = await bcrypt.genSalt(10);

    let pass = await bcrypt.hash(req.body.password, salt);
    conn.query(
      "SELECT * from user WHERE Email=?",
      [req.body.email],
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
            message: "Email id already exists",
          });
        } else {
          conn.query(
            "INSERT INTO user (Name,Email,Phone_num,password,User_type) VALUES (?,?,?,?,?)",
            [
              req.body.Name,
              req.body.Email,
              req.body.Phone_num,
              pass,
              req.body.User_type,
            ],
            function (error, results, fields) {
              if (error) {
                console.log(error);
                res.json({
                  status: false,
                  message: "there are some error with query",
                });
              } else {
                console.log("response here");
                res.json({
                  status: true,
                  message: "success",
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
module.exports = register;
