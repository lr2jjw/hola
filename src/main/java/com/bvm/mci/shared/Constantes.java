
package com.bvm.mci.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bvm.mci.dto.AjusteDerechoKey;
import com.bvm.mci.dto.ComboBoxValues;

public class Constantes {
        /**
         * Windows desktop
         */
        public static final String EXITO = "EXITO";
        public static final String SHORTCUT_EJEMPLO = "Shortcut de ejemplo";
        public static final String SCEJEMPLOID = "scEjemploId";
        public static final String STITULO_MENU = "Administraci&oacute;n de Indices";
        public static final String STITULO_BOTON_INICIO = "Inicio";
        public static final String PATH = "/api/";

        /**
         * Ventana de ejemplo
         */
        public static final String SVENTANA_EJEMPLO_TITULO = "Ventana de ejemplo";

        /**
         * Propiedades generales de ventanas
         */
        public static final boolean VENTANA_SHADOW = false;
        public static final int VENTANA_SHADOWOFFSET = 0;
        public static final boolean VENTANA_ANIMCOLLAPSE = true;
        public static final boolean VENTANA_COLLAPSIBLE = true;
        public static final boolean VENTANA_MAXIMIZABLE = true;
        public static final boolean VENTANA_MINIZABLE = true;
        public static final int VENTANA_ANCHO = 1100;
        public static final int VENTANA_ALTO = 630;
        // public static final Scroll VENTANA_SCROLLMODE = Style.Scroll.AUTO;

        /**
         * Propiedades de los servicios ServerPush
         */
        // public static final Domain DOMINIO_AVISO_DIFERENCIAS_VALIDACIONES =
        // DomainFactory.getDomain("dominio_aviso_diferencias_validaciones");
        // Propiedades de los servicios ServerPush

