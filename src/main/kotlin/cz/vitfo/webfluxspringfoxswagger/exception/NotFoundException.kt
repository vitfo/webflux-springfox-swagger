package cz.vitfo.webfluxspringfoxswagger.exception

import java.lang.RuntimeException

class NotFoundException(what: String) : RuntimeException("Not found: $what")