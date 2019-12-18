// another component to abstract out separately into VideoPlayer component
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*

interface VideoPlayerProps: RProps {
    var video: Video
    var onVideoButtonPressed: (Video) -> Unit
    var unwatchedVideo: Boolean
}

class VideoPlayerComponent(props: VideoPlayerProps) : RComponent<VideoPlayerProps, RState>(props) {
    override fun RBuilder.render() {
        // social hare buttons/components add here
        styledDiv {
            css {
                position = Position.absolute
                top = 50.px
                right = 100.px
                color = Color.green
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            styledButton {
                css {
                    // this will change button display whther it has been pressed
                    display = Display.block
                    backgroundColor = if(props.unwatchedVideo) Color.lawnGreen else Color.red
                }
                attrs {
                    onClickFunction = {
                        props.onVideoButtonPressed(props.video)
                    }
                }
                if (props.unwatchedVideo) {
                    +"Mark as watched"
                }
                else {
                    +"Mark as unwatched"
                }
            }
            styledDiv{
                css {
                    display = Display.flex
                    marginBottom = 10.px
                }
                EmailShareButton {
                    attrs.url = props.video.videoUrl
                    EmailIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
                TelegramShareButton {
                    attrs.url = props.video.videoUrl
                    TelegramIcon {
                        attrs.size = 32
                        attrs.round = true
                    }
                }
            }
            ReactPlayer {
                attrs.url = props.video.videoUrl
            }
        }

    }
}

// lambda with receiver for component for video component
fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit) : ReactElement {
    return child(VideoPlayerComponent::class){
        this.attrs(handler)
    }
}