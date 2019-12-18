// extend react component for social sharing
@file:JsModule("react-share")
@file:JsNonModule

import react.RClass
import react.RProps
@JsName("EmailIcon")
external val EmailIcon: RClass<IconProps>

@JsName("EmailShareButton")
external val EmailShareButton: RClass<ShareProps>

@JsName("TelegramIcon")
external val TelegramIcon: RClass<IconProps>

@JsName("TelegramShareButton")
external val TelegramShareButton: RClass<ShareProps>


external interface IconProps : RProps {
    var size : Int
    var round: Boolean
}

external interface ShareProps: RProps {
    var url: String
}