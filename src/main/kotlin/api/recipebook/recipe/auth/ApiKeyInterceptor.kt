package api.recipebook.recipe.auth

import api.recipebook.recipe.error.UnauthorizedException
import api.recipebook.recipe.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor


@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")
        if (apiKey == null){
            throw UnauthorizedException()
        }

        if (!apiKeyRepository.existsById(apiKey)){
            throw UnauthorizedException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //nothing
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //nothing
    }
}