        public static final String DOMINIO_AVISO_DIFERENCIAS_VALIDACIONES = "dominio_aviso_diferencias_validaciones"; // Cambiado
                                                                                                                      // a
                                                                                                                      // String
        /**
         * Valores ISO
         */
        /*
         * public static final List<ComboBoxValues> SISOCURRENCYCODES = new
         * ArrayList<ComboBoxValues>( Arrays.asList( new
         * ComboBoxValues("ADP","Andoran peseta (ADP)"), new
         * ComboBoxValues("AED","United Arab Emirates Dirham (AED)"), new
         * ComboBoxValues("AFA","Afghani (AFA)"), new
         * ComboBoxValues("ALL","Albanian Lek (ALL)"), new
         * ComboBoxValues("AMD","Armenian Dram (AMD)"), new
         * ComboBoxValues("ANG","West Indian Guilder (ANG)"), new
         * ComboBoxValues("AOK","Angolan Kwanza (AOK)"), new
         * ComboBoxValues("ARA","Argentinian Austral (ARA)"), new
         * ComboBoxValues("ARS","Argentina Peso (ARS)"), new
         * ComboBoxValues("ATS","Austrian Schilling (ATS)"), new
         * ComboBoxValues("AUD","Australian Dollar (AUD)"), new
         * ComboBoxValues("AWG","Aruban Guilder (AWG)"), new
         * ComboBoxValues("AZM","Azerbaijan Manat (AZM)"), new
         * ComboBoxValues("BAD","Bosnia (BAD)"), new
         * ComboBoxValues("BBD","Barbados Dollar (BBD)"), new
         * ComboBoxValues("BDT","Bangladesh Taka (BDT)"), new
         * ComboBoxValues("BEF","Belgian Franc (BEF)"), new
         * ComboBoxValues("BGL","Bulgarian Lev (BGL)"), new
         * ComboBoxValues("BHD","Bahrain Dinar (BHD)"), new
         * ComboBoxValues("BIF","Burundi Franc (BIF)"), new
         * ComboBoxValues("BMD","Bermudan Dollar (BMD)"), new
         * ComboBoxValues("BND","Brunei Dollar (BND)"), new
         * ComboBoxValues("BOB","Bolivian Boliviano (BOB)"), new
         * ComboBoxValues("BRL","Brazilian Real (BRL)"), new
         * ComboBoxValues("BRR","Brazil (BRR)"), new
         * ComboBoxValues("BSD","Bahaman Dollar (BSD)"), new
         * ComboBoxValues("BWP","Botswana Pula (BWP)"), new
         * ComboBoxValues("BYR","Belorussian Ruble (BYR)"), new
         * ComboBoxValues("BZD","Belize Dollar (BZD)"), new
         * ComboBoxValues("CAD","Canadian Dollar (CAD)"), new
         * ComboBoxValues("CDP","Santo Domiongo (CDP)"), new
         * ComboBoxValues("CHF","Swiss Franc (CHF)"), new
         * ComboBoxValues("CLP","CHILEAN PESO (CLP)"), new
         * ComboBoxValues("CNY","China (CNY)"), new
         * ComboBoxValues("COP","Colombian Peso (COP)"), new
         * ComboBoxValues("CRC","Costa Rica Colon (CRC)"), new
         * ComboBoxValues("CUP","Cuban Peso (CUP)"), new
         * ComboBoxValues("CVE","Cape Verde Escudo (CVE)"), new
         * ComboBoxValues("CYP","Cyprus Pound (CYP)"), new
         * ComboBoxValues("CZK","Czech Krona (CZK)"), new
         * ComboBoxValues("DEM","German Mark (DEM)"), new
         * ComboBoxValues("DJF","Djibouti Franc (DJF)"), new
         * ComboBoxValues("DKK","Danish Krone (DKK)"), new
         * ComboBoxValues("DOP","Dominican Peso (DOP)"), new
         * ComboBoxValues("DRP","Dominican Republic Peso (DRP)"), new
         * ComboBoxValues("DZD","Algerian Dinar (DZD)"), new
         * ComboBoxValues("ECS","ECUADORIAN SUCRE (ECS)"), new
         * ComboBoxValues("ECS","Ecuador Sucre (ECS)"), new
         * ComboBoxValues("ECU","European Currency Unit (ECU)"), new
         * ComboBoxValues("EEK","Estonian Krone (EEK)"), new
         * ComboBoxValues("EGP","Egyptian Pound (EGP)"), new
         * ComboBoxValues("ESP","Spanish Peseta (ESP)"), new
         * ComboBoxValues("ETB","Ethiopian Birr (ETB)"), new
         * ComboBoxValues("EUR","Currency of EMU member states (EUR)"), new
         * ComboBoxValues("FIM","Finnish Mark (FIM)"), new
         * ComboBoxValues("FJD","Fiji Dollar (FJD)"), new
         * ComboBoxValues("FKP","Falkland Pound (FKP)"), new
         * ComboBoxValues("FRF","French Franc (FRF)"), new
         * ComboBoxValues("GBP","British Pound (GBP)"), new
         * ComboBoxValues("GEK","Georgian Kupon (GEK)"), new
         * ComboBoxValues("GHC","Ghanian Cedi (GHC)"), new
         * ComboBoxValues("GIP","Gibraltar Pound (GIP)"), new
         * ComboBoxValues("GMD","Gambian Dalasi (GMD)"), new
         * ComboBoxValues("GNF","Guinea Franc (GNF)"), new
         * ComboBoxValues("GRD","Greek Drachma (GRD)"), new
         * ComboBoxValues("GTQ","Guatemalan Quedzal (GTQ)"), new
         * ComboBoxValues("GWP","Guinea Peso (GWP)"), new
         * ComboBoxValues("GYD","Guyanese Dollar (GYD)"), new
         * ComboBoxValues("HKD","Hong Kong Dollar (HKD)"), new
         * ComboBoxValues("HNL","Honduran Lempira (HNL)"), new
         * ComboBoxValues("HRD","Croatian Dinar (HRD)"), new
         * ComboBoxValues("HTG","Haitian Gourde (HTG)"), new
         * ComboBoxValues("HUF","Hungarian forint (HUF)"), new
         * ComboBoxValues("IDR","Indeonesian Rupiah (IDR)"), new
         * ComboBoxValues("IEP","Irish Pound (IEP)"), new
         * ComboBoxValues("ILS","Israeli Scheckel (ILS)"), new
         * ComboBoxValues("INR","Indian Rupee (INR)"), new
         * ComboBoxValues("IQD","Iraqui Dinar (IQD)"), new
         * ComboBoxValues("IRR","Iranian Rial (IRR)"), new
         * ComboBoxValues("ISK","Iceland Krona (ISK)"), new
         * ComboBoxValues("ITL","Italian Lira (ITL)"), new
         * ComboBoxValues("JMD","JAMAICAN DOLLAR (JMD)"), new
         * ComboBoxValues("JOD","Jordanian Dinar (JOD)"), new
         * ComboBoxValues("JPY","Japanese Yen (JPY)"), new
         * ComboBoxValues("KES","Kenyan Shilling (KES)"), new
         * ComboBoxValues("KHR","Cambodian Riel (KHR)"), new
         * ComboBoxValues("KIS","Kirghizstan Som (KIS)"), new
         * ComboBoxValues("KMF","Comoros Franc (KMF)"), new
         * ComboBoxValues("KPW","North Korean Won (KPW)"), new
         * ComboBoxValues("KRW","South Korean Won (KRW)"), new
         * ComboBoxValues("KWD","Kuwaiti Dinar (KWD)"), new
         * ComboBoxValues("KYD","Cayman Dollar (KYD)"), new
         * ComboBoxValues("KZT","Kazakhstani Tenge (KZT)"), new
         * ComboBoxValues("LAK","Laotian Kip (LAK)"), new
         * ComboBoxValues("LBP","Lebanese Pound (LBP)"), new
         * ComboBoxValues("LKR","Sri Lankan Rupee (LKR)"), new
         * ComboBoxValues("LRD","Liberian Dollar (LRD)"), new
         * ComboBoxValues("LSL","Lesotho Loti (LSL)"), new
         * ComboBoxValues("LTL","Lithuanian Lita (LTL)"), new
         * ComboBoxValues("LUF","Luxembourgian Franc (LUF)"), new
         * ComboBoxValues("LVL","Latvian Lat (LVL)"), new
         * ComboBoxValues("LYD","Libyan Dinar (LYD)"), new
         * ComboBoxValues("MAD","Moroccan Dirham (MAD)"), new
         * ComboBoxValues("MDL","Moldavian Lei (MDL)"), new
         * ComboBoxValues("MGF","Madagascan Franc (MGF)"), new
         * ComboBoxValues("MNC","Monaco (MNC)"), new
         * ComboBoxValues("MNT","Mongolian Tugrik (MNT)"), new
         * ComboBoxValues("MOP","Macao Pataca (MOP)"), new
         * ComboBoxValues("MRO","Mauritanian Ouguiya (MRO)"), new
         * ComboBoxValues("MTL","Maltese Lira (MTL)"), new
         * ComboBoxValues("MUR","Mauritius Rupee (MUR)"), new
         * ComboBoxValues("MVR","Maldive Rufiyaa (MVR)"), new
         * ComboBoxValues("MWK","Malawi Kwacha (MWK)"), new
         * ComboBoxValues("MXN","Mexican Peso (new) (MXN)"), new
         * ComboBoxValues("MXP","Mexican Peso (old) (MXP)"), new
         * ComboBoxValues("MYR","Malaysian Ringgit (MYR)"), new
         * ComboBoxValues("MZM","Mozambique Metical (MZM)"), new
         * ComboBoxValues("NGN","Nigerian Naira (NGN)"), new
         * ComboBoxValues("NIC","Nicaragua (NIC)"), new
         * ComboBoxValues("NIO","Nicaraguan Cordoba (NIO)"), new
         * ComboBoxValues("NIS","New Israeli Shekel (NIS)"), new
         * ComboBoxValues("NLG","Dutch Guilder (NLG)"), new
         * ComboBoxValues("NOK","Norwegian Krone (NOK)"), new
         * ComboBoxValues("NPR","Nepalese Rupee (NPR)"), new
         * ComboBoxValues("NZD","New Zealand Dollars (NZD)"), new
         * ComboBoxValues("OMR","Omani Rial (OMR)"), new
         * ComboBoxValues("PAB","Panamanian Balboa (PAB)"), new
         * ComboBoxValues("PEI","Peruvian Inti (PEI)"), new
         * ComboBoxValues("PEN","Peruvian Sol  (PEN)"), new
         * ComboBoxValues("PES","Peruvian Sol (PES)"), new
         * ComboBoxValues("PGK","Papua New Guinea Kina (PGK)"), new
         * ComboBoxValues("PHP","Philippino Peso (PHP)"), new
         * ComboBoxValues("PKR","Pakistan Rupee (PKR)"), new
         * ComboBoxValues("PLN","Polish Zloty (PLN)"), new
         * ComboBoxValues("PLZ","Poland (PLZ)"), new
         * ComboBoxValues("PTE","Portuguese Escudo (PTE)"), new
         * ComboBoxValues("PYG","Paraguayan Guarani (PYG)"), new
         * ComboBoxValues("QAR","Qatar Riyal (QAR)"), new
         * ComboBoxValues("RMB","Chinese Renminbi Yuan (RMB)"), new
         * ComboBoxValues("ROL","Roumanian Lei (ROL)"), new
         * ComboBoxValues("RUR","Russian Rouble (RUR)"), new
         * ComboBoxValues("RWF","Rwanda Franc (RWF)"), new
         * ComboBoxValues("SAR","Saudi Riyal (SAR)"), new
         * ComboBoxValues("SBD","Solomon Islands Dollar (SBD)"), new
         * ComboBoxValues("SCR","Seychelles Rupee (SCR)"), new
         * ComboBoxValues("SDP","Sudanese Pound (SDP)"), new
         * ComboBoxValues("SEK","Swedish Krona (SEK)"), new
         * ComboBoxValues("SGD","Singapore Dollar (SGD)"), new
         * ComboBoxValues("SHP","St.Helena Pound (SHP)"), new
         * ComboBoxValues("SIT","Slovenian Tolar (SIT)"), new
         * ComboBoxValues("SKK","Slovakian Krona (SKK)"), new
         * ComboBoxValues("SLL","Leone (SLL)"), new ComboBoxValues("SOL","Peru (SOL)"),
         * new ComboBoxValues("SOS","Somalian Shilling (SOS)"), new
         * ComboBoxValues("SRG","Surinam Guilder (SRG)"), new
         * ComboBoxValues("STD","Sao Tome / Principe Dobra (STD)"), new
         * ComboBoxValues("SUR","Russian Ruble (old) (SUR)"), new
         * ComboBoxValues("SVC","El Salvador Colon (SVC)"), new
         * ComboBoxValues("SYP","Syrian Pound (SYP)"), new
         * ComboBoxValues("SZL","Swaziland Lilangeni (SZL)"), new
         * ComboBoxValues("THB","Thailand Baht (THB)"), new
         * ComboBoxValues("TJR","Tadzhikistani Ruble (TJR)"), new
         * ComboBoxValues("TMM","Turkmenistani Manat (TMM)"), new
         * ComboBoxValues("TND","Tunisian Dinar (TND)"), new
         * ComboBoxValues("TOP","Tongan Pa'anga (TOP)"), new
         * ComboBoxValues("TPE","Timor Escudo (TPE)"), new
         * ComboBoxValues("TRL","Turkish Lira (TRL)"), new
         * ComboBoxValues("TTD","Trinidad and Tobago Dollar (TTD)"), new
         * ComboBoxValues("TWD","New Taiwan Dollar (TWD)"), new
         * ComboBoxValues("TZS","Tanzanian Shilling (TZS)"), new
         * ComboBoxValues("UAK","Ukrainian Karbowanez (UAK)"), new
         * ComboBoxValues("UGS","Ugandan Shilling (UGS)"), new
         * ComboBoxValues("USD","American Dollar (USD)"), new
         * ComboBoxValues("UYP","Uruguayan New Peso (UYP)"), new
         * ComboBoxValues("UYU","Uruguay (UYU)"), new
         * ComboBoxValues("VEB","Venezuelan Bolivar (VEB)"), new
         * ComboBoxValues("VND","Vietnamese Dong (VND)"), new
         * ComboBoxValues("VUV","Vanuatu Vatu (VUV)"), new
         * ComboBoxValues("WST","Samoan Tala (WST)"), new
         * ComboBoxValues("XAF","Gabon C.f.A Franc (XAF)"), new
         * ComboBoxValues("XCD","East Carribean Dollar (XCD)"), new
         * ComboBoxValues("XOF","Benin C.f.A. Franc (XOF)"), new
         * ComboBoxValues("YER","Yemeni Ryal (YER)"), new
         * ComboBoxValues("ZAR","South African Rand (ZAR)"), new
         * ComboBoxValues("ZMK","Zambian Kwacha (ZMK)"), new
         * ComboBoxValues("ZRZ","Zaire (ZRZ)"), new
         * ComboBoxValues("ZWD","Zimbabwean Dollar (ZWD)") ));
         * 
         * public static final List<ComboBoxValues> SISOCOUNTRYCODES = new
         * ArrayList<ComboBoxValues>( Arrays.asList( new
         * ComboBoxValues("AF","Afghanistan (AF)"), new
         * ComboBoxValues("AL","Albania (AL)"), new ComboBoxValues("DZ","Algeria (DZ)"),
         * new ComboBoxValues("AS","AmericanSamoa (AS)"), new
         * ComboBoxValues("AD","Andorra (AD)"), new ComboBoxValues("AO","Angola (AO)"),
         * new ComboBoxValues("AI","Anguilla (AI)"), new
         * ComboBoxValues("AQ","Antartica (AQ)"), new
         * ComboBoxValues("AG","AntiguaandBarbuda (AG)"), new
         * ComboBoxValues("AR","Argentina (AR)"), new
         * ComboBoxValues("AM","Armenia (AM)"), new ComboBoxValues("AW","Aruba (AW)"),
         * new ComboBoxValues("AU","Australia (AU)"), new
         * ComboBoxValues("AT","Austria (AT)"), new
         * ComboBoxValues("AZ","Azerbaijan (AZ)"), new
         * ComboBoxValues("BS","Bahamas (BS)"), new ComboBoxValues("BH","Bahrain (BH)"),
         * new ComboBoxValues("BD","Bangladesh (BD)"), new
         * ComboBoxValues("BB","Barbados (BB)"), new
         * ComboBoxValues("BE","Belgium (BE)"), new ComboBoxValues("BZ","Belize (BZ)"),
         * new ComboBoxValues("BJ","Benin (BJ)"), new
         * ComboBoxValues("BM","Bermuda (BM)"), new ComboBoxValues("BT","Bhutan (BT)"),
         * new ComboBoxValues("BO","Bolivia (BO)"), new
         * ComboBoxValues("BA","BosniaandHerzegowina (BA)"), new
         * ComboBoxValues("BW","Botswana (BW)"), new
         * ComboBoxValues("BV","BouvetIsland (BV)"), new
         * ComboBoxValues("BR","Brazil (BR)"), new
         * ComboBoxValues("IO","BritishIndianOceanTerritory (IO)"), new
         * ComboBoxValues("VG","BritishVirginIslands (VG)"), new
         * ComboBoxValues("BN","BruneiDarussalam (BN)"), new
         * ComboBoxValues("BG","Bulgaria (BG)"), new
         * ComboBoxValues("BF","BurkinaFaso (BF)"), new
         * ComboBoxValues("BI","Burundi (BI)"), new
         * ComboBoxValues("BY","ByelorussianSSR (BY)"), new
         * ComboBoxValues("KH","Cambodia (KH)"), new
         * ComboBoxValues("CM","Cameroon (CM)"), new ComboBoxValues("CA","Canada (CA)"),
         * new ComboBoxValues("CV","CapeVerde (CV)"), new
         * ComboBoxValues("KY","CaymanIslands (KY)"), new
         * ComboBoxValues("CF","CentralAfricanRepublic (CF)"), new
         * ComboBoxValues("TD","Chad (TD)"), new ComboBoxValues("CL","Chile (CL)"), new
         * ComboBoxValues("CN","China (CN)"), new
         * ComboBoxValues("CX","ChristmasIsland (CX)"), new
         * ComboBoxValues("CC","Cocos(Keeling)Islands (CC)"), new
         * ComboBoxValues("CO","Colombia (CO)"), new
         * ComboBoxValues("KM","Comoros (KM)"), new ComboBoxValues("CG","Congo (CG)"),
         * new ComboBoxValues("CK","CookIslands (CK)"), new
         * ComboBoxValues("CR","CostaRica (CR)"), new
         * ComboBoxValues("CI","Coted'Ivoire (CI)"), new
         * ComboBoxValues("HR","Croatia (HR)"), new ComboBoxValues("CU","Cuba (CU)"),
         * new ComboBoxValues("CY","Cyprus (CY)"), new
         * ComboBoxValues("CZ","CzechRepublic (CZ)"), new
         * ComboBoxValues("KP","DemocraticPeople'sRepublicofKorea (KP)"), new
         * ComboBoxValues("DK","Denmark (DK)"), new
         * ComboBoxValues("DJ","Djibouti (DJ)"), new
         * ComboBoxValues("DM","Dominica (DM)"), new
         * ComboBoxValues("DO","DominicanRepublic (DO)"), new
         * ComboBoxValues("TP","EastTimor (TP)"), new
         * ComboBoxValues("EC","Ecuador (EC)"), new ComboBoxValues("EG","Egypt (EG)"),
         * new ComboBoxValues("SV","ElSalvador (SV)"), new
         * ComboBoxValues("GQ","EquatorialGuinea (GQ)"), new
         * ComboBoxValues("ER","Eritrea (ER)"), new ComboBoxValues("EE","Estonia (EE)"),
         * new ComboBoxValues("ET","Ethiopia (ET)"), new
         * ComboBoxValues("FK","FalklandIslands(Malvinas) (FK)"), new
         * ComboBoxValues("FO","FaroeIslands (FO)"), new
         * ComboBoxValues("DE","FederalRepublicofGermany (DE)"), new
         * ComboBoxValues("FJ","Fiji (FJ)"), new ComboBoxValues("FI","Finland (FI)"),
         * new ComboBoxValues("FR","France (FR)"), new
         * ComboBoxValues("FX","France,Metropolitan (FX)"), new
         * ComboBoxValues("GF","FrenchGuiana (GF)"), new
         * ComboBoxValues("PF","FrenchPolynesia (PF)"), new
         * ComboBoxValues("TF","FrenchSouthernTerritories (TF)"), new
         * ComboBoxValues("GA","Gabon (GA)"), new ComboBoxValues("GM","Gambia (GM)"),
         * new ComboBoxValues("GE","Georgia (GE)"), new
         * ComboBoxValues("GH","Ghana (GH)"), new ComboBoxValues("GI","Gibraltar (GI)"),
         * new ComboBoxValues("GR","Greece (GR)"), new
         * ComboBoxValues("GL","Greenland (GL)"), new
         * ComboBoxValues("GD","Grenada (GD)"), new
         * ComboBoxValues("GP","Guadeloupe (GP)"), new ComboBoxValues("GU","Guam (GU)"),
         * new ComboBoxValues("GT","Guatemala (GT)"), new
         * ComboBoxValues("GN","Guinea (GN)"), new
         * ComboBoxValues("GW","Guinea-bissau (GW)"), new
         * ComboBoxValues("GY","Guyana (GY)"), new ComboBoxValues("HT","Haiti (HT)"),
         * new ComboBoxValues("HM","HeardandMcdonaldIslands (HM)"), new
         * ComboBoxValues("HN","Honduras (HN)"), new
         * ComboBoxValues("HK","Hong-Kong (HK)"), new
         * ComboBoxValues("HU","Hungary (HU)"), new ComboBoxValues("IS","Iceland (IS)"),
         * new ComboBoxValues("IN","India (IN)"), new
         * ComboBoxValues("ID","Indonesia (ID)"), new ComboBoxValues("IR","Iran (IR)"),
         * new ComboBoxValues("IQ","Iraq (IQ)"), new
         * ComboBoxValues("IE","Ireland (IE)"), new ComboBoxValues("IL","Israel (IL)"),
         * new ComboBoxValues("IT","Italy (IT)"), new
         * ComboBoxValues("JM","Jamaica (JM)"), new ComboBoxValues("JP","Japan (JP)"),
         * new ComboBoxValues("JO","Jordan (JO)"), new
         * ComboBoxValues("KZ","Kazakhstan (KZ)"), new
         * ComboBoxValues("KE","Kenya (KE)"), new ComboBoxValues("KI","Kiribati (KI)"),
         * new ComboBoxValues("KW","Kuwait (KW)"), new
         * ComboBoxValues("KG","Kyrgyzstan (KG)"), new
         * ComboBoxValues("LA","LaoPeople'sDemocraticRepublic (LA)"), new
         * ComboBoxValues("LV","Latvia (LV)"), new ComboBoxValues("LB","Lebanon (LB)"),
         * new ComboBoxValues("LS","Lesotho (LS)"), new
         * ComboBoxValues("LR","Liberia (LR)"), new
         * ComboBoxValues("LY","LibyanArabJamahiriya (LY)"), new
         * ComboBoxValues("LI","Liechtenstein (LI)"), new
         * ComboBoxValues("LT","Lithuania (LT)"), new
         * ComboBoxValues("LU","Luxembourg (LU)"), new
         * ComboBoxValues("MO","Macao (MO)"), new
         * ComboBoxValues("MG","Madagascar (MG)"), new
         * ComboBoxValues("MW","Malawi (MW)"), new ComboBoxValues("MY","Malaysia (MY)"),
         * new ComboBoxValues("MV","Maldives (MV)"), new
         * ComboBoxValues("ML","Mali (ML)"), new ComboBoxValues("MT","Malta (MT)"), new
         * ComboBoxValues("MH","MarshallIslands (MH)"), new
         * ComboBoxValues("MQ","Martinique (MQ)"), new
         * ComboBoxValues("MR","Mauritania (MR)"), new
         * ComboBoxValues("MU","Mauritius (MU)"), new
         * ComboBoxValues("YT","Mayotte (YT)"), new ComboBoxValues("MX","Mexico (MX)"),
         * new ComboBoxValues("FM","Micronesia (FM)"), new
         * ComboBoxValues("MD","Moldova (MD)"), new ComboBoxValues("MC","Monaco (MC)"),
         * new ComboBoxValues("MN","Mongolia (MN)"), new
         * ComboBoxValues("MS","Montserrat (MS)"), new
         * ComboBoxValues("MA","Morocco (MA)"), new
         * ComboBoxValues("MZ","Mozambique (MZ)"), new
         * ComboBoxValues("MM","Myanmar (MM)"), new ComboBoxValues("NA","Namibia (NA)"),
         * new ComboBoxValues("NR","Nauru (NR)"), new ComboBoxValues("NP","Nepal (NP)"),
         * new ComboBoxValues("NL","Netherlands (NL)"), new
         * ComboBoxValues("AN","NetherlandsAntilles (AN)"), new
         * ComboBoxValues("NC","NewCaledonia (NC)"), new
         * ComboBoxValues("NZ","NewZealand (NZ)"), new
         * ComboBoxValues("NI","Nicaragua (NI)"), new ComboBoxValues("NE","Niger (NE)"),
         * new ComboBoxValues("NG","Nigeria (NG)"), new
         * ComboBoxValues("NU","Niue (NU)"), new
         * ComboBoxValues("NF","NorfolkIsland (NF)"), new
         * ComboBoxValues("MP","NorthernMarianaIslands (MP)"), new
         * ComboBoxValues("NO","Norway (NO)"), new ComboBoxValues("OM","Oman (OM)"), new
         * ComboBoxValues("PK","Pakistan (PK)"), new ComboBoxValues("PW","Palau (PW)"),
         * new ComboBoxValues("PA","Panama (PA)"), new
         * ComboBoxValues("PG","PapuaNewGuinea (PG)"), new
         * ComboBoxValues("PY","Paraguay (PY)"), new ComboBoxValues("PE","Peru (PE)"),
         * new ComboBoxValues("PH","Philippines (PH)"), new
         * ComboBoxValues("PN","Pitcairn (PN)"), new ComboBoxValues("PL","Poland (PL)"),
         * new ComboBoxValues("PT","Portugal (PT)"), new
         * ComboBoxValues("PR","PuertoRico (PR)"), new
         * ComboBoxValues("QA","Qatar (QA)"), new
         * ComboBoxValues("KR","RepublicofKorea (KR)"), new
         * ComboBoxValues("RE","Reunion (RE)"), new ComboBoxValues("RO","Romania (RO)"),
         * new ComboBoxValues("RU","RussianFederation (RU)"), new
         * ComboBoxValues("RW","Rwanda (RW)"), new
         * ComboBoxValues("KN","SaintKittsandNevis (KN)"), new
         * ComboBoxValues("LC","SaintLucia (LC)"), new
         * ComboBoxValues("WS","Samoa (WS)"), new ComboBoxValues("SM","SanMarino (SM)"),
         * new ComboBoxValues("ST","SaoTomeandPrincipe (ST)"), new
         * ComboBoxValues("SA","SaudiArabia (SA)"), new
         * ComboBoxValues("SN","Senegal (SN)"), new
         * ComboBoxValues("SC","Seychelles (SC)"), new
         * ComboBoxValues("SL","SierraLeone (SL)"), new
         * ComboBoxValues("SG","Singapore (SG)"), new
         * ComboBoxValues("SK","SlovakRepublic (SK)"), new
         * ComboBoxValues("SI","Slovenia (SI)"), new
         * ComboBoxValues("SB","SolomonIslands (SB)"), new
         * ComboBoxValues("SO","Somalia (SO)"), new
         * ComboBoxValues("ZA","SouthAfrica (ZA)"), new
         * ComboBoxValues("GS","SouthGeorgiaandtheSouthSandwichIslands (GS)"), new
         * ComboBoxValues("ES","Spain (ES)"), new ComboBoxValues("LK","SriLanka (LK)"),
         * new ComboBoxValues("SH","St.Helena (SH)"), new
         * ComboBoxValues("PM","St.PierreandMiquelon (PM)"), new
         * ComboBoxValues("VC","St.VincentandTheGrenadines (VC)"), new
         * ComboBoxValues("SD","Sudan (SD)"), new ComboBoxValues("SR","Suriname (SR)"),
         * new ComboBoxValues("SJ","SvalbardandJanMayenIslands (SJ)"), new
         * ComboBoxValues("SZ","Swaziland (SZ)"), new
         * ComboBoxValues("SE","Sweden (SE)"), new
         * ComboBoxValues("CH","Switzerland (CH)"), new
         * ComboBoxValues("SY","SyrianArabRepublic (SY)"), new
         * ComboBoxValues("TW","Taiwan,ProvinceofChina (TW)"), new
         * ComboBoxValues("TJ","Tajikistan (TJ)"), new
         * ComboBoxValues("TH","Thailand (TH)"), new ComboBoxValues("TG","Togo (TG)"),
         * new ComboBoxValues("TK","Tokelau (TK)"), new
         * ComboBoxValues("TO","Tonga (TO)"), new
         * ComboBoxValues("TT","TrinidadandTobago (TT)"), new
         * ComboBoxValues("TN","Tunisia (TN)"), new ComboBoxValues("TR","Turkey (TR)"),
         * new ComboBoxValues("TM","Turkmenistan (TM)"), new
         * ComboBoxValues("TC","TurksandCaicosIslands (TC)"), new
         * ComboBoxValues("TV","Tuvalu (TV)"), new ComboBoxValues("UG","Uganda (UG)"),
         * new ComboBoxValues("UA","Ukraine (UA)"), new
         * ComboBoxValues("AE","UnitedArabEmirates (AE)"), new
         * ComboBoxValues("GB","UnitedKingdom (GB)"), new
         * ComboBoxValues("TZ","UnitedRepublicofTanzania (TZ)"), new
         * ComboBoxValues("US","UnitedStates (US)"), new
         * ComboBoxValues("UM","UnitedStatesMinorOutlayingIslands (UM)"), new
         * ComboBoxValues("VI","UnitedStatesVirginIslands (VI)"), new
         * ComboBoxValues("UY","Uruguay (UY)"), new
         * ComboBoxValues("UZ","Uzbekistan (UZ)"), new
         * ComboBoxValues("VU","Vanuatu (VU)"), new
         * ComboBoxValues("VA","VaticanCityState(HolySee) (VA)"), new
         * ComboBoxValues("VE","Venezuela (VE)"), new
         * ComboBoxValues("VN","Vietnam (VN)"), new
         * ComboBoxValues("WF","WallisandFutunaIslands (WF)"), new
         * ComboBoxValues("EH","WesternSahara (EH)"), new
         * ComboBoxValues("YE","Yemen (YE)"), new
         * ComboBoxValues("YU","Yugoslavia (YU)"), new
         * ComboBoxValues("ZR","Zaire (ZR)"), new ComboBoxValues("ZM","Zambia (ZM)"),
         * new ComboBoxValues("ZW","Zimbabwe (ZW)") ));
         */

