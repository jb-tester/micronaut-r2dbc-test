## Micronaut Data R2DBC test

problems:
- RxJava types should not be used in reactor/reactivestreams repositories
- attempts to get the Single results from Mono or Flowable repository methods fail (like Single-type controller methods that call the rector-typed repository methods)