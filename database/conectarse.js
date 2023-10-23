const { Pool } = require("pg");

// Configura la conexión a la base de datos PostgreSQL (base de datos por defecto)
const dbConfig = {
  user: process.env.USER_DB,
  host: process.env.HOST_DB, // Puedes cambiar esto según la ubicación de tu base de datos
  database: process.env.NOMBRE_DB, // Nombre de la base de datos
  password: process.env.PASS_DB,
  port: 5432, // Puerto predeterminado de PostgreSQL
  ssl: {
    rejectUnauthorized: false, // Ajusta esto según tus necesidades de seguridad
  },
  connect_timeout: 10,
};

const pool = new Pool(dbConfig);

module.exports = pool;
