const pool = require('../database/conectarse')

module.exports = {
  getAll: async function () {
    const results = await pool.query('select * from certificados')
    return results.rows
  },

  create: async function (data) {
    const { nombre, descripcion, url, fecha_finalizacion, ruta_foto_certificado } = data

    const insertQuery = 'INSERT INTO certificados (nombre_certificado,descripcion,url_certificado,ruta_foto_certificado,fecha_fin_certificado) values ($1,$2,$3,$4,$5)'
    try {
      await pool.query(insertQuery, [nombre, descripcion, url, ruta_foto_certificado, fecha_finalizacion])
    } catch (error) {
      console.error('Error al insertar en la base de datos:', error)
    }
  },

  actualizar: async function (data) {
    const { id_cer, nombre, descripcion, fecha_finalizacion, url, ruta_Foto } = data

    const updateQuery = `
      UPDATE certificados 
      SET 
        nombre_certificado = $1,
        descripcion = $2,
        url_certificado = $3,
        ruta_foto_certificado = $4,
        fecha_fin_certificado = $5
      WHERE certificado_id = $6;
    `

    try {
      await pool.query(updateQuery, [nombre, descripcion, url, ruta_Foto, fecha_finalizacion, id_cer])
      console.log('Certificado actualizado')
    } catch (error) {
      console.error('Error al actualizar en la base de datos:', error)
    }
  },

  deleteById: async function (data) {
    const { id_cer } = data

    const deleteQuery = `delete from certificados where certificado_id = $1`

    try {
      await pool.query(deleteQuery, [id_cer])
      console.log('Certificado Eliminado')
    } catch (error) {
      console.error('Error al actualizar en la base de datos:', error)
    }
  },

  getByNombre: async function (nombrePagina) {
    const results = await pool.query('select * from certificados where nombre_certificado = $1', [nombrePagina])
    return results.rows
  },
}
