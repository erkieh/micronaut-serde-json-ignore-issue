package com.example

import com.fasterxml.jackson.annotation.JsonIgnore
import io.kotest.core.spec.style.StringSpec
import io.micronaut.serde.ObjectMapper
import io.micronaut.serde.annotation.Serdeable
import io.micronaut.test.extensions.kotest5.annotation.MicronautTest

@MicronautTest
class SerdeableWithIgnoredNonSerdeableFieldTest(private val objectMapper: ObjectMapper) : StringSpec({

    "test serialiaztion and deserializaion with ignore field" {
        val original = SerdeableWithIgnoredNonSerdeable(NonSerdeable("value"))

        val serialized = objectMapper.writeValueAsString(original)
        val deserialized = objectMapper.readValue(serialized, SerdeableWithIgnoredNonSerdeable::class.java)

        assert(deserialized == original.copy(ignoredNonSerdeable = null))
    }
})

@Serdeable
data class SerdeableWithIgnoredNonSerdeable(@field:JsonIgnore val ignoredNonSerdeable: NonSerdeable? = null)

data class NonSerdeable(val value: String)
