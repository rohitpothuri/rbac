package com.rohitpothuri.rbac.realm.service

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RealmInterceptor extends HandlerInterceptor{
  
  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Object): Boolean = {
    handler match {
      case method: HandlerMethod if method.getMethodAnnotation(classOf[IncludeRealmInterceptor]) != null =>
        val realm: String = request.getParameter("realm")
        if (realm == null || realm.isEmpty){
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Realm parameter is missing")
          return false
        }
        request.setAttribute("realm", realm)
        true
      case _ => return true
    }
  }
}
