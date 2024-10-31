// StudentAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learn.list_student.R

class StudentAdapter(private var studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // Lớp ViewHolder để ánh xạ các view
    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMSSV: TextView = itemView.findViewById(R.id.tvMSSV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    // Hàm cập nhật danh sách sinh viên
    fun updateList(newList: List<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }
}
