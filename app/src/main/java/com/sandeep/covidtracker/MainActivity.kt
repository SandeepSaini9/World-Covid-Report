package com.sandeep.covidtracker

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var tvCases: TextView? = null
    var tvRecovered: TextView? = null
    var tvActive: TextView? = null
    var tvTodayCases: TextView? = null
    var tvTotalDeaths: TextView? = null
    var tvTodayDeaths: TextView? = null
    var tvCritical: TextView? = null
    var tvcasesPerOneMillion: TextView? = null
    var tvAffectedCountries: TextView? = null
    var tvdeathsPerOneMillion: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCases = findViewById(R.id.tvCases)
        tvRecovered = findViewById(R.id.tvRecovered)
        tvActive = findViewById(R.id.tvActive)
        tvTodayCases = findViewById(R.id.tvTodayCases)
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths)
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths)
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries)
        tvCritical=findViewById(R.id.tvCritical)
        tvcasesPerOneMillion=findViewById(R.id.tvcasesPerOneMillion)
        tvdeathsPerOneMillion=findViewById(R.id.tvdeathsPerOneMillion)
        fetchdata()
    }

    private fun fetchdata() {
        val url="https://disease.sh/v3/covid-19/all"
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                try {
                    val jsonObject = JSONObject(
                        response
                    )

                    tvCases!!.text = jsonObject.getString(
                        "cases"
                    )
                    tvRecovered!!.text = jsonObject.getString(
                        "recovered"
                    )
                    tvActive!!.text = jsonObject.getString(
                        "active"
                    )
                    tvTodayCases!!.text = jsonObject.getString(
                        "todayCases"
                    )
                    tvTotalDeaths!!.text = jsonObject.getString(
                        "deaths"
                    )
                    tvTodayDeaths!!.text = jsonObject.getString(
                        "todayDeaths"
                    )
                    tvAffectedCountries!!.text = jsonObject.getString(
                        "affectedCountries"
                    )
                    tvCritical!!.text = jsonObject.getString(
                        "critical"
                    )
                    tvcasesPerOneMillion!!.text = jsonObject.getString(
                        "casesPerOneMillion"
                    )
                    tvdeathsPerOneMillion!!.text = jsonObject.getString(
                        "deathsPerOneMillion"
                    )
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->
            Toast.makeText(
                this@MainActivity,
                error.message,
                Toast.LENGTH_SHORT
            )
                .show()
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}