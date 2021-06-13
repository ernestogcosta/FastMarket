package br.iesb.mobile.fastmarket.domain


data class ChatbotRequest(
    var message: String,
    var sessionId: String
)

//import android.view.contentcapture.ContentCaptureSessionId
//sessionId: ContentCaptureSessionId