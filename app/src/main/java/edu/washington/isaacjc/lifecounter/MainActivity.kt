package edu.washington.isaacjc.lifecounter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = CustomAdaptor(this)
    }

    private class CustomAdaptor(context: Context): BaseAdapter() {
        private val mContext: Context
        private val startingHP = 20

        init {
            mContext = context
        }

        override fun getCount(): Int {
            return 4
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "String"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val row = layoutInflater.inflate(R.layout.row_main, parent, false)
            val playerName = row.findViewById<TextView>(R.id.player_textView)
            playerName.text = "Player ${(position+1)}"
            val hp = row.findViewById<TextView>(R.id.hp_textView)
            hp.text = "$startingHP"
            val plus = row.findViewById<Button>(R.id.plus_btn)
            plus.setOnClickListener {
                listenFunction(playerName, hp, 1)
            }
            val minus = row.findViewById<Button>(R.id.minus_btn)
            minus.setOnClickListener {
                listenFunction(playerName, hp, -1)
            }
            val plus5 = row.findViewById<Button>(R.id.plus5_btn)
            plus5.setOnClickListener {
                listenFunction(playerName, hp, 5)
            }
            val minus5 = row.findViewById<Button>(R.id.minus5_btn)
            minus5.setOnClickListener {
                listenFunction(playerName, hp, -5)
            }
            return row
        }

        fun listenFunction(player:TextView, tv:TextView, increment:Int) {
            val currentLife = tv.text.toString().toInt()
            val newLife = currentLife + increment
            tv.text = "$newLife"

            if (newLife <=0) {
                val text = player.text.toString() + " LOSES!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(mContext, text, duration)
                toast.show()
            }


        }

    }


}
