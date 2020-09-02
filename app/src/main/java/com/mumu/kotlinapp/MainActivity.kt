package com.mumu.kotlinapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_header.*
import kotlinx.android.synthetic.main.main_tablayout.*

class MainActivity:FragmentActivity(){
//    lateinit var vp:ViewPager
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    private fun initView(){
//        vp = findViewById(R.id.vp)
        initFragment()
        initOnClick()
    }
    private fun initFragment(){
        var fragments:MutableList<Fragment> = ArrayList<Fragment>()
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        fragments.add(Fragment1())
        vp.adapter =viewPagerAdapter(supportFragmentManager,fragments)
        vp.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        initTabLayout(tab1_tv,tab2_v)
                        clearTabLayout(tab2_tv,tab2_v)
                        clearTabLayout(tab2_tv,tab3_v)
                    }
                    1->{
                        initTabLayout(tab2_tv, tab2_v)
                        clearTabLayout(tab1_tv, tab1_v)
                        clearTabLayout(tab3_tv, tab3_v)
                    }
                    2->{
                        initTabLayout(tab3_tv, tab3_v)
                        clearTabLayout(tab2_tv, tab2_v)
                        clearTabLayout(tab1_tv, tab1_v)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

    }
    private fun initOnClick(){
        tab1.setOnClickListener {
            vp.setCurrentItem(0)
            initTabLayout(tab1_tv, tab1_v)
            clearTabLayout(tab2_tv, tab2_v)
            clearTabLayout(tab3_tv, tab3_v)
        }
        tab2.setOnClickListener {
            vp.setCurrentItem(1)
            initTabLayout(tab2_tv, tab2_v)
            clearTabLayout(tab1_tv, tab1_v)
            clearTabLayout(tab3_tv, tab3_v)
        }
        tab3.setOnClickListener {
            vp.setCurrentItem(2)
            initTabLayout(tab3_tv, tab3_v)
            clearTabLayout(tab2_tv, tab2_v)
            clearTabLayout(tab1_tv, tab1_v)
        }
        header_an.setOnClickListener {
            header_1.visibility= View.GONE
            header_2.visibility=View.VISIBLE
            header_an.visibility = View.GONE
        }
        test(null)
    }


    private fun test(arg:String?){
        println(arg?.length?:"0")
    }

    private fun initTabLayout(tv:TextView,tv2:TextView){
        tv.setTextColor(resources.getColor(R.color.tab_select))
        tv2.setBackgroundColor(resources.getColor(R.color.tab_select))
    }
    private fun clearTabLayout(tv:TextView,tv2:TextView){
        tv.setTextColor(resources.getColor(R.color.tab_clear))
        tv2.setBackgroundColor(Color.WHITE)
    }
    class viewPagerAdapter(fm:FragmentManager?,var list:List<Fragment>):FragmentPagerAdapter(fm!!){
        override fun getItem(pos:Int):Fragment{
            return list.get(pos)
        }
        override fun getCount():Int{
            return list.size
        }
    }
}
