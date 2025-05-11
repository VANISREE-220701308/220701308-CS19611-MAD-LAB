package com.example.scicalex3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import java.util.Stack
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val wordOperator = arrayOf('c','s','t','l')
        val input  = findViewById<EditText>(R.id.input)
        val output  = findViewById<TextView>(R.id.output)

        val add = findViewById<Button>(R.id.badd)
        val sub = findViewById<Button>(R.id.bsub)
        val mul = findViewById<Button>(R.id.bmul)
        val div = findViewById<Button>(R.id.bdiv)
        val mod = findViewById<Button>(R.id.bmod)
        val cos = findViewById<Button>(R.id.bcos)
        val sin = findViewById<Button>(R.id.bsin)
        val tan = findViewById<Button>(R.id.btan)
        val clr = findViewById<Button>(R.id.bclr)
        val eql = findViewById<Button>(R.id.beq)
        val xp2 = findViewById<Button>(R.id.bxp2)
        val xpy = findViewById<Button>(R.id.bxpy)
        val log = findViewById<Button>(R.id.blog)
        val epx = findViewById<Button>(R.id.bepx)
        val sqrt = findViewById<Button>(R.id.bsqrt)
        val dot = findViewById<Button>(R.id.bdot)

        val b1 = findViewById<Button>(R.id.b1)
        val b2 = findViewById<Button>(R.id.b2)
        val b3 = findViewById<Button>(R.id.b3)
        val b4 = findViewById<Button>(R.id.b4)
        val b5 = findViewById<Button>(R.id.b5)
        val b6 = findViewById<Button>(R.id.b6)
        val b7 = findViewById<Button>(R.id.b7)
        val b8 = findViewById<Button>(R.id.b8)
        val b9 = findViewById<Button>(R.id.b9)
        val b0 = findViewById<Button>(R.id.b0)

        b1.setOnClickListener {
            val i = input.text.toString()+"1"
            input.setText(i)
        }
        b2.setOnClickListener {
            val i = input.text.toString()+"2"
            input.setText(i)
        }
        b3.setOnClickListener {
            val i = input.text.toString()+"3"
            input.setText(i)
        }
        b4.setOnClickListener {
            val i = input.text.toString()+"4"
            input.setText(i)
        }
        b5.setOnClickListener {
            val i = input.text.toString()+"5"
            input.setText(i)
        }
        b6.setOnClickListener {
            val i = input.text.toString()+"6"
            input.setText(i)
        }
        b7.setOnClickListener {
            val i = input.text.toString()+"7"
            input.setText(i)
        }
        b8.setOnClickListener {
            val i = input.text.toString()+"8"
            input.setText(i)
        }
        b9.setOnClickListener {
            val i = input.text.toString()+"9"
            input.setText(i)
        }
        b0.setOnClickListener {
            val i = input.text.toString()+"0"
            input.setText(i)
        }
        dot.setOnClickListener {
            val i = input.text.toString()+"."
            input.setText(i)
        }
        clr.setOnClickListener {
            input.setText("")
        }
        cos.setOnClickListener {
            val i = input.text.toString()+"cos "
            input.setText(i)
        }
        sin.setOnClickListener {
            val i = input.text.toString()+"sin "
            input.setText(i)
        }
        tan.setOnClickListener {
            val i = input.text.toString()+"tan "
            input.setText(i)
        }
        sqrt.setOnClickListener {
            val i = input.text.toString()+"sqrt"
            input.setText(i)
        }
        xp2.setOnClickListener {
            val i = input.text.toString()+"^2"
            input.setText(i)
        }
        xpy.setOnClickListener {
            val i = input.text.toString()+"^"
            input.setText(i)
        }
        log.setOnClickListener {
            val i = input.text.toString()+"log "
            input.setText(i)
        }
        epx.setOnClickListener {
            val i = input.text.toString()+"e^"
            input.setText(i)
        }
        div.setOnClickListener {
            val i = input.text.toString()+"/"
            input.setText(i)
        }
        mul.setOnClickListener {
            val i = input.text.toString()+"*"
            input.setText(i)
        }
        add.setOnClickListener {
            val i = input.text.toString()+"+"
            input.setText(i)
        }
        sub.setOnClickListener {
            val i = input.text.toString()+"-"
            input.setText(i)
        }
        mod.setOnClickListener {
            val i = input.text.toString()+"%"
            input.setText(i)
        }
        fun isNum(n:Char):Boolean{
            return n.isDigit() || n=='.'
        }
        data class NumPos(val num:String,val i:Int)
        fun getVal(ip:String,pos:Int, n:Int): NumPos {
            var i=pos
            var num = ""
            while(i<n && isNum(ip[i])){
                num+=ip[i].toString()
                i+=1
            }
            return NumPos(num,i)
        }
        fun precedence(op:Char):Int{
            return if(op=='+'|| op=='-')
                1
            else
                2
        }
        fun cal(n1:Double,n2:Double,op:Char):Double{
            var res = 0.0
            when (op) {
                '%' -> res = n1%n2
                '^' -> res= n1.pow(n2)
                '*' -> res = n1*n2
                '/' -> res = n1/n2
                '-' -> res=n1-n2
                '+' -> res=n1+n2
            }
            return res
        }
        eql.setOnClickListener {
            val ip = input.text.toString()
            val numStack = Stack<Double>()
            val opStack = Stack<Char>()
            var i=0
            val n= ip.length
            var f=false
            while(i < n){
                if(isNum(ip[i])){
                    f=true
                    val (num,ri)=getVal(ip,i,n)
                    i=ri
                    numStack.push(num.toDouble())
                }
                else if(ip[i]=='e'){
                    i+=2
                    f=true
                    val (num,ri)=getVal(ip,i,n)
                    i=ri
                    numStack.push(exp(num.toDouble()))
                }
                else {
                    // Symbol operators
                    if (!wordOperator.contains(ip[i])) {
                        if (!f) {
                            break
                        }
                        while(opStack.size!=0 && precedence(opStack.peek())>=precedence(ip[i])){
                            val n2=numStack.pop()
                            val n1=numStack.pop()
                            val op = opStack.pop()
                            numStack.push(cal(n1,n2,op))

                        }
                        opStack.push(ip[i])
                        i += 1
                        f=false
                    }
                    // Word operator
                    else {
                        if(f==true){
                            f=false
                            break
                        }
                        f=true
                        if (ip[i] == 'c') {
                            i += 4
                            val (num,ri)=getVal(ip,i,n)
                            i=ri
                            numStack.push(cos((num.toDouble()%360)*PI/ 180))
                        }
                        else if (ip[i] == 't') {
                            i += 4
                            val (num,ri)=getVal(ip,i,n)
                            i=ri
                            numStack.push(tan((num.toDouble()%360)*PI/ 180))
                        }
                        else if (ip[i] == 'l') {
                            i += 4
                            val (num,ri)=getVal(ip,i,n)
                            i=ri
                            numStack.push(ln(num.toDouble()))
                        }
                        else if(ip[i+4]!=' '){
                            i += 4
                            val (num,ri)=getVal(ip,i,n)
                            i=ri
                            numStack.push(sin((num.toDouble()%360)*PI/ 180))
                        }
                        else{
                            i += 5
                            val (num,ri)=getVal(ip,i,n)
                            i=ri
                            numStack.push(sqrt(num.toDouble()))
                        }
                    }
                }
            }
            while(f && opStack.size!=0){
                val n2=numStack.pop()
                val n1=numStack.pop()
                val op = opStack.pop()
                numStack.push(cal(n1,n2,op))

            }


            output.text = if(f) String.format(Locale.US,"%.3f", numStack.pop()) else "invalid"
        }
    }
}