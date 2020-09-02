package com.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DataUtils {

	public Date periodo(int periodo) {

		Calendar calendar = Calendar.getInstance();
		if (periodo == 2) {
			calendar.add(Calendar.MONDAY, -1);
		} else if (periodo == 3) {
			calendar.add(Calendar.DAY_OF_MONTH, -15);
		}
		Date dateRetorno = calendar.getTime();
		return dateRetorno;
	}

	public String formataData(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
		String dataFormata = dateFormat.format(data);
		return dataFormata;
	}
	
	public Date converterStringParaData(String data) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss"); 
		Date dataConvertida = new Date();
		try {
			dataConvertida = formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataConvertida;
	}
}
