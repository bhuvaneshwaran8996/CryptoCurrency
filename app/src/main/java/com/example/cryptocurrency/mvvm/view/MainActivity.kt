package com.example.cryptocurrency.mvvm.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.ActivityMainBinding
import com.example.cryptocurrency.databinding.DataInflateBinding
import com.example.cryptocurrency.mvvm.RecyclerAdapter
import com.example.cryptocurrency.mvvm.Status
import com.example.cryptocurrency.mvvm.model.CryptoModel
import com.example.cryptocurrency.mvvm.model.Data
import com.example.cryptocurrency.mvvm.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.security.AccessController.getContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding;
    private val TAG = "MainActivity"
    val cryptoList = ArrayList<Data>();
    val searchList = ArrayList<Data>();
    var adapter: RecyclerAdapter = RecyclerAdapter(this, cryptoList);


    private val cryptoViewModel by viewModels<CryptoViewModel>()
    val list = mutableListOf<String>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main);

        binding.editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                /*adapter.filter.filter(p0).toString()*/
             // adapter.filter.filter(p0.toString());
                searchList.clear();
                filter(p0.toString());

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
              //  adapter.getFilter().filter(charSequence.toString())



            }
        });



        cryptoViewModel.getCryptoData().observe(this, androidx.lifecycle.Observer {
            it?.let {
                when (it.status) {


                    Status.SUCCESS -> {
                        hideLoading()
                        binding.recyclerView.visibility = VISIBLE;
                        it.data?.let { country -> process(it.data?.body()) }!!
                    }
                    Status.ERROR -> {
                        hideLoading()
                        binding.recyclerView.visibility = GONE;
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.recyclerView.visibility = GONE;
                        showLoading()
                    }
                }
            }
        })






        binding.swipe.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {

                cryptoViewModel.getCryptoData().observe(this@MainActivity, Observer {
                    when (it.status) {


                        Status.SUCCESS -> {
                            hideLoading()
                            it.data?.let { country -> process(it.data?.body()) }!!
                        }
                        Status.ERROR -> {
                            hideLoading()
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            showLoading()
                        }
                    }
                })

            }
        })
    }


    public fun process(cryptoModel: CryptoModel?) {
        binding.swipe.isRefreshing = false;
        cryptoViewModel.getCryptoDataFromDB(cryptoModel).observe(this, Observer {
            it?.let {
                when (it.status) {


                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = VISIBLE;
                        hideLoading()
                        it.data?.let { country -> processCryptoDB(it.data) }!!

                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = GONE;
                        hideLoading()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.recyclerView.visibility = GONE;
                        showLoading()
                    }
                }
            }
        })
    }

    private fun processCryptoDB(data: List<CryptoModel>) {
        val cryptoModel: CryptoModel? = data.get(data.size - 1)
        cryptoModel?.data?.let {
            cryptoList.clear();
            cryptoList.addAll(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(this);
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.adapter=RecyclerAdapter(this, cryptoList);
        }


        Log.d("test", "");

    }

    fun hideLoading() {
        binding.progressBar.visibility = View.GONE;

    }

    fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE;
    }

    public fun filter(search: String) {

        if (!search.equals("")) {

            for(item in 0..cryptoList.size-1){
                if (cryptoList[item].name!!.toLowerCase().contains(search.toString().toLowerCase())) {
                    searchList.add(cryptoList[item])
                }
            }

        } else {
            searchList.addAll(cryptoList);
        }
        binding.recyclerView.adapter=RecyclerAdapter(this, searchList);







    }




}