package com.ndroid.dessert_shop

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ndroid.dessert_shop.data.Gateau
import com.ndroid.dessert_shop.db.DessertShopDataBase

class GateauxAdapteur(
    var mContext: Context,
    var resource: Int,
    var values: ArrayList<Gateau>
    ) :ArrayAdapter<Gateau>(mContext,resource,values){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val gateau = values[position]
        val itemView = LayoutInflater.from(mContext).inflate(resource,parent,false)
        val gateauTitre = itemView.findViewById<TextView>(R.id.titre)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imageGateau = itemView.findViewById<ImageView>(R.id.imageGateau)
        val price = itemView.findViewById<TextView>(R.id.prix)
        val imageShowPopup = itemView.findViewById<ImageView>(R.id.image_show_popup)
        val tvLikes = itemView.findViewById<TextView>(R.id.tv_like)
        val tvShare = itemView.findViewById<TextView>(R.id.tvShare)

        gateauTitre.text = gateau.titre
        description.text = gateau.description

        val bitmap = getBitmap(gateau.image)
        imageGateau.setImageBitmap(bitmap)
        tvLikes.text = "${gateau.jaime} j'aime"

        price.text = gateau.prix
        val db = DessertShopDataBase(mContext)

        imageShowPopup.setOnClickListener{
            val popupMenu = PopupMenu(mContext,imageShowPopup)
            popupMenu.menuInflater.inflate(R.menu.list_popup_menu,popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.itemShow -> {
                        Intent(mContext,GateauDetailActivity::class.java).also {
                            it.putExtra("titre",gateau.titre)
                            it.putExtra("imageNumber",gateau.image)
                            it.putExtra("prix",gateau.prix)
                            it.putExtra("description_complet",gateau.description_complet)
                            mContext.startActivity(it)
                        }

                    }

                    R.id.itemDelete ->{
                        showDeleteConfirmationDialog(position,gateau)
                    }

                }
                true
            }
            popupMenu.show()
        }

        tvLikes.setOnClickListener{

            db.incrementLikes(gateau)
            val incrementLikes = gateau.jaime+1
            tvLikes.text = "$incrementLikes j'aime"
        }

        tvShare.setOnClickListener{
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"https://www.infinidessert.com/gateaux/${gateau.id}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, gateau.titre)
            mContext.startActivity(shareIntent)

        }

        return itemView
    }

    private fun showDeleteConfirmationDialog(position: Int,gateau: Gateau) {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("Confirmation")
        builder.setMessage("Êtes-vous sure de vouloir delete cet élément ?")
        builder.setPositiveButton("Oui"){
            dialogInterface: DialogInterface, id->
            val db = DessertShopDataBase(mContext)
            val resultDelete = db.deleteGateaux(gateau.id)
            if(resultDelete){
                values.removeAt(position)
                notifyDataSetChanged()
            }else{
                Toast.makeText(mContext,"Erreur de supprestion",Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Non"){
            dialogInterface: DialogInterface, id->
            dialogInterface.dismiss()
        }
        builder.setNeutralButton("Annuler"){
            dialogInterface: DialogInterface, id->
            dialogInterface.dismiss()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    fun getBitmap(byteArray: ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
}