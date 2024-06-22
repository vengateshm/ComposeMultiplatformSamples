package apps.whatsapp.model

data class Chat(
    val userName: String,
    val userImgUrl: String,
    val lastMessage: String,
    val dateTime: String,
    val unreadCount: Int,
    val isOnline: Boolean = false,
    val messages: List<Message> = listOf(
        Message("I'm good, thanks! How about you?", true, "Now"),
        Message("Hey, how are you doing?", false, "3 days ago"),
    )
)

fun Chat.toUnreadCountText() = if (unreadCount > 5) "5+" else "$unreadCount"
