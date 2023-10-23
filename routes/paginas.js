const express = require("express");
const router = express.Router();
const { uploadImage } = require("../multer_config");

// Importa el controlador de páginas
const controladorPagina = require("../controllers/paginasControllers");

router.get("/", controladorPagina.getAll);
router.get("/:nombre", controladorPagina.getByNombre);
router.put("/:id", uploadImage, controladorPagina.actualizarById);
router.delete("/:id", controladorPagina.deleteById);
router.post("/", uploadImage, controladorPagina.create);

module.exports = router;
