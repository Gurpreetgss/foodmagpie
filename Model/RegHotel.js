const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const regHotel= async (req, res, next) => {
  const error = validationResult(req);

  if (!error) {
    return res.json({ status: 412, error: error.array() });
  }
  try {
    console.log("test")
    //nodemon
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
              "INSERT INTO hotel(Hotel_id,Org_id,Hotel_name,Hotel_address,Hotel_phn,Hotel_lat,Hotel_long) VALUES (?,?,?,?,?,?,?)",
              [
                req.body.Hotel_id,
                req.body.Org_id,
                req.body.Hotel_name,
                req.body.Hotel_address,
                req.body.Hotel_phn,
                req.body.Hotel_lat,
                req.body.Hotel_long,
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
module.exports = regHotel;