        public static final List<String[]> SISOCOUNTRYCODES = Collections.unmodifiableList(Arrays.asList(
                        new String[] { "AF", "Afghanistan (AF)" }, new String[] { "AL", "Albania (AL)" },
                        new String[] { "DZ", "Algeria (DZ)" }, new String[] { "AS", "American Samoa (AS)" },
                        new String[] { "AD", "Andorra (AD)" }, new String[] { "AO", "Angola (AO)" },
                        new String[] { "AI", "Anguilla (AI)" }, new String[] { "AQ", "Antarctica (AQ)" },
                        new String[] { "AG", "Antigua and Barbuda (AG)" }, new String[] { "AR", "Argentina (AR)" },
                        new String[] { "AM", "Armenia (AM)" }, new String[] { "AW", "Aruba (AW)" },
                        new String[] { "AU", "Australia (AU)" }, new String[] { "AT", "Austria (AT)" },
                        new String[] { "AZ", "Azerbaijan (AZ)" }, new String[] { "BS", "Bahamas (BS)" },
                        new String[] { "BH", "Bahrain (BH)" }, new String[] { "BD", "Bangladesh (BD)" },
                        new String[] { "BB", "Barbados (BB)" }, new String[] { "BE", "Belgium (BE)" },
                        new String[] { "BZ", "Belize (BZ)" }, new String[] { "BJ", "Benin (BJ)" },
                        new String[] { "BM", "Bermuda (BM)" }, new String[] { "BT", "Bhutan (BT)" },
                        new String[] { "BO", "Bolivia (BO)" }, new String[] { "BA", "Bosnia and Herzegovina (BA)" },
                        new String[] { "BW", "Botswana (BW)" }, new String[] { "BV", "Bouvet Island (BV)" },
                        new String[] { "BR", "Brazil (BR)" },
                        new String[] { "IO", "British Indian Ocean Territory (IO)" },
                        new String[] { "VG", "British Virgin Islands (VG)" },
                        new String[] { "BN", "Brunei Darussalam (BN)" }, new String[] { "BG", "Bulgaria (BG)" },
                        new String[] { "BF", "Burkina Faso (BF)" }, new String[] { "BI", "Burundi (BI)" },
                        new String[] { "BY", "Byelorussian SSR (BY)" }, new String[] { "KH", "Cambodia (KH)" },
                        new String[] { "CM", "Cameroon (CM)" }, new String[] { "CA", "Canada (CA)" },
                        new String[] { "CV", "Cape Verde (CV)" }, new String[] { "KY", "Cayman Islands (KY)" },
                        new String[] { "CF", "Central African Republic (CF)" }, new String[] { "TD", "Chad (TD)" },
                        new String[] { "CL", "Chile (CL)" }, new String[] { "CN", "China (CN)" },
                        new String[] { "CX", "Christmas Island (CX)" },
                        new String[] { "CC", "Cocos (Keeling) Islands (CC)" }, new String[] { "CO", "Colombia (CO)" },
                        new String[] { "KM", "Comoros (KM)" }, new String[] { "CG", "Congo (CG)" },
                        new String[] { "CK", "Cook Islands (CK)" }, new String[] { "CR", "Costa Rica (CR)" },
                        new String[] { "CI", "Cote d'Ivoire (CI)" }, new String[] { "HR", "Croatia (HR)" },
                        new String[] { "CU", "Cuba (CU)" }, new String[] { "CY", "Cyprus (CY)" },
                        new String[] { "CZ", "Czech Republic (CZ)" },
                        new String[] { "KP", "Democratic People's Republic of Korea (KP)" },
                        new String[] { "DK", "Denmark (DK)" }, new String[] { "DJ", "Djibouti (DJ)" },
                        new String[] { "DM", "Dominica (DM)" }, new String[] { "DO", "Dominican Republic (DO)" },
                        new String[] { "TP", "East Timor (TP)" }, new String[] { "EC", "Ecuador (EC)" },
                        new String[] { "EG", "Egypt (EG)" }, new String[] { "SV", "El Salvador (SV)" },
                        new String[] { "GQ", "Equatorial Guinea (GQ)" }, new String[] { "ER", "Eritrea (ER)" },
                        new String[] { "EE", "Estonia (EE)" }, new String[] { "ET", "Ethiopia (ET)" },
                        new String[] { "FK", "Falkland Islands (Malvinas) (FK)" },
                        new String[] { "FO", "Faroe Islands (FO)" },
                        new String[] { "DE", "Federal Republic of Germany (DE)" }, new String[] { "FJ", "Fiji (FJ)" },
                        new String[] { "FI", "Finland (FI)" }, new String[] { "FR", "France (FR)" },
                        new String[] { "FX", "France, Metropolitan (FX)" }, new String[] { "GF", "French Guiana (GF)" },
                        new String[] { "PF", "French Polynesia (PF)" },
                        new String[] { "TF", "French Southern Territories (TF)" }, new String[] { "GA", "Gabon (GA)" },
                        new String[] { "GM", "Gambia (GM)" }, new String[] { "GE", "Georgia (GE)" },
                        new String[] { "GH", "Ghana (GH)" }, new String[] { "GI", "Gibraltar (GI)" },
                        new String[] { "GR", "Greece (GR)" }, new String[] { "GL", "Greenland (GL)" },
                        new String[] { "GD", "Grenada (GD)" }, new String[] { "GP", "Guadeloupe (GP)" },
                        new String[] { "GU", "Guam (GU)" }, new String[] { "GT", "Guatemala (GT)" },
                        new String[] { "GN", "Guinea (GN)" }, new String[] { "GW", "Guinea-Bissau (GW)" },
                        new String[] { "GY", "Guyana (GY)" }, new String[] { "HT", "Haiti (HT)" },
                        new String[] { "HM", "Heard and McDonald Islands (HM)" },
                        new String[] { "HN", "Honduras (HN)" }, new String[] { "HK", "Hong Kong (HK)" },
                        new String[] { "HU", "Hungary (HU)" }, new String[] { "IS", "Iceland (IS)" },
                        new String[] { "IN", "India (IN)" }, new String[] { "ID", "Indonesia (ID)" },
                        new String[] { "IR", "Iran (IR)" }, new String[] { "IQ", "Iraq (IQ)" },
                        new String[] { "IE", "Ireland (IE)" }, new String[] { "IL", "Israel (IL)" },
                        new String[] { "IT", "Italy (IT)" }, new String[] { "JM", "Jamaica (JM)" },
                        new String[] { "JP", "Japan (JP)" }, new String[] { "JO", "Jordan (JO)" },
                        new String[] { "KZ", "Kazakhstan (KZ)" }, new String[] { "KE", "Kenya (KE)" },
                        new String[] { "KI", "Kiribati (KI)" }, new String[] { "KW", "Kuwait (KW)" },
                        new String[] { "KG", "Kyrgyzstan (KG)" },
                        new String[] { "LA", "Lao People's Democratic Republic (LA)" },
                        new String[] { "LV", "Latvia (LV)" }, new String[] { "LB", "Lebanon (LB)" },
                        new String[] { "LS", "Lesotho (LS)" }, new String[] { "LR", "Liberia (LR)" },
                        new String[] { "LY", "Libyan Arab Jamahiriya (LY)" },
                        new String[] { "LI", "Liechtenstein (LI)" }, new String[] { "LT", "Lithuania (LT)" },
                        new String[] { "LU", "Luxembourg (LU)" }, new String[] { "MO", "Macao (MO)" },
                        new String[] { "MG", "Madagascar (MG)" }, new String[] { "MW", "Malawi (MW)" },
                        new String[] { "MY", "Malaysia (MY)" }, new String[] { "MV", "Maldives (MV)" },
                        new String[] { "ML", "Mali (ML)" }, new String[] { "MT", "Malta (MT)" },
                        new String[] { "MH", "Marshall Islands (MH)" }, new String[] { "MQ", "Martinique (MQ)" },
                        new String[] { "MR", "Mauritania (MR)" }, new String[] { "MU", "Mauritius (MU)" },
                        new String[] { "YT", "Mayotte (YT)" }, new String[] { "MX", "Mexico (MX)" },
                        new String[] { "FM", "Micronesia (FM)" }, new String[] { "MD", "Moldova (MD)" },
                        new String[] { "MC", "Monaco (MC)" }, new String[] { "MN", "Mongolia (MN)" },
                        new String[] { "MS", "Montserrat (MS)" }, new String[] { "MA", "Morocco (MA)" },
                        new String[] { "MZ", "Mozambique (MZ)" }, new String[] { "MM", "Myanmar (MM)" },
                        new String[] { "NA", "Namibia (NA)" }, new String[] { "NR", "Nauru (NR)" },
                        new String[] { "NP", "Nepal (NP)" }, new String[] { "NL", "Netherlands (NL)" },
                        new String[] { "AN", "Netherlands Antilles (AN)" }, new String[] { "NC", "New Caledonia (NC)" },
                        new String[] { "NZ", "New Zealand (NZ)" }, new String[] { "NI", "Nicaragua (NI)" },
                        new String[] { "NE", "Niger (NE)" }, new String[] { "NG", "Nigeria (NG)" },
                        new String[] { "NU", "Niue (NU)" }, new String[] { "NF", "Norfolk Island (NF)" },
                        new String[] { "MP", "Northern Mariana Islands (MP)" }, new String[] { "NO", "Norway (NO)" },
                        new String[] { "OM", "Oman (OM)" }, new String[] { "PK", "Pakistan (PK)" },
                        new String[] { "PW", "Palau (PW)" }, new String[] { "PA", "Panama (PA)" },
                        new String[] { "PG", "Papua New Guinea (PG)" }, new String[] { "PY", "Paraguay (PY)" },
                        new String[] { "PE", "Peru (PE)" }, new String[] { "PH", "Philippines (PH)" },
                        new String[] { "PN", "Pitcairn (PN)" }, new String[] { "PL", "Poland (PL)" },
                        new String[] { "PT", "Portugal (PT)" }, new String[] { "PR", "Puerto Rico (PR)" },
                        new String[] { "QA", "Qatar (QA)" }, new String[] { "KR", "Republic of Korea (KR)" },
                        new String[] { "RE", "Reunion (RE)" }, new String[] { "RO", "Romania (RO)" },
                        new String[] { "RU", "Russian Federation (RU)" }, new String[] { "RW", "Rwanda (RW)" },
                        new String[] { "KN", "Saint Kitts and Nevis (KN)" }, new String[] { "LC", "Saint Lucia (LC)" },
                        new String[] { "WS", "Samoa (WS)" }, new String[] { "SM", "San Marino (SM)" },
                        new String[] { "ST", "Sao Tome and Principe (ST)" }, new String[] { "SA", "Saudi Arabia (SA)" },
                        new String[] { "SN", "Senegal (SN)" }, new String[] { "SC", "Seychelles (SC)" },
                        new String[] { "SL", "Sierra Leone (SL)" }, new String[] { "SG", "Singapore (SG)" },
                        new String[] { "SK", "Slovak Republic (SK)" }, new String[] { "SI", "Slovenia (SI)" },
                        new String[] { "SB", "Solomon Islands (SB)" }, new String[] { "SO", "Somalia (SO)" },
                        new String[] { "ZA", "South Africa (ZA)" },
                        new String[] { "GS", "South Georgia and the South Sandwich Islands (GS)" },
                        new String[] { "ES", "Spain (ES)" }, new String[] { "LK", "Sri Lanka (LK)" },
                        new String[] { "SH", "St. Helena (SH)" }, new String[] { "PM", "St. Pierre and Miquelon (PM)" },
                        new String[] { "VC", "St. Vincent and The Grenadines (VC)" },
                        new String[] { "SD", "Sudan (SD)" }, new String[] { "SR", "Suriname (SR)" },
                        new String[] { "SJ", "Svalbard and Jan Mayen Islands (SJ)" },
                        new String[] { "SZ", "Swaziland (SZ)" }, new String[] { "SE", "Sweden (SE)" },
                        new String[] { "CH", "Switzerland (CH)" }, new String[] { "SY", "Syrian Arab Republic (SY)" },
                        new String[] { "TW", "Taiwan, Province of China (TW)" },
                        new String[] { "TJ", "Tajikistan (TJ)" }, new String[] { "TH", "Thailand (TH)" },
                        new String[] { "TG", "Togo (TG)" }, new String[] { "TK", "Tokelau (TK)" },
                        new String[] { "TO", "Tonga (TO)" }, new String[] { "TT", "Trinidad and Tobago (TT)" },
                        new String[] { "TN", "Tunisia (TN)" }, new String[] { "TR", "Turkey (TR)" },
                        new String[] { "TM", "Turkmenistan (TM)" },
                        new String[] { "TC", "Turks and Caicos Islands (TC)" }, new String[] { "TV", "Tuvalu (TV)" },
                        new String[] { "UG", "Uganda (UG)" }, new String[] { "UA", "Ukraine (UA)" },
                        new String[] { "AE", "United Arab Emirates (AE)" },
                        new String[] { "GB", "United Kingdom (GB)" },
                        new String[] { "TZ", "United Republic of Tanzania (TZ)" },
                        new String[] { "US", "United States (US)" },
                        new String[] { "UM", "United States Minor Outlying Islands (UM)" },
                        new String[] { "VI", "United States Virgin Islands (VI)" },
                        new String[] { "UY", "Uruguay (UY)" }, new String[] { "UZ", "Uzbekistan (UZ)" },
                        new String[] { "VU", "Vanuatu (VU)" },
                        new String[] { "VA", "Vatican City State (Holy See) (VA)" },
                        new String[] { "VE", "Venezuela (VE)" }, new String[] { "VN", "Vietnam (VN)" },
                        new String[] { "WF", "Wallis and Futuna Islands (WF)" },
                        new String[] { "EH", "Western Sahara (EH)" }, new String[] { "YE", "Yemen (YE)" },
                        new String[] { "YU", "Yugoslavia (YU)" }, new String[] { "ZR", "Zaire (ZR)" },
                        new String[] { "ZM", "Zambia (ZM)" }, new String[] { "ZW", "Zimbabwe (ZW)" }));
        public static final String SISOCOUNTRYCODES_US = "US";
        /*
         * public static final Map<String,String> SCODIGOISOMONEDAS = new
         * HashMap<String,String>(){ { put("ADP","Andoran peseta (ADP)");
         * put("AED","United Arab Emirates Dirham (AED)"); put("AFA","Afghani (AFA)");
         * put("ALL","Albanian Lek (ALL)"); put("AMD","Armenian Dram (AMD)");
         * put("ANG","West Indian Guilder (ANG)"); put("AOK","Angolan Kwanza (AOK)");
         * put("ARA","Argentinian Austral (ARA)"); put("ARS","Argentina Peso (ARS)");
         * put("ATS","Austrian Schilling (ATS)"); put("AUD","Australian Dollar (AUD)");
         * put("AWG","Aruban Guilder (AWG)"); put("AZM","Azerbaijan Manat (AZM)");
         * put("BAD","Bosnia (BAD)"); put("BBD","Barbados Dollar (BBD)");
         * put("BDT","Bangladesh Taka (BDT)"); put("BEF","Belgian Franc (BEF)");
         * put("BGL","Bulgarian Lev (BGL)"); put("BHD","Bahrain Dinar (BHD)");
         * put("BIF","Burundi Franc (BIF)"); put("BMD","Bermudan Dollar (BMD)");
         * put("BND","Brunei Dollar (BND)"); put("BOB","Bolivian Boliviano (BOB)");
         * put("BRL","Brazilian Real (BRL)"); put("BRR","Brazil (BRR)");
         * put("BSD","Bahaman Dollar (BSD)"); put("BWP","Botswana Pula (BWP)");
         * put("BYR","Belorussian Ruble (BYR)"); put("BZD","Belize Dollar (BZD)");
         * put("CAD","Canadian Dollar (CAD)"); put("CDP","Santo Domiongo (CDP)");
         * put("CHF","Swiss Franc (CHF)"); put("CLP","CHILEAN PESO (CLP)");
         * put("CNY","China (CNY)"); put("COP","Colombian Peso (COP)");
         * put("CRC","Costa Rica Colon (CRC)"); put("CUP","Cuban Peso (CUP)");
         * put("CVE","Cape Verde Escudo (CVE)"); put("CYP","Cyprus Pound (CYP)");
         * put("CZK","Czech Krona (CZK)"); put("DEM","German Mark (DEM)");
         * put("DJF","Djibouti Franc (DJF)"); put("DKK","Danish Krone (DKK)");
         * put("DOP","Dominican Peso (DOP)");
         * put("DRP","Dominican Republic Peso (DRP)");
         * put("DZD","Algerian Dinar (DZD)"); put("ECS","ECUADORIAN SUCRE (ECS)");
         * put("ECS","Ecuador Sucre (ECS)"); put("ECU","European Currency Unit (ECU)");
         * put("EEK","Estonian Krone (EEK)"); put("EGP","Egyptian Pound (EGP)");
         * put("ESP","Spanish Peseta (ESP)"); put("ETB","Ethiopian Birr (ETB)");
         * put("EUR","Currency of EMU member states (EUR)");
         * put("FIM","Finnish Mark (FIM)"); put("FJD","Fiji Dollar (FJD)");
         * put("FKP","Falkland Pound (FKP)"); put("FRF","French Franc (FRF)");
         * put("GBP","British Pound (GBP)"); put("GEK","Georgian Kupon (GEK)");
         * put("GHC","Ghanian Cedi (GHC)"); put("GIP","Gibraltar Pound (GIP)");
         * put("GMD","Gambian Dalasi (GMD)"); put("GNF","Guinea Franc (GNF)");
         * put("GRD","Greek Drachma (GRD)"); put("GTQ","Guatemalan Quedzal (GTQ)");
         * put("GWP","Guinea Peso (GWP)"); put("GYD","Guyanese Dollar (GYD)");
         * put("HKD","Hong Kong Dollar (HKD)"); put("HNL","Honduran Lempira (HNL)");
         * put("HRD","Croatian Dinar (HRD)"); put("HTG","Haitian Gourde (HTG)");
         * put("HUF","Hungarian forint (HUF)"); put("IDR","Indeonesian Rupiah (IDR)");
         * put("IEP","Irish Pound (IEP)"); put("ILS","Israeli Scheckel (ILS)");
         * put("INR","Indian Rupee (INR)"); put("IQD","Iraqui Dinar (IQD)");
         * put("IRR","Iranian Rial (IRR)"); put("ISK","Iceland Krona (ISK)");
         * put("ITL","Italian Lira (ITL)"); put("JMD","JAMAICAN DOLLAR (JMD)");
         * put("JOD","Jordanian Dinar (JOD)"); put("JPY","Japanese Yen (JPY)");
         * put("KES","Kenyan Shilling (KES)"); put("KHR","Cambodian Riel (KHR)");
         * put("KIS","Kirghizstan Som (KIS)"); put("KMF","Comoros Franc (KMF)");
         * put("KPW","North Korean Won (KPW)"); put("KRW","South Korean Won (KRW)");
         * put("KWD","Kuwaiti Dinar (KWD)"); put("KYD","Cayman Dollar (KYD)");
         * put("KZT","Kazakhstani Tenge (KZT)"); put("LAK","Laotian Kip (LAK)");
         * put("LBP","Lebanese Pound (LBP)"); put("LKR","Sri Lankan Rupee (LKR)");
         * put("LRD","Liberian Dollar (LRD)"); put("LSL","Lesotho Loti (LSL)");
         * put("LTL","Lithuanian Lita (LTL)"); put("LUF","Luxembourgian Franc (LUF)");
         * put("LVL","Latvian Lat (LVL)"); put("LYD","Libyan Dinar (LYD)");
         * put("MAD","Moroccan Dirham (MAD)"); put("MDL","Moldavian Lei (MDL)");
         * put("MGF","Madagascan Franc (MGF)"); put("MNC","Monaco (MNC)");
         * put("MNT","Mongolian Tugrik (MNT)"); put("MOP","Macao Pataca (MOP)");
         * put("MRO","Mauritanian Ouguiya (MRO)"); put("MTL","Maltese Lira (MTL)");
         * put("MUR","Mauritius Rupee (MUR)"); put("MVR","Maldive Rufiyaa (MVR)");
         * put("MWK","Malawi Kwacha (MWK)"); put("MXN","Mexican Peso (new) (MXN)");
         * put("MXP","Mexican Peso (old) (MXP)"); put("MYR","Malaysian Ringgit (MYR)");
         * put("MZM","Mozambique Metical (MZM)"); put("NGN","Nigerian Naira (NGN)");
         * put("NIC","Nicaragua (NIC)"); put("NIO","Nicaraguan Cordoba (NIO)");
         * put("NIS","New Israeli Shekel (NIS)"); put("NLG","Dutch Guilder (NLG)");
         * put("NOK","Norwegian Krone (NOK)"); put("NPR","Nepalese Rupee (NPR)");
         * put("NZD","New Zealand Dollars (NZD)"); put("OMR","Omani Rial (OMR)");
         * put("PAB","Panamanian Balboa (PAB)"); put("PEI","Peruvian Inti (PEI)");
         * put("PEN","Peruvian Sol  (PEN)"); put("PES","Peruvian Sol (PES)");
         * put("PGK","Papua New Guinea Kina (PGK)"); put("PHP","Philippino Peso (PHP)");
         * put("PKR","Pakistan Rupee (PKR)"); put("PLN","Polish Zloty (PLN)");
         * put("PLZ","Poland (PLZ)"); put("PTE","Portuguese Escudo (PTE)");
         * put("PYG","Paraguayan Guarani (PYG)"); put("QAR","Qatar Riyal (QAR)");
         * put("RMB","Chinese Renminbi Yuan (RMB)"); put("ROL","Roumanian Lei (ROL)");
         * put("RUR","Russian Rouble (RUR)"); put("RWF","Rwanda Franc (RWF)");
         * put("SAR","Saudi Riyal (SAR)"); put("SBD","Solomon Islands Dollar (SBD)");
         * put("SCR","Seychelles Rupee (SCR)"); put("SDP","Sudanese Pound (SDP)");
         * put("SEK","Swedish Krona (SEK)"); put("SGD","Singapore Dollar (SGD)");
         * put("SHP","St.Helena Pound (SHP)"); put("SIT","Slovenian Tolar (SIT)");
         * put("SKK","Slovakian Krona (SKK)"); put("SLL","Leone (SLL)");
         * put("SOL","Peru (SOL)"); put("SOS","Somalian Shilling (SOS)");
         * put("SRG","Surinam Guilder (SRG)");
         * put("STD","Sao Tome / Principe Dobra (STD)");
         * put("SUR","Russian Ruble (old) (SUR)"); put("SVC","El Salvador Colon (SVC)");
         * put("SYP","Syrian Pound (SYP)"); put("SZL","Swaziland Lilangeni (SZL)");
         * put("THB","Thailand Baht (THB)"); put("TJR","Tadzhikistani Ruble (TJR)");
         * put("TMM","Turkmenistani Manat (TMM)"); put("TND","Tunisian Dinar (TND)");
         * put("TOP","Tongan Pa'anga (TOP)"); put("TPE","Timor Escudo (TPE)");
         * put("TRL","Turkish Lira (TRL)");
         * put("TTD","Trinidad and Tobago Dollar (TTD)");
         * put("TWD","New Taiwan Dollar (TWD)"); put("TZS","Tanzanian Shilling (TZS)");
         * put("UAK","Ukrainian Karbowanez (UAK)"); put("UGS","Ugandan Shilling (UGS)");
         * put("USD","American Dollar (USD)"); put("UYP","Uruguayan New Peso (UYP)");
         * put("UYU","Uruguay (UYU)"); put("VEB","Venezuelan Bolivar (VEB)");
         * put("VND","Vietnamese Dong (VND)"); put("VUV","Vanuatu Vatu (VUV)");
         * put("WST","Samoan Tala (WST)"); put("XAF","Gabon C.f.A Franc (XAF)");
         * put("XCD","East Carribean Dollar (XCD)");
         * put("XOF","Benin C.f.A. Franc (XOF)"); put("YER","Yemeni Ryal (YER)");
         * put("ZAR","South African Rand (ZAR)"); put("ZMK","Zambian Kwacha (ZMK)");
         * put("ZRZ","Zaire (ZRZ)"); put("ZWD","Zimbabwean Dollar (ZWD)"); }};
         */
        /**
         * Bloqueo de funciones: consulta de indice
         */
        public static final boolean BPERMISO_CONSULTAINDICE_CSVBYPASS = true;
        public static final boolean BPERMISO_CONSULTAINDICE_CANDADO = true;
        public static final boolean BPERMISO_CONSULTAINDICE_RETOPE = true;
        public static final boolean BPERMISO_CONSULTAINDICE_CAMBIOMUESTRA = true;
        public static final boolean BPERMISO_CONSULTAINDICE_CAMBIOSGRID = true;

