package com.example.musicapp.model

import java.io.Serializable
import java.sql.Date

    data class Usuarios(
        var idUsuario: String,
        var nombre: String,
        var email: String,
        var password: String,
        var avatar: String
    ):Serializable

    data class Festivales(
        var idFest: String,
        var fecha: Date,
        var genero: String,
        var nombre: String,
        var precio: Float,
        var puntuacion_media: Int,
        var url_original: String,
        var fotos: List<String>,
        var comentarios: String,
        var latitud: String,
        var longitud: String
    ):Serializable

    data class Conciertos(
        var idconciertos: String,
        var fecha: Date,
        var genero: String,
        var nombre: String,
        var precio: Float,
        var url_original: String,
        var horario: String,
        var latitud: String,
        var longitud: String
    ):Serializable
