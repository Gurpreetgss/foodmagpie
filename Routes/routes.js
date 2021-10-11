const { check } = require("express-validator");

const reg = require("../model/registerUser");
const login = require("../model/signIn");
const resetPassword = require("../model/resetPassword");
const regHotel = require("../Model/RegHotel");
const regOrg = require("../Model/RegOrg");
const regReq = require("../Model/regReq");
const update = require("../model/update");
// const insertProp = require("../model/InsertProperty");
const Properties = require("../Model/getOrg");
const getHotel = require("../Model/getHotel");
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

router.post("/regOrg", regOrg);
router.post("/regHotel", regHotel);
router.post("/regReq", regReq);
router.post("/update", update);
router.post("/reset", resetPassword);

router.get("/getOrg", Properties);
router.post("/getHotel", getHotel);

router.post("/delete", DeleteProperty);

module.exports = router;
