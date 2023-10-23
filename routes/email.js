const express = require("express");
const nodemailer = require("nodemailer");
const router = express.Router();

router.post("/", async (req, res) => {
  const { nombre, apellido, telefono, email, mensaje } = req.body;

  const transporter = nodemailer.createTransport({
    service: "Gmail",
    auth: {
      user: process.env.USER_EMAIL,
      pass: process.env.PASS_EMAIL,
    },
    tls: {
      rejectUnauthorized: false, // Desactiva la verificación del certificado
    },
  });

  const mailOptions = {
    from: email, // Utiliza la dirección de correo de la persona que envía el formulario
    to: "marignomaxy@gmail.com",
    subject: "Mensaje Portafolio",
    text: `
      Nombre: ${nombre} ${apellido}
      Teléfono: ${telefono}
      Mensaje: ${mensaje}
      Mail de la persona que envia: ${email}
      `,
  };

  try {
    await transporter.sendMail(mailOptions);
    res.status(200).json({ mensaje: "Correo enviado correctamente" });
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "Error al enviar el correo electrónico" });
  }
});

module.exports = router;
