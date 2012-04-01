import java.util.ArrayList;
/*********************************************************************
 * Copyright (c) 2010-2012 Duncan A. Buell
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
**/
public class Constants
{

/*********************************************************************
 * Text filter on precinct name.
 *
 * @return the filtered name
**/
  static public String filterPctName(String what)
  { 
    final int OLD = 0;
    final int NEW = 1;
    String local = what;
    ArrayList<String> r;
    ArrayList<ArrayList<String>> translation;

    translation = new ArrayList<ArrayList<String>>();

// begin abbeville
    r = new ArrayList<String>();
    r.add("HALL'S STORE"); r.add("HALLS STORE"); // 011
    translation.add(r);

// begin aiken
    r = new ArrayList<String>();
    r.add("AIKEN NO. 1"); r.add("AIKEN #1"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 2"); r.add("AIKEN #2"); // 002
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 3"); r.add("AIKEN #3"); // 003
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 4"); r.add("AIKEN #4"); // 004
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 5"); r.add("AIKEN #5"); // 005
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 6"); r.add("AIKEN #6"); // 006
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BATH NO. 7"); r.add("BATH"); // 007
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BEECH ISLAND 8"); r.add("BEECH ISLAND"); // 008
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BELVEDERE NO. 9"); r.add("BELVEDERE #9"); // 009
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CAROLINA HTS 10"); r.add("CAROLINA HEIGHTS"); // 010
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CHINA SPRGS 11"); r.add("CHINA SPRINGS"); // 011
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CLEARWATER 12"); r.add("CLEARWATER"); // 012
    translation.add(r);
    r = new ArrayList<String>();
    r.add("COLLEGE ACRE 13"); r.add("COLLEGE ACRES"); // 013
    translation.add(r);
    r = new ArrayList<String>();
    r.add("EUREKA NO. 14"); r.add("EUREKA"); // 014
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GLOVERVILLE 15"); r.add("GLOVERVILLE"); // 015
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GRANITEVILLE 16"); r.add("GRANITEVILLE"); // 016
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JACKSON NO. 17"); r.add("JACKSON"); // 017
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LANGLEY NO. 18"); r.add("LANGLEY"); // 018
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LYNWOOD NO. 19"); r.add("LYNWOOD"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MILLBROOK NO. 20"); r.add("MILLBROOK"); // 020
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONETTA NO. 21"); r.add("MONETTA"); // 021
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONTMORENCI 22"); r.add("MONTMORENCI #22"); // 022
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NEW ELLENTON 23"); r.add("NEW ELLENTON"); // 023
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NEW HOLLAND 24"); r.add("NEW HOLLAND"); // 024
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 25"); r.add("N. AUGUSTA #25"); // 025
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 26"); r.add("N. AUGUSTA #26"); // 026
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 27"); r.add("N. AUGUSTA #27"); // 027
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 28"); r.add("N. AUGUSTA #28"); // 028
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 29"); r.add("N. AUGUSTA #29"); // 029
    translation.add(r);
    r = new ArrayList<String>();
    r.add("OAK GROVE NO 30"); r.add("OAK GROVE"); // 030
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PERRY NO. 31"); r.add("PERRY"); // 031
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SALLEY NO. 32"); r.add("SALLEY"); // 032
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SHAWS FORK 33"); r.add("SHAWS FORK"); // 033
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SHILOH NO. 34"); r.add("SHILOH"); // 034
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SIX POINTS 35"); r.add("SIX POINTS #35"); // 035
    translation.add(r);
    r = new ArrayList<String>();
    r.add("TABERNACLE 36"); r.add("TABERNACLE"); // 036
    translation.add(r);
    r = new ArrayList<String>();
    r.add("TALATHA NO. 37"); r.add("TALATHA"); // 037
    translation.add(r);
    r = new ArrayList<String>();
    r.add("VAUCLUSE NO. 38"); r.add("VAUCLUSE"); // 038
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WAGENER NO. 39"); r.add("WAGENER"); // 039
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WARD NO. 40"); r.add("WARD"); // 040
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WARRENVILLE 41"); r.add("WARRENVILLE"); // 041
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITE POND 42"); r.add("WHITE POND"); // 042
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WINDSOR NO. 43"); r.add("WINDSOR"); // 043
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BELVEDERE NO 44"); r.add("BELVEDERE #44"); // 044
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MISTY LAKES NO 45"); r.add("MISTY LAKES"); // 045
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SIX POINTS 46"); r.add("SIX POINTS #46"); // 046
    translation.add(r);
    r = new ArrayList<String>();
    r.add("AIKEN NO. 47"); r.add("AIKEN #47"); // 047
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HAMMOND NO. 48"); r.add("HAMMOND"); // 048
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WILLOW SPRGS 49"); r.add("WILLOW SPRINGS"); // 049
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BREEZY HILL 50"); r.add("BREEZY HILL"); // 050
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MID VALLEY NO. 51"); r.add("MIDLAND VALLEY #51"); // 051
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEVELS NO. 52"); r.add("LEVELS"); // 052
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HOLLOW CREEK 53"); r.add("HOLLOW CREEK"); // 053
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 54"); r.add("N. AUGUSTA #54"); // 054
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO 55"); r.add("N. AUGUSTA #55"); // 055
    translation.add(r);
    r = new ArrayList<String>();
    r.add("COUCHTON NO. 56"); r.add("COUCHTON"); // 056
    translation.add(r);
    r = new ArrayList<String>();
    r.add("REDD'S BRANCH 57"); r.add("REDDS BRANCH"); // 057
    translation.add(r);
    r = new ArrayList<String>();
    r.add("FOX CREEK NO 58"); r.add("FOX CREEK #58"); // 058
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PINE FOREST 59"); r.add("PINE FOREST"); // 059
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GEM LAKES NO 60"); r.add("GEM LAKES"); // 060
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SILVER BLUFF 61"); r.add("SILVER BLUFF"); // 061
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BELVEDERE NO 62"); r.add("BELVEDERE #62"); // 062
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ASCAUGA LAKE 63"); r.add("ASCAUGA LAKE"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CEDAR CREEK NO. 64"); r.add("CEDAR CREEK #64"); // 064
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SLEEPY HOLLOW 65"); r.add("SLEEPY HOLLOW #65"); // 065
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HITCHCOCK NO. 66"); r.add("HITCHCOCK #66"); // 066
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO. 67"); r.add("N. AUGUSTA #67"); // 067
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N AUGUSTA NO. 68"); r.add("N. AUGUSTA #68"); // 068
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ANDERSON POND 69"); r.add("ANDERSON POND #69"); // 069
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SANDSTONE NO. 70"); r.add("SANDSTONE #70"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MID VALLEY NO. 71"); r.add("MIDLAND VALLEY #71"); // 071
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEVELS NO. 72"); r.add("LEVELS NO.72"); // 072
    translation.add(r);
    r = new ArrayList<String>();
    r.add("FOX CREEK NO. 73"); r.add("FOX CREEK #73"); // 073
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BELVEDERE NO. 74"); r.add("BELVEDERE #74"); // 074
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SOUTH AIKEN 75"); r.add("SOUTH AIKEN #75"); // 075
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SOUTH AIKEN 76"); r.add("SOUTH AIKEN #76"); // 076
    translation.add(r);

// begin allendale
    r = new ArrayList<String>();
    r.add("FAIRFAX NO.2"); r.add("FAIRFAX NO. 2"); // 004
    translation.add(r);

// begin anderson
    r = new ArrayList<String>();
    r.add("APPLETON - EQUINOX"); r.add("APPLETON EQUINOX"); // 009
    translation.add(r);

