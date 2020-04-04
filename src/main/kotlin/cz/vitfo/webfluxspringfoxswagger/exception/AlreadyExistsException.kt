package cz.vitfo.webfluxspringfoxswagger.exception

class AlreadyExistsException(what: String) : RuntimeException("Already exists: $what")