        /**
         * Bloqueo de funciones: emisiones internacionales
         */
        public static final boolean BPERMISO_EMISIONESINTERNACIONALES_CANDADO = true;
        public static final boolean BPERMISO_EMISIONESINTERNACIONALES_APLICARDIVIDENDO = true;
        public static final boolean BPERMISO_EMISIONESINTERNACIONALES_AGREGAREMISION = true;
        public static final boolean BPERMISO_EMISIONESINTERNACIONALES_BORRAREMISION = true;

        /**
         * Bloqueo de presentaci�n de ventanas
         */
        public static final boolean BVENTANA_DERECHOS_ACTIVADA = true;
        public static final boolean BVENTANA_CONSULTAINTRADIA_ACTIVADA = true;
        public static final boolean BVENTANA_EMISIONESINTERNACIONALES_ACTIVADA = true;
        public static final boolean BVENTANA_VALIDACIONES_ACTIVADA = true;
        public static final boolean BVENTANA_REPORTES_ACTIVADA = true;
        public static final boolean BVENTANA_EMERGENTE_ACTIVADA = true;
        public static final boolean BVENTANA_CONSULTAINTRADIAETF_ACTIVADA = true;
        public static final boolean BVENTANA_DERECHOSEVA_ACTIVADA = true;

        public static final String VENTANA_DERECHOS_ID = "admon-derechos-shortcut";
        public static final String VENTANA_CONSULTAINTRADIA_ID = "consultaintradia-shortcut";
        public static final String VENTANA_EMISIONESINTERNACIONALES_ID = "indicesinternacionales-shortcut";
        public static final String VENTANA_VALIDACIONES_ID = "validaciones-shortcut";
        public static final String VENTANA_REPORTES_ID = "reportes-shortcut";
        public static final String VENTANA_EMERGENTE_ID = "emergente-shortcut";
        public static final String VENTANA_CONSULTAINTRADIAETF_ID = "consultaintradiaetf-shortcut";
        public static final String VENTANA_DERECHOSEVA_ID = "admon-derechoseva-shortcut";
        public static final String VENTANA_ADMONUSUARIOS_ID = "admon-usuarios-shortcut";
        public static final String VENTANA_ADMONREPORTES_ID = "admon-reportes-shortcut";
        public static final String VENTANA_ADMONALERTAS_ID = "admon-alertas-shortcut";
        public static final String VENTANA_INDICESPOREMISION_ID = "indicesporemision-shortcut";
        public static final String VENTANA_ADMONINDICES_ID = "admon-indices-shortcut";
        public static final String VENTANA_OPERADOR_ID = "operador-shortcut";

