package com.ruby.rank.fragment.adapter

import android.content.Context
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.ruby.rank.R
import com.ruby.rank.common.Config
import com.ruby.rank.common.MatchPool
import com.ruby.rank.common.net.ServiceAPI
import com.ruby.rank.database.Subject
import com.ruby.rank.model.SearchResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_search.view.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.io.FileOutputStream


class SearchAdapter(val context: Context?, val data: List<SearchResult.Anime>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(anime: SearchResult.Anime) {
            itemView.nameView.text = "《${anime.name_cn}》"
            Glide.with(itemView).load(anime.images!!.large).apply(RequestOptions().centerCrop()).into(itemView.imageView)
            itemView.addView.setOnClickListener {
                val retrofit = Retrofit.Builder()
                        .baseUrl(ServiceAPI.pic)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                val service = retrofit.create(ServiceAPI.Image::class.java)
                service.get(anime.images.large.substring(29))
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            val filename = anime.name_cn + ".jpg"
                            val path = Environment.getExternalStorageDirectory().toString() + File.separator + "rank" + File.separator + filename
                            val file = File(path)
                            if (!file.exists()) {
                                File(file.parent).mkdirs()
                                file.createNewFile()
                            }
                            val outputStream = FileOutputStream(file)
                            outputStream.write(it.bytes())
                            outputStream.flush()
                            outputStream.close()
                            val subject = Subject(name = anime.name_cn,imagePath = path,imageUrl = anime.images.large)
                            subject.save()
                            MatchPool.produce()
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete {
                            itemView.addView.text = "已添加"
                            itemView.addView.isEnabled = false
                            val config = Config(itemView.context)
                            val count = config.get(Config.SUBJECT_COUNT,0)+1
                            config.set(Config.SUBJECT_COUNT,count)
                        }
                        .subscribe()
            }
        }

    }

}