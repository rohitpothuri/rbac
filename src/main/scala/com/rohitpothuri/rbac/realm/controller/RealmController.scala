package com.rohitpothuri.rbac.realm.controller

import com.rohitpothuri.rbac.common.model.ServiceException
import com.rohitpothuri.rbac.realm.model.Realm
import com.rohitpothuri.rbac.realm.service.RealmService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.*

import java.util.Optional
@RestController
@RequestMapping(path = Array("/realms"))
class RealmController(@Autowired realmService: RealmService) {
  @Operation(summary = "Get all realms", description = "Returns list of realms")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The realms were not found")))
  @GetMapping(path = Array("/"))
  def getAllRealms(pageable: Pageable): ResponseEntity[Page[Realm]] =
    ResponseEntity.ok(realmService.findAll(pageable))


  @Operation(summary = "Get Realm by ID", description = "Retrieve a realm based on its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved realm"),
    new ApiResponse(responseCode = "404", description = "Realm not found")))
  @GetMapping(path = Array("/{id}"))
  def getRealmById(@PathVariable("id") id: Long): ResponseEntity[Realm] = {
    val realmOptional: Optional[Realm] = realmService.findById(id)
    if (realmOptional.isPresent) {
      ResponseEntity.ok(realmOptional.get())
    } else {
      ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
  }

  @Operation(summary = "Save realms", description = "Returns saved realms")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved realms"),
    new ApiResponse(responseCode = "404", description = "realms not found"),
    new ApiResponse(responseCode = "500", description = "Unable to save realms")))
  @PostMapping(path = Array("/"))
  def createRealm(@Valid @RequestBody realm: Realm): ResponseEntity[Realm] = {
    ResponseEntity.ok(realmService.save(realm))
  }

  @Operation(summary = "Save List of Realms", description = "Save a list of realms.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully saved realms"),
    new ApiResponse(responseCode = "500", description = "Error saving realms")))
  @PostMapping(path = Array("/save-list"), consumes = Array("application/json"))
  def saveRealms(@RequestBody realms: java.util.List[Realm]): ResponseEntity[java.util.List[Realm]] = {
    new ResponseEntity[java.util.List[Realm]](realmService.saveAll(realms), HttpStatus.OK)
  }
  @Operation(summary = "Delete all realms", description = "Delete all realms")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully deleted all realms"),
    new ApiResponse(responseCode = "404", description = "The realm was not found")))
  @DeleteMapping("/deleteAll")
  def deleteAllRealms(): ResponseEntity[String] = {
    try {
      realmService.deleteAll()
      ResponseEntity.ok("All departments deleted successfully")
    } catch {
      case e: ServiceException =>
        // Log the error for debugging
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting realms: " + e.getMessage)
    }
  }

  @Operation(summary = "Delete Realm by ID", description = "Deletes a realm based on its ID.")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Realm deleted successfully"),
    new ApiResponse(responseCode = "404", description = "Realm not found"),
    new ApiResponse(responseCode = "500", description = "Error deleting realm")))
  @DeleteMapping(path = Array("/{id}"))
  def deleteRealm(@PathVariable("id") id: Long): ResponseEntity[String] = {
    try {
      realmService.deleteById(id)
      ResponseEntity.ok(s"Realm $id deleted successfully")
    } catch {
      case e: ServiceException =>
        // Log the error for debugging
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(s"Error deleting realm: ${e.getMessage}")
    }
  }

}
