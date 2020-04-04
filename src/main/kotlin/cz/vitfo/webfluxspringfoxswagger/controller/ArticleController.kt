package cz.vitfo.webfluxspringfoxswagger.controller

import cz.vitfo.webfluxspringfoxswagger.contract.Article
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ArticleController {

    companion object {
        val articles = mutableListOf(
                Article("a1", "Article 1 title", "Article 1 text"),
                Article("a2", "Article 2 title", "Article 2 text")
        )
    }

    @ApiOperation("Get all articles")
    @GetMapping("/articles")
    fun getAllArticles(): Mono<ResponseEntity<List<Article>>> {
        return Mono.just(ResponseEntity.ok(articles.toList()))
    }
}