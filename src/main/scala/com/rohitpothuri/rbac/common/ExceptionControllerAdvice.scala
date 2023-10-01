package com.rohitpothuri.rbac.common

import com.rohitpothuri.rbac.department.model.Department
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.MethodArgumentNotValidException
import jakarta.validation.UnexpectedTypeException
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.validation.FieldError
import org.springframework.web.method.HandlerMethod

import scala.collection.mutable
import scala.jdk.CollectionConverters.*

@ControllerAdvice
class ExceptionControllerAdvice {

  val logger: Logger = LoggerFactory.getLogger(classOf[ExceptionControllerAdvice])

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[MethodArgumentNotValidException]))
  def handleValidationExceptions(ex: MethodArgumentNotValidException, handlerMethod: HandlerMethod): ResponseEntity[String] = {
    val errors: mutable.Map[String, String] = mutable.Map()
    val errorMessages = ex.getBindingResult.getAllErrors.asScala.map { error =>
      val fieldName = error.asInstanceOf[FieldError].getField
      val errorMessage = error.getDefaultMessage
      s"$fieldName: $errorMessage"
    }.mkString("\n")
    val message = s"Method Argument Not Valid Exception:\n${errorMessages}"
    logger.error(s"$message \n ${ex.getStackTrace.mkString("\n")}", ex)
    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[UnexpectedTypeException]))
  def handleUnexpectedTypeValidationException(ex: UnexpectedTypeException, handlerMethod: HandlerMethod): ResponseEntity[String] = {
    val message = s"Unexpected type validation Exception:\n${ex.getMessage}"
    logger.error(s"$message \n ${ex.getStackTrace.mkString("\n")}", ex)
    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[EmptyResultDataAccessException]))
  def handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException, handlerMethod: HandlerMethod): ResponseEntity[String] = {
    val message = s"Empty Result Data Access Exception:\n${ex.getMessage}"
    logger.error(s"$message \n ${ex.getStackTrace.mkString("\n")}", ex)
    ResponseEntity.status(HttpStatus.NOT_FOUND).body(message)
  }

}
