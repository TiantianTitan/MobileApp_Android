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
import androidx.appcompat.app.AlertDialog
import com.ndroid.dessert_shop.data.Gateau

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


        gateauTitre.text = gateau.titre
        description.text = gateau.description
        val bitmap = getBitmap(gateau.image)
        imageGateau.setImageBitmap(bitmap)

        price.text = gateau.prix

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
                        showDeleteConfirmationDialog(position)

//                        values.removeAt(position)
//                        notifyDataSetChanged()
                    }

                }
                true
            }

            popupMenu.show()
        }



        return itemView
    }

    private fun showDeleteConfirmationDialog(position: Int) {
        val builder = AlertDialog.Builder(mContext)
        builder.setTitle("Confirmation")
        builder.setMessage("Êtes-vous sure de vouloir delete cet élément ?")
        builder.setPositiveButton("Oui"){
            dialogInterface: DialogInterface, id->
            values.removeAt(position)
            notifyDataSetChanged()
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