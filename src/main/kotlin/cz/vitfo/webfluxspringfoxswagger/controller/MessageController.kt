package cz.vitfo.webfluxspringfoxswagger.controller

import cz.vitfo.webfluxspringfoxswagger.contract.Message
import cz.vitfo.webfluxspringfoxswagger.contract.MessageV2
import cz.vitfo.webfluxspringfoxswagger.exception.AlreadyExistsException
import cz.vitfo.webfluxspringfoxswagger.exception.NotFoundException
import io.swagger.annotations.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@Api(value = "Message controller", description = "Wraps all the endpoint for messages")
class MessageController {

    companion object {
        val messages = mutableListOf(
                Message("m1", "Message 1"),
                Message("m2", "Message 2")
        )
    }

    @ApiOperation("Get all messages")
    @GetMapping("/messages")
    fun getAll(): Mono<ResponseEntity<List<Message>>> {
        return Mono.just(ResponseEntity.ok(messages.toList()))
    }

    @ApiOperation("Get message by identifier")
    @GetMapping("/message/{identifier}")
    fun getMessagePath(@PathVariable identifier: String): Mono<ResponseEntity<Message>> {
        return Mono.just(identifier)
                .map {
                    val message = messages.find { it.identifier == identifier }
                    message
                            ?.let { ResponseEntity.ok(message) }
                            ?: throw NotFoundException("Message not found")
                }
    }

    @GetMapping("/message")
    fun getMessageParam(@RequestParam identifier: String): Mono<ResponseEntity<Message>> {
        return Mono.just(identifier)
                .map {
                    val message = messages.find { it.identifier == identifier }
                    message
                            ?.let { ResponseEntity.ok(message) }
                            ?: throw NotFoundException("Message not found")
                }
    }

    @ApiOperation("Get message by its identifier", response = MessageV2::class)
    @GetMapping("/message_v2")
    fun getMessageParamDetailedInformation(
            @ApiParam(value = "Identifier of the message", name = "identifier", allowableValues = "m1 to m1000", example = "m100", allowEmptyValue = false)
            @RequestParam identifier: String): Mono<ResponseEntity<MessageV2>> {
        return Mono.just(identifier)
                .map {
                    val message = messages.find { it.identifier == identifier }
                    message
                            ?.let { ResponseEntity.ok(MessageV2.fromMessage(it)) }
                            ?: throw NotFoundException("Message not found")
                }
    }

    @ApiOperation("Save message", response = String::class)
    @PostMapping("/message")
    fun saveMessage(@RequestBody messageToSave: Message): Mono<ResponseEntity<String>> {
        return Mono.just(messageToSave)
                .map {
                    val message = messages.find { it.identifier == messageToSave.identifier }
                    message
                            ?.let {
                                throw AlreadyExistsException("message with identifier ${messageToSave.identifier}")
                            }
                            ?: kotlin.run {
                                messages.add(messageToSave)
                                ResponseEntity.ok("Ok")
                            }
                }
    }

    @ApiOperation("Update message")
    @PutMapping("/message")
    fun updateMessage(@RequestBody messageToUpdate: Message): Mono<ResponseEntity<String>> {
        return Mono.just(messageToUpdate)
                .map {
                    val message = messages.find { it.identifier == messageToUpdate.identifier }
                    message
                            ?.let {
                                message.text = messageToUpdate.text
                                ResponseEntity.ok("Ok")
                            }
                            ?: throw NotFoundException("Message not found")
                }
    }

    @ApiOperation("Delete message")
    @DeleteMapping("/message")
    fun deleteMessage(@RequestParam identifier: String): Mono<ResponseEntity<String>> {
        return Mono.just(identifier)
                .map {
                    val message = messages.find { it.identifier == identifier }
                    message
                            ?.let {
                                messages.remove(message)
                                ResponseEntity.ok("Ok")
                            }
                            ?: throw NotFoundException("Message not found")
                }
    }
}