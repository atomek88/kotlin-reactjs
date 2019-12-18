// module to extend react-player component from React Libraries - see interfaces of components from readme to see what
// needs to be implemented to be typesafe
@file:JsModule("react-player")

import react.*

@JsName("default")
external  val ReactPlayer: RClass<ReactPlayerProps>

external interface ReactPlayerProps : RProps {
    var url: String
}