import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class csvReader {

    public static HashMap<Integer, String> csvRead(File airportLookup, HashMap<Integer, String> toConvert) {

        HashMap<Integer, String> converted = new HashMap<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(airportLookup))) {

            String headerLine = bf.readLine();
            String[] headers = parseCsvLine(headerLine);
            if (headers.length < 6) {
                System.out.println("Airport lookup malformed");
                System.exit(0);
            }

            HashMap<String, Integer> columnMap = new HashMap<>();
            for (int j = 0; j < headers.length; j++) {
                if (headers[j].isEmpty()) {
                    System.out.println("Airport lookup malformed");
                    System.exit(0);
                }
                columnMap.put(headers[j], j);
            }

            if (!columnMap.containsKey("name") ||
                !columnMap.containsKey("iso_country") ||
                !columnMap.containsKey("icao_code") ||
                !columnMap.containsKey("iata_code") ||
                !columnMap.containsKey("municipality") ||
                !columnMap.containsKey("coordinates")) {
                System.out.println("Airport lookup malformed");
                System.exit(0);
            }

            int nameIndex        = columnMap.get("name");
            int isoCountryIndex  = columnMap.get("iso_country");
            int icaoIndex        = columnMap.get("icao_code");
            int iataIndex        = columnMap.get("iata_code");
            int cityIndex        = columnMap.get("municipality");
            int coordinatesIndex = columnMap.get("coordinates");

            HashMap<String, String> lookup     = new HashMap<>();
            HashMap<String, String> cityLookup = new HashMap<>();

            String line;
            while ((line = bf.readLine()) != null) {

                String[] fields     = parseCsvLine(line);
                String iataCode     = fields[iataIndex];
                String icaoCode     = fields[icaoIndex];
                String city         = fields[cityIndex];
                String name         = fields[nameIndex];
                String coordinates  = fields[coordinatesIndex];
                String isoCountry   = fields[isoCountryIndex];

                if (name.isEmpty() || iataCode.isEmpty() || icaoCode.isEmpty() ||
                    city.isEmpty() || coordinates.isEmpty() || isoCountry.isEmpty()) {
                    System.out.println("Airport lookup malformed");
                    System.exit(0);
                }

                lookup.put(iataCode, name);
                lookup.put(icaoCode, name);
                cityLookup.put(iataCode, city);
                cityLookup.put(icaoCode, city);

            }

            for (int i = 0; i < toConvert.size(); i++) {
                String token = toConvert.get(i);

                if (token.startsWith("*##")) {
                    String code = stripTrailingPunct(token.substring(3));
                    String city = cityLookup.get(code);
                    converted.put(i, city != null ? city : token.substring(0, 3 + code.length()));

                } else if (token.startsWith("*#")) {
                    String code = stripTrailingPunct(token.substring(2));
                    String city = cityLookup.get(code);
                    converted.put(i, city != null ? city : token.substring(0, 2 + code.length()));

                } else if (token.startsWith("##")) {
                    if (token.length() < 6) { converted.put(i, stripTrailingPunct(token)); continue; }
                    String code = token.substring(2, 6);
                    String name = lookup.get(code);
                    converted.put(i, name != null ? name : stripTrailingPunct(token));

                } else if (token.startsWith("#")) {
                    if (token.length() < 4) { converted.put(i, stripTrailingPunct(token)); continue; }
                    String code = token.substring(1, 4);
                    String name = lookup.get(code);
                    converted.put(i, name != null ? name : stripTrailingPunct(token));
                }

            }

        } catch (IOException e) {
            System.out.println("Airport lookup not found");
        }

        return converted;

    }

    private static String stripTrailingPunct(String s) {
        int k = s.length() - 1;
        while (k >= 0 && !Character.isLetterOrDigit(s.charAt(k)) && s.charAt(k) != '#' && s.charAt(k) != '*') k--;
        return s.substring(0, k + 1);
    }

    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        fields.add(sb.toString());
        return fields.toArray(new String[0]);
    }

}
