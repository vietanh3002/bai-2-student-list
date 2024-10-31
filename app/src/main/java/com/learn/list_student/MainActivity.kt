package com.learn.list_student

import Student
import StudentAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var edtSearch: EditText
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: List<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentList = listOf(
            Student("Nguyễn Văn A", "20210001"),
            Student("Trần Thị B", "20210002"),
            Student("Lê Văn C", "20210003"),
            Student("Phạm Minh D", "20210004"),
            Student("Ngô Thị E", "20210005"),
            Student("Lý Văn F", "20210006"),
            Student("Vũ Thị G", "20210007"),
            Student("Đỗ Văn H", "20210008"),
            Student("Hồ Thị I", "20210009"),
            Student("Bùi Văn K", "20210010"),
            Student("Nguyễn Thị L", "20210011"),
            Student("Trần Văn M", "20210012"),
            Student("Lê Thị N", "20210013"),
            Student("Phạm Văn O", "20210014"),
            Student("Ngô Thị P", "20210015"),
            Student("Lý Văn Q", "20210016"),
            Student("Vũ Thị R", "20210017"),
        )

        recyclerView = findViewById(R.id.recyclerView)
        edtSearch = findViewById(R.id.edtSearch)
        studentAdapter = StudentAdapter(studentList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Thiết lập TextWatcher cho ô tìm kiếm
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val keyword = s.toString().trim()
                filterList(keyword)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(keyword: String) {
        if (keyword.length > 2) {
            val filteredList = studentList.filter { student ->
                student.name.contains(keyword, ignoreCase = true) ||
                        student.mssv.contains(keyword)
            }
            studentAdapter.updateList(filteredList)
        } else {
            // Hiển thị lại toàn bộ danh sách khi từ khóa <= 2 ký tự
            studentAdapter.updateList(studentList)
        }
    }
}