package api.recipebook.recipe.controller

import api.recipebook.recipe.error.NotFoundException
import api.recipebook.recipe.error.UnauthorizedException
import api.recipebook.recipe.payload.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException


@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException): WebResponse<String>{

        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun validationHandler(notFoundException: NotFoundException): WebResponse<String>{

        return WebResponse(
            code = 404,
            status = "DATA NOT FOUND",
            data = "DATA NOT FOUND"
        )
    }

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun validationHandler(unauthorizedException: UnauthorizedException): WebResponse<String>{

        return WebResponse(
            code = 404,
            status = "UNATHORIZED",
            data = "API KEY NOT MATCH"
        )
    }
}