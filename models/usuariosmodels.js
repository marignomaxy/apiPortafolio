const bcrypt = require("bcrypt");
const pool = require("../database/conectarse");

const encriptarContrasena = async (contrasena) => {
  const saltRounds = 10;

  try {
    const hash = await bcrypt.hash(contrasena, saltRounds);
    return hash;
  } catch (error) {
    throw error;
  }
};

module.exports = {
  async getByEmail(emailEnviado) {
    try {
      const results = await pool.query(
        "SELECT * FROM USUARIOS WHERE mail = $1",
        [emailEnviado]
      );
      return results.rows[0];
    } catch (error) {
      console.error("Error al realizar la consulta:", error);
      return [];
    }
  },
  async create(data) {
    const { mail, contraseña } = data;
    const insertQuery = "INSERT INTO USUARIOS(mail,contraseña) values($1, $2)";
    try {
      const contraseñaEncriptada = await encriptarContrasena(contraseña);
      console.log(contraseñaEncriptada);
      await pool.query(insertQuery, [mail, contraseñaEncriptada]);
    } catch (error) {
      console.error("Error al insertar en la base de datos:", error);
    }
  },
};
