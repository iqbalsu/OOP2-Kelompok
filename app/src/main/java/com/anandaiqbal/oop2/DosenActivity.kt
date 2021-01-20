package com.anandaiqbal.oop2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.anandaiqbal.oop2.Database.Constant
import com.anandaiqbal.oop2.Database.Dosen
import com.anandaiqbal.oop2.Database.RoomDB
import com.rizkihanip.oop2.R
import kotlinx.android.synthetic.main.activity_dosen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DosenActivity : AppCompatActivity() {
    val db by lazy { RoomDB(this) }
    lateinit var dosenAdapter: DosenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dosen)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadDosen()
    }

    fun loadDosen() {
        CoroutineScope(Dispatchers.IO).launch {
            val allDosen = db.dosenDao().getAllDosen()
            Log.d("DosenActivity", "dbResponse: $allDosen")
            withContext(Dispatchers.Main) {
                dosenAdapter.setData(allDosen)
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        dosenAdapter = DosenAdapter(arrayListOf(), object: DosenAdapter.OnAdapterListener {
            override fun onClick(dosen: Dosen) {
                intentEdit(dosen.id, Constant.TYPE_READ)
            }

            override fun onDelete(dosen: Dosen) {
                deleteDialog(dosen)
            }
            override fun onUpdate(dosen: Dosen) {
                intentEdit(dosen.id, Constant.TYPE_UPDATE)
            }


        })
        list_dosen.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dosenAdapter
        }
    }

    fun intentEdit(dosenId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id",dosenId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(dosen: Dosen) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.dosenDao().deleteDosen(dosen)
                    loadDosen()
                }
            }
        }
        alertDialog.show()
    }
}