import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import kotlin.browser.window

interface VideoListProps: RProps {
    var videos: List<Video>
    var selectedVideo: Video?
    var onSelectVideo: (Video) -> Unit
}

class VideoListComponent(props: VideoListProps) : RComponent<VideoListProps, RState>(props) {
    override fun RBuilder.render() {
      /*  for (video in props.videos){
            p {
                // this key variable is trigger for react to update this component if changed
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        window.alert("Clicked $video!")
                    }
                }
                +"${video.speaker}: ${video.title}"
            }
        }*/
        props.videos.map{ video ->
            p {
                // this key variable is trigger for react to update this component if changed
                key = video.id.toString()
                attrs {
                    onClickFunction = {
                        // state should be lifted out of component, only modify state with setState
                        props.onSelectVideo(video)
                    }
                }
                if(video == props.selectedVideo) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}
// lambda with receiver for component for video component
fun RBuilder.videoList(handler: VideoListProps.() -> Unit) : ReactElement {
    return child(VideoListComponent::class){
        this.attrs(handler)
    }
}