

const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const updatePass = async (req, res, next) => {
  try {
    const salt = await bcrypt.genSalt(10);
    // now we set user password to hashed password
    let pass = await bcrypt.hash(req.body.password, salt);
    console.log(pass);
    await conn.query(
      `UPDATE user SET password='${pass}' WHERE Email='${req.body.Email}'`,

      function (error, results, fields) {
        if (error) {
          console.log(error);
          res.json({
            status: false,
            message: "there are some error with query",
          });
        } else if (results.length != 0) {
          console.log("response here");
          res.json({
            status: true,
            message: "password updated Successfully",
          });
        } else {
          console.log("hwree");
          res.json({
            status: false,
            message: "No Such Email Id",
          });
        }
      }
    );
  } catch (err) {
    next(err);
  }
};
module.exports = updatePass;