package cz.vitfo.webfluxspringfoxswagger

import cz.vitfo.webfluxspringfoxswagger.exception.AlreadyExistsException
import cz.vitfo.webfluxspringfoxswagger.exception.NotFoundException
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@ControllerAdvice
class GlobalErrorHandler {

    private val logger = LogManager.getLogger(GlobalErrorHandler::class.java)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun handleException(e: NotFoundException): String {
        logger.error(e.localizedMessage, e)
        return e.localizedMessage
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [AlreadyExistsException::class])
    fun handleException(e: AlreadyExistsException): String {
        logger.error(e.localizedMessage, e)
        return e.localizedMessage
    }
}