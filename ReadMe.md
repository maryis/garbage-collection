# context
This is a simple spring application to simulate GC in java. 

# notes

- when using executor service pool, if one thread raise exception during start, the other ones do not start
- when using executor service, if there is any runtime error in threads, we won't see any thing, so we need to handle them via try-catch or ... (check sweepEden)
- ConcurrentModificationException : not related to thread programming, but removing from a collection in a loop on iterator, use iterator.remove instead
- We can use conditional breakpoints to debug threads, maybe by using thread name as condition, defining dependency, ...
- I didnt shutdown executor service hear, as spring application will call close or shutdown method on any bean in the context

# to run
```mvn spring-boot:run```