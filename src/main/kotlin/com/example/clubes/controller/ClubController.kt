package com.example.clubes.controller

import org.springframework.ui.Model
import com.example.clubes.service.ClubService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ClubController(private val clubService: ClubService) {
    @GetMapping("/clubs")
    fun listar(model: Model): String {
        model.addAttribute("clubs", clubService.listClubs())
        return "clubs"
    }
}