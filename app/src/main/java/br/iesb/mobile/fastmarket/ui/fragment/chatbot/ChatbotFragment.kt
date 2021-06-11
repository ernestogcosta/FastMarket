package br.iesb.mobile.fastmarket.ui.fragment.chatbot

import android.widget.Toast
import br.iesb.mobile.fastmarket.domain.ChatbotRequest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.FragmentChatbotBinding
import br.iesb.mobile.fastmarket.repository.DialogFlowService
import br.iesb.mobile.fastmarket.ui.adapter.RecyclerAdapter
import br.iesb.mobile.fastmarket.ui.adapter.RecyclerAdapterChatbot
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapterChatbot: RecyclerAdapterChatbot
    private var messageList = mutableListOf<String>()
    private val uniqueId = UUID.randomUUID().toString();

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
        binding.rvMessageList.apply{
            binding.rvMessageList.layoutManager = LinearLayoutManager(activity?.applicationContext)
            recyclerView = requireView().findViewById(R.id.rvMessageList)
            recyclerAdapterChatbot = RecyclerAdapterChatbot(messageList)
            recyclerView.adapter = recyclerAdapterChatbot
        }
        sendMessage("oi", uniqueId)
    }
    fun addMessage(v: View){
        if(binding.messageUser.text.toString().isEmpty()){
            Toast.makeText(view?.context, "Digite a sua mensagem!", Toast.LENGTH_LONG).show()
        }else{
            val index: Int = messageList.size
            messageList.add(index, binding.messageUser.text.toString())
            recyclerAdapterChatbot.notifyItemInserted(index)
            sendMessage(binding.messageUser.text.toString(), uniqueId)
            binding.messageUser.setText("")
            recyclerView.scrollToPosition(messageList.size - 1)
        }
    }
    fun addMessageBot(msg: String){
        val index: Int = messageList.size
        messageList.add(index, msg)
        recyclerAdapterChatbot.notifyItemInserted(index)
        recyclerView.scrollToPosition(messageList.size - 1)
    }
    private fun sendMessage(msg: String, userId: String) {
        val index: Int = messageList.size
        val retrofit = Retrofit.Builder().baseUrl("https://fast-market2.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(DialogFlowService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                val response = service.sendTextMessage(ChatbotRequest(msg, userId))
                addMessageBot(response[0].message)
            }
        }
    }
}