        public static List<String> ROLE_FULL_VENTANAS_IDS = new ArrayList<String>(
                        Arrays.asList(new String(VENTANA_DERECHOS_ID), new String(VENTANA_CONSULTAINTRADIA_ID),
                                        new String(VENTANA_EMISIONESINTERNACIONALES_ID),
                                        new String(VENTANA_VALIDACIONES_ID), new String(VENTANA_REPORTES_ID),
                                        new String(VENTANA_EMERGENTE_ID), new String(VENTANA_CONSULTAINTRADIAETF_ID),
                                        new String(VENTANA_DERECHOSEVA_ID), new String(VENTANA_ADMONUSUARIOS_ID)));
        public static List<String> ROLE_MONITOREO_VENTANAS_IDS = new ArrayList<String>(Arrays
                        .asList(new String(VENTANA_CONSULTAINTRADIA_ID), new String(VENTANA_CONSULTAINTRADIAETF_ID)));

        /**
         * formatos
         */
        public static final String SFORMATOINDICE = "###,###,###,###,###,##0.000000";
        public static final String SFORMATOVARIACION = "###,###,###,###,###,##0.00";
        public static final String SFORMATOSUMATORIA = "###,###,###,###,###,##0.000000";
        public static final String SFORMATOENTEROS = "###,###,###,###,###,##0";

