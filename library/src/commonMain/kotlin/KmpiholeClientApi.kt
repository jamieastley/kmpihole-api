package dev.jamieastley.kmpihole.api

import dev.jamieastley.kmpihole.api.auth.AuthApi

interface KmpiholeClientApi {
    val auth: AuthApi
}