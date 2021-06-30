package com.example.mydiary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.mydiary.databinding.ActivityMainBinding
import com.example.mydiary.databinding.ItemDiaryBinding

// ViewModel 인스턴스를 만들고 관찰
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var diaryViewModel: DiaryViewModel
    private lateinit var diaryAdapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val swipeHelperCallback = SwipeHelperCallback()
        //val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(this, AddActivity::class.java)

        diaryAdapter = DiaryAdapter()
        binding.rvDiaryList.adapter = diaryAdapter

        // itemTouchHelper와 recyclerView 연결
        //itemTouchHelper.attachToRecyclerView(binding.rvDiaryList)

        // adapter 내부의 함수에 현재 로직을 넘김
        // callback 구현
        diaryAdapter.setDeleteButtonClickListener {
                it ->
            diaryViewModel.delete(it)
            Log.d("delete", "db")
        }

        // ViewModel 객체 생성 -> 인스턴스가 이미 있다면 이를 반환하여 메모리 낭비를 줄임
        diaryViewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)
        // observe가 MainActivity의 생명주기를 관찰, 이 owner가 파괴되면 ViewModel 파괴될수도 안될수도
        // activity가 활성화 되어 있으면 View에서 LiveData를 관찰하여 자동으로 변경을 파악하고 수행
        diaryViewModel.getAll().observe(this, Observer<List<DiaryData>>{
            it ->
            //update UI
            // observer 내부 onChanged 메소드에서 관찰하던 LiveData가 변하면 무엇을 할 것인지 액션 지정
            diaryAdapter.diaryList = it as MutableList<DiaryData>
            diaryAdapter.notifyDataSetChanged()
        })

        binding.btnMore.setOnClickListener {
            Log.d("btnMore", "success")
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}