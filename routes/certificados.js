var express = require("express");
var router = express.Router();
var controladorCertificado = require("../controllers/certificadoControllers");
const { uploadImage } = require("../multer_config");

/* GET users listing. */
router.get("/", controladorCertificado.getAll);
router.get("/:nombre", controladorCertificado.getByNombre);
router.put("/:id", uploadImage, controladorCertificado.actualizarById);
router.delete("/:id", controladorCertificado.deleteById);
router.post("/", uploadImage, controladorCertificado.create);

module.exports = router;
