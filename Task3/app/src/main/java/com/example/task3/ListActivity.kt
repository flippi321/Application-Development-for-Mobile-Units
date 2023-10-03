import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

    private lateinit var venner: MutableList<Venn>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste)

        venner = intent.getSerializableExtra("venner") as MutableList<Venn>
        val listView = findViewById<ListView>(R.id.listVenner)
        val adapter = VennAdapter(this, venner)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            // Code to edit friend's details
            val selectedVenn = venner[position]
            // Edit and update the friend's details as needed
        }
    }
}
