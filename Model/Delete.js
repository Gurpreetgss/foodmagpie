const conn = require("../config/database");

const DeleteProperty = (req, res, next) => {
  let sql =
    `DELETE FROM user WHERE User_id=` + req.body.User_id;
  conn.query(sql, function (err, data, fields) {
    if (err) {
      res.json({
        status: false,
        message: "there is some error in query",
        err: err.sqlMessage,
      });
    } else {
      res.json({
        status: true,
        message: "Deleted Successfully",
        err: "",
      });
    }
  });
};

module.exports = DeleteProperty;