    r = new ArrayList<String>();
    r.add("BISHOPS BRANCH"); r.add("BISHOP BRANCH"); // 013
    translation.add(r);

    r = new ArrayList<String>();
    r.add("CENTERVILLE STA A"); r.add("CENTERVILLE STA. A"); // 019
    translation.add(r);

    r = new ArrayList<String>();
    r.add("EDGEWOOD STATION A"); r.add("EDGEWOOD STA. A"); // 025
    translation.add(r);

    r = new ArrayList<String>();
    r.add("FORK NO. 1"); r.add("FORK #1"); // 029
    translation.add(r);

    r = new ArrayList<String>();
    r.add("FORK NO. 2"); r.add("FORK #2"); // 030
    translation.add(r);

    r = new ArrayList<String>();
    r.add("GREEN POND STA A"); r.add("GREEN POND STA. A"); // 033
    translation.add(r);

    r = new ArrayList<String>();
    r.add("ROCK SPRING"); r.add("ROCK SPRINGS"); // 058
    translation.add(r);

    r = new ArrayList<String>();
    r.add("CENTERVILLE STA B"); r.add("CENTERVILLE STA. B"); // 091
    translation.add(r);

    r = new ArrayList<String>();
    r.add("EDGEWOOD STA B"); r.add("EDGEWOOD STA. B"); // 092
    translation.add(r);

