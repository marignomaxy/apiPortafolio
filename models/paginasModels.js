const pool = require("../database/conectarse");

module.exports = {
  getByNombre: async function (nombrePagina) {
    const results = await pool.query(
      "select * from paginas where nombre_pagina = $1",
      [nombrePagina]
    );
    return results.rows;
  },

  getById: async function (id) {
    const results = await pool.query(
      "select * from paginas where pagina_id = $1",
      [id]
    );
    return results.rows;
  },

  deleteById: async function (data) {
    const { id_pag } = data;

    const deleteQuery = `DELETE FROM paginas WHERE pagina_id = $1`;

    try {
      await pool.query(deleteQuery, [id_pag]);
      console.log("Página Eliminada");
    } catch (error) {
      console.error("Error al eliminar en la base de datos:", error);
    }
  },

  getAll: async function () {
    const results = await pool.query("select * from paginas");
    return results.rows;
  },

  actualizar: async function (data) {
    const {
      id_pag,
      nombre,
      descripcion,
      tecnologia,
      destacado,
      fecha,
      ruta_Foto,
      url,
    } = data;

    const updateQuery = `
      UPDATE paginas 
      SET 
        nombre_pagina = $1,
        descripcion = $2,
        tecnologia = $3,
        destacado = $4,
        ruta_foto = $5,
        fecha_fin = $6,
        url_pagina = $7
      WHERE pagina_id = $8;
    `;

    try {
      await pool.query(updateQuery, [
        nombre,
        descripcion,
        tecnologia,
        destacado,
        ruta_Foto,
        fecha,
        url,
        id_pag,
      ]);
    } catch (error) {
      console.error("Error al actualizar en la base de datos:", error);
    }
  },

  create: async function (data) {
    const {
      nombre,
      descripcion,
      tecnologia,
      destacado,
      fecha,
      ruta_Foto,
      url,
    } = data;

    const insertQuery =
      "insert into paginas (nombre_pagina,descripcion,tecnologia,destacado,ruta_foto,fecha_fin,url_pagina) values ($1,$2,$3,$4,$5,$6,$7)";
    try {
      await pool.query(insertQuery, [
        nombre,
        descripcion,
        tecnologia,
        destacado,
        ruta_Foto,
        fecha,
        url,
      ]);
    } catch (error) {
      console.error("Error al insertar en la base de datos:", error);
    }
  },
};
