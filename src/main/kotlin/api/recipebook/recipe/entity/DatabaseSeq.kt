package api.recipebook.recipe.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences")
class DatabaseSeq (
    @Id
    val id: String,
    val seq: Long
)


