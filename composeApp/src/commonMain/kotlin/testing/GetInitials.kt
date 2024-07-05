package testing

fun getInitials(fullName: String):String {
    val names = fullName.split(" ").filter { it.isNotBlank() }

    return when {
        names.size == 1 && names.first().length <= 1 -> {
            names.first().first().toString().uppercase()
        }

        names.size == 1 && names.first().length > 1 -> {
            val name = names.first().uppercase()
            "${name.first()}${name[1].uppercase()}"
        }
        else -> {
            "${names.first().first().uppercase()}${names.last().first().uppercase()}"
        }
    }
}