        /**
         * Tipos de Cambio Dolar y Euro
         */
        public static final String SID_SERIE_DOLAR = "SF43718";
        public static final String SID_SERIE_EURO = "SF46410";
        public static final String SCODGIO_ISO_PESOMX = "MXN";
        public static final String SCODIGO_ISO_DOLAR = "USD";
        public static final String SCODIGO_ISO_EURO = "EUR";
        public static final String SXML_ARCHIVO_TEMPORAL = "Archivo_temporal.xml";

        /**
         * Reporte Post-Ajustes
         */
        public static final String SID_REPORTE4 = "4";
        public static final String SID_REPORTE5 = "5";

        /**
         * Consulta Intrad�a
         */
        public static final Integer IREFRESHINDICES = 10000;
        public static final Integer IREFRESHEMISIONES = 10000;

        /**
         * General
         */
        public static final String USUARIO_MCI = "Admin_MCI";
        public static final String USUARIO_REACT_GUI = "REACTIVACION_GUI";
        public static final String SISTEMA_MCI = "WebApp_MCI";

        /**
         * Ids de tipos de ajustes
         */
        public static final String SID_AJUSTE_DIVIDENDO = "1";
        public static final String SID_AJUSTE_ACCIONARIO = "2";
        public static final String SID_AJUSTE_SPLIT = "3";
        public static final String SID_AJUSTE_PRECIO = "4";
        public static final String SID_AJUSTE_ESCISION = "5";
        public static final String SID_AJUSTE_FUSION = "6";

