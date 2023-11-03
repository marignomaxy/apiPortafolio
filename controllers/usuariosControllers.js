var modeloUsuario = require('../models/usuariosmodels')
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')

async function compararContrasena(contrasenaIngresada, contrasenaEnBaseDeDatos) {
  try {
    return await bcrypt.compare(contrasenaIngresada, contrasenaEnBaseDeDatos)
  } catch (error) {
    console.error('Error al comparar contraseñas:', error)
    return false 
  }
}

module.exports = {
  getByEmail: async function (req, res, next) {
    const { mail, contraseña } = req.body
    try {
      let usuario = await modeloUsuario.getByEmail(mail)

      if (await compararContrasena(contraseña, usuario.contraseña)) {
        const payload = {
          usuario: usuario.mail, 
        }
        const secretKey = process.env.SECRET_KEY
        const options = {
          expiresIn: '1h', 
        }
        const token = jwt.sign(payload, secretKey, options)
        res.status(200).json({ usuario, token })
      } else {
        res.status(401).json({ error: 'Contraseña incorrecta' })
      }
    } catch (error) {
      console.error('Error al obtener usuarios por email:', error)
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },

  create: async function (req, res, next) {
    console.log(req.body)
    try {
      await modeloUsuario.create(req.body)
      res.status(201).json('Usuario Creado')
    } catch (error) {
      console.error('Error al obtener usuarios por email:', error)
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },
}
