package com.utkarsh.fxn.data.firebase
//for now used for both movie and tv show
class TvFirebase {
    var id: String? = null
    var imdb_rating: String? = null
    var name: String? = null
    var poster_path: String? = null
    var year: String? = null

    //primary constructor
    constructor() {}

    //secondary constructor
    constructor(
        id: String?,
        imdb_rating: String?,
        name: String?,
        poster_path: String?,
        year: String?
    ) {
        this.id = id
        this.imdb_rating = imdb_rating
        this.name = name
        this.poster_path = poster_path
        this.year = year
    }

}