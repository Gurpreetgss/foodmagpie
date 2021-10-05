const conn = require("../config/database");

const GetProperties = (req, res, next) => {
  let sql = `SELECT * FROM Hotel where Org_id=${req.body.Org_id}`;
  conn.query(sql, function (err, data, fields) {
    if (err) {
      res.json({
        status: false,
        data,
        err: err.sqlMessage,
      });
    } else {
      res.json({
        status: true,
        data,
        err: "",
      });
    }
  });
};

module.exports = GetProperties;
