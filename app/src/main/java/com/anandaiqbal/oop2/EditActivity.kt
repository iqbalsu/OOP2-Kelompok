package com.anandaiqbal.oop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.anandaiqbal.oop2.Database.Constant
import com.anandaiqbal.oop2.Database.Dosen
import com.anandaiqbal.oop2.Database.RoomDB
import com.rizkihanip.oop2.R
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { RoomDB(this) }
    private var dosenId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
        setupView()
    }

    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.dosenDao().addDosen(
                    Dosen (0, Integer.parseInt(edit_nipy.text.toString()), edit_nama.text.toString(), edit_pengampu.text.toString()  )
                )
                finish()
            }
        }
    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                getDosen()
            }
        }
    }

    fun getDosen() {
        dosenId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val dosens =  db.dosenDao().getDosen( dosenId )[0]
            edit_nipy.setText( dosens.nipy.toString() )
            edit_nama.setText( dosens.nama )
            edit_pengampu.setText( dosens.pengampu )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}