package com.example.listings;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import graphql.ExecutionInput;
import graphql.execution.Execution;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Component
public class CachingPreparsedDocumentProvider implements PreparsedDocumentProvider {
    private final AsyncCache<String, PreparsedDocumentEntry> cache = Caffeine
            .newBuilder()
            .maximumSize(2500)
            .expireAfterAccess(Duration.ofHours(1))
            .buildAsync();

    // Why do I still have to override this method? https://github.com/graphql-java/graphql-java/commit/0b957bbd6a4b2a8e893154b2c277f92a593565dc shows it removed?
    @Override
    public PreparsedDocumentEntry getDocument(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
        return null;
    }

    @Override
    public CompletableFuture<PreparsedDocumentEntry> getDocumentAsync(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
//        return cache.get(executionInput.getQuery(), operationString -> parseAndValidateFunction.apply(executionInput));
        return cache.get(executionInput.getQuery(), operationString -> callIfCacheMiss(executionInput, parseAndValidateFunction));
    }

    public PreparsedDocumentEntry callIfCacheMiss(ExecutionInput executionInput, Function<ExecutionInput, PreparsedDocumentEntry> parseAndValidateFunction) {
        System.out.println("Pre-parsed operation wasn't found in cache: " + executionInput);
        return parseAndValidateFunction.apply(executionInput);

    }
}