    r = new ArrayList<String>();
    r.add("CENTER ROCK"); r.add("CENTER  ROCK"); // 157
    translation.add(r);

// begin bamberg
    r = new ArrayList<String>();
    r.add("HUNTER'S CHAPEL"); r.add("HUNTERS CHAPEL"); // 007
    translation.add(r);

// begin berkeley
    r = new ArrayList<String>();
    r.add("BOULDER BLUFF 1"); r.add("BOULDER BLUFF #1"); // 005
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CARNES CROSS RD 1"); r.add("CARNES CROSS RD #1"); // 008
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CARNES CROSS RD 2"); r.add("CARNES CROSS RD #2"); // 009
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GOOSE CREEK 1"); r.add("GOOSE CREEK #1"); // 018
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GOOSE CREEK 2"); r.add("GOOSE CREEK #2"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HANAHAN 1"); r.add("HANAHAN #1"); // 021
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HANAHAN 2"); r.add("HANAHAN #2"); // 022
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HANAHAN 3"); r.add("HANAHAN #3"); // 023
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HANAHAN 4"); r.add("HANAHAN #4"); // 024
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MCBETH"); r.add("MACBETH"); // 030
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONCKS CORNER 1"); r.add("MONCKS CORNER #1"); // 031
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONCKS CORNER 2"); r.add("MONCKS CORNER #2"); // 032
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONCKS CORNER 3"); r.add("MONCKS CORNER #3"); // 033
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MONCKS CORNER 4"); r.add("MONCKS CORNER #4"); // 034
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SANGAREE 1"); r.add("SANGAREE #1"); // 038
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SANGAREE 2"); r.add("SANGAREE #2"); // 039
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SANGAREE 3"); r.add("SANGAREE #3"); // 040
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST STEPHEN 1"); r.add("ST. STEPHEN 1"); // 042
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST STEPHEN 2"); r.add("ST. STEPHEN 2"); // 043
    translation.add(r);
    r = new ArrayList<String>();
    r.add("STRATFORD 1"); r.add("STRATFORD #1"); // 044
    translation.add(r);
    r = new ArrayList<String>();
    r.add("STRATFORD 2"); r.add("STRATFORD #2"); // 045
    translation.add(r);
    r = new ArrayList<String>();
    r.add("STRATFORD 3"); r.add("STRATFORD #3"); // 046
    translation.add(r);
    r = new ArrayList<String>();
    r.add("STRATFORD 4"); r.add("STRATFORD #4"); // 047
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WASSAMASSAW 1"); r.add("WASSAMASSAW #1"); // 048
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WASSAMASSAW 2"); r.add("WASSAMASSAW #2"); // 049
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WESTVIEW 1"); r.add("WESTVIEW #1"); // 050
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WESTVIEW 2"); r.add("WESTVIEW #2"); // 051
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WESTVIEW 3"); r.add("WESTVIEW #3"); // 052
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HOWE HALL 1"); r.add("HOWE HALL #1"); // 055
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HOWE HALL 2"); r.add("HOWE HALL #2"); // 056
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITESVILLE 1"); r.add("WHITESVILLE #1"); // 059
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITESVILLE 2"); r.add("WHITESVILLE #2"); // 060
    translation.add(r);

// begin cherokee
    r = new ArrayList<String>();
    r.add("L' JOHNS/SARRATT"); r.add("L JOHNS/SARRATT"); // 022
    translation.add(r);

// begin colleton
    r = new ArrayList<String>();
    r.add("WALTERBORO NO 1"); r.add("WALTERBORO #1"); // 029
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WALTERBORO NO 2"); r.add("WALTERBORO #2"); // 030
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WALTERBORO NO 3"); r.add("WALTERBORO #3"); // 031
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WALTERBORO NO 4"); r.add("WALTERBORO #4"); // 032
    translation.add(r);

// begin darlington
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 1"); r.add("DARLINGTON #1"); // 007
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 2"); r.add("DARLINGTON #2"); // 008
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 4"); r.add("DARLINGTON #4"); // 010
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 5"); r.add("DARLINGTON #5"); // 011
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 1"); r.add("HARTSVILLE #1"); // 013
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 4"); r.add("HARTSVILLE #4"); // 016
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 5"); r.add("HARTSVILLE #5"); // 017
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 6"); r.add("HARTSVILLE #6"); // 018
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 7"); r.add("HARTSVILLE #7"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 8"); r.add("HARTSVILLE #8"); // 020
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LAMAR NO 1"); r.add("LAMAR #1"); // 025
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LAMAR NO 2"); r.add("LAMAR #2"); // 026
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 3"); r.add("DARLINGTON #3"); // 038
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DARLINGTON NO 6"); r.add("DARLINGTON #6"); // 040
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HARTSVILLE NO 9"); r.add("HARTSVILLE #9"); // 041
    translation.add(r);

// begin dillon
    r = new ArrayList<String>();
    r.add("GADDY'S MILL"); r.add("GADDYS MILL"); // 008
    translation.add(r);

// begin edgefield
    r = new ArrayList<String>();
    r.add("EDGEFIELD NO. 1"); r.add("EDGEFIELD NO.1"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("EDGEFIELD NO. 2"); r.add("EDGEFIELD NO.2"); // 002
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JOHNSTON NO. 1"); r.add("JOHNSTON NO.1"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JOHNSTON NO. 2"); r.add("JOHNSTON NO.2"); // 002
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MERRIWEATHER 1"); r.add("MERRIWETHER 1"); // 007
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MERRIWEATHER 2"); r.add("MERRIWETHER 2"); // 008
    translation.add(r);

// begin georgetown
    r = new ArrayList<String>();
    r.add("BROWN'S FERRY"); r.add("BROWNS FERRY"); // 105
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CARVER'S BAY"); r.add("CARVERS BAY"); // 105
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GEORGETOWN #1"); r.add("GEORGETOWN 1"); // 111
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GEORGETOWN #3"); r.add("GEORGETOWN 3"); // 112
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GEORGETOWN #4"); r.add("GEORGETOWN 4"); // 113
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GEORGETOWN #5"); r.add("GEORGETOWN 5"); // 114
    translation.add(r);

// begin greenville
    r = new ArrayList<String>();
    r.add("BROOK GLENN"); r.add("BROOKGLENN"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("RIVER WALK"); r.add("RIVERWALK"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. PLEASANT"); r.add("MT PLEASANT"); // 345
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 1"); r.add("GREENVILLE 01"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 2"); r.add("GREENVILLE 02"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 3"); r.add("GREENVILLE 03"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 4"); r.add("GREENVILLE 04"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 5"); r.add("GREENVILLE 05"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 6"); r.add("GREENVILLE 06"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 7"); r.add("GREENVILLE 07"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 8"); r.add("GREENVILLE 08"); // xxx
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GREENVILLE 9"); r.add("GREENVILLE 09"); // xxx
    translation.add(r);

// begin hampton
    r = new ArrayList<String>();
    r.add("HAMPTON NO.2"); r.add("HAMPTON NO. 2"); // 003
    translation.add(r);

// begin horry
    r = new ArrayList<String>();
    r.add("CRESCENT"); r.add("CRESENT"); // 119
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GALIVANTS FERRY"); r.add("GALLIVANTS FERRY"); // 137
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JERNIGANS X ROADS"); r.add("JERIGANS CROSSROADS"); // 152
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MARLOWE #1"); r.add("MARLOWE"); // 165
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NIXON X ROADS #1"); r.add("NIXONS XROADS #1"); // 174
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NIXON X ROADS #2"); r.add("NIXONS XROADS #2"); // 175
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WINDY HILL 1"); r.add("WINDY HILL #1"); // 217
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WINDY HILL 2"); r.add("WINDY HILL #2"); // 218
    translation.add(r);

// begin kershaw
    r = new ArrayList<String>();
    r.add("DOBY'S MILL"); r.add("DOBYS MILL"); // 119
    translation.add(r);
    r = new ArrayList<String>();
    r.add("RABON'S CROSSROADS"); r.add("RABONS CROSSROADS"); // 143
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SHAYLOR'S HILL"); r.add("SHAYLORS HILL"); // 149
    translation.add(r);

// begin laurens
    r = new ArrayList<String>();
    r.add("MARTINS - POPLAR SPRING"); r.add("MARTINS-POPLAR SPRING"); // 119
    translation.add(r);

// begin lee
    r = new ArrayList<String>();
    r.add("MT CLIO"); r.add("MT. CLIO"); // 014
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST CHARLES"); r.add("ST. CHARLES"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST MATTHEWS"); r.add("ST. MATTHEWS"); // 020
    translation.add(r);

// begin lexington
    r = new ArrayList<String>();
    r.add("LAKE MURRAY #1"); r.add("LAKE MURRAY NO. 1"); // 003
    translation.add(r);
    r = new ArrayList<String>();
    r.add("OLD BARNWELL RD"); r.add("OLD BARNWELL ROAD"); // 005
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BEULAH CHURCH"); r.add("BEULAH  CHURCH"); // 006
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PELION #1"); r.add("PELIAN NO. 1"); // 022
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SWANSEA #1"); r.add("SWANSEA NO. 1"); // 024
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MACK - EDISTO"); r.add("MACK-EDISTO"); // 025
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GASTON #1"); r.add("GASTON 1"); // 026
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEAPHART ROAD"); r.add("LEAPHART RD"); // 029
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEXINGTON NO. 2"); r.add("LEXINGTON  NO. 2"); // 034
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CAYCE WARD NO. 1"); r.add("CAYCE NO. 1"); // 035
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CAYCE WARD NO. 2"); r.add("CAYCE NO. 2"); // 036
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CAYCE WARD NO. 3"); r.add("CAYCE NO. 3"); // 037
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CONGAREE #1"); r.add("CONGAREE NO. 1"); // 039
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PINERIDGE #1"); r.add("PINE RIDGE 1"); // 043
    translation.add(r);
    r = new ArrayList<String>();
    r.add("W COLUMBIA NO 1"); r.add("WEST COLUMBIA NO. 1"); // 044
    translation.add(r);
    r = new ArrayList<String>();
    r.add("W COLUMBIA NO 2"); r.add("WEST COLUMBIA NO. 2"); // 045
    translation.add(r);
    r = new ArrayList<String>();
    r.add("W COLUMBIA NO 3"); r.add("WEST COLUMBIA NO. 3"); // 046
    translation.add(r);
    r = new ArrayList<String>();
    r.add("W COLUMBIA NO 4"); r.add("WEST COLUMBIA NO. 4"); // 047
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CAYCE 2-A"); r.add("CAYCE 2A"); // 055
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BARR ROAD #1"); r.add("BARR ROAD 1"); // 056
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST. MICHAEL"); r.add("ST. MICHAELS"); // 059
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PILGRIM CHURCH"); r.add("PILGRIM CHRUCH"); // 064
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEXINGTON #3"); r.add("LEXINGTON NO. 3"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LEXINGTON #4"); r.add("LEXINGTON NO. 4"); // 071
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PARK ROAD #1"); r.add("PARK ROAD 1"); // 073
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GASTON #2"); r.add("GASTON 2"); // 075
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LAKE MURRAY #2"); r.add("LAKE MURRAY NO. 2"); // 076
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CONGAREE #2"); r.add("CONGAREE NO. 2"); // 076
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST. DAVIDS"); r.add("SAINT DAVIDS"); // 078
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SANDHILL"); r.add("SAND HILL"); // 080
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SWANSEA #2"); r.add("SWANSEA NO. 2"); // 082
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PELION #2"); r.add("PELION NO. 2"); // 083
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BARR ROAD #2"); r.add("BARR ROAD 2"); // 089
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PARK ROAD #2"); r.add("PARK ROAD 2"); // 090
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PINERIDGE #2"); r.add("PINE RIDGE 2"); // 091
    translation.add(r);
    r = new ArrayList<String>();
    r.add("RED BANK SOUTH #1"); r.add("RED BANK SOUTH 1"); // 013
    translation.add(r);
    r = new ArrayList<String>();
    r.add("RED BANK SOUTH #2"); r.add("RED BANK SOUTH 2"); // 092
    translation.add(r);

// begin marion
    r = new ArrayList<String>();
    r.add("BRITTON'S NECK"); r.add("BRITTONS NECK"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N WEST MULLINS"); r.add("NORTHWEST MULLINS"); // 013
    translation.add(r);
    r = new ArrayList<String>();
    r.add("S WEST MULLINS"); r.add("SOUTHWEST MULLINS"); // 014
    translation.add(r);
    r = new ArrayList<String>();
    r.add("N EAST MULLINS"); r.add("NORTHEAST MULLINS"); // 015
    translation.add(r);
    r = new ArrayList<String>();
    r.add("S EAST MULLINS"); r.add("SOUTHEAST MULLINS"); // 016
    translation.add(r);

// begin marlboro
    r = new ArrayList<String>();
    r.add("QUICKS  X  ROADS"); r.add("QUICKS X ROADS"); // 008
    translation.add(r);

// begin mccormick
    r = new ArrayList<String>();
    r.add("MT. CARMEL"); r.add("MT CARMEL"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CLARK'S HILL"); r.add("CLARKS HILL"); // 015
    translation.add(r);

// begin newberry
    r = new ArrayList<String>();
    r.add("MT.  BETHEL GARMANY"); r.add("MT BETHEL GARMANY"); // 027
    translation.add(r);
    r = new ArrayList<String>();
    r.add("O'NEAL"); r.add("ONEAL"); // 030
    translation.add(r);

// begin oconee
    r = new ArrayList<String>();
    r.add("NEWRY CORINTH"); r.add("NEWRY CORNITH"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WESTMINSTER 2"); r.add("WESTMINSTER"); // 027
    translation.add(r);

// begin sumter
    r = new ArrayList<String>();
    r.add("MCCRAY'S MILL #1"); r.add("MCCRAYS MILL #1"); // 111
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST.  JOHN"); r.add("ST JOHN"); // 112
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BURNS-DOWNS"); r.add("BURNS-DOWN"); // 120
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LERMIRA"); r.add("LEMIRA"); // 127
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ST. PAUL"); r.add("ST PAUL"); // 152
    translation.add(r);

// begin york
    r = new ArrayList<String>();
    r.add("MT. GALLANT"); r.add("MT GALLANT"); // 023
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. HOLLY"); r.add("MT HOLLY"); // 025
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PLEASANT RD"); r.add("PLEASANT ROAD"); // 065
    translation.add(r);

// zork

// begin spartanburg
    r = new ArrayList<String>();
    r.add("WOODRUFF ARMORY DRIVE FS"); r.add("WOODRUFF ARMORY DRIVE"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODRUFF ARMORY DRIV"); r.add("WOODRUFF ARMORY DRIVE"); // 001
    translation.add(r);
    r = new ArrayList<String>();
    r.add("REBIRTH MISSIONARY BAPTIST"); r.add("REBIRTH MISSIONARY BAPT"); // 004
    translation.add(r);
    r = new ArrayList<String>();
    r.add("REBIRTH MISSIONARY B"); r.add("REBIRTH MISSIONARY BAPT"); // 004
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. SINAI BAPTIST"); r.add("MT SINAI BAPT"); // 007
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNT SINAI BAPTIST"); r.add("MT SINAI BAPT"); // 007
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS 9TH"); r.add("BOILING SPRINGS 9TH GRADE"); // 008
    translation.add(r);
    r = new ArrayList<String>();
    r.add("COOLEY SPRINGS BAPTI"); r.add("COOLEY SPRINGS BAPTIST"); // 012
    translation.add(r);
    r = new ArrayList<String>();
    r.add("E.P. TODD ELEMENTARY"); r.add("EP TODD ELEMENTARY"); // 018
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CHEROKEE SPRINGS FIRE STATION"); r.add("CHEROKEE SPRINGS FS"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CHEROKEE SPRINGS FIRE"); r.add("CHEROKEE SPRINGS FS"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CHEROKEE SPRINGS FIR"); r.add("CHEROKEE SPRINGS FS"); // 019
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CHESNEE SENIOR CENTE"); r.add("CHESNEE SENIOR CENTER"); // 020
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CONVERSE FIRE STATIO"); r.add("CONVERSE FS"); // 024
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CONVERSE FIRE STATION"); r.add("CONVERSE FS"); // 024
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODRUFF FIRE STATIO"); r.add("WOODRUFF FS"); // 027
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODRUFF FIRE STATION"); r.add("WOODRUFF FS"); // 027
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CROSS ANCHOR FIRE ST"); r.add("CROSS ANCHOR FS"); // 028
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CROSS ANCHOR FIRE STATION"); r.add("CROSS ANCHOR FS"); // 028
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NORTH SPARTANBURG #"); r.add("NORTH SPARTANBURG FS"); // 029
    translation.add(r);
    r = new ArrayList<String>();
    r.add("NORTH SPARTANBURG # 2"); r.add("NORTH SPARTANBURG FS"); // 029
    translation.add(r);
    r = new ArrayList<String>();
    r.add("FAIRFOREST MIDDLE SC"); r.add("FAIRFOREST MIDDLE SCHOOL"); // 035
    translation.add(r);
    r = new ArrayList<String>();
    r.add("TRAVELERS REST BAPTI"); r.add("TRAVELERS REST BAPT"); // 036
    translation.add(r);
    r = new ArrayList<String>();
    r.add("TRAVELERS REST BAPTIST"); r.add("TRAVELERS REST BAPT"); // 036
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GLENDALE FIRE STATIO"); r.add("GLENDALE FS"); // 038
    translation.add(r);
    r = new ArrayList<String>();
    r.add("GLENDALE FIRE STATION"); r.add("GLENDALE FS"); // 038
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HOLLY SPRINGS BAPTIS"); r.add("HOLLY SPRINGS BAPT"); // 044
    translation.add(r);
    r = new ArrayList<String>();
    r.add("HOLLY SPRINGS BAPTIST"); r.add("HOLLY SPRINGS BAPT"); // 044
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LANDRUM UNITED METHO"); r.add("LANDRUM UNITED METH"); // 050
    translation.add(r);
    r = new ArrayList<String>();
    r.add("LANDRUM UNITED METHODIST"); r.add("LANDRUM UNITED METH"); // 050
    translation.add(r);
    r = new ArrayList<String>();
    r.add("R.D. ANDERSON VOCATIONAL"); r.add("RD ANDERSON VOCATIONAL"); // 056
    translation.add(r);
    r = new ArrayList<String>();
    r.add("RD ANDERSON VOCATION"); r.add("RD ANDERSON VOCATIONAL"); // 056
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SWOFFORD CAREER CENT"); r.add("SWOFFORD CAREER CENTER"); // 057
    translation.add(r);
    r = new ArrayList<String>();
    r.add("T.W. EDWARDS RECREATION CENTER"); r.add("TW EDWARDS RECREATION"); // 058
    translation.add(r);
    r = new ArrayList<String>();
    r.add("T.W. EDWARDS RECREATION"); r.add("TW EDWARDS RECREATION"); // 058
    translation.add(r);
    r = new ArrayList<String>();
    r.add("T.W. EDWARDS REC CENTER"); r.add("TW EDWARDS RECREATION"); // 058
    translation.add(r);
    r = new ArrayList<String>();
    r.add("TW EDWARDS RECREATIO"); r.add("TW EDWARDS RECREATION"); // 058
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PAULINE GLENN SPRINGS ELEM"); r.add("PAULINE GLENN SPRINGS"); // 060
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PAULINE GLENN SPRING"); r.add("PAULINE GLENN SPRINGS"); // 060
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PAULINE GLENN SPRINGS ELTY"); r.add("PAULINE GLENN SPRINGS"); // 060
    translation.add(r);
    r = new ArrayList<String>();
    r.add("POPLAR SPRINGS FIRE"); r.add("POPLAR SPRINGS FS"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("POPLAR SPRINGS FIRE STATION"); r.add("POPLAR SPRINGS FS"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("POWELL SAXON UNA FIR"); r.add("POWELL SAXON UNA FS"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("POWELL SAXON UNA FIRE"); r.add("POWELL SAXON UNA FS"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("POWELL SAXON UNA FIRE STATION"); r.add("POWELL SAXON UNA FS"); // 063
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. MORIAH BAPTIST"); r.add("MOUNT MORIAH BAPTIST"); // 068
    translation.add(r);
    r = new ArrayList<String>();
    r.add("C.C. WOODSON RECREATION"); r.add("CC WOODSON RECREATION"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("C C WOODSON RECREATION"); r.add("CC WOODSON RECREATION"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("C C WOODSON RECREATIO"); r.add("CC WOODSON RECREATION"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("C C WOODSON RECREATI"); r.add("CC WOODSON RECREATION"); // 070
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PINE STREET ELEMENTARY"); r.add("PINE STREET ELEM"); // 072
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PINE STREET ELEMENTA"); r.add("PINE STREET ELEM"); // 072
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SPARTANBURG HIGH SCH"); r.add("SPARTANBURG HIGH SCHOOL"); // 076
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PARK HILLS ELEMENTAR"); r.add("PARK HILLS ELEM"); // 080
    translation.add(r);
    r = new ArrayList<String>();
    r.add("PARK HILLS ELEMENTARY"); r.add("PARK HILLS ELEM"); // 080
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SILVERHILL UNITED ME"); r.add("SILVERHILL UNITED METH"); // 081
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SILVERHILL UNITED MET"); r.add("SILVERHILL UNITED METH"); // 081
    translation.add(r);
    r = new ArrayList<String>();
    r.add("SILVERHILL UNITED METHODIST"); r.add("SILVERHILL UNITED METH"); // 081
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODLAND HEIGHTS REC CENTER"); r.add("WOODLAND HEIGHTS RECREATION"); // 082
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODLAND HEIGHTS REC"); r.add("WOODLAND HEIGHTS RECREATION"); // 082
    translation.add(r);
    r = new ArrayList<String>();
    r.add("UNA FIRE"); r.add("UNA FS"); // 093
    translation.add(r);
    r = new ArrayList<String>();
    r.add("UNA FIRE STATION"); r.add("UNA FS"); // 093
    translation.add(r);
    r = new ArrayList<String>();
    r.add("VICTOR MILL METHODIST"); r.add("VICTOR MILL METH"); // 095
    translation.add(r);
    r = new ArrayList<String>();
    r.add("VICTOR MILL METHODIS"); r.add("VICTOR MILL METH"); // 095
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. CALVARY PRESBYTERIAN"); r.add("MT CALVARY PRES"); // 096
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNT CALVARY PRES"); r.add("MT CALVARY PRES"); // 096
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNT CALVARY PRESBY"); r.add("MT CALVARY PRES"); // 096
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WELLFORD FIRE STATION"); r.add("WELLFORD"); // 097
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WELLFORD FIRE"); r.add("WELLFORD"); // 097
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITLOCK JR. HIGH"); r.add("WHITLOCK JUNIOR HIGH"); // 103
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WOODRUFF AMERICAN LE"); r.add("WOODRUFF AMERICAN LEGION"); // 103
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNT ZION FG BAPTIST"); r.add("MT ZION FG BAPT"); // 106
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNT ZION FG BAPTIS"); r.add("MT ZION FG BAPT"); // 106
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MT. ZION FULL GOSPEL BAPTIST"); r.add("MT ZION FG BAPT"); // 106
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JESSE BOBO ELEMENTAR"); r.add("JESSE BOBO ELEM"); // 107
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JESSE BOBO ELEMENTARY"); r.add("JESSE BOBO ELEM"); // 107
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITE STONE METHODIS"); r.add("WHITE STONE METH"); // 108
    translation.add(r);
    r = new ArrayList<String>();
    r.add("WHITE STONE METHODIST"); r.add("WHITE STONE METH"); // 108
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS INTE"); r.add("BOILING SPRINGS INTERMED"); // 112
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS INTERMEDIATE"); r.add("BOILING SPRINGS INTERMED"); // 112
    translation.add(r);
    r = new ArrayList<String>();
    r.add("CARLISLE FOSTERS GRO"); r.add("CARLISLE FOSTERS GROVE"); // 113
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BEECH SPRINGS INTERMEDIATE"); r.add("BEECH SPRINGS INTERMED"); // 115
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BEECH SPRINGS INTERM"); r.add("BEECH SPRINGS INTERMED"); // 115
    translation.add(r);
    r = new ArrayList<String>();
    r.add("REIDVILLE FIRE STATI"); r.add("REIDVILLE FS"); // 118
    translation.add(r);
    r = new ArrayList<String>();
    r.add("REIDVILLE FIRE STATION"); r.add("REIDVILLE FS"); // 118
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JESSE BOYD ELEMENTAR"); r.add("JESSE BOYD ELEM"); // 120
    translation.add(r);
    r = new ArrayList<String>();
    r.add("JESSE BOYD ELEMENTARY"); r.add("JESSE BOYD ELEM"); // 120
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ANDERSON MILL ELEMENTARY"); r.add("ANDERSON MILL ELEM"); // 121
    translation.add(r);
    r = new ArrayList<String>();
    r.add("ANDERSON MILL ELEMEN"); r.add("ANDERSON MILL ELEM"); // 121
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DANIEL MORGAN TECH CENTER"); r.add("DANIEL MORGAN TECH"); // 122
    translation.add(r);
    r = new ArrayList<String>();
    r.add("DANIEL MORGAN TECHNO"); r.add("DANIEL MORGAN TECH"); // 122
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS ELEMENTARY"); r.add("BOILING SPRINGS ELEM"); // 123
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS ELEMENTAR"); r.add("BOILING SPRINGS ELEM"); // 123
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS JUNI"); r.add("BOILING SPRINGS JUNIOR"); // 124
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS JR. HIGH"); r.add("BOILING SPRINGS JUNIOR"); // 124
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNTAIN VIEW BAPTIST"); r.add("MOUNTAIN VIEW BAPT"); // 126
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNTAIN VIEW BAPTIS"); r.add("MOUNTAIN VIEW BAPT"); // 126
    translation.add(r);
    r = new ArrayList<String>();
    r.add("MOUNTAIN VIEW BAPTI"); r.add("MOUNTAIN VIEW BAPT"); // 126
    translation.add(r);
    r = new ArrayList<String>();
    r.add("BOILING SPRINGS HIGH SCHOOL"); r.add("BOILING SPRINGS HIGH"); // 127
    translation.add(r);
    r = new ArrayList<String>();
    r.add("EP TODD"); r.add("EP TODD ELEMENTARY"); // 127
    translation.add(r);
// end spartanburg

    for(int i = 0; i < translation.size(); ++i)
    {
// FileUtils.logFile.printf("zork %s%n", local.trim());
      if(local.trim().equals(translation.get(i).get(OLD)))
        local = translation.get(i).get(NEW);
    }
    local = local.replace(" ","_");

/*
    local = local.replace("#","");
    local = local.replace("NO.","");
    local = local.replace(" NO "," ");
    local = local.replace("WEST","W");
    local = local.replace("  "," ");
    local = local.replace("  "," ");

    local = local.replace("ROAD","RD");

    local = local.replace("ST. JOHN","ST JOHN"); // sumter
    local = local.replace("ST. PAUL","ST PAUL"); // sumter
    local = local.replace("BURNS-DOWNS","BURNS-DOWN"); // sumter
    local = local.replace("MCCRAY'S MILL","MCCRAY MILL"); // sumter
    local = local.replace("MCCRAYS MILL","MCCRAY MILL"); // sumter
    local = local.replace("LERMIRA","LEMIRA"); // sumter




    local = local.replace("APPLETON - EQUINOX","APPLETON EQUINOX"); // horry
    local = local.replace("BISHOPS BRANCH","BISHOP BRANCH"); // horry
    local = local.replace("CENTERVILLE STA. A","CENTERVILLE STA A"); // horry
    local = local.replace("CENTERVILLE STA. B","CENTERVILLE STA B"); // horry
    local = local.replace("EDGEWOOD STA. A","EDGEWOOD STA A"); // horry
    local = local.replace("EDGEWOOD STA. B","EDGEWOOD STA B"); // horry
    local = local.replace("GREEN POND STA. A","GREEN POND STA A"); // horry
    local = local.replace("ROCK SPRINGS","ROCK SPRING"); // horry

    local = local.replace("MACBETH","MCBETH"); // berkeley
    local = local.replace("ST. STEPHEN","ST STEPHEN"); // berkeley

*/
    return local;
  } // static public String filterPctName(String what)

}
