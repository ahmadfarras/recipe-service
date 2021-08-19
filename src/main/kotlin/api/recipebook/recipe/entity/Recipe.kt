package api.recipebook.recipe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "recipe")
class Recipe(

    @Id
    val id: String,
    var name: String,
    var ingredient: String,
    var step: String,
    var createdDate: Date,
    var lastUpdated: Date?
)