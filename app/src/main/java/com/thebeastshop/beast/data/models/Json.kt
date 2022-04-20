package com.thebeastshop.beast.data.models

import kotlinx.serialization.json.Json

val DEFAULT_JSON_FORMAT = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

val JSON = DEFAULT_JSON_FORMAT