package stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy", Locale.US);

    public static void main(String[] args) {
	String _firstDate = "1-September-2002";
	String _lastDate = "22-November-2002";
	String _weekDay = "Monday";

	//	Scanner in = new Scanner(System.in);
	//	try {
	//	    _firstDate = in.nextLine();
	//	} catch (Exception e) {
	//	    _firstDate = null;
	//	}
	//	try {
	//	    _lastDate = in.nextLine();
	//	} catch (Exception e) {
	//	    _lastDate = null;
	//	}
	//
	//	try {
	//	    _weekDay = in.nextLine();
	//	} catch (Exception e) {
	//	    _weekDay = null;
	//	}

	openAndClosePrices(_firstDate, _lastDate, _weekDay);

    }

    private static List<Limite> discoveryLimites() {
	final List<Limite> limites = new ArrayList<>();
	try {
	    Integer currentPage = 1;
	    Integer finalPage = 10;
	    do {
		System.out.println("Page:" + currentPage);
		StockInfo stock = getStockDataBy("https://jsonmock.hackerrank.com/api/stocks/?page=" + currentPage);
		if (stock != null) {
		    finalPage = stock.getTotal_pages();
		    final DataInfo first = stock.getData().get(0);
		    final DataInfo last = stock.getData().get(stock.getPer_page() - 1);
		    final Limite limite = new Limite(dateFormat.parse(first.date), dateFormat.parse(last.date), currentPage);
		    limites.add(limite);
		    System.out.println(limite);
		}
		currentPage++;
	    } while (currentPage <= finalPage);

	} catch (ParseException pE) {
	    pE.printStackTrace();
	}
	return limites;
    }

    private static List<String> getListOfDates(final String firstDate, final String lastDate, final String weekDay) {
	List<String> datesOfStock = new ArrayList<>();
	try {
	    DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
	    final List<String> weekdays = Arrays.asList(dfs.getWeekdays());
	    final int indexWeekDay = weekdays.indexOf(weekDay);

	    final Date initDate = dateFormat.parse(firstDate);
	    final Date finalDate = dateFormat.parse(lastDate);
	    final Calendar calendar = Calendar.getInstance();
	    calendar.setTime(initDate);
	    while (calendar.get(Calendar.DAY_OF_WEEK) != indexWeekDay) {
		calendar.add(Calendar.DATE, 1);
	    }

	    while (calendar.getTime().compareTo(finalDate) <= 0) {
		datesOfStock.add(dateFormat.format(calendar.getTime()));
		calendar.add(Calendar.DATE, 7);
	    }
	} catch (ParseException pE) {
	    pE.printStackTrace();
	}
	return datesOfStock;
    }

    private static Integer getPageNumber(final List<Limite> pLimites, final String pDate) {
	for (Limite limite : pLimites) {
	    if (limite.isContened(pDate)) {
		return limite.getPageNumber();
	    }
	}
	return 1;
    }

        


    private static StockInfo getStockDataBy(final String pStringUrl) {
	try {

	    Runtime rt = Runtime.getRuntime();
	    Process pr = rt.exec("curl -s -S " + pStringUrl);

	    final InputStreamReader reader = new InputStreamReader(pr.getInputStream());
	    BufferedReader br = new BufferedReader(reader);

	    Gson gson = new GsonBuilder().setDateFormat("d-MMMM-yyyy").create();
	    return gson.fromJson(br, StockInfo.class);
	} catch (IOException pE) {
	    pE.printStackTrace();
	}
	return null;
    }

    /*
     * Complete the function below.
     */
    static void openAndClosePrices(String firstDate, String lastDate, String weekDay) {

	List<String> datesOfStock = getListOfDates(firstDate, lastDate, weekDay);
	List<Limite> limites = discoveryLimites();

	System.out.println(datesOfStock);

	for (String stringDateFomartted : datesOfStock) {
	    final Integer page = getPageNumber(limites, stringDateFomartted);
	    final String url = "https://jsonmock.hackerrank.com/api/stocks/?date=" + stringDateFomartted + "&page=" + page;
	    StockInfo stock = getStockDataBy(url);
	    if (stock != null) {
		final List<DataInfo> datas = stock.getData();
		if (!datas.isEmpty()) {
		    final DataInfo data = datas.get(0);
		    final String s = String.format("%s %s %s", data.getDate(), data.getOpen(), data.getClose());
		    System.out.println(s);
		}
	    }
	}

    }

    private static class Limite {
	private Date finalDate;

	private Date initialDate;

	private Integer pageNumber;

	public Limite(final Date pInitialDate, final Date pFinalDate, final Integer pPageNumber) {
	    finalDate = pFinalDate;
	    initialDate = pInitialDate;
	    pageNumber = pPageNumber;
	}

	public Date getFinalDate() {
	    return finalDate;
	}

	public Date getInitialDate() {
	    return initialDate;
	}

	public Integer getPageNumber() {
	    return pageNumber;
	}

	public boolean isContened(final String pDate) {
	    try {
		final Date date = dateFormat.parse(pDate);
		if (initialDate.compareTo(date) <= 0 && finalDate.compareTo(date) >= 0) {
		    return true;
		}
	    } catch (ParseException pE) {
		pE.printStackTrace();
	    }
	    return false;
	}

	public void setFinalDate(final Date pFinalDate) {
	    finalDate = pFinalDate;
	}

	public void setInitialDate(final Date pInitialDate) {
	    initialDate = pInitialDate;
	}

	public void setPageNumber(final Integer pPageNumber) {
	    pageNumber = pPageNumber;
	}

	@Override public String toString() {
	    return " - initialDate=" + initialDate +
			    " - finalDate=" + finalDate +
			    " - pageNumber=" + pageNumber;
	}
    }

    private class DataInfo {
	private Float close;

	private String date;

	private Float open;

	public Float getClose() {
	    return close;
	}

	public String getDate() {
	    return date;
	}

	public Float getOpen() {
	    return open;
	}

	public void setClose(final Float pClose) {
	    close = pClose;
	}

	public void setDate(final String pDate) {
	    date = pDate;
	}

	public void setOpen(final Float pOpen) {
	    open = pOpen;
	}

	@Override public String toString() {
	    return "DataInfo{" +
			    "close=" + close +
			    ", date=" + date +
			    ", open=" + open +
			    '}';
	}
    }

    private class StockInfo {
	private List<DataInfo> data;

	private Integer page;

	private Integer per_page;

	private Integer total;

	private Integer total_pages;

	public List<DataInfo> getData() {
	    return data;
	}

	public Integer getPage() {
	    return page;
	}

	public Integer getPer_page() {
	    return per_page;
	}

	public Integer getTotal() {
	    return total;
	}

	public Integer getTotal_pages() {
	    return total_pages;
	}

	public void setData(final List<DataInfo> pData) {
	    data = pData;
	}

	public void setPage(final Integer pPage) {
	    page = pPage;
	}

	public void setPer_page(final Integer pPer_page) {
	    per_page = pPer_page;
	}

	public void setTotal(final Integer pTotal) {
	    total = pTotal;
	}

	public void setTotal_pages(final Integer pTotal_pages) {
	    total_pages = pTotal_pages;
	}

	@Override public String toString() {
	    return "StockInfo{" +
			    "data=" + data +
			    ", page=" + page +
			    ", perPage=" + per_page +
			    ", total=" + total +
			    ", totalPages=" + total_pages +
			    '}';
	}
    }
}