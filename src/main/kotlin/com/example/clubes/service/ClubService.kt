package com.example.clubes.service

import com.example.clubes.model.Club
import com.example.clubes.repository.ClubRepository
import org.springframework.stereotype.Service

@Service
class ClubService(private val repository: ClubRepository) {

    fun listClubs(): MutableList<Club> {
        return repository.findAll()
    }

    fun findById(idClub: Int): Club? {
        return repository.findAll().find { it.idClub == idClub }
    }

    fun save(club: Club) {
        repository.save(club)
    }

    fun delete(idClub: Int) {
        repository.deleteById(idClub)
    }
}