package api.recipebook.recipe.utility

import api.recipebook.recipe.entity.DatabaseSeq
import api.recipebook.recipe.error.NotFoundException
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component

@Component
class GeneralUtil(val mongoOperations: MongoOperations) {

    fun generateRecipeId(seqName : String) : String{
        val counter = mongoOperations.findAndModify(
            query(where("_id").`is`(seqName)),
            Update().inc("seq", 1), options().returnNew(true).upsert(true),
            DatabaseSeq::class.java
        ) ?: throw NotFoundException()

        return "R" + String.format("%04d", counter.seq)
    }
}