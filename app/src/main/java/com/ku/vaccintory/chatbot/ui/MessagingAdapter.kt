package com.ku.vaccintory.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ku.vaccintory.R
import com.ku.vaccintory.chatbot.data.Message
import com.ku.vaccintory.chatbot.utills.Constants.RECEIVE_ID
import com.ku.vaccintory.chatbot.utills.Constants.SEND_ID

class MessagingAdapter :RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>(){
    var messageList = mutableListOf<Message>()
    inner class MessageViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        init {
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item,parent,false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList(position)

        when (currentMessage.id){
            SEND_ID -> {
                holder.itemView.tv_message.apply{
                    text = currentMessage.message
                    visibility  = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                text = currentMessage.message
                visibility = View.VISIBLE
            }
            holder.itemView.tv_bot_message.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    override fun getItemCount(): Int {
        return messageList.size
    }
    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyDataSetChanged(messageList.size)
    }

}