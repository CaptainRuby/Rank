package com.ruby.rank.model

data class SearchResult(val results: Int, val list: List<Anime>) {

    data class Anime(val name_cn: String, val images: Images?) {

        data class Images(val large: String)

    }
}