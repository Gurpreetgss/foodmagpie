const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const regOrg = async (req, res, next) => {
  const error = validationResult(req);

  if (!error) {
    return res.json({ status: 412, error: error.array() });
  }
  try {
    console.log("test")
//     conn.query(
//       "SELECT * from user WHERE email=?",
//       [req.body.email],
//       function (error, results, fields) {
//         if (error) {
//           console.log(error);
//           res.json({
//             status: false,
//             message: "there are some error with query",
//           });
//         } else if (results.length != 0) {
//           res.json({
//             status: false,
//             message: "Email id already exists",
//           });
//         } else {
//           let uname = req.body.email;
//           console.log(uname);
//           if (uname.trim() !== "" && uname !== undefined) {
            conn.query(
              "INSERT INTO organisation(Org_id,Org_name,Org_address,Org_phn,Org_lat,Org_long) VALUES (?,?,?,?,?,?)",
              [
                req.body.Org_id,
                req.body.Org_name,
                req.body.Org_address,
                req.body.Org_phn,
                req.body.Org_lat,
                req.body.Org_long,
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
        //   } else {
        //     return res.json({ status: false, message: error.array() });
        //   }
        // }
    //   }
    // );
  } catch (err) {
    next(err);
  }
};
module.exports = regOrg;
