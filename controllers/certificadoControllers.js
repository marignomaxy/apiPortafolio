var modeloCertificados = require('../models/certificadosModels')
const jwt = require('jsonwebtoken')

module.exports = {
  getByNombre: async function (req, res, next) {
    try {
      var nombreCerti = req.params.nombre
      let pagina = await modeloCertificados.getByNombre(nombreCerti)
      var url = pagina.ruta_foto
      pagina.ruta_foto = `http://localhost:3000/${url}`
      res.status(200).json(pagina)
    } catch (error) {
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },

  actualizarById: async function (req, res, next) {
    const token = req.header('Authorization')
    if (!token) {
      return res.status(401).json({ error: 'Acceso no autorizado. Token no proporcionado.' })
    }
    try {
      const secretKey = process.env.SECRET_KEY
      const decoded = jwt.verify(token, secretKey)
      if (!decoded.usuario) {
        return res.status(401).json({ error: 'Token no válido. Acceso no autorizado.' })
      } else {
        var id_cer = req.params.id
        if (req.file) {
          const imagen = req.file
          const rutaRelativa = 'uploads/' + imagen.filename
          console.log('ruta', rutaRelativa)
        }

        const { nombre, descripcion, fecha_finalizacion, url } = req.body

        await modeloCertificados.actualizar({
          id_cer,
          nombre,
          descripcion,
          fecha_finalizacion,
          url,
          ruta_Foto: rutaRelativa,
        })
        res.status(200).json('Pagina Actualizada')
      }
    } catch (error) {
      console.error('Error: ', error)
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },

  deleteById: async function (req, res, next) {
    const token = req.header('Authorization')
    if (!token) {
      return res.status(401).json({ error: 'Acceso no autorizado. Token no proporcionado.' })
    }
    try {
      const secretKey = process.env.SECRET_KEY
      const decoded = jwt.verify(token, secretKey)
      if (!decoded.usuario) {
        return res.status(401).json({ error: 'Token no válido. Acceso no autorizado.' })
      } else {
        var id_cer = req.params.id
        await modeloCertificados.deleteById({
          id_cer,
        })
        res.status(200).json('Certificado Eliminada')
      }
    } catch (error) {
      console.error('Error: ', error)
      res.status(400).json({ error: 'Error al intentar eliminar' })
    }
  },

  getAll: async function (req, res, next) {
    try {
      let certificados = await modeloCertificados.getAll()
      certificados = certificados.map((certificado) => {
        var url = certificado.ruta_foto_certificado
        certificado.ruta_foto = `https://apiportafolio.onrender.com/${url}`
        return certificado
      })
      res.status(200).json(certificados)
    } catch (error) {
      console.error('Error al obtener certificados:', error)
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },

  create: async function (req, res, next) {
    const token = req.header('Authorization')
    if (!token) {
      return res.status(401).json({ error: 'Acceso no autorizado. Token no proporcionado.' })
    }
    try {
      const secretKey = process.env.SECRET_KEY
      const decoded = jwt.verify(token, secretKey)
      if (!decoded.usuario) {
        return res.status(401).json({ error: 'Token no válido. Acceso no autorizado.' })
      } else {
        // Obtén la imagen cargada por multer desde req.file
        const imagen = req.file
        const rutaRelativa = 'uploads/' + imagen.filename
        console.log('ruta relativa', rutaRelativa)

        const { nombre, descripcion, fecha_finalizacion, url } = req.body

        await modeloCertificados.create({
          nombre,
          descripcion,
          fecha_finalizacion,
          url,
          ruta_foto_certificado: rutaRelativa,
        })
        res.status(201).json('Usuario Creado')
      }
    } catch (error) {
      console.error('Error crear certificado', error)
      res.status(500).json({ error: 'Error interno del servidor' })
    }
  },
}
