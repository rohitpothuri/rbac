package com.rohitpothuri.rbac.country.service

import com.rohitpothuri.rbac.country.model
import com.rohitpothuri.rbac.country.model.Country
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

import java.util
import java.util.Locale

@Service
class CountryService {
  @Cacheable(Array("countries"))
  def getAllCountries: util.ArrayList[Country] = {
    val countries = new util.ArrayList[Country]()
    for (cc <- Locale.getISOCountries) {
      countries.add(new Country(Locale.of("", cc).getDisplayCountry, cc.toUpperCase))
    }
    countries
  }
}
