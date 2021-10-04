const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const updatePass = async (req, res, next) => {
  try {
    const salt = await bcrypt.genSalt(10);
    // now we set user password to hashed password
    let pass = await bcrypt.hash(req.body.password, salt);
    await conn.query(
      `UPDATE user SET password='${pass}' WHERE email='${req.body.email}'`,

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
          res.json({
            status: true,
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
