package api.recipebook.recipe.repository

import api.recipebook.recipe.entity.ApiKey
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ApiKeyRepository : MongoRepository<ApiKey, String> {
}