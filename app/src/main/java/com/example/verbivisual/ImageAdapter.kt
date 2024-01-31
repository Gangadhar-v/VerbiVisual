package com.example.verbivisual

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream

class ImageAdapter(val images:List<Image>,val context: Context): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    inner class ImageViewHolder(item: View):ViewHolder(item){
        var image = item.findViewById<ImageView>(R.id.ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false)

        return ImageViewHolder(item)
    }

    override fun getItemCount(): Int {
      return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        val src = image.src

        Glide.with(context)
            .asBitmap()
            .load(src)
            .placeholder(R.drawable.img)
            .into(object: CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    holder.image.setImageBitmap(resource)

                    holder.image.setOnLongClickListener {
                        shareImage(resource)
                        true
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    TODO("Not yet implemented")
                }

            })


    }

    private fun shareImage(bitmap:Bitmap){
        val uri = getImageUri(context,bitmap)
        val shareIntent = Intent(Intent.ACTION_SEND).apply{
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM,uri)
        }
        context.startActivity(Intent.createChooser(shareIntent,"Share Image"))
    }

    private fun getImageUri(inContext:Context,inImage:Bitmap):Uri{
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG,100,bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }






}