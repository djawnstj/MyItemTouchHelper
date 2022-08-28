package com.djawnstj.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.djawnstj.list.databinding.ActivityMainBinding

/**
 * RecyclerView.Adapter 에서 ItemTouchHelper 이용하기
 */
class MainActivity : AppCompatActivity() {

    companion object { private const val TAG = "MainActivity" }

    // 뷰바인딩 변수
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 리싸이클러뷰 어뎁터에 들어갈 아이템들이 담긴 배열 변수
    private val list by lazy { mutableListOf<User>() }
    // 리싸이클러뷰 어뎁터
    private val recyclerViewAdapter by lazy { RecyclerViewAdapter() }
    // 리스트 어댑터
    private val listAdapter by lazy { ListAdapter() }
    // 리싸이클러뷰 아이템 이동 콜백 변수
    private val itemTouchHelper by lazy { ItemTouchHelper(ItemTouchCallback(listAdapter)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()

    }

    /** 리싸이클러뷰 초기화 함수 */
    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = listAdapter

        // 리싸이클러뷰에 itemTouchHelper 연결
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        setListItems()
    }

    /** 리싸이클러뷰 어뎁터에 아이템 넣는 함수 */
    private fun setListItems() {
        for (i in 0..20) {
            list.add(User(
                i.toLong(),
                "홍길동",
                i+20
            ))
        }
        listAdapter.submitList(list)
    }

}