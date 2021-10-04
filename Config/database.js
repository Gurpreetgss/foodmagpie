const mysql = require("mysql");

const connection = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "rootroot",
  database: "Food_magpie",
  multipleStatements: true,
});

connection.connect(function (err) {
  if (!err) {
    console.log("Database is connected");
  } else {
    throw err;
  }
});

module.exports = connection;
