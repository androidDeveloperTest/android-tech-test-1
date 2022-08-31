package com.example.data.datasource.remote.model


data class HeroRemoteDo(
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val data: Data,
    val etag: String
)

data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Results>
)

data class Results(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<Urls>,
    val thumbnail: Thumbnail,
    val comics: Comics,
    val stories: Stories,
    val events: Events,
    val series: Series
)

data class Urls(
    val type: String,
    val url: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Comics(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Stories(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Series(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Events(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<Items>
)

data class Items(
    val resourceURI: String,
    val name: String
)
