
package com.example.pokedex.data.repository

// class PokemonJSON(val application: Application) : PokemonRepository {
// override fun getPokemonByID(name: String): Pokemon {
// val context = application.applicationContext
// val jsonString = context.assets.open("$name.json")
// .bufferedReader()
// .use { it.readText() }
// val jsonObject = JSONObject(jsonString)
//
//
// val id = jsonObject.getString("id").padStart(3, '0')
// val name = jsonObject.getString("name").replaceFirstChar { it.uppercaseChar() }
// var weight = jsonObject.getString("weight")
// if (weight.length == 1) {
// weight = weight.substring(0, weight.length - 1) + "0." + weight.last() + " KG"
// } else {
// weight = weight.substring(0, weight.length - 1) + "." + weight.last() + " KG"
// }
//
// var height = jsonObject.getString("height")
//
// if (height.length == 1) {
// height = height.substring(0, height.length - 1) + "0." + height.last() + " M"
// } else {
// height = height.substring(0, height.length - 1) + "." + height.last() + " M"
// }
//
// val imageURL = jsonObject.getJSONObject("sprites")
// .getJSONObject("other")
// .getJSONObject("official-artwork")
// .getString("front_default")
// val stats = jsonObject.getJSONArray("stats")
// val statMap = mutableMapOf<String, Int>()
//
// for (i in 0 until stats.length()) {
// val statObject = stats.getJSONObject(i)
// val statName = statObject.getJSONObject("stat").getString("name")
// val baseStat = statObject.getInt("base_stat")
// statMap[statName] = baseStat
// }
// val types = mutableListOf<String>()
// val typesJSON = jsonObject.getJSONArray("types")
// for (i in 0 until typesJSON.length()) {
// types.add(typesJSON.getJSONObject(i).getJSONObject("type").getString("name"))
// }
// return Pokemon(id, name, imageURL, statMap, types, weight, height)
// }
//
//
// }