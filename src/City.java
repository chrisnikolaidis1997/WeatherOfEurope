import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class City {
	String city;
	double X,Y;
	String line;
	String[] temper;
	String condition;
	double T,pressure;
	int humidity;
	public City(String c,double x,double y){
		city = c;
		X = x;
		Y = y;
		openpage();
		takeTemp();
		takeConditions();
		getPressure();
		getHumidity();
	}
	private void openpage(){
		try{
		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+Y+"&lon="+X+"&units=metric&appid=c18a86635fb26190c538298412e282ea");
		URLConnection con = url.openConnection();
		InputStream is =con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		line = " ";
		do{
			line = br.readLine();
		}while(line==null);
		}
		catch(IOException e){
			System.out.println("Cannot open url for "+city);
		}
	}
	public static void table(){
		System.out.println("CITY       TEMPER         CONDITIONS      PRESSURE      HUMIDITY");
		for(int i=0;i<40;i++){
			System.out.println(Capital[i].city+"       "+Capital[i].T+"         "+Capital[i].condition+"      "+Capital[i].pressure+"      "+Capital[i].humidity);
		}
	}
	public static void mean(){
		double mean =0;
		for(int i=0;i<40;i++){
			mean = mean + Capital[i].T;
		}
		System.out.println("MEAN TEMPERATURE OF EUROPE:"+(mean/40));
	}
	private void takeTemp(){
		temper = line.split("\"temp\"");
		temper= temper[1].split(":");
		temper= temper[1].split(",");
		T = Double.valueOf(temper[0]);
	}
	private void takeConditions(){
		temper = line.split("\"main\"");
		temper = temper[1].split(",");
		temper = temper[0].split("\"");
		condition = temper[1];
	}
	private void getPressure(){
		temper = line.split("\"pressure\"");
		temper = temper[1].split(":");
		temper = temper[1].split(",");
		pressure = Double.valueOf(temper[0]);
	}
	private void getHumidity(){
		temper = line.split("\"humidity\"");
		temper = temper[1].split(":");
		temper = temper[1].split(",");
		humidity = Integer.parseInt(temper[0]);
	}
		
	static City[] Capital = new City[40];
	public static void main(String x[]){
		//BALCANIA
		Capital[0] = new City("Athens",23.72,37.97);  //23.72,37.97
		Capital[1] = new City("Tirana",19.81,41.32);
		Capital[2] = new City("Skopje",21.43,42.00);
		Capital[3] = new City("Sofia",23.32,42.69);
		Capital[4] = new City("Belgrade",20.45,44.79);
		Capital[5] = new City("Podgorica",19.26,42.43);
		Capital[6] = new City("Bucharest",26.10,44.43);
		Capital[7] = new City("Sarajevo",18.41,43.85);
		Capital[8] = new City("Zagreb",15.98,45.81);
		//EAST BLOCK
		Capital[9] = new City("Chisinau",28.86,47.01);
		Capital[10] = new City("Kiev",30.52,50.45);
		Capital[11] = new City("Minsk",27.56,53.90);
		Capital[12] = new City("Vilnius",25.28,54.69);
		Capital[13] = new City("Riga",24.10,56.94);
		Capital[14] = new City("Tallin",24.75,59.44);
		Capital[15] = new City("Moskva",37.62,55.75);
		//CENTRAL EUROPE
		Capital[16] = new City("Ljubljana",14.50,46.06);
		Capital[17] = new City("Budapest",19.04,47.50);
		Capital[18] = new City("Vienna",16.37,48.20);
		Capital[19] = new City("Bratislava",17.11,48.15);
		Capital[20] = new City("Prague",14.44,50.07);
		Capital[21] = new City("Warsaw",21.01,52.23);
		//SKANDINAVIA
		Capital[22] = new City("Oslo",10.75,59.91);
		Capital[23] = new City("Stockholm",18.07,59.33);
		Capital[24] = new City("Helsinki",24.94,60.17);
		Capital[25] = new City("Copenhagen",12.57,55.67);
		//WEST EUROPE
		Capital[26] = new City("Berlin",13.40,52.52);
		Capital[27] = new City("Amsterdam",4.89,52.37);
		Capital[28] = new City("Brussels",4.35,50.85);
		Capital[29] = new City("Bern",7.45,46.95);
		Capital[30] = new City("Paris",2.35,48.85);
		Capital[31] = new City("Rome",12.49,41.90);
		Capital[32] = new City("Madrid",-3.70,40.41);
		Capital[33] = new City("Lisbon",-9.14,38.72);
		Capital[34] = new City("London",-0.13,51.51);
		Capital[35] = new City("Glasgow",-4.25,55.86);
		Capital[36] = new City("Belfast",-5.93,54.60);
		Capital[37] = new City("Dublin",-6.26,53.35);
		Capital[38] = new City("Reykjavik",-21.82,64.13);
		Capital[39] = new City("Nuuk",-51.69,64.18);
		
		City temp;
		for(int i=0; i < 40; i++){
            for(int j=1; j < (40-i); j++){
                    if(Capital[j-1].T < Capital[j].T){
                            //swap the elements!
                            temp = Capital[j-1];
                            Capital[j-1] = Capital[j];
                            Capital[j] = temp;
                    }
             }
    }
		 table();
		 mean();
		 Scanner in = new Scanner(System.in);
		 in.next();
	}
}
