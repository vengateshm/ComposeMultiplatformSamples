package samples.di.koin

class MyRepositoryImpl(
    private val dbClient: DBClient
) : MyRepository {
    override fun getMessage(): String {
        return "Hello Koin!"
    }
}