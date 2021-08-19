package api.recipebook.recipe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "api_key")
data class ApiKey (

    @Id
    val id : String
)