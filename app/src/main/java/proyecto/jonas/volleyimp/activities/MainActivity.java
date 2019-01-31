package proyecto.jonas.volleyimp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

import proyecto.jonas.volleyimp.R;
import proyecto.jonas.volleyimp.adapters.MonedasAdapter;
import proyecto.jonas.volleyimp.adapters.MyPageAdapter;
import proyecto.jonas.volleyimp.services.DivisasService;
import proyecto.jonas.volleyimp.services.IVolleyCallback;
import proyecto.jonas.volleyimp.services.BilletesService;

public class MainActivity extends AppCompatActivity {

    private Button btnBilletes;
    private Button btnDivisas;
    private Button limpiarLista;
    private ListView lvMonedas;
    private HashMap mMonedaList;

    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProperties();
        executeButton();
    }

    private void executeButton() {
    //   btnBilletes.setOnClickListener(new View.OnClickListener() {
    //       @Override
    //       public void onClick(View v) {
    //           showBilletes();
    //       }
    //   });

    //   limpiarLista.setOnClickListener(new View.OnClickListener() {
    //       @Override
    //       public void onClick(View v) {
    //           lvMonedas.setAdapter(null);
    //       }
    //   });

    //   btnDivisas.setOnClickListener(new View.OnClickListener() {
    //       @Override
    //       public void onClick(View v) {
    //           lvMonedas.setAdapter(null);
    //           showDivisas();

    //       }
    //   });

    }

    private void initProperties() {
     //  btnBilletes = findViewById(R.id.btnBilletes);
     //  btnDivisas = findViewById(R.id.btnDivisas);
     //  limpiarLista = findViewById(R.id.btnLimpiar);
     //  lvMonedas = findViewById(R.id.lvMonedas);


        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Billetes"));
        tabLayout.addTab(tabLayout.newTab().setText("Divisas"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyPageAdapter pageAdapter = new MyPageAdapter(getSupportFragmentManager() , tabLayout.getTabCount());


        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    private void showDivisas() {
        DivisasService htmlDecode = new DivisasService(this, new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap jsonString) {
                showListViewParams(jsonString);
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });
        htmlDecode.callRequestData();
    }

    private void showBilletes() {
        BilletesService htmlDecode = new BilletesService(this, new IVolleyCallback() {
            @Override
            public void onSuccess(HashMap jsonString) {
                showListViewParams(jsonString);
            }

            @Override
            public void onFailure(Exception e) {
                String holis = "";
            }
        });
        htmlDecode.callRequestData();
    }

    private void showListViewParams(HashMap hmParams) {
      //  mMonedaList =  hmParams;//fixme
        MonedasAdapter monedasAdapter = new MonedasAdapter(this,hmParams);
        lvMonedas.setAdapter(monedasAdapter);
    }

}