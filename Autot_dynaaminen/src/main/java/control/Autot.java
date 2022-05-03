package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import model.Auto;
import model.dao.Dao;


@WebServlet("/autot/*")
public class Autot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Autot() {
        super();
       System.out.println("Autot.Autot()");
    }

    //haetaan autojen tiedot
    //GET /autot{hakusana}
    //GET /autot/haeyksi/rekno
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Autot.doGet()");
		String pathInfo = request.getPathInfo();	//haetaan kutsun polkutiedot, esim. /audi			
		System.out.println("polku: "+pathInfo);	
		Dao dao = new Dao();
		ArrayList<Auto> autot;
		String strJSON="";
		if(pathInfo==null) {//haetaan kaikki autot
			autot = dao.listaaKaikki();
			strJSON = new JSONObject().put("autot", autot).toString();
		}else if (pathInfo.indexOf("haeyksi")!=-1) {//polussa on sana "haeyksi", eli haetaan yhden auton tiedot
			String rekno = pathInfo.replace("/haeyksi/", ""); //poistataan polusta "haeyksi", jolloin j‰‰ vain rekisterinumero
			Auto auto = dao.etsiAuto(rekno);
			JSONObject JSON = new JSONObject();
			JSON.put("merkki", auto.getMerkki());
			JSON.put("malli", auto.getMalli());
			JSON.put("vuosi", auto.getVuosi());
			JSON.put("rekno", auto.getRekno());
			strJSON = JSON.toString();
		}else {
			String hakusana = pathInfo.replace("/", "");
			autot = dao.listaaKaikki(hakusana);
			strJSON = new JSONObject().put("autot", autot).toString();
		}
		System.out.println(strJSON);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(strJSON);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Autot.doPost()");
		JSONObject jsonObj = new JsonStrToObj().convert(request); //Muutetaan kutsun mukana tuleva json-string json-objektiksi	
		Auto auto = new Auto();
		auto.setRekno(jsonObj.getString("rekNo"));
		auto.setMerkki(jsonObj.getString("merkki"));
		auto.setMalli(jsonObj.getString("malli"));
		auto.setVuosi(jsonObj.getInt("vuosi"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();			
		if(dao.lisaaAuto(auto)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Auton lis‰‰minen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Auton lis‰‰minen ep‰onnistui {"response":0}
		}
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Autot.doPut()");
		JSONObject jsonObj = new JsonStrToObj().convert(request); //Muutetaan kutsun mukana tuleva json-string json-objektiksi	
		String vanharekno = jsonObj.getString("vanharekno");
		Auto auto = new Auto();
		auto.setRekno(jsonObj.getString("rekNo"));
		auto.setMerkki(jsonObj.getString("merkki"));
		auto.setMalli(jsonObj.getString("malli"));
		auto.setVuosi(jsonObj.getInt("vuosi"));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();			
		if(dao.muutaAuto(auto, vanharekno)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Auton p‰ivitt‰minen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Auton p‰ivitt‰minen ep‰onnistui {"response":0}
		}
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Autot.doDelete()");
		String pathInfo = request.getPathInfo();	//haetaan kutsun polkutiedot, esim. /AAA-111		
		System.out.println("polku: "+pathInfo);	
		String poistettavaRekno = pathInfo.replace("/", "");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();			
		if(dao.poistaAuto(poistettavaRekno)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Auton poistaminen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Auton poistaminen ep‰onnistui {"response":0}
		}
	}

}
