package com.example.tutorialsproject.util;

import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    private static final String TAG = StringUtil.class.getSimpleName();

    /**
     * Partially capitalizes the string from paramter start and offset.
     */
    @Nullable
    public static String capitalizeString(String string, int start, int offset) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String formattedString = string.substring(start, offset).toUpperCase() + string.substring(offset, string.length());
        return formattedString;
    }

    /**
     * Capitalizes each word in the string.
     */
    @Nullable
    public static String capitalizeString(String string) {

        if (string == null) {
            return null;
        }

        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can add other
                // chars here
                found = false;
            }
        } // end for

        return String.valueOf(chars);
    }

    public static String getQuantityString(int quantity){
        return ("Qty: " + String.valueOf(quantity));
    }

    public static String convertIntToString(int value){
        return ("(" + String.valueOf(value) + ")");
    }

    public static void removeFromArrayListByName(ArrayList<String> arrayList, String name) {

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equalsIgnoreCase(name)) {
                arrayList.remove(i);
            }
        }

    }

    public static ArrayList<String> getValuesFromHashMap(HashMap<String, String> hashMap) {
        ArrayList<String> values = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            values.add(hashMap.get(key));
        }
        return values;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object  o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    /**
     * Write String data to a csv file
     */
    public static void generateCsvFile(String sFileName, String data)
            throws IOException, JSONException {
        JSONObject objectToWrite = new JSONObject(data);
        File folder = new File(Environment.getExternalStorageDirectory() + "/Folder");

        boolean var = false;
        if (!folder.exists()) {
            var = folder.mkdir();
        }

        System.out.println("" + var);

        final String filename = folder.toString() + "/" + sFileName;

        FileWriter writer = new FileWriter(filename, true);

        try {

            writer.append(objectToWrite.get("x").toString());
            writer.append(',');
            writer.append(objectToWrite.get("y").toString());
            writer.append(',');
            writer.append(objectToWrite.get("z").toString());
            writer.append('\n');
            writer.flush();
            writer.close();
        } catch (Exception e) {
            writer.flush();
            writer.close();
            e.printStackTrace();
        }
    }

}
