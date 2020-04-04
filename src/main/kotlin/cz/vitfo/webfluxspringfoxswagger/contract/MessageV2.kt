package cz.vitfo.webfluxspringfoxswagger.contract

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Message v2", description = "Message object version 2")
class MessageV2(
        @ApiModelProperty(notes = "Identifier of the message", allowEmptyValue = false, required = true)
        val identifier: String,
        @ApiModelProperty(notes = "Text of the message", allowEmptyValue = true, required = true)
        var text: String) {

    companion object {
        fun fromMessage(message: Message): MessageV2 {
            return MessageV2(
                    message.identifier,
                    message.text
            )
        }
    }
}

