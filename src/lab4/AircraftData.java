package lab4;


import messer.ADSBAirbonePositionMessage;
import messer.ADSBAirboneVelocityMessage;
import messer.ADSBAircraftIdentificationMessage;
import messer.ADSBMessage;


public class AircraftData {

    public int lastEvenorOdd;
    public ADSBAirbonePositionMessage positionMessageEven;
    public ADSBAirbonePositionMessage positionMessageOdd;
    public ADSBAirboneVelocityMessage velocityMessage;
    public ADSBAircraftIdentificationMessage identificationMessage;
    public ADSBMessage otherMessage;

    // create kml String of Object
    public String toKml() {
        //check if enough messages came in
        if( positionMessageOdd == null || positionMessageEven == null || velocityMessage == null)
            return "";

        double lat ;
        try {
            lat = decodeCprLatitude();
        } catch (Exception e) {
            return "";
        }

        double lon = decodeCprLongtitude(lat);


        return  "<Style id=\"" + positionMessageOdd.getIcao() + "\">\n" +
                "<IconStyle>\n" +
                "<scale>0.7</scale>\n" +
                "<heading>" + velocityMessage.getHeading() + "</heading>\n" +
                "<Icon>\n" +
                "<href>http://localhost:3333/icons/plane09.png</href>\n" + //TODO: calc out of heading which planeicon to use
                "</Icon>\n" +
                "</IconStyle>\n" +
                "</Style>\n" +
                "<Placemark>\n" +
                "<name>" + positionMessageOdd.getIcao() + "</name>\n" +
                "<description>\n" +
                        "EZY19PE Lon: 9.645923815275493 Lat: 49.78056335449219 Alt: 11292m Dir: 137deg Vel: 483kn Clm: 0ft/min\n" +
                "</description>\n" +
                "<styleUrl>#" + positionMessageOdd.getIcao() + "</styleUrl>\n" +
                "<Point>\n" +
                    "<coordinates>" + lat + "," + lon +" ," + positionMessageEven.getAltitude() +"</coordinates>\n" +
                "<altitudeMode>relativeToGround</altitudeMode>\n" +
                "<extrude>1</extrude>\n" +
                "</Point>\n" +
                "</Placemark>\n";
    }

    public double decodeCprLongtitude(double lat) {
        int lon0 = positionMessageEven.getCprLongtitude();
        int lon1 = positionMessageOdd.getCprLongtitude();

        int nl = nlLookup(lat);

        int m = (int) Math.floor(( (double) ( (nl-1) * lon0 - nl * lon1) / 131072.0 ) +0.5);

        double rlon0 = dlon(0, nl) * (mod(m,nl       ) + (double) lon0/131072.0);
        double rlon1 = dlon(1, nl) * (mod(m,nl - 1) + (double) lon0/131072.0);

        if(lastEvenorOdd == 0)
            return rlon0;
        else
            return rlon1;
    }

    private double decodeCprLatitude() throws Exception {

        int lat0 = positionMessageEven.getCprLatitude();
        int lat1 = positionMessageOdd.getCprLatitude();

        int j = (int ) Math.floor(( (double) (59 * lat0 - 60 * lat1) / 131072.0) + 0.5);
        double rlat1 = dlat(1) * (mod(j, 60 - 1) + (double) lat1/131072.0);
        double rlat0 = dlat(0) * (mod(j, 60    ) + (double) lat0/131072.0);

        if (nlLookup(rlat0) != nlLookup(rlat1)){
            System.out.println("nl not equal");
            throw new Exception("nl not equal");
        }


        if(lastEvenorOdd == 0)
            return rlat0;
        else
            return rlat1;
    }

    private static double dlat(int evenodd) {
        return 360.0/(4 * 15 - evenodd);
    }

    private static double dlon(int evenodd, int nl) {
        return 360.0/(Math.max(nl - evenodd, 1));
    }

    private static int mod(int x, int y) {
        return x - y * (x/y);
    }

    private static int nlLookup(double latitude) {
        double lat = Math.abs(latitude);
        if (lat < 10.47047130)
            return 59;
        else if (lat < 14.82817437)
            return 58;
        else if (lat < 18.18626357)
            return 57;
        else if (lat < 21.02939493)
            return 56;
        else if (lat < 23.54504487)
            return 55;
        else if (lat < 25.82924707)
            return 54;
        else if (lat < 27.93898710)
            return 53;
        else if (lat < 29.911356862)
            return 52;
        else if (lat < 31.77209708)
            return 51;
        else if (lat < 33.53993436)
            return 50;
        else if (lat < 35.22899598)
            return 49;
        else if (lat < 36.85025108)
            return 48;
        else if (lat < 38.41241892)
            return 47;
        else if (lat < 39.92256684)
            return 46;
        else if (lat < 41.38651832)
            return 45;
        else if (lat < 42.80914012)
            return 44;
        else if (lat < 44.19454951)
            return 43;
        else if (lat < 45.54626723)
            return 42;
        else if (lat < 46.86733252)
            return 41;
        else if (lat < 48.16039128)
            return 40;
        else if (lat < 49.42776439)
            return 39;
        else if (lat < 50.67150166)
            return 38;
        else if (lat < 51.89342469)
            return 37;
        else if (lat < 53.09516153)
            return 36;
        else if (lat < 54.27817472)
            return 35;
        else if (lat < 55.44378444)
            return 34;
        else if (lat < 56.59318756)
            return 33;
        else if (lat < 57.72747354)
            return 32;
        else if (lat < 58.84763776)
            return 31;
        else if (lat < 59.95459277)
            return 30;
        else if (lat < 61.04917774)
            return 29;
        else if (lat < 62.13216659)
            return 28;
        else if (lat < 63.20427479)
            return 27;
        else if (lat < 64.26616523)
            return 26;
        else if (lat < 65.31845310)
            return 25;
        else if (lat < 66.36171008)
            return 24;
        else if (lat < 67.39646774)
            return 23;
        else if (lat < 68.42322022)
            return 22;
        else if (lat < 69.44242631)
            return 21;
        else if (lat < 70.45451075)
            return 20;
        else if (lat < 71.45986473)
            return 19;
        else if (lat < 72.45884545)
            return 18;
        else if (lat < 73.45177442)
            return 17;
        else if (lat < 74.43893416)
            return 16;
        else if (lat < 75.42056257)
            return 15;
        else if (lat < 76.39684391)
            return 14;
        else if (lat < 77.36789461)
            return 13;
        else if (lat < 78.33374083)
            return 12;
        else if (lat < 79.29428225)
            return 11;
        else
            // north/south of 80.24923213
            return 10;
    }
}
