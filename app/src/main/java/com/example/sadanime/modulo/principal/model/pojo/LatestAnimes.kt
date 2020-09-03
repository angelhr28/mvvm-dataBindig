package com.example.sadanime.modulo.principal.model.pojo

import com.google.gson.annotations.SerializedName

data class LatestAnimes(

	@field:SerializedName("animes")
	val animes: List<AnimesItem?>? = null
)

data class EpisodeListItem(

	@field:SerializedName("episode")
	val episode: Int? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class AnimesItem(

	@field:SerializedName("episodeList")
	val episodeList: List<EpisodeListItem?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("poster")
	val poster: String? = null,

	@field:SerializedName("episodes")
	val episodes: String? = null
)
