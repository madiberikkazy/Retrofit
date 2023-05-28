package com.example.experince2

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.experince2.databinding.MainActivityBinding
import com.example.experince2.ui.theme.Experince2Theme
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpCookie


const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    private  lateinit var  binding: MainActivityBinding

    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecylerView()

        lifecycleScope.launchWhenCreated {
            binding.prograaBar.isVisible = true
            val response = try{
                RetrofitInstance.api.getTodos()
            }catch (e:IOException){
                Log.e(TAG, " IOExeption, you might not have internet connect" )
                binding.prograaBar.isVisible = false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.e(TAG, " HTTPExeption, unexpected responce" )
                binding.prograaBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null){
                todoAdapter.todos= response.body()!!
            }else{
                Log.e(TAG, "Responce not sucsecful", )
            }
            binding.prograaBar.isVisible = false
        }
    }

    private fun setupRecylerView() = binding.rvTodos.apply {
        todoAdapter = ToDoAdapter()
        adapter =  todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}
