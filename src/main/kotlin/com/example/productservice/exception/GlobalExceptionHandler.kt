package com.example.productservice.exception

import com.example.productservice.domain.dto.response.BaseResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(
        exception: MethodArgumentNotValidException
    ): ResponseEntity<BaseResponseDto<Any?>>{
        val errors = mutableListOf<String?>()
        exception.bindingResult.fieldErrors.forEach {
            errors.add(it.defaultMessage)
        }
        return ResponseEntity(
            BaseResponseDto(
                data = errors
            ),
            HttpStatus.BAD_REQUEST
        )
    }
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(
        exception: CustomException
    ): ResponseEntity<BaseResponseDto<Any?>>{
        return ResponseEntity(
            BaseResponseDto(
                message = exception.exceptionMessage,
                status = "T",
                error = exception.data
            ),
            HttpStatusCode.valueOf(exception.statusCode)
        )
    }
}