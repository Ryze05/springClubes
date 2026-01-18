package com.example.clubes.controller

import com.example.clubes.model.Club
import org.springframework.ui.Model
import com.example.clubes.service.ClubService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ClubController(private val clubService: ClubService) {
    @GetMapping("/clubes")
    fun listar(model: Model): String {
        model.addAttribute("clubes", clubService.listClubs())
        return "clubes"
    }

    @GetMapping("/clubes/{id_club}")
    fun verClub(@PathVariable("id_club") idClub: Int, model: Model): String {
        val club = clubService.findById(idClub) ?: return "errorClub"

        model.addAttribute("club", club)
        return "detalleClub"
    }

    @GetMapping("/clubes/nuevo")
    fun nuevoClub(model: Model): String {
        val clubVacio = Club(0, "", "", 0, 0.0,"")

        model.addAttribute("club", clubVacio)
        model.addAttribute("titulo", "Nuevo Club")
        return "formularioClub"
    }

    @GetMapping("/clubes/editar/{id_club}")
    fun editarClub(@PathVariable("id_club") idClub: Int, model: Model): String {
        val club = clubService.findById(idClub) ?: return "redirect:/clubes"
        model.addAttribute("club", club)
        model.addAttribute("titulo", "Editar Club")
        return "formularioClub"
    }

    @PostMapping("/clubes/guardar")
    fun guardarClub(@ModelAttribute club: Club): String {
        clubService.save(club)
        return "redirect:/clubes"
    }

    @GetMapping("/clubes/borrar/{id_club}")
    fun borrarClub(@PathVariable("id_club") idClub: Int): String {
        clubService.delete(idClub)
        return "redirect:/clubes"
    }
}