package wm.functions

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

fun sendEmail(comment: String, email: String) {
    /**
     * Para proteger tu cuenta, a partir del 30 de mayo del 2022, Google dejará de admitir aplicaciones
     * y dispositivos de terceros que te pidan que inicies sesión en tu cuenta de Google usando solo tu
     * nombre de usuario y contraseña.
     */

    SimpleEmail().apply {
        hostName = "smtp.googlemail.com"
        setSmtpPort(465)
        setAuthenticator(DefaultAuthenticator("migueelamaya@gmail.com", ""))
        isSSLOnConnect = true
        setFrom("migueelamaya@gmail.com")
        subject = "Comentario web FiestasPatronales"
        setMsg(comment)
        addTo(email)
        send()
    }
}