package com.dev.ccodetest.models.login

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Parent Data class for sample response.
 */
data class AllPeople(

    @JsonProperty("count")
    val count : Int,

    @JsonProperty("next")
    val next : String,

    @JsonProperty("previous")
    val previous : String,

    @JsonProperty("results")
    val results : List<AllPeopleResult>

)