package com.test.kumparan.api

import com.google.gson.*
import java.lang.reflect.Type

class BooleanTypeAdapter: JsonSerializer<Boolean>, JsonDeserializer<Boolean> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Boolean? {
        return try {
            json.asInt == 1
        } catch (e: NumberFormatException) {
            json.asBoolean
        }

    }

    override fun serialize(
        src: Boolean?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src!!)
    }
}