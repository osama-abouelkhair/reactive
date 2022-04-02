package com.baeldung.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @RestController
    class FooController {
        @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/sse/foo")
        public Flux<Foo> get() {
            return Flux.fromStream(Stream.generate(() -> new Foo("foo" + Instant.now())))
                    .delayElements(Duration.ofSeconds(1));
        }
    }

    class Foo {
        public Foo(String foo) {
            this.foo = foo;
        }

        private String foo;

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }
    }
}
