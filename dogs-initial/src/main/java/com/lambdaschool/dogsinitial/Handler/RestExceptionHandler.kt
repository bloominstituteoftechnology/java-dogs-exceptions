package com.lambdaschool.dogsinitial.Handler

import com.lambdaschool.dogsinitial.Exception.ResourceNotFoundException
import com.lambdaschool.dogsinitial.Model.ErrorDetail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.Timestamp
import java.util.*
import javax.servlet.http.HttpServletRequest


@ControllerAdvice
class RestExceptionHandler: ResponseEntityExceptionHandler(){

    @Autowired
    private val messageSource: MessageSource? = null


    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(rnfe: ResourceNotFoundException, request: HttpServletRequest): ResponseEntity<*> {
        val errorDetail = ErrorDetail("Resource not Found",
                HttpStatus.NOT_FOUND.value(),
                rnfe.message.toString(),
                Timestamp(System.currentTimeMillis()),
                rnfe.javaClass.name)


        return ResponseEntity<Any>(errorDetail, null, HttpStatus.NOT_FOUND)
    }



}