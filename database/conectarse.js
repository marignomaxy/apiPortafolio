const { Pool } = require("pg");


const dbConfig = {
  user: process.env.USER_DB,
  host: process.env.HOST_DB, 
  database: process.env.NOMBRE_DB,
  password: process.env.PASS_DB,
  port: 5432,
  ssl: {
    rejectUnauthorized: false, 
  },
  connect_timeout: 10,
};

const pool = new Pool(dbConfig);

module.exports = pool;
