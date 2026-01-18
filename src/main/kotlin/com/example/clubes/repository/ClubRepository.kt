package com.example.clubes.repository

import com.example.clubes.model.Club
import org.springframework.stereotype.Repository
import java.io.File

@Repository
class ClubRepository {
    private val filePath = "src/main/resources/data/clubs.csv"

    fun findAll(): MutableList<Club> =
        File(filePath).readLines().map { linea ->
            val partes = linea.split(";")
            Club(
                idClub = partes[0].toInt(),
                name = partes[1],
                foundation = partes[2],
                titles = partes[3].toInt(),
                billing = partes[4].toDouble(),
                picture = partes[5]
            )
        }.toMutableList()

    fun save(club: Club) {
        val clubs = findAll()

        val index = clubs.indexOfFirst { it.idClub == club.idClub }

        if (index != -1) {
            clubs[index] = club
        } else {
            val nuevoId = (clubs.maxOfOrNull { it.idClub } ?: 0) + 1

            clubs.add(club.copy(idClub = nuevoId))
        }
        escribirArchivo(clubs)
    }

    fun deleteById(idClub: Int) {
        val clubs = findAll()
        clubs.removeIf { it.idClub == idClub }
        escribirArchivo(clubs)
    }

    private fun escribirArchivo(clubs: List<Club>) {
        val contenido = clubs.joinToString(separator = "\n") {
            "${it.idClub};${it.name};${it.foundation};${it.titles};${it.billing};${it.picture}"
        }
        File(filePath).writeText(contenido)
    }
}