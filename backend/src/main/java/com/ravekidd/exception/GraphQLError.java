package com.ravekidd.exception;

import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Controller;

/**
 * GraphQL error handler that resolves exceptions thrown during data fetching.
 */
@Controller
public class GraphQLError extends DataFetcherExceptionResolverAdapter {

    /**
     * Resolves a single GraphQL error based on the given exception and data fetching environment.
     *
     * @param ex  The exception that occurred during data fetching.
     * @param env The data fetching environment.
     * @return The resolved GraphQL error.
     */
    @Override
    protected graphql.GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        ErrorType type;

        if (ex instanceof DataIntegrityViolationException) {
            type = ErrorType.BAD_REQUEST;
        } else {
            type = ErrorType.INTERNAL_ERROR;
        }

        return GraphqlErrorBuilder.newError(env)
                .message("Received message: " + ex.getMessage())
                .errorType(type)
                .build();
    }
}
