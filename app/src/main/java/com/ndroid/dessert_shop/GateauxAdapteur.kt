package com.ndroid.dessert_shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
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

        gateauTitre.text = gateau.titre
        description.text = gateau.description
        imageGateau.setImageResource(gateau.image)
        price.text = gateau.prix

        return itemView
    }







}