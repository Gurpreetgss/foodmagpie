const { check } = require("express-validator");

const reg = require("../model/registerUser");
const login = require("../model/signIn");
const resetPassword = require("../model/resetPassword");
const regHotel=require("../Model/RegHotel");
const regOrg=require("../Model/RegOrg");
const regReq=require("../Model/regReq");
const update = require("../model/update");
// const insertProp = require("../model/InsertProperty");
// const Properties = require("../model/GetProperties");
// const UpdateProp = require("../model/UpdateProperty");
const DeleteProperty = require("../model/Delete");
// const getRenterProperty = require("../model/GetRProperty");
// const getEmaiId = require("../model/GetEmaiId");
// const getFilterProperty = require("../model/filter");

// const addToFav = require("../model/addFav");
// const viewFav = require("../model/viewFav");

const router = require("express").Router();


router.post(
  "/Signup",
  [
    check("email", "Please enter a valid email")
      .not()
      .isEmpty()
      .escape()
      .isEmail(),
    check("password", "Please enter a valid password")
      .notEmpty()
      .trim()
      .isLength({
        min: 6,
      }),
  ],
  reg
);

router.post(
  "/login",
  [
    check("email", "Please enter a valid email").notEmpty().escape().isEmail(),
    check("password", "Please enter a valid password")
      .notEmpty()
      .trim()
      .isLength({
        min: 6,
      }),
  ],
  login
);

router.post("/regOrg",regOrg);
router.post("/regHotel",regHotel);
router.post("/regReq",regReq);
router.post("/update", update);
router.post("/reset", resetPassword);
// router.post("/insertprop", insertProp);

// router.get("/getprop", Properties);
// router.post("/updateprop", UpdateProp);
router.post("/delete", DeleteProperty);
// router.post("/getrenterprop", getRenterProperty);
// router.post("/addtofav", addToFav);
// router.post("/viewfav", viewFav);
// router.post("/filter", getFilterProperty);
// router.post("/email", getEmaiId);

module.exports = router;
