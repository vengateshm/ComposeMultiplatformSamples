package apps.whatsapp.repository

import apps.whatsapp.model.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ChatRepository {
    private val chats = listOf(
        Chat(
            userName = "Jane Cooper",
            userImgUrl = "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fHww",
            lastMessage = "Haha that's terrifying",
            dateTime = "07:38 pm",
            unreadCount = 0,
            isOnline = true
        ),
        Chat(
            userName = "Cody Fisher",
            userImgUrl = "https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fHww",
            lastMessage = "Haha oh man",
            dateTime = "05:14 pm",
            unreadCount = 3
        ),
        Chat(
            userName = "Floyd Miles",
            userImgUrl = "https://images.unsplash.com/photo-1485206412256-701ccc5b93ca?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTl8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHx8MA%3D%3D",
            lastMessage = "perfect!",
            dateTime = "11:49 pm",
            unreadCount = 10
        ),
        Chat(
            userName = "Marvin McKinney",
            userImgUrl = "https://images.unsplash.com/photo-1463453091185-61582044d556?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjN8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHx8MA%3D%3D",
            lastMessage = "omg, this is amazing!",
            dateTime = "07:40 pm",
            unreadCount = 1
        ),
        Chat(
            userName = "Courtney Henry",
            userImgUrl = "https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjh8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHx8MA%3D%3D",
            lastMessage = "aww",
            dateTime = "08:20 pm",
            unreadCount = 1
        ),
        Chat(
            userName = "Dianne Russell",
            userImgUrl = "https://images.unsplash.com/photo-1496360166961-10a51d5f367a?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjd8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHx8MA%3D%3D",
            lastMessage = "I'll be there in 2 mins",
            dateTime = "01:09 am",
            unreadCount = 1
        ),
        Chat(
            userName = "Darrel Steward",
            userImgUrl = "https://images.unsplash.com/photo-1501196354995-cbb51c65aaea?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mzh8fHJhbmRvbSUyMHBlb3BsZXxlbnwwfHwwfHx8MA%3D%3D",
            lastMessage = "Haha that's terrifying",
            dateTime = "01:55 pm",
            unreadCount = 1
        ),
    )

    fun getChats(): Flow<List<Chat>> {
        return flow { emit(chats) }
    }
}