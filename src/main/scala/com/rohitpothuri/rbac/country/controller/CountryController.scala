package com.rohitpothuri.rbac.country.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.{ApiResponse, ApiResponses}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}
import com.rohitpothuri.rbac.country.service.CountryService
import io.swagger.v3.oas.annotations.tags.Tag

import java.util

@RestController
@RequestMapping(path = Array("/countries"))
@Tag(name = "Countries", description = "Endpoints for getting ISO Countries")
class CountryController(@Autowired countryService: CountryService){
  @Operation(summary = "Get all countries", description = "Returns list of ISO Countries")
  @ApiResponses(value = Array(
    new ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    new ApiResponse(responseCode = "404", description = "The countries were not found")))
  @GetMapping
  def getAllCountries: ResponseEntity[Array[String]] =
    ResponseEntity.ok(countryService.getAllCountries)

}
