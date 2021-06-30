package com.example.mydiary

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mydiary.databinding.ActivityAddBinding
import com.example.mydiary.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var diaryViewModel: DiaryViewModel

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)


        diaryViewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)

        if (intent != null && intent.hasExtra(EXTRA_CONTACT_TITLE) && intent.hasExtra(
                EXTRA_CONTACT_CONTENT) && intent.hasExtra(EXTRA_CONTACT_ID)) {
            binding.editTitle.setText(intent.getStringExtra(EXTRA_CONTACT_TITLE))
            binding.editContent.setText(intent.getStringExtra(EXTRA_CONTACT_CONTENT))
            id = intent.getIntExtra(EXTRA_CONTACT_ID, -1)
        }

        binding.btnAdd.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val content = binding.editContent.text.toString()

            if(title.isNullOrBlank() || content.isNullOrBlank()){
                Toast.makeText(this, "빈칸 있음", Toast.LENGTH_SHORT).show()
            }else {
                Log.d("btnAdd", "true")
                val diaryData = DiaryData(id, title, content) //null
                // ViewModel을 통해 값 넣어주기
                diaryViewModel.insert(diaryData)
                diaryViewModel.getAll().observe(this, Observer {

                })
                finish()
            }
        }
        setContentView(binding.root)
    }

    // 상수?
    companion object {
        const val EXTRA_CONTACT_TITLE = "EXTRA_CONTACT_TITlE"
        const val EXTRA_CONTACT_CONTENT = "EXTRA_CONTACT_CONTENT"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }
}