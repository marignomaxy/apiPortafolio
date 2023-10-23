var modeloPagina = require("../models/paginasModels");

module.exports = {
  getByNombre: async function (req, res, next) {
    try {
      var nombrePagina = req.params.nombre;
      let pagina = await modeloPagina.getByNombre(nombrePagina);
      var url = pagina.ruta_foto;
      pagina.ruta_foto = `http://localhost:3000/${url}`;
      res.status(200).json(pagina);
    } catch (error) {
      res.status(500).json({ error: "Error interno del servidor" });
    }
  },

  deleteById: async function (req, res, next) {
    try {
      var id_pag = req.params.id;

      await modeloPagina.deleteById({
        id_pag,
      });
      res.status(200).json("Pagina Eliminada");
    } catch (error) {
      console.error("Error: ", error);
      res.status(400).json({ error: "Error al intentar eliminar" });
    }
  },

  actualizarById: async function (req, res, next) {
    try {
      var id_pag = req.params.id;

      if (req.file) {
        const imagen = req.file;
        rutaRelativa = "uploads/" + imagen.filename;
        console.log("ruta", rutaRelativa);
      }

      const { nombre, descripcion, tecnologia, destacado, fecha, url } =
        req.body;

      // Recupera el valor actual de la ruta desde la base de datos
      const paginaExistente = await modeloPagina.getById(id_pag);
      console.log("pagina", paginaExistente);
      const rutaExistente = paginaExistente[0].ruta_Foto;

      // Actualiza la base de datos solo si se proporciona una nueva imagen
      const rutaFinal = rutaExistente ? rutaRelativa : rutaExistente;

      await modeloPagina.actualizar({
        id_pag,
        nombre,
        descripcion,
        tecnologia,
        destacado,
        fecha,
        ruta_Foto: rutaFinal,
        url,
      });
      res.status(200).json("Página Actualizada");
    } catch (error) {
      console.error("Error: ", error);
      res.status(500).json({ error: "Error interno del servidor" });
    }
  },

  getAll: async function (req, res, next) {
    try {
      let paginas = await modeloPagina.getAll();
      paginas = paginas.map((pagina) => {
        var url = pagina.ruta_foto;
        pagina.ruta_foto = `https://apiportafolio.onrender.com/${url}`;
        return pagina;
      });
      res.status(200).json(paginas);
    } catch (error) {
      res.status(500).json({ error: "Error interno del servidor", error });
    }
  },

  create: async function (req, res, next) {
    try {
      const imagen = req.file;
      const rutaRelativa = "uploads/" + imagen.filename;

      const { nombre, descripcion, tecnologia, destacado, fecha, url } =
        req.body;

      await modeloPagina.create({
        nombre,
        descripcion,
        tecnologia,
        destacado,
        fecha,
        ruta_Foto: rutaRelativa,
        url,
      });

      res.status(201).json("Página Creada");
    } catch (error) {
      console.error("Error al insertar en la base de datos:", error);
      res.status(500).json({ error: "Error interno del servidor" });
    }
  },
};
