package com.alisakralaraby.feeconnectdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaperWorkActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnjoiningCollege, btnTemporaryDocument, btnPermanentDocument, btnKaidFasl, btnSahbAwrak, btnSenTagnid,
                        btnEkhlaaTaraf, btnDerasatOlea, btnGraduatesInstructions, btnTarbeaAskarea, btnjoininghousing,
                        btnEltimasTathalom, btnJoiningEtihad, btnOrganizingEvents, btnIdCardReplacement, btnWifiAccount;
    private LinearLayout paperWorkLinearLayout;
    private WebView webViewPaperWork;
    private String joiningPapers, temporaryDocument, permanentDocument, kaidFasl, sahbAwrak, senTagnid, ekhlaaTaraf,
                        derasatOlea, graduatesInstructions, tarbeaAskarea, joinStudentsHousing, wifiAccount,
                        joiningEtihad, eltimasTathalom, organizingEvents, idCardReplacement;
    private boolean exit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_work);

        calling();

        gettingStrings();

        //setting onclick listener to all buttons used
        btnjoiningCollege.setOnClickListener(this);
        btnTemporaryDocument.setOnClickListener(this);
        btnPermanentDocument.setOnClickListener(this);
        btnKaidFasl.setOnClickListener(this);
        btnSahbAwrak.setOnClickListener(this);
        btnSenTagnid.setOnClickListener(this);
        btnEkhlaaTaraf.setOnClickListener(this);
        btnDerasatOlea.setOnClickListener(this);
        btnGraduatesInstructions.setOnClickListener(this);
        btnTarbeaAskarea.setOnClickListener(this);
        btnjoininghousing.setOnClickListener(this);
        btnIdCardReplacement.setOnClickListener(this);
        btnOrganizingEvents.setOnClickListener(this);
        btnJoiningEtihad.setOnClickListener(this);
        btnEltimasTathalom.setOnClickListener(this);
        btnWifiAccount.setOnClickListener(this);

    }
    //this methos is for calling and connectiong the views with the xml views
    private void calling(){

        btnjoiningCollege   = (Button) findViewById(R.id.btnjoiningCollege);
        btnTemporaryDocument = (Button) findViewById(R.id.btnTemporaryDocument);
        webViewPaperWork      = (WebView) findViewById(R.id.webViewPaperWork);
        paperWorkLinearLayout = (LinearLayout) findViewById(R.id.paperWorkLinearLayout);
        btnPermanentDocument = (Button)findViewById(R.id.btnPermanentDocument);
        btnKaidFasl = (Button) findViewById(R.id.btnKaidFasl);
        btnSahbAwrak = (Button) findViewById(R.id.btnSahbAwrak);
        btnSenTagnid = (Button) findViewById(R.id.btnSenTagnid);
        btnEkhlaaTaraf = (Button) findViewById(R.id.btnEkhlaaTaraf);
        btnDerasatOlea = (Button) findViewById(R.id.btnDerasatOlea);
        btnGraduatesInstructions = (Button) findViewById(R.id.btnGraduatesInstructions);
        btnTarbeaAskarea = (Button) findViewById(R.id.btnTarbeaAskarea);
        btnjoininghousing = (Button) findViewById(R.id.btnjoininghousing);
        btnIdCardReplacement = (Button) findViewById(R.id.btnIdCardReplacement);
        btnOrganizingEvents = (Button) findViewById(R.id.btnOrganizingEvents);
        btnJoiningEtihad = (Button) findViewById(R.id.btnJoiningEtihad);
        btnEltimasTathalom = (Button) findViewById(R.id.btnEltimasTathalom);
        btnWifiAccount = (Button) findViewById(R.id.btnWifiAccount);
    }

    //this methos is for assigning the string values
    private void gettingStrings(){

        joiningPapers = getString(R.string.joining_papers);
        temporaryDocument = getString(R.string.temporary_document);
        permanentDocument = getString(R.string.permanent_document);
        kaidFasl = getString(R.string.kaidFasl);
        sahbAwrak = getString(R.string.sahbAwrak);
        senTagnid = getString(R.string.senTagnid);
        ekhlaaTaraf = getString(R.string.ekhlaaTaraf);
        derasatOlea = getString(R.string.derasatOlea);
        graduatesInstructions = getString(R.string.graduatesInstructions);
        tarbeaAskarea = getString(R.string.tarbeaAskaria);
        joinStudentsHousing = getResources().getString(R.string.madina);
        idCardReplacement = getResources().getString(R.string.id_card_replacement);
        joiningEtihad = getResources().getString(R.string.joining_etihad);
        eltimasTathalom = getResources().getString(R.string.eltimas_tathalom);
        organizingEvents = getResources().getString(R.string.organizing_events);
        wifiAccount = getResources().getString(R.string.wifi_account);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnjoiningCollege :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, joiningPapers, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnjoininghousing :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, joinStudentsHousing, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnTemporaryDocument :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, temporaryDocument, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnPermanentDocument :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, permanentDocument, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnKaidFasl :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, kaidFasl, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnSahbAwrak :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, sahbAwrak, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnSenTagnid :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, senTagnid, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnEkhlaaTaraf :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, ekhlaaTaraf, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnDerasatOlea :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, derasatOlea, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnGraduatesInstructions :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, graduatesInstructions, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnTarbeaAskarea :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, tarbeaAskarea, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnIdCardReplacement :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, idCardReplacement, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnJoiningEtihad :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, joiningEtihad, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnEltimasTathalom :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, eltimasTathalom, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnOrganizingEvents :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, organizingEvents, "text/html", "utf-8", null);
                exit = false;
                break;
            case R.id.btnWifiAccount :
                paperWorkLinearLayout.setVisibility(View.INVISIBLE);
                webViewPaperWork.setVisibility(View.VISIBLE);
                webViewPaperWork.loadDataWithBaseURL(null, wifiAccount, "text/html", "utf-8", null);
                exit = false;
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (exit == false){

            paperWorkLinearLayout.setVisibility(View.VISIBLE);
            webViewPaperWork.setVisibility(View.INVISIBLE);
            exit = true;
        }else {

            finish();
        }
    }
}
