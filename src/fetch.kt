import kotlinx.coroutines.*
import kotlin.browser.window

// coroutines to requests to external api
suspend fun fetchVideo(id: Int): Video {
    val responsePromise = window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
    val response = responsePromise.await()
    val jsonPromise = response.json()
    val json = jsonPromise.await()
    return json.unsafeCast<Video>()
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async(Dispatchers.Default) {
            fetchVideo(id)
        }
    }.awaitAll()
}