package dateFormat;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
	//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
	//
	//        int datesCount = Integer.parseInt(bufferedReader.readLine().trim());
	//
	//        List<String> dates = IntStream.range(0, datesCount).mapToObj(i -> {
	//            try {
	//                return bufferedReader.readLine();
	//            } catch (IOException ex) {
	//                throw new RuntimeException(ex);
	//            }
	//        })
	//            .collect(toList());

	final List<String> dates = Arrays.asList(
			"20th Oct 2052",
			"6th Jun 1933",
			"26th May 1960",
			"20th Sep 1958",
			"16th Mar 2068",
			"25th May 1912",
			"16th Dec 2018",
			"26th Dec 2061",
			"4th Nov 2030",
			"28th Jul 1963"
	);
	List<String> result = Result.reformatDate(dates);

	//        bufferedWriter.write(
	//            result.stream()
	//                .collect(joining("\n"))
	//            + "\n"
	//        );
	//
	//        bufferedReader.close();
	//        bufferedWriter.close();
    }
}

class Result {

    /*
     * Complete the 'reformatDate' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY dates as parameter.
     */

    public static List<String> reformatDate(List<String> dates) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.US);
	SimpleDateFormat simpleDateFormatFinal = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

	List<String> result = new ArrayList<>();
	for (String date : dates) {
	    // Convert ordinal numbers to numbers
	    final String strDateTest = date.replaceAll("(\\d)+[a-zA-Z]+", "");
	    try {
		// Convert String to LocalDateTime
		final Date formatedDate = simpleDateFormat.parse(strDateTest);
		// Add into the list
		result.add(simpleDateFormatFinal.format(formatedDate));
	    } catch (ParseException pE) {
		pE.printStackTrace();
	    }
	}
	System.out.println(result.toString());
	// Write your code here
	return result;
    }

}