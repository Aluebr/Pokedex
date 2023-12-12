package com.example.pokedex.view

import androidx.compose.ui.graphics.Color
import com.example.pokedex.ui.theme.Bug
import com.example.pokedex.ui.theme.Dark
import com.example.pokedex.ui.theme.Dragon
import com.example.pokedex.ui.theme.Electric
import com.example.pokedex.ui.theme.Fairy
import com.example.pokedex.ui.theme.Fighting
import com.example.pokedex.ui.theme.Fire
import com.example.pokedex.ui.theme.Flying
import com.example.pokedex.ui.theme.Ghost
import com.example.pokedex.ui.theme.Grass
import com.example.pokedex.ui.theme.Ground
import com.example.pokedex.ui.theme.Ice
import com.example.pokedex.ui.theme.Normal
import com.example.pokedex.ui.theme.Poison
import com.example.pokedex.ui.theme.Psychic
import com.example.pokedex.ui.theme.Rock
import com.example.pokedex.ui.theme.Shadow
import com.example.pokedex.ui.theme.Steel
import com.example.pokedex.ui.theme.Unknown
import com.example.pokedex.ui.theme.Water

enum class ColorTypes(val color: Color) {
    NORMAL(Normal),
    FIGHTING(Fighting),
    FLYING(Flying),
    POISON(Poison),
    GROUND(Ground),
    ROCK(Rock),
    BUG(Bug),
    GHOST(Ghost),
    STEEL(Steel),
    FIRE(Fire),
    WATER(Water),
    GRASS(Grass),
    ELECTRIC(Electric),
    PSYCHIC(Psychic),
    ICE(Ice),
    DRAGON(Dragon),
    DARK(Dark),
    FAIRY(Fairy),
    UNKNOWN(Unknown),
    SHADOW(Shadow)
}