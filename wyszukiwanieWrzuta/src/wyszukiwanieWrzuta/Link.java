package wyszukiwanieWrzuta;

import java.io.IOException;

import org.jsoup.Jsoup;

public class Link {
	String link;
	public Link(String link){
		this.link = link;
	}
	
	public String changeToDownload(){
		String nick = getNick();
		String code = getCode();
		
		try {
			String linia[] = null;
			String czesci[] = null;
			String html2 = Jsoup.connect("http://" + nick +".wrzuta.pl/u/" + code).get().html();
			int index = html2.indexOf("<script type=\"text/javascript\">");
			String wyciety = html2.substring(index + "<script type=\"text/javascript\">".length() ,index + "<script type=\"text/javascript\">".length() + 1000);
			linia = wyciety.split(";");
			wyciety = linia[0];
			czesci = wyciety.split("\"");
			String lastLink = "";
			int licznik = 0;
			while(licznik < czesci.length){
				if(licznik%2==1){
					lastLink+=czesci[licznik];
				}
				
				licznik++;
			}
			return lastLink;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String getCode(){
		String code = "";
		String[] podzielonyLink = link.split("/");
		int i = 0;
		while(!(podzielonyLink[i].equalsIgnoreCase("audio")) && (i < podzielonyLink.length - 1)){
			i++;
		}
		if(podzielonyLink[i].equals("audio")){
			code = podzielonyLink[i + 1];
		}
		return code;
	}
	
	public String getNick(){
		String nick = "";
		String[] podzielonyLink = link.split("/");
		int i = 0;
		while(!podzielonyLink[i].endsWith(".wrzuta.pl") && (i < podzielonyLink.length - 1)){
			i++;
		}
		if(podzielonyLink[i].endsWith(".wrzuta.pl")){
			String[] podzielonyAdres = podzielonyLink[i].split("\\.");
			nick = podzielonyAdres[0];
		}
		
		return nick;
	}
	
	
}
