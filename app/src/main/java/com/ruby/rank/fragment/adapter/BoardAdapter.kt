package com.ruby.rank.fragment.adapter

import android.content.Context
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruby.rank.R
import com.ruby.rank.database.Subject
import kotlinx.android.synthetic.main.item_board.view.*
import java.io.File


class BoardAdapter(val context: Context?) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    var data: List<Subject> = mutableListOf()

    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_board, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(subject: Subject) {
            itemView.numView.text = (layoutPosition+1).toString()
            itemView.nameView.text = "《${subject.name}》"
            itemView.pointView.text = subject.point.toString()
            Glide.with(itemView).load(subject.imagePath).apply(RequestOptions().centerCrop()).into(itemView.imageView)
        }

    }

}