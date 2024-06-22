actual fun getPlatform(): Platform {
    return AndroidPlatform()
}

class AndroidPlatform : Platform {
    override val name: String
        get() = "Android"
}