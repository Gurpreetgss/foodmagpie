const { validationResult } = require("express-validator");
const bcrypt = require("bcryptjs");
const conn = require("../config/database");

const updateProfile = async (req, res, next) => {
    
  try {
    let sql=`UPDATE ${req.body.table}`;
    if(req.body.table==="hotel"){
        sql+=` SET Hotel_id='${req.body.Hotel_id}',Org_id='${req.body.Org_id}',Hotel_name='${req.body.Hotel_name}',Hotel_address='${req.body.Hotel_address}'
        ,Hotel_phn='${req.body.Hotel_phn}',Hotel_lat='${req.body.Hotel_lat}',Hotel_long='${req.body.Hotel_long}' where (Hotel_id='${req.body.Hotel_id}')`;
         
    }else if (req.body.table==="organisation"){ 
        sql+=` SET Org_id='${req.body.Org_id}',Org_name='${req.body.Org_name}',Org_address='${req.body.Org_address}
        ',Org_phn='${req.body.Org_phn}',Org_lat='${req.body.Org_lat}',Org_long='${req.body.Org_long}' WHERE (Org_id = '${req.body.Org_id}')`;

    }else if(req.body.table==="requester"){
        sql+=` SET Req_id='${req.body.Req_id}',Req_name='${req.body.Req_name}',Req_address='${req.body.Req_address}'
        ,Req_phn='${req.body.Req_phn}',Req_lat='${req.body.Req_lat}',Req_long='${req.body.Req_long}' where(Req_id='${req.body.Req_id}')`;
    }
      
    conn.query(
       sql,

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
            message: `${req.body.table} Profile Updated Successfully`,
          });
        }
      }
    );
  } catch (err) {
    next(err);
  }
};
module.exports = updateProfile;
