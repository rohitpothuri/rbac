package com.rohitpothuri.rbac.corporation.controller

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.corporation.model.Corporation
import com.rohitpothuri.rbac.corporation.service.CorporationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.*

import java.util.Optional

@RestController
@RequestMapping(path = Array("/corporations"))
@Tag(name = "Corporations", description = "Endpoints for managing corporations")
class CorporationController(@Autowired val corporationService: CorporationService) {

  @GetMapping
  @Operation(summary = "Get All Corporations", description = "Get a list of all corporations.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved corporations",
    content = Array(new Content(mediaType = "application/json",
      schema = new Schema(implementation = classOf[Page[Corporation]]))))
  def getAllCorporations(pageable: Pageable): ResponseEntity[Page[Corporation]] = {
    val corporationsPage: Page[Corporation] = corporationService.getAllCorporations(pageable)
    ResponseEntity.ok(corporationsPage)
  }

  @GetMapping(path = Array("/{id}"))
  @Operation(summary = "Get Corporation by ID", description = "Get a corporation by its ID.")
  @ApiResponses(value = Array(
  new ApiResponse(responseCode = "200", description = "Successfully retrieved corporation",
    content = Array(new Content(mediaType = "application/json",
      schema = new Schema(implementation = classOf[Corporation])))),
  new ApiResponse(responseCode = "404", description = "Corporation not found")
  ))
  def getCorporationById(@PathVariable("id") id: Long): ResponseEntity[Corporation] = {
    val corporation: Optional[Corporation] = corporationService.getCorporationById(id)
    if (corporation.isPresent) {
      ResponseEntity.ok(corporation.get())
    } else {
      ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
  }

  @DeleteMapping(path=Array("/{id}"))
  @Operation(summary = "Delete Corporation by ID", description = "Delete a corporation by its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Corporation deleted successfully"),
    new ApiResponse(responseCode = "404", description = "Corporation not found"),
    new ApiResponse(responseCode = "500", description = "Error deleting corporation")))
  def deleteCorporation(@PathVariable("id") id: Long): ResponseEntity[String] = {
    corporationService.deleteCorporation(id)
    ResponseEntity.ok(s"Corporation $id deleted successfully")
  }

  @DeleteMapping
  @Operation(summary = "Delete All Corporations", description = "Delete all corporations.")
  @ApiResponses(value = Array(
  new ApiResponse(responseCode = "200", description = "All corporations deleted successfully"),
  new ApiResponse(responseCode = "500", description = "Error deleting all corporations")
  ))
  def deleteAllCorporations(): ResponseEntity[String] = {
    try {
      corporationService.deleteAll()
      ResponseEntity.ok("All corporations deleted successfully")
    } catch {
      case e: ServiceException =>
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s"Error deleting all corporations: ${e.getMessage}")
    }
  }

  @PostMapping
  @Operation(summary = "Save Corporations", description = "Save a list of corporations.")
  @ApiResponses(value = Array(
  new ApiResponse(responseCode = "200", description = "Successfully saved corporations",
    content = Array(new Content(mediaType = "application/json",
      schema = new Schema(implementation = classOf[java.util.List[Corporation]])))),
  new ApiResponse(responseCode = "500", description = "Error saving corporations")
  ))
  def saveCorporations(@RequestBody corporations: java.util.List[Corporation]): ResponseEntity[java.util.List[Corporation]] = {
    new ResponseEntity[java.util.List[Corporation]](corporationService.saveAll(corporations), HttpStatus.OK)
  }
}
