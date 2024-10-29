package com.example.searchinlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchinlist.ui.theme.SearchInListTheme

class MainActivity : ComponentActivity() {
    private lateinit var studentAdapter: StudentAdapter
    private val studentList = listOf(
        Student("Mai Minh Hoàng", "12345"),
        Student("Trần Thị Vũ", "67890"),
        Student("Tăng Vũ Minh", "11223"),
        Student("Lê Nguyên Sỹ", "44556"),
        Student("Vũ Anh Ngọc", "43556")

    )
    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitity_main)
        searchEditText=findViewById(R.id.searchEditText)
        recyclerView=findViewById(R.id.recyclerView)
        // Thiết lập Adapter cho RecyclerView
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter
        searchEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val keyword = s.toString().lowercase()
                    if(keyword.isNullOrEmpty()){
                        studentAdapter.updateList(listOf())
                        return
                    }
                    val filteredList = studentList.filter {
                        it.name.lowercase().contains(keyword) || it.mssv.contains(keyword)
                    }
                    studentAdapter.updateList(filteredList)
                }
            }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SearchInListTheme {
        Greeting("Android")
    }
}