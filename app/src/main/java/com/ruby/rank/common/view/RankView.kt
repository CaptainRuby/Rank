package com.ruby.rank.common.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ruby.rank.R
import com.ruby.rank.common.MatchPool
import com.ruby.rank.model.Rank

class RankView(context: Context, val onResultClick: () -> Unit) : LinearLayout(context) {

    var rank: Rank? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_rank, this)
        initView()
    }

    fun initView() {
        val rankView = findViewById<LinearLayout>(R.id.rankView)
        val aImageView = findViewById<ImageView>(R.id.aImageView)
        val bImageView = findViewById<ImageView>(R.id.bImageView)
        val aNameView = findViewById<TextView>(R.id.aNameView)
        val bNameView = findViewById<TextView>(R.id.bNameView)
        val aResultView = findViewById<Button>(R.id.aResultView)
        val bResultView = findViewById<Button>(R.id.bResultView)
        rank = MatchPool.getRank()
        rank?.let { rank ->
            rankView.visibility = View.VISIBLE
            Glide.with(context).load(rank.A.imagePath).apply(RequestOptions().centerCrop()).into(aImageView)
            Glide.with(context).load(rank.B.imagePath).apply(RequestOptions().centerCrop()).into(bImageView)
            aNameView.text = rank.A.name
            bNameView.text = rank.B.name
            aResultView.setOnClickListener {
                onResultClick()
                rank.account(1.0)
            }
            bResultView.setOnClickListener {
                onResultClick()
                rank.account(1.0)
            }
        }

    }


}
