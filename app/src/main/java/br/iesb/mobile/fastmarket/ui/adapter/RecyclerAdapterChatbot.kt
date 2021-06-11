package br.iesb.mobile.fastmarket.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.fastmarket.R

class RecyclerAdapterChatbot(messageList: MutableList<String>): RecyclerView.Adapter<RecyclerAdapterChatbot.ViewHolder>() {
    private var messages: MutableList<String> = messageList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterChatbot.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout_chatbot,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapterChatbot.ViewHolder, position: Int) {
        holder.itemText.text = messages[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var itemText: TextView
        init {
            itemText = itemView.findViewById(R.id.item_text)
        }
    }
}