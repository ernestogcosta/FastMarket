package br.iesb.mobile.fastmarket.ui.fragment.chatbot

import android.widget.Toast
import br.iesb.mobile.fastmarket.domain.ChatbotRequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.iesb.mobile.fastmarket.databinding.FragmentChatbotBinding
import br.iesb.mobile.fastmarket.repository.DialogFlowService
import retrofit2.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class ChatbotFragment : Fragment() {
    private lateinit var binding: FragmentChatbotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatbotBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var uniqueId = UUID.randomUUID().toString();
        parseJSON("oi", uniqueId)
    }
    private fun parseJSON(msg: String, userId: String) {
        binding.textView3.text = ""
        val retrofit = Retrofit.Builder().baseUrl("https://fast-market2.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(DialogFlowService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = service.sendTextMessage(ChatbotRequest(msg, userId))
                binding.textView3.text = response[0].message
            }
        }
    }
}