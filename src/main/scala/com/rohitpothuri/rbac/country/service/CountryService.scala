package com.rohitpothuri.rbac.country.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

import java.util.Locale

@Service
class CountryService {
  @Cacheable(Array("countries"))
  def getAllCountries: Array[String] = {
    Locale.getISOCountries.map(cc => new Locale("", cc).getDisplayCountry).sorted
  }
}
