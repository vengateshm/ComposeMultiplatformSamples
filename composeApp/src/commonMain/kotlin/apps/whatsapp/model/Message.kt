package apps.whatsapp.model

data class Message(
    val message: String,
    val isCurrentUser: Boolean,
    val dateTime: String
)
