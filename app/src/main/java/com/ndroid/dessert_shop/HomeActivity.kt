package com.ndroid.dessert_shop

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Infini_Dessert_Shop"
        /*

        val salutation = findViewById<TextView>(R.id.salutation)

        // 1 : récupérer l'email envoyé par l'activité Main
        val email = intent.getStringExtra("email")
        // 2 : Afficher l'email dans la salutation
        salutation.text =  "Bienvenu : $email !"
*/
        val listGateaux = findViewById<ListView>(R.id.list_gateau)
        val gateauArray = arrayListOf(
            Gateau(
                "Gâteau chocolat",
                "Ce gâteau est non seulement attrayant visuellement avec ses diverses textures et couleurs, mais il promet également une explosion de saveurs pour ceux qui auront la chance de le déguster.",
                R.drawable.g1,
                "prix: 40€",
                """
        • Forme et Base : Le gâteau est rond avec une base probablement composée de biscuit émietté, ajoutant une texture croquante.
        
        • Glaçage et Décorations : Le gâteau est recouvert d'un glaçage crémeux, peut-être au caramel ou au dulce de leche, qui coule élégamment sur les côtés.
        
        • Éléments de Décoration :
            - Rosaces de Crème : Il y a plusieurs rosaces de crème chantilly ou de crème au beurre disposées sur le dessus du gâteau.
            - Chocolats : Des morceaux de barres chocolatées sont placés sur les rosaces, ajoutant une touche de gourmandise.
            - Fruits Rouges : Un petit amas de groseilles rouges trône au centre du gâteau, apportant une touche de couleur vive et une saveur acidulée.
            - Biscuit et Bonbons : Des morceaux de biscuits et peut-être des meringues colorées (en rose) ajoutent de la variété dans les textures et les saveurs.
            - Bougies : Trois bougies (deux roses et une verte) sont plantées sur le gâteau, suggérant qu'il est préparé pour une célébration, probablement un anniversaire.
        """.trimIndent()
            ),
            Gateau(
                "Gâteau noël",
                "Ce gâteau est visuellement impressionnant et semble être non seulement délicieux mais aussi conçu avec soin et créativité pour rendre la célébration d'anniversaire mémorable.",
                R.drawable.g2,
                "prix: 45€",
                """
        • Forme et Base : Le gâteau est rond et repose sur une planche en bois. La base est probablement un gâteau à la crème ou au beurre, recouvert d'un glaçage lisse et blanc.
        
        • Décorations et Thème : Le thème du gâteau semble être festif et coloré, avec un accent particulier sur les éléments de décoration dorés et festifs.
        
        • Éléments de Décoration :
            - Message de Joyeux Anniversaire : Sur le dessus du gâteau, il y a une décoration en lettres dorées scintillantes qui lit "Happy Birthday", ajoutant une touche festive et élégante.
            - Fraises Déguisées en Pères Noël : De petites fraises sont décorées pour ressembler à des Pères Noël, avec des chapeaux rouges et des visages en crème, ce qui donne une touche amusante et saisonnière.
            - Étoiles et Cœurs Dorés : Des piques décoratives en forme d'étoiles et de cœurs dorés sont placées autour du gâteau, renforçant le thème festif.
            - Rosaces de Crème et Fleurs en Sucre : Des rosaces de crème rose et blanche, ainsi que des fleurs en sucre délicates, ajoutent de la couleur et de la texture.
            - Barres Chocolatées et Oreos : Des morceaux de barres chocolatées et des Oreos sont disposés autour des fraises et des fleurs, ajoutant de la gourmandise et du contraste.
            - Perles et Petites Boules : De petites perles de chocolat entourent la base du gâteau, apportant une finition soignée et élégante.
        """.trimIndent()
            ),
            Gateau(
                "Gâteau carnaval",
                "Ce gâteau est non seulement visuellement impressionnant avec ses ballons et ses décorations dorées, mais il promet également une explosion de saveurs grâce aux fruits frais et aux tranches d'orange. Il est parfaitement adapté pour une célébration d'anniversaire mémorable et festive.",
                R.drawable.g3,
                "prix: 50€",
                """
        • Forme et Base : Le gâteau est rond avec une base classique recouverte de crème ou de glaçage blanc, posé sur une planche en bois.
        
        • Décorations et Thème : Ce gâteau a une apparence très festive et colorée, avec des éléments de décoration variés et un thème d'anniversaire prononcé.
        
        • Éléments de Décoration :
            - Ballons Transparants : Deux grands ballons transparents remplis de confettis dorés sont attachés au sommet du gâteau, apportant une touche spectaculaire et festive.
            - Message de Joyeux Anniversaire : Un décor en lettres dorées scintillantes lisant "Happy Birthday" est placé sur le dessus du gâteau, soulignant le thème de la célébration.
            - Fruits Frais : Le dessus du gâteau est orné de plusieurs fruits frais, dont des fraises, des myrtilles et peut-être des morceaux d'ananas, apportant des couleurs vives et une touche de fraîcheur.
            - Rosaces de Crème : De jolies rosaces de crème ornent le pourtour du gâteau, ajoutant de la texture et de l'élégance.
            - Tranches d'Oranges : Des demi-tranches d'orange sont disposées autour du gâteau, ajoutant une touche de couleur et une saveur acidulée.
            - Piques Décoratives : Des piques dorées en forme d'étoiles et d'autres motifs festifs sont plantées sur le gâteau, accentuant l'ambiance de fête.
            - Fleurs en Sucre : Une ou deux fleurs en sucre délicates sont placées sur le côté du gâteau pour une touche de douceur et de beauté.
        """.trimIndent()
            ),
            Gateau(
                "Gâteau cosmétique",
                "Ce gâteau est non seulement visuellement attrayant avec ses décorations thématiques et ses fleurs en sucre, mais il est aussi conçu avec une grande attention aux détails, parfait pour une célébration spéciale dédiée à une passionnée de maquillage ou de beauté.",
                R.drawable.g4,
                "prix: 100€",
                """
        • Forme et Base : Le gâteau est à deux étages avec une base circulaire, recouvert d'un glaçage crémeux et blanc.
        
        • Thème et Décorations : Le thème de ce gâteau semble être axé sur la beauté et le maquillage, avec plusieurs éléments décoratifs qui rappellent ces concepts.
        
        • Éléments de Décoration :
            - Fleurs en Sucre : De grandes fleurs en sucre, notamment des roses blanches et roses, sont disposées sur le gâteau, ajoutant une touche élégante et romantique.
            - Décorations de Maquillage : Divers éléments en sucre représentant des produits de maquillage sont utilisés pour décorer le gâteau. On peut voir des accessoires comme un rouge à lèvres, un pinceau de maquillage, une palette de fards à paupières, et un sac à main noir en sucre.
            - Message de Joyeux Anniversaire : Une carte avec un message "Happy Birthday" est placée sur le dessus du gâteau, soulignant le thème de la célébration.
            - Rosaces de Crème : Des rosaces de crème décorent les bords et les étages du gâteau, apportant une texture douce et délicate.
            - Effets Visuels : L'image présente des effets visuels en forme de cœur et des messages tels que "LOVE YOU", ajoutant une touche sentimentale à la présentation du gâteau.
        """.trimIndent()
            )
        )

        val adapter = GateauxAdapteur(this,R.layout.item_gateau,gateauArray)
        listGateaux.adapter = adapter

        listGateaux.setOnItemClickListener{ adapterView,view,position,id->
            val clickedGateau = gateauArray[position]
            Intent(this,GateauDetailActivity::class.java).also{
                it.putExtra("titre",clickedGateau.titre)
                it.putExtra("imageNumber",clickedGateau.image)
                it.putExtra("prix",clickedGateau.prix)
                it.putExtra("description_complet",clickedGateau.description_complet)
                startActivity(it)
            }
        }


    } // fin onCreate


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // Handle add action
                true
            }
            R.id.action_config -> {
                // Handle config action
                true
            }
            R.id.action_logout -> {
                // Handle logout action
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }








}