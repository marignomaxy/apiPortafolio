var express = require('express');
var router = express.Router();
var usuarioController = require('../controllers/usuariosControllers');

/* GET users listing. */
router.post('/', usuarioController.getByEmail);
router.post('/crear', usuarioController.create);

module.exports = router;