        /**
         * Descripci�n de los tipos de ajuste
         */
        public static final String TIPO_AJUSTE_DERECHO = "Ajuste por un Derecho";
        public static final String TIPO_AJUSTE_RECOMPRA = "Ajuste por una Recompra";
        public static final String TIPO_AJUSTE_MANTENIMIENTO = "Ajuste por Mantenimiento";

        /**
         * PROCESO POR AJUSTE DERECHO
         *
         * Constante que indica los procesos a realizar a un registro de EVA Se utiliza
         * la llave compuesta de ajuste y derecho Los valores para los procesos son: 1.-
         * Dividendo 2.- Accionario 3.- Split 4.- Precio 5.- Fusion Dividendo Accionario
         * Split Precio
         */
        public static final Map<AjusteDerechoKey, List<Long>> PROCESO_AJUSTE_PRECIO = new HashMap<AjusteDerechoKey, List<Long>>() {
                {
                        // TipoAjusteEva.AJUSTE_POR_RECOMPRA
                        put(new AjusteDerechoKey(new Long(1), new Long(0)), Arrays.asList(new Long(2)));
                        // TipoAjusteEva.AJUSTE_POR_MANTENIMIENTO
                        put(new AjusteDerechoKey(new Long(2), new Long(0)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO, TipoDerecho.DIVIDENDO_ACCIONES_MISMA_SERIE
                        put(new AjusteDerechoKey(new Long(0), new Long(1)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SUSCRIPCION_ACCIONES_MISMA_SERIE
                        put(new AjusteDerechoKey(new Long(0), new Long(2)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE_ABSORCION_PERDIDAS
                        put(new AjusteDerechoKey(new Long(0), new Long(3)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DIVIDENDO_EFECTIVO
                        put(new AjusteDerechoKey(new Long(0), new Long(4)), Arrays.asList(new Long(1), new Long(2)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DIVIDENDO_ACCIONES_SERIE_PREEXISTENTE
                        put(new AjusteDerechoKey(new Long(0), new Long(5)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DIVIDENDO_ACCIONES_SERIE_NUEVA
                        put(new AjusteDerechoKey(new Long(0), new Long(6)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SUSCRIPCION_ACCIONES_SERIE_PREEXISTENTE
                        put(new AjusteDerechoKey(new Long(0), new Long(7)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SUSCRIPCION_ACCIONES_SERIE_NUEVA
                        put(new AjusteDerechoKey(new Long(0), new Long(8)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE_REEMBOLSO
                        put(new AjusteDerechoKey(new Long(0), new Long(9)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE_SUBSISTIENDO_SERIE_INSCRITA
                        put(new AjusteDerechoKey(new Long(0), new Long(10)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE_CREANDO_SERIE
                        put(new AjusteDerechoKey(new Long(0), new Long(11)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.REEMBOLSO
                        put(new AjusteDerechoKey(new Long(0), new Long(12)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SPLIT
                        put(new AjusteDerechoKey(new Long(0), new Long(13)), Arrays.asList(new Long(3)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SPLIT_INVERSO
                        put(new AjusteDerechoKey(new Long(0), new Long(14)), Arrays.asList(new Long(3)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.ESCISION
                        put(new AjusteDerechoKey(new Long(0), new Long(15)), new ArrayList<Long>());
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.FUSION
                        put(new AjusteDerechoKey(new Long(0), new Long(16)), Arrays.asList(new Long(5)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE
                        put(new AjusteDerechoKey(new Long(0), new Long(17)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DIVIDENDO_ESPECIE
                        put(new AjusteDerechoKey(new Long(0), new Long(18)), new ArrayList<Long>());
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DISTRIBUCION_ACCIONES
                        put(new AjusteDerechoKey(new Long(0), new Long(19)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.DISTRIBUCION_EFECTIVO
                        put(new AjusteDerechoKey(new Long(0), new Long(20)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.NO_CLASIFICADO
                        put(new AjusteDerechoKey(new Long(0), new Long(21)), new ArrayList<Long>());
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.OTROS
                        put(new AjusteDerechoKey(new Long(0), new Long(22)), new ArrayList<Long>());
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.CANJE_CAMBIO_INTEGRACION_CAPITAL_Y_O_SERIES
                        put(new AjusteDerechoKey(new Long(0), new Long(23)), Arrays.asList(new Long(2), new Long(4)));

                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SUSCRIPCION_CBFS
                        put(new AjusteDerechoKey(new Long(0), new Long(24)), Arrays.asList(new Long(2), new Long(4)));
                        // TipoAjusteEva.AJUSTE_POR_DERECHO,TipoDerecho.SUSCRIPCION_LLAMADA_DE_CAPITAL_CCDS
                        put(new AjusteDerechoKey(new Long(0), new Long(25)), Arrays.asList(new Long(2), new Long(4)));
                }
        };

        public static String rendimientoRS = "RS";
        public static String rendimientoRT = "RT";

        // Se agrega para el envio de RV y ZURV cuando se manda el IPC
        public static String ipcCveIndice = "ME";
        public static String formatoRV = "RV";
        public static String formatoZURV = "ZURV";
        public static String formatoIV = "IV";
        public static String formatoIR = "IR";

        public static String idipcCveIndice = "ID";
        public static String irtCveIndice = "RT";

        // Parametros de inicio para mostrar diferentes perfiles
        /*
         * public static String parametroMonitoreo = "monitoreo"; public static String
         * parametroCompleto = "FullFunction";
         */
        public static String parametroMonitoreo = "ROLE_MONITOREO";
        public static String parametroCompleto = "ROLE_ADMIN";

        // constantes de n�mero m�ximo para valores de nuevo indice
        public static Double VALOR_MAXIMO_INDICE = 999999999999.0;
        public static Double VALOR_MINIMO_INDICE = 0.0;
        public static Double VALOR_MAXIMO_SUMATORIA = 9999999999999998.999999;
        public static Double VALOR_MINIMO_SUMATORIA = 0.0;

        public static String FORMATO_DEFAULT_DIFUSION = "UI";

        public static String GRUPO_TODOS_VALOR = "0";
        public static String GRUPO_TODOS_DESCRIPCION = "TODOS";
        public static List<String> GRUPOS_MUESTRA_NULA = new ArrayList<String>(
                        Arrays.asList(new String("TC"), new String("ES")));
        public static String VIMEX_GRUPO_NOMBRE = "DE ESTRATEGIA";

        public static List<Integer> LISTATIPOSINDICESEXCUIDOS = new ArrayList<Integer>(
                        Arrays.asList(new Integer(1), new Integer(8), new Integer(9), new Integer(100)));

        // para seguridad
        public static Long SECCION_INTRA_ACCESO = new Long(1);
        public static Long SECCION_INTRA_ARCHIVO = new Long(2);
        public static Long SECCION_INTRA_ARCHIVORESUMEN = new Long(94);
        public static Long SECCION_INTRA_MUES_ACCESO = new Long(3);
        public static Long SECCION_INTRA_MUES_ARCHIVO = new Long(4);
        public static Long SECCION_INTRA_MUES_CAMBIOS = new Long(5);
        public static Long SECCION_INTRA_MUES_DESBLOQ = new Long(65);
        public static Long SECCION_RETOPE_ACCESO = new Long(6);
        public static Long SECCION_RETOPE_ARCHIVO = new Long(7);
        public static Long SECCION_RETOPE_APLICAR = new Long(8);
        public static Long SECCION_CAMBMUES_ACCESO = new Long(9);
        public static Long SECCION_CAMBMUES_ARCHIVO = new Long(10);
        public static Long SECCION_CAMBMUES_APLICAR = new Long(11);
        public static Long SECCION_INTER_ACCESO = new Long(12);
        public static Long SECCION_INTER_APLICAR = new Long(13);
        public static Long SECCION_INTER_ARCHIVO = new Long(14);
        public static Long SECCION_INTER_AGREGA_EMI = new Long(15);
        public static Long SECCION_INTER_BORRA_EMI = new Long(16);
        public static Long SECCION_INTER_DIVIDENDO = new Long(17);
        public static Long SECCION_INTER_BLOQ_REUTERS = new Long(18);
        public static Long SECCION_INTER_DETEN_REUTERS = new Long(19);
        public static Long SECCION_INTER_SUBIR_REUTERS = new Long(20);
        public static Long SECCION_HISTCAMB_ACCESO = new Long(21);
        public static Long SECCION_HISTCAMB_ARCHIVO = new Long(22);
        public static Long SECCION_REPROCE_ACCESO = new Long(23);
        public static Long SECCION_REPROCE_PRECIORT = new Long(24);
        public static Long SECCION_REPROCE_PRECIOSIM = new Long(25);
        public static Long SECCION_REPROCE_ACCIONES = new Long(26);
        public static Long SECCION_REPROCE_CSVACC = new Long(27);
        public static Long SECCION_REPROCE_CSVNIV = new Long(28);
        public static Long SECCION_REPROCE_NUEVOIND = new Long(29);
        public static Long SECCION_DERECHO_ACCESO = new Long(30);
        public static Long SECCION_DERECHO_APLICAR = new Long(31);
        public static Long SECCION_DERECHO_DESCARTAR = new Long(32);
        public static Long SECCION_DERECHO_DETALLE = new Long(33);
        public static Long SECCION_REPORTES_ACCESO = new Long(34);
        public static Long SECCION_REPORTES_DETALLE = new Long(35);
        public static Long SECCION_REPORTES_ENVIAR = new Long(36);
        public static Long SECCION_REPORTES_CONSOLIDA = new Long(37);
        public static Long SECCION_CIERRE_ACCESO = new Long(38);
        public static Long SECCION_CIERRE_CSVACUM = new Long(39);
        public static Long SECCION_CIERRE_CSVPRECIOS = new Long(40);
        public static Long SECCION_CIERRE_ACTTIPOCAMB = new Long(41);
        public static Long SECCION_CIERRE_ACTNIVEL = new Long(42);
        public static Long SECCION_CIERRE_GUARDADEF = new Long(43);
        public static Long SECCION_CIERRE_ENVIAREP = new Long(44);
        public static Long SECCION_CIERRE_RESET = new Long(45);
        public static Long SECCION_CIERRE_ARCHIVO = new Long(46);
        public static Long SECCION_CIERRE_DETALLE = new Long(47);
        public static Long SECCION_CIERRE_MUES_ACCESO = new Long(48);
        public static Long SECCION_CIERRE_MUES_ACTPRECIO = new Long(49);
        public static Long SECCION_CIERRE_MUES_ARCHIVO = new Long(50);
        public static Long SECCION_ETF_ACCESO = new Long(51);
        public static Long SECCION_ETF_SUBIRARCH = new Long(52);
        public static Long SECCION_ETF_RECARGAEMISNET = new Long(53);
        public static Long SECCION_ETF_ARCHIVO = new Long(54);
        public static Long SECCION_ETF_MUES_ACCESO = new Long(55);
        public static Long SECCION_EVA_ACCESO = new Long(56);
        public static Long SECCION_EVA_DESCARTA = new Long(57);
        public static Long SECCION_EVA_REACTIVA = new Long(58);
        public static Long SECCION_EVA_ARCHIVO = new Long(59);
        public static Long SECCION_USUARIOS_ACCESO = new Long(60);
        public static Long SECCION_USUARIOS_DETALLE = new Long(61);
        public static Long SECCION_USUARIOS_ALTA = new Long(62);
        public static Long SECCION_USUARIOS_BAJA = new Long(63);
        public static Long SECCION_USUARIOS_MOD = new Long(64);
        public static Long SECCION_REPORTESADMON_ACCESO = new Long(66);
        public static Long SECCION_REPORTESADMON_DESTIN_ACCESO = new Long(67);
        public static Long SECCION_REPORTESADMON_DESTIN_ALTA = new Long(68);
        public static Long SECCION_REPORTESADMON_DESTIN_BAJA = new Long(69);
        public static Long SECCION_REPORTESADMON_DESTIN_MOD = new Long(70);
        public static Long SECCION_REPORTESADMON_DESTIN_ARCHIVO = new Long(95);
        public static Long SECCION_REPORTESADMON_DESTIN_DESBLOQ = new Long(71);
        public static Long SECCION_REPORTESADMON_ERI_ALTA = new Long(72);
        public static Long SECCION_REPORTESADMON_ERI_BAJA = new Long(73);
        public static Long SECCION_REPORTESADMON_ERI_MOD = new Long(74);
        public static Long SECCION_REPORTESADMON_ERI_ARCHIVO = new Long(96);
        public static Long SECCION_REPORTESADMON_ERI_DESBLOQ = new Long(75);
        public static Long SECCION_ALERTASADMON_ACCESO = new Long(76);
        public static Long SECCION_ALERTASADMON_DESTGRU_ACCESO = new Long(77);
        public static Long SECCION_ALERTASADMON_DESTGRU_ALTADEST = new Long(78);
        public static Long SECCION_ALERTASADMON_DESTGRU_BAJADEST = new Long(79);
        public static Long SECCION_ALERTASADMON_DESTGRU_MODDEST = new Long(80);
        public static Long SECCION_ALERTASADMON_DESTGRU_DESBLOQDEST = new Long(81);
        public static Long SECCION_ALERTASADMON_DESTGRU_ALTAGRU = new Long(82);
        public static Long SECCION_ALERTASADMON_DESTGRU_BAJAGRU = new Long(83);
        public static Long SECCION_ALERTASADMON_DESTGRU_MODGRU = new Long(84);
        public static Long SECCION_ALERTASADMON_DESTGRU_DESBLOQGRU = new Long(85);
        public static Long SECCION_ALERTASADMON_ALEGRU_ACCESO = new Long(86);
        public static Long SECCION_ALERTASADMON_ALEGRU_ALTA = new Long(87);
        public static Long SECCION_ALERTASADMON_ALEGRU_BAJA = new Long(88);
        public static Long SECCION_ALERTASADMON_ALEGRU_MOD = new Long(89);
        public static Long SECCION_ALERTASADMON_ALEGRU_DESBLOQ = new Long(90);
        public static Long SECCION_INDXEMI_ACCESO = new Long(91);
        public static Long SECCION_INDXEMI_CONSULTAINDXEMI_ACCESO = new Long(92);
        public static Long SECCION_INDXEMI_CONSULTAINDXEMI_ARCHIVO = new Long(93);

        public static Long SECCION_INDICESADMON_ACCESO = new Long(97);
        public static Long SECCION_INDICESADMON_NUEVOIND_ACCESO = new Long(98);
        public static Long SECCION_INDICESADMON_EDITARIND_ACCESO = new Long(99);
        public static Long SECCION_INDICESADMON_REPFORM_ACCESO = new Long(100);
        public static Long SECCION_INDICESADMON_REPFORM_DESBLOQ = new Long(101);
        public static Long SECCION_INDICESADMON_DETENERINDTC_ACCESO = new Long(125);
        public static Long SECCION_INDICESADMON_DETENERINDTC_ACTUALIZAR = new Long(126);

        public static Long SECCION_OPERADOR_ACCESO = new Long(102);
        public static Long SECCION_OPERADOR_VERIFREP_ACCESO = new Long(103);
        public static Long SECCION_OPERADOR_VERIFREP_ARCHIVO = new Long(104);
        public static Long SECCION_OPERADOR_ACUMULADOS_ACCESO = new Long(105);
        public static Long SECCION_OPERADOR_ACUMULADOS_ARCHIVO = new Long(106);
        public static Long SECCION_OPERADOR_CUADRECONSIST_ACCESO = new Long(107);
        public static Long SECCION_OPERADOR_CUADRECONSIST_ARCHIVO = new Long(108);
        public static Long SECCION_OPERADOR_VALIDSUM_ACCESO = new Long(109);
        public static Long SECCION_OPERADOR_VALIDSUM_ARCHIVO = new Long(110);
        public static Long SECCION_OPERADOR_VALIDVMIND_ACCESO = new Long(111);
        public static Long SECCION_OPERADOR_VALIDVMIND_ARCHIVO = new Long(112);
        public static Long SECCION_OPERADOR_VALIDINFL_ACCESO = new Long(113);
        public static Long SECCION_OPERADOR_VALIDINFL_ARCHIVO = new Long(114);
        public static Long SECCION_OPERADOR_CANCELACIONES_ACCESO = new Long(115);
        public static Long SECCION_OPERADOR_CANCELACIONES_ARCHIVO = new Long(116);
        public static Long SECCION_OPERADOR_CONTEOFORM_ACCESO = new Long(117);
        public static Long SECCION_OPERADOR_CONTEOFORM_ARCHIVO = new Long(118);
        public static Long SECCION_OPERADOR_HISTPRECINT_ACCESO = new Long(119);
        public static Long SECCION_OPERADOR_HISTPRECINT_ARCHIVO = new Long(120);
        public static Long SECCION_OPERADOR_FORMATIND_ACCESO = new Long(121);
        public static Long SECCION_OPERADOR_FORMATIND_ARCHIVO = new Long(122);
        public static Long SECCION_OPERADOR_PRECISPRECACC_ACCESO = new Long(123);
        public static Long SECCION_OPERADOR_PRECISPRECACC_ARCHIVO = new Long(124);

        public static Long SECCION_ETF_TASADIV = new Long(127);
        public static Long SECCION_ETF_CARGA = new Long(128);
        public static Long SECCION_ETF_REPROCESO = new Long(129);
        public static Long SECCION_ETF_ALTA = new Long(130);
        public static Long SECCION_ETF_BAJA = new Long(131);
        public static Long SECCION_ETF_DIFUSION = new Long(132);
        public static Long SECCION_ETF_REACTIVACION = new Long(133);

        public static final List<ComboBoxValues> SPERMISOSCODES = new ArrayList<ComboBoxValues>(Arrays
                        .asList(new ComboBoxValues("true", "Permitido"), new ComboBoxValues("false", "Denegado")));

        public static final List<ComboBoxValues> TIPOSDESTINATARIOS = new ArrayList<ComboBoxValues>(
                        Arrays.asList(new ComboBoxValues("email", "Email"), new ComboBoxValues("ftp", "FTP"),
                                        new ComboBoxValues("sftp", "SFTP")));

        public static final List<ComboBoxValues> TIPOSDESTINATARIOS_ALERTAS = new ArrayList<ComboBoxValues>(
                        Arrays.asList(new ComboBoxValues("email", "Email"), new ComboBoxValues("sms", "SMS")
                        // new ComboBoxValues("sftp","SFTP")
                        ));

        public static List<String> TIPOSDESTINATARIOS_CONSULTA = new ArrayList<String>(
                        Arrays.asList(new String("email"), new String("ftp"), new String("sftp")));

        public static List<String> TIPOSDESTINATARIOS_CONSULTA_ALERTAS = new ArrayList<String>(
                        Arrays.asList(new String("email"), new String("sms")));

        public static List<Long> LISTAPLANTILLASEXCUIDAS = new ArrayList<Long>(Arrays.asList(
                        // new Long(6),
                        new Long(-1)));

        public static final List<ComboBoxValues> LISTANIVELESALERTAS = new ArrayList<ComboBoxValues>(Arrays.asList(
                        new ComboBoxValues("1", "1"), new ComboBoxValues("2", "2"), new ComboBoxValues("3", "3")));

        public static final String TIPOSDESTINATARIOS_SMS = "sms";
        public static final String TIPOSDESTINATARIOS_MAIL = "email";

        public static Boolean BANDERA_FORMATO_DEFAULT = true;
        public static String APLICACION_PARA_FORMATOS = "MCI";

        public static Long ESTATUS_INACTIVO_TEMP = 4L;
        public static Long ESTATUS_ACTIVO = 1L;
        public static Long ESTATUS_PENDIENTE = 3L;
        public static String REMATE_APERTURA = "AN";
        public static String ETIQUETA_ESTATUS_ACTIVO = "Activo";
        public static String ETIQUETA_ESTATUS_PENDIENTE = "Pendiente";
        public static String CADENA_VACIA = "";
        public static String CALENDARIO_MANTENIMIENTO_BAJA = "B";
        public static String CALENDARIO_MANTENIMIENTO_REACTIVACION = "R";
}
