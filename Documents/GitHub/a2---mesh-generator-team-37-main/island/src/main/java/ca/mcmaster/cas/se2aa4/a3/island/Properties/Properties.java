package ca.mcmaster.cas.se2aa4.a3.island.Properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class Properties {
    public static String volcanoTier1Colors = "255,0,0";
    public static String volcanoTier2Colors = "32,32,32";
    public static String volcanoTier3Colors = "64,64,64";
    public static String volcanoTier4Colors = "96,96,96";
    public static String lagoonColors = "0,0,100";
    public static String lakeColors = "66,135,245";
    public static String landColors = "122,171,135";
    public static String oceanColors = "0,0,55";
    public static String aquaColors = "122,171,134";
    public static String beachColors = "194,178,128";
    public static String forestColors = "39,118,0";
    public static String tundraColors = "238,169,169";
    public static String rockMountTier1Colors = "84,84,84";
    public static String rockMountTier2Colors = "111,111,111";
    public static String rockMountTier3Colors = "135,135,135";
    public static String rockMountTier4Colors = "161,161,161";
    public static String landLowColors = "88,235,52";
    public static String landMediumColors = "54,214,15";
    public static String landHighColors = "40,184,6";
    public static String testColors = "192,0,125";
    public static String plainsColors = "210,197,6";
    public static String lakeHumidity = "90";
    public static String normalHumidity = "60";
    public static String hottestTemp = "25";
    public static String normalTemp = "15";
    public static String coldTemp = "5";

    public static Structs.Property getLandHighProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(landHighColors)
                .build();
    }

    public static Structs.Property getLandMediumProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(landMediumColors)
                .build();
    }

    public static Structs.Property getLandLowProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(landLowColors)
                .build();
    }

    public static Structs.Property getRockMountTier1Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(rockMountTier1Colors)
                .build();
    }

    public static Structs.Property getRockMountTier2Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(rockMountTier2Colors)
                .build();
    }

    public static Structs.Property getRockMountTier3Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(rockMountTier3Colors)
                .build();
    }

    public static Structs.Property getRockMountTier4Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(rockMountTier4Colors)
                .build();
    }

    public static Structs.Property gettestProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(testColors)
                .build();
    }

    public static Structs.Property getVolcanoTier1Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(volcanoTier1Colors)
                .build();
    }

    public static Structs.Property getVolcanoTier2Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(volcanoTier2Colors)
                .build();
    }

    public static Structs.Property getVolcanoTier3Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(volcanoTier3Colors)
                .build();
    }

    public static Structs.Property getVolcanoTier4Props() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(volcanoTier4Colors)
                .build();
    }

    public static Structs.Property getLagoonProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(lagoonColors)
                .build();
    }

    public static Structs.Property getLakeProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(lakeColors)
                .build();
    }

    public static Structs.Property getLakeHumidityProps() {
        return Structs.Property.newBuilder()
                .setKey("humidity")
                .setValue("90")
                .build();
    }

    public static Structs.Property get_normHumidityProps() {
        return Structs.Property.newBuilder()
                .setKey("humidity")
                .setValue("60")
                .build();
    }

    public static Structs.Property getLandProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(landColors)
                .build();
    }

    public static Structs.Property getOceanProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(oceanColors)
                .build();
    }

    public static Structs.Property getAquaProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(aquaColors)
                .build();
    }

    public static Structs.Property getMoistProps() {
        return Structs.Property.newBuilder()
                .setKey("moisture")
                .setValue("1")
                .build();
    }

    public static Structs.Property getBeachProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(beachColors)
                .build();
    }

    public static Structs.Property getForestProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(forestColors)
                .build();
    }

    public static Structs.Property get_temp_temperature() {
        return Structs.Property.newBuilder()
                .setKey("temperature")
                .setValue("15")
                .build();
    }

    public static Structs.Property get_hot_temperature() {
        return Structs.Property.newBuilder()
                .setKey("temperature")
                .setValue("25")
                .build();
    }

    public static Structs.Property get_cold_temperature() {
        return Structs.Property.newBuilder()
                .setKey("temperature")
                .setValue("5")
                .build();
    }
    public static Structs.Property get_other_temperature() {
        return Structs.Property.newBuilder()
                .setKey("temperature")
                .setValue("2")
                .build();
    }

    public static Structs.Property getTundraProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(tundraColors)
                .build();
    }

    public static Structs.Property get_PlainsProps() {
        return Structs.Property.newBuilder()
                .setKey("rgb_color")
                .setValue(plainsColors)
                .build();
    }


}
