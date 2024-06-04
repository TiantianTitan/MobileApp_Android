package com.ndroid.dessert_shop

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView

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
        imageGateau.setImageResource(gateau.image)
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
                        values.removeAt(position)
                        notifyDataSetChanged()
                    }

                }
                true
            }

            popupMenu.show()
        }



        return itemView
    }







}