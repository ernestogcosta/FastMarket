package br.iesb.mobile.fastmarket.repository

import br.iesb.mobile.fastmarket.domain.ChatbotRequest
import br.iesb.mobile.fastmarket.domain.ChatbotResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Headers

interface DialogFlowService {
    @POST("/api/message/text/send")
    suspend fun sendTextMessage(@Body chatbotBody: ChatbotRequest): List<ChatbotResponse>
}

/*
interface DialogFlowService {
    @POST("api/message/text/send")
    fun sendTextMessage(@Body requestBody: RequestBody): Response<ChatbotMessage>
}*/