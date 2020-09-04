package com.utkarsh.fxn.ui.search

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.utkarsh.fxn.R
import com.utkarsh.fxn.databinding.FragmentSearchBinding
import com.utkarsh.fxn.ui.searchresult.SearchViewPagerAdapter
import com.utkarsh.fxn.ui.searchresult.ZoomOutPageTransformer
import com.utkarsh.fxn.util.hideKeyboard
import com.utkarsh.fxn.util.showKeyboard
import com.utkarsh.fxn.util.showSearchKeyboard
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater)

        binding.searchView.requestFocus()
        binding.searchView.isFocusableInTouchMode = true
        showSearchKeyboard(binding.searchView)

        (activity as AppCompatActivity).setSupportActionBar(binding.actionBarSearch)

        setHasOptionsMenu(true)

//        binding.textView4.setOnClickListener{
//
//            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToExploreFragment())
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showSearchKeyboard(searchView)
//
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(context, R.layout.suggestion_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        searchView.suggestionsAdapter = cursorAdapter


        val suggestions = listOf("Apple","pple","Appe","Appl","Aple","Apple","Appgle","Applse","Appler","Applef","Appleg","Appleh","Applve","Applec","Appled", "Blueberry", "Carrot", "Daikon")


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)

                val a =view_pagerr.currentItem
                Log.v("utk", "curent selected tab - $a ")
                val mViewPagerAdapter = SearchViewPagerAdapter(this@SearchFragment, query!!)
                val tab = tabss.getTabAt(a)
                tab!!.select()

                view_pagerr.adapter = mViewPagerAdapter

                view_pagerr.currentItem = a



//                textView4.text=query
                return true
            }
        })

        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                hideKeyboard()
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                // Do something with selection
                return true
            }
        })

        val mViewPagerAdapter = SearchViewPagerAdapter(this@SearchFragment, "")
        view_pagerr.adapter = mViewPagerAdapter
        view_pagerr.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(tabss, view_pagerr) { tab, position ->
            if (position == 0)
                tab.text = "Movies"
            else
                tab.text = "Tv Shows"


        }.attach()





    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        (activity as AppCompatActivity).menuInflater.inflate(R.menu.search_menu,menu)
        val menuItem = menu?.findItem(R.id.search_menu_item)

        val searchDialog = menuItem.actionView as androidx.appcompat.widget.SearchView






        searchDialog.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {


                Toast.makeText(context, "Aja tujhe sundaas dikhata hu!", Toast.LENGTH_SHORT).show()

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                //var qquery : String = "dark"
//            if(searchDialog.isIconified){return true}
//            if (query!!.isNotEmpty()) {
//                val mViewPagerAdapter = SearchViewPagerAdapter(this@SearchFragment, query!!)
//                view_pagerr.adapter = mViewPagerAdapter
//                view_pagerr.setPageTransformer(ZoomOutPageTransformer())
//
//            }

                if (query!!.isNotEmpty())
                //textView4.text=query

            Log.v("utk", "this the query - $query ")


            return true
        }
        })

        searchDialog.addOnAttachStateChangeListener(object: View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(p0: View?) {
                // the view was expanded

            }

            override fun onViewDetachedFromWindow(p0: View?) {
                // the view was collapsed
                Log.v("utk", "Search view detached from window")
            }
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item!!.itemId) {
            R.id.search_menu_item -> {

//                genre_view.visibility = View.GONE
//                view_pager_view.visibility = View.VISIBLE
//                view_pagerr.visibility = View.VISIBLE

//                val mViewPagerAdapter = SearchViewPagerAdapter(this@SearchFragment, "")
//                view_pagerr.adapter = mViewPagerAdapter!!
//                view_pagerr.setPageTransformer(ZoomOutPageTransformer())
//
//                TabLayoutMediator(tabss, view_pagerr) { tab, position ->
//                    if (position == 0)
//                        tab.text = "Movies"
//                    else
//                        tab.text = "Tv Shows"
//
//
//                }.attach()
                return true


            }
        }



        return super.onOptionsItemSelected(item)
    }
}