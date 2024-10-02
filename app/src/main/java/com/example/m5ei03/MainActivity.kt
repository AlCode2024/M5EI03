// Clase Usuario con un constructor primario
class Usuario(
    val nombre: String,
    val edad: Int?,
    val trabajo: String?,
    val referencia: String? // Puede ser nulo, y será solo el nombre de la referencia
) {
    // Función de clase para mostrar los datos del usuario
    fun mostrarDatos() {
        println("Nombre: $nombre")
        println("Edad: ${edad ?: "Sin informacion"}") // Si la edad es nula, se muestra "No especificada" (?: operador elvis)
        println("Trabajo: ${trabajo ?: "Sin Información"}") // Si trabajo es nulo, se muestra "No especificado"
        if (referencia != null) {
            println("Fue citado por: $referencia")
        } else {
            println("Fue citado por: Llego solo")
        }
    }
}

fun main() {
    // Mensaje de bienvenida
    println("Bienvenido al sistema de registro de usuarios.")

    val listaUsuarios = mutableListOf<Usuario>()

    // Se crea funcion con nombre obligatorio para que funcione la app
    fun crearUsuario(): Usuario? {
        println("Ingrese el nombre del usuario (Obligatorio):")
        val nombre = readLine()?.takeIf { it.isNotBlank() } ?: run {
            println("No ingresó el nombre, no se ha creado ningún usuario.")
            return null
        }

        println("Ingrese la edad del usuario (o presione Enter si no desea ingresar la edad):")
        val edad = readLine()?.toIntOrNull() // Si no se ingresa un valor válido queda null

        println("Ingrese el trabajo del usuario (o presione Enter si no tiene trabajo):")
        val trabajo = readLine().takeIf { it?.isNotBlank() == true }

        println("¿Quién lo referencia? (Deje en blanco si no tiene referencia):")
        val referencia = readLine()?.takeIf { it.isNotBlank() }

        return Usuario(nombre, edad, trabajo, referencia)
    }

    // Pregunta final
    fun deseaAgregarOtroUsuario(): String? {
        while (true) {
            println("¿Desea agregar otro usuario? (1: Sí, 2: No):")
            when (readLine()) {
                "1" -> return "1"
                "2" -> return null
                else -> println("Opción no válida. Por favor, ingrese 1 para Sí o 2 para No.")
            }
        }
    }

    // Ingresar el primer usuario
    var usuario = crearUsuario()
    // si dice no, muestra la lista creada
    if (usuario != null) {
        listaUsuarios.add(usuario)
    }

    // Ciclo para ingresar usuarios
    while (deseaAgregarOtroUsuario() != null) {
        usuario = crearUsuario()
        if (usuario != null) {
            listaUsuarios.add(usuario)
        }
    }

    // Muestra los datos de todos los usuarios registrados
    if (listaUsuarios.isNotEmpty()) {
        println("----- Lista de Usuarios Registrados -----")
        for ((index, usuario) in listaUsuarios.withIndex()) {
            println("----- Usuario ${index + 1} -----")
            usuario.mostrarDatos()
        }
    } else {
        println("No se ha registrado ningún usuario.")
    }

    // Mensaje de despedida
    println("Gracias por su preferencia.")
}
