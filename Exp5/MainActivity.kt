package com.example.ex_5

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val insert = findViewById<Button>(R.id.insert)
        val update = findViewById<Button>(R.id.update)
        val delete = findViewById<Button>(R.id.delete)
        val view = findViewById<Button>(R.id.view)
        val viewall = findViewById<Button>(R.id.viewall)
        val name = findViewById<EditText>(R.id.name)
        val regno = findViewById<EditText>(R.id.regno)
        val cgpa = findViewById<EditText>(R.id.cgpa)

        fun clearText(){
            regno.setText("")
            name.setText("")
            cgpa.setText("")
            regno.requestFocus()
        }

        val db =openOrCreateDatabase("StudentDB", MODE_PRIVATE,null)
        db.execSQL("create table if not exists student(regno varchar, name varchar, cgpa varchar);")
        insert.setOnClickListener {
            if(regno.text.toString().isEmpty()||name.text.toString().isEmpty()||regno.text.toString().isEmpty()){
                showMessage("Error","Please enter all values")
            } else {
                db.execSQL("insert into student values( '" + regno.text.toString() + "','" + name.text.toString() + "','" + cgpa.text.toString() + "');")
                showMessage("Success", "Record added")
                clearText()
            }
        }
        update.setOnClickListener {
            if(regno.text.toString().isEmpty()){
                showMessage("Error","Please enter Regno")
            } else {
                val c=db.rawQuery("select * from student where regno='"+regno.text.toString()+"';",null)
                if(c.moveToFirst()){
                    db.execSQL("update student set name='"+regno.text.toString()+"',name='"+name.text.toString()+"',cgpa='"+cgpa.text.toString()+"';")
                    showMessage("Success","Record Modified")
                } else {
                    showMessage("Error","Invalid Regno")
                }
                clearText()
                c.close()                
            }
        }
        delete.setOnClickListener {
            if(regno.text.toString().isEmpty()) {
                showMessage("Error", "Please enter Regno")
            }else {
                val c = db.rawQuery(
                    "SELECT * FROM student where regno='" + regno.text.toString() + "'",
                    null
                )
                if (c.moveToFirst()) {
                    db.execSQL("delete from student where regno='" + regno.text.toString() + "'")
                    showMessage("Success", "Record Deleted")
                } else {
                    showMessage("Error", "Invalid Regno")
                }
                clearText()
                c.close()
            }
        }
        view.setOnClickListener {
            if(regno.text.toString().isEmpty()){
                showMessage("Error","Please enter Regno")
            }else {
                val c = db.rawQuery(
                    "select * from student where regno='" + regno.text.toString()+"'",
                    null
                )
                if (c.moveToFirst()) {
                    name.setText(c.getString(1))
                    cgpa.setText(c.getString(2))
                } else {
                    showMessage("Error", "Invalid Regno")
                }
                c.close()
            }
        }
        viewall.setOnClickListener {
            val c=db.rawQuery("select * from student",null)
            if(c.count==0){
                showMessage("Error","No Record found")
            } else {
                val buffer = StringBuffer()
                while(c.moveToNext()){
                    buffer.append(("Regno:"+c.getString(0)).trimIndent())
                    buffer.append(("Name:"+c.getString(1)).trimIndent())
                    buffer.append(("Cgpa:"+c.getString(2)).trimIndent())
                    buffer.append(("\n").trimIndent())
                }
                showMessage("Student Details",buffer.toString())
            }
            c.close()

        }
    }
    private fun showMessage(title:String?,message: String?){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }
//

}