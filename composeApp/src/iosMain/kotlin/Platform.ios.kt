actual fun getPlatform(): Platform {
    return IOSPlatform()
}

class IOSPlatform : Platform {
    override val name: String
        get() = "iOS"
}