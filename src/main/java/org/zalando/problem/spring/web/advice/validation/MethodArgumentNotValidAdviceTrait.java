package org.zalando.problem.spring.web.advice.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;

/**
 * @see MethodArgumentNotValidException
 * @see Violation
 * @see ConstraintViolationProblem
 * @see ConstraintViolationProblem#TYPE_VALUE
 * @see BaseValidationAdviceTrait#defaultConstraintViolationStatus()
 */
public interface MethodArgumentNotValidAdviceTrait extends BaseBindingResultAdviceTrait {

    @ExceptionHandler
    default ResponseEntity<Problem> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final NativeWebRequest request) {
        return newConstraintViolationProblem(exception, createViolations(exception.getBindingResult()), request);
    }

}
