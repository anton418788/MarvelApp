package com.example.marvelapp.screens.cards_info

import com.example.marvelapp.R

data class ConcreteCard(
    val id: Int,
    val name: Int,
    var info: Int,
    val imageUrl : String
)

fun List<ConcreteCard>.getConcreteCardById(ConcreteCardId: Int): ConcreteCard? {
    return this.find { it.id == ConcreteCardId }
}

fun getConcreteCardById(ConcreteCardId: Int) = ConcreteCards.getConcreteCardById(ConcreteCardId)


val ConcreteCards = listOf(
    ConcreteCard(
        1, R.string.Deadpool,
        R.string.DeadpoolInfo,
        "https://iili.io/Jsf7PKG.png"
    ),
    ConcreteCard(
        2, R.string.IronMan,
        R.string.IronManInfo,
        "https://iili.io/JsfGayJ.png"
    ),
    ConcreteCard(
        3, R.string.CaptainAmerica,
        R.string.CaptainAmericaInfo,
        "https://iili.io/Jsf1GNR.png"
    ),
    ConcreteCard(
        4, R.string.Spiderman,
        R.string.SpidermanInfo,
        "https://iili.io/Jsflxfe.png"
    ),
)