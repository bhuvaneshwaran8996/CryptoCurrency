package com.example.cryptocurrency.mvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency.R

import com.example.cryptocurrency.databinding.DataInflateBinding
import com.example.cryptocurrency.mvvm.model.Data

class RecyclerAdapter(context: Context, cryptoList: ArrayList<Data>) : RecyclerView.Adapter<RecyclerAdapter.CryptoHolder>(),Filterable {

    public val context: Context;
    var cryptoList = ArrayList<Data>();
   var filteredList = ArrayList<Data>();

    init {
        this.context = context;
        this.cryptoList = cryptoList;

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.CryptoHolder {


        var binding: DataInflateBinding = DataBindingUtil.inflate<DataInflateBinding>(LayoutInflater.from(context), R.layout.data_inflate, null, false)


        return CryptoHolder(binding);

    }

    override fun getItemCount(): Int {

        return cryptoList.size;
    }

    fun setNewList(filteredcryptoList: ArrayList<Data>) {

        this.cryptoList = filteredcryptoList;
        notifyDataSetChanged();
    }


    override fun onBindViewHolder(holder: RecyclerAdapter.CryptoHolder, position: Int) {


        holder.view.model = cryptoList.get(position);
        Glide.with(context).load(cryptoList.get(position).explorer).into(holder.view.cryptoImage)

    }

    override fun getFilter(): Filter {
        return filter;
    }


    private val filter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence): FilterResults {

            if (constraint.isEmpty()) {
                filteredList.addAll(cryptoList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (item in 0..cryptoList.size) {
                    if (cryptoList[item].name!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(cryptoList[item])
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredList = filterResults?.values as ArrayList<Data>
            setNewList(filteredList);
            notifyDataSetChanged()
        }
    }
    class CryptoHolder(v: DataInflateBinding) : RecyclerView.ViewHolder(v.root) {

        var view: DataInflateBinding = v;

    }

}