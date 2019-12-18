// file for web app, create class to hold attribu8tes of video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.dom.*
import react.*
import kotlinx.css.*
import styled.*

// add interface to control state in app container
interface AppState: RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

class App: RComponent<RProps, AppState>() {
    // need to override this init without App component to init with some vals
    override fun AppState.init() {
        unwatchedVideos = listOf(
                Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
                Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
                Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
        )
        watchedVideos = listOf(
                Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
        )
        // expand to pull videos from external api
        val mainScope = MainScope()
        mainScope.launch(Dispatchers.Default) {
            val videos = fetchVideos()
            setState {
                unwatchedVideos = videos
            }
        }
    }
    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
        }
        styledDiv {
            css {
                fontFamily = "sans-serif"
                color = Color.darkRed
            }
            h3 {
                +"Videos to watch"
            }
            videoList {
                videos = state.unwatchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState{
                        currentVideo = video
                    }
                }
            }
        }
        // why this styling is not working???
        styledDiv{
            css {
                fontFamily = "papyrus"
                color = Color.darkSlateBlue
            }
            h3 {
                +"Videos watched"
            }
            videoList {
                videos = state.watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
        }
        // video player component here, state changes what video si selected
        // this state. hack for null safety usage if a currentvideo has been selected then it is called
        state.currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
                unwatchedVideo = currentVideo in state.unwatchedVideos
                onVideoButtonPressed = {
                    if (video in state.unwatchedVideos) {
                        setState {
                            watchedVideos += video
                            unwatchedVideos -= video
                        }
                    }
                    else {
                        setState {
                            watchedVideos -= video
                            unwatchedVideos += video
                        }
                    }
                }
            }
        }
    }
}
